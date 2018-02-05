[es-sql](http://172.19.103.68:9200/_plugin/sql/) 例子
[elasticsearch-sql文档](https://github.com/NLPchina/elasticsearch-sql/wiki/Functions-support)
**重命名配置支持**

{elasticsearch_home}/config/elasticsearch.yml

```
script.inline: on
script.indexed: on
script.engine.groovy.inline.aggs: on
```

**目前2.4.2 es-sql不支持update delete**
6.x 系列的es支持delete




**查询表**

```SELECT * FROM wlyy_quota_test```


**条件查询表**

```SELECT * FROM wlyy_quota_test where townName='湖里区'```


**模糊查询**

SELECT * FROM wlyy_quota_test where townName like '%湖里%'


**查询返回单个字段**

```SELECT city FROM wlyy_quota_test```


**查询返回单个字段并且重命名**

```SELECT city as slavename FROM wlyy_quota_test```


**查询根据某个字段聚合**

```SELECT city  FROM wlyy_quota_test group by city```


**查询根据某个字段聚合并且重命名**

```SELECT city as slavename  FROM wlyy_quota_test group by slavename```


**条件查询并且排序表**

```SELECT * FROM wlyy_quota_test where townName='湖里区' order by createTime desc ```


**分页查询(分页查询不能与group by 同时使用)**

```SELECT city as slavename  FROM wlyy_quota_test limit 0,10```


**分页查询带条件(分页查询不能与group by 同时使用)**

```SELECT townName as slavename  FROM wlyy_quota_test where townName='湖里区' limit 0,10```


**count使用**

```SELECT count(*) num FROM wlyy_quota_test where townName='湖里区'```


**count distinct使用**

```select count( distinct town) from wlyy_quota_test```


**max使用**

```SELECT max(result1) FROM wlyy_quota_test where townName='湖里区'```


**min使用**

```SELECT min(result1) FROM wlyy_quota_test where townName='湖里区'```


**sum使用**

```select sum(result1) from wlyy_quota_test```


**按照年龄分组使用**

```SELECT count(age) FROM account GROUP BY range(age, 20,25,30,35,40)```


**时间聚合使用**

```select town,townName,sum(result1) result1 from wlyy_quota_test group by town,townName,date_histogram(field='quotaDate','interval'='month')```

interval例子
```
1天    date_histogram(field='quotaDate','interval'='1d')
每周   date_histogram(field='quotaDate','interval'='week')
每月   date_histogram(field='quotaDate','interval'='month')
每季度 date_histogram(field='quotaDate','interval'='quarter')
每年   date_histogram(field='quotaDate','interval'='year')
```

**子字段(nested)查询**

```SELECT townName.name FROM wlyy_quota_test where townName.name like '%湖里%'```


**join(只支持2个表，简单的查询 不支持分组)**

```select  table1.town town1 ,table2.townName townName2 from wlyy_quota_test table1 join wlyy_quota_test table2 on table1.town=table2.town```

