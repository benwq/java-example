#!/usr/bin/env python
# -*- coding:UTF-8 -*-
# @Time    : 17/10/24 18:47
# @Author  : cc
# @Site    : 
# @File    : get_mysql_data.py
# @Software: PyCharm Community Edition
import pymysql

def get_mysql_data(host_value, port_value, user_value, passwd_value, db_value,exec_sql):
    conn = pymysql.connect(host=host_value, port=port_value, user=user_value, passwd=passwd_value, db=db_value,
                           charset='utf8')
    # 使用cursor()方法获取操作游标
    cursor = conn.cursor()
    # 使用execute方法执行SQL语句
    cursor.execute(exec_sql)
    results = cursor.fetchall()
    # 使用 fetchone() 方法获取一条数据库。

    list_hand_in = []
    list_hand_out = []
    list_sys_in = []
    list_sys_out =[]

    for i in range(len(results)):
        temp_list = []
        temp_list = list(results[i])
        list_hand_in.append(temp_list[0])
        list_hand_out.append(temp_list[1])
        list_sys_in.append(temp_list[2])
        list_sys_out.append(temp_list[3])

    # 关闭数据库连接
    conn.close()
    return list_hand_in, list_hand_out, list_sys_in,list_sys_out