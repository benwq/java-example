#!/usr/bin/env python
# -*- coding:UTF-8 -*-
# @Time    : 17/10/24 19:02
# @Author  : cc
# @Site    : 
# @File    : travel_model.py
# @Software: PyCharm Community Edition

import get_mysql_data
import numpy as np
import time
from sklearn import svm
from sklearn.externals import joblib

exe_sql="select hand_in,hand_out,sys_in,sys_out " \
        "from hand_con_sys " \
        "where hand_equcode in ('EQU_1450843964632') " \
        "and hand_in is not null " \
        "and sys_in is not null " \
        "and (hand_in >  CAST(sys_in AS SIGNED));"
# print exe_sql
list_hand_in, list_hand_out, list_sys_in,list_sys_out = get_mysql_data.get_mysql_data( "10.10.21.122", 3306, "root", "supconit","rll",exe_sql)

# print  len(list_hand_in)
# print list_hand_in
# print list_hand_out
# print list_sys_in
# print list_sys_out

list_sys_in_int=[]
for i in range(len(list_sys_in)):
    list_sys_in_int.append(int(list_sys_in[i]))
# print list_sys_in_int
####将系统的数据转换成训练数据格式，array_sys_in矩阵####
temp_sys_in=[]
for i in range(len(list_sys_in)):
    temp=[]
    temp.append(int(list_sys_in[i]))
    temp_sys_in.append(temp)
array_sys_in=np.array(temp_sys_in)
# print array_sys_in.shape
# ##########进行模型训练，训练时间svr_fit#########

train_size = 300

t0=time.time()
clf=svm.SVR(kernel='linear',gamma='auto')
clf.fit(array_sys_in[:train_size], list_hand_in[:train_size])
svr_fit = time.time() - t0
# print  clf.get_params()

##########模型保存###############
joblib.dump(clf,'rf.model')


def get_svr_fit():
    return svr_fit