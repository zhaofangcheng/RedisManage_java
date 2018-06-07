package com.smart.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTools {
		
	   private static JedisCluster cluster;
	 
	   static{
	    	String host=getProperty("host");
	    	String[] hostSingle=host.split(";");
	    	JedisPoolConfig config = new JedisPoolConfig(); 
	    	config.setMaxTotal(100); 
	    	config.setMaxIdle(50); 
	    	config.setMinIdle(20); 
	    	config.setMaxWaitMillis(6 * 1000); 
	    	config.setTestOnBorrow(true); 
	        Set<HostAndPort> nodes =new HashSet<HostAndPort>();  
	        for(int i=0;i<hostSingle.length;i++){
	        	String[] host_temp=hostSingle[i].split(":");
	        	nodes.add(new HostAndPort(host_temp[0],Integer.parseInt(host_temp[1])));
	        }  
	        cluster = new JedisCluster(nodes, 2000, 100,config);
	    }
	    
	    public String get(String key){
	    	return cluster.get(key);
	    }
	    
	    public long del(String key){
	    	return cluster.del(key);
	    }
	    public List<Map<String,Object>>  like(String likeKey){
	    	List<Map<String,Object>> returnList=new ArrayList();
	        Iterator car=keys(likeKey).iterator();
	        while(car.hasNext()){
	        	Map returnMap=new HashMap();
	        	String key=car.next()+"";
	        	returnMap.put("key",key);
	        	returnMap.put("value",get(key));
	        	returnList.add(returnMap);
	        }
	        return returnList;
	    }
	    public TreeSet<String> keys(String pattern){  
	       // System.out.println("Start getting keys...");  
	        TreeSet<String> keys = new TreeSet<String>();  
	        Map<String, JedisPool> clusterNodes = cluster.getClusterNodes();  
	        for(String k : clusterNodes.keySet()){  
	         //   System.out.println("Getting keys from: {}"+k);  
	            JedisPool jp = clusterNodes.get(k);  
	           // System.out.println(jp.isClosed());;
	            Jedis connection = null;
	            try {
	            	//集群中有可能存在node不存在或者获取不到连接的情况
	            	connection= jp.getResource();  
	                keys.addAll(connection.keys("*"+pattern+"*"));  
	            } catch(Exception e){  
	            	System.out.println("Getting keys error: {}"+ k);
	              //System.out.println("Getting keys error: {}"+ e);  
	            } finally{  
	             //   System.out.println("Connection closed.");  
	                if(connection!=null){
	                	connection.close();//用完一定要close这个链接！！！  
	                }
	            }  
	         //   System.out.println(keys);
	        }  
	      //  System.out.println("Keys gotten!");  
	        return keys;  
	    }  
		public JedisCluster getCluster() {
			return cluster;
		}

		public void setCluster(JedisCluster cluster) {
			this.cluster = cluster;
		}

		  /***
		   * 获取配置文件key value内容
		   * @param name
		   * @return
		   */
	  public static String getProperty(String name){
		    InputStream is =RedisTools.class.getClassLoader().getResourceAsStream("project.properties");
			Properties p = new Properties();
			try {
				p.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return p.getProperty(name);
	  }
}
