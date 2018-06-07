# RedisManage_java
java版本redis集群管理工具,纯servlet
'''
>>@Version:0.1Beta\<br>  
>>@auther:zhaofangcheng\<br>    
>>@e-mail:zhaofangcheng@126.com\<br>  
>>依赖jar：\<br>  
>>>>>jedis-2.7.3.jar\<br>  
>>>>>commons-pool2-2.2.jar \<br>  

redis助手java版本，提供简单的查看和删除功能，用来解决某些环境下redis集群只能连接上vpn才能查看的情况，可以将本程序部署在隔离区，通过隔离区连接到redis集群，用户访问隔离区就可以了\<br>  
使用方法:
         模糊查询：http://xxxx:8000/like?eshop\<br>  
         精准查询：http://xxxx:8000/query?eshop_floor, key必须存在，否则无法查到\<br>  
         精准删除：http://xxxx:8000/del?eshop_floor, key必须存在，否则无法删除\<br>  
'''\<br>  

![Alt text](https://github.com/zhaofangcheng/RedisManage/blob/master/redisManage.png)
