#!/usr/bin/env python
# -*- coding:UTF-8 -*-
# @Time    : 17/10/24 19:40
# @Author  : cc
# @Site    : 
# @File    : proving_data.py
# @Software: PyCharm Community Edition
import sys
import types
from decimal import Decimal
import travel_model
import get_mysql_data
import numpy as np
import time
from sklearn import svm
from sklearn.externals import joblib
import matplotlib.pyplot as plt

def Calculation(hand_data,sys_data):
    temp_deviation= Decimal((sys_data - hand_data)*1.0/ float('%.1f' % hand_data)*100).quantize(Decimal('0.0'))
    result=100-abs(temp_deviation)
    return int(result)

if __name__ == '__main__':
    # --主函数传参数：argv[1]数据库IP、argv[2]数据库端口号、argv[3]用户名、argv[4]密码、argv[5]数据库名、argv[6]表名、argv[7]时间列表---
    # for ii in range(len(sys.argv)):
    #     print  sys.argv[ii]

    # exe_sql="select hand_in,hand_out,sys_in,sys_out " \
    #         "from hand_con_sys " \
    #         "where hand_equcode in ('EQU_1450843964632') " \
    #         "and hand_in is not null " \
    #         "and sys_in is not null " \
    #         "and (hand_in >  CAST(sys_in AS SIGNED));"
    # print exe_sql
    # list_hand_in, list_hand_out, list_sys_in,list_sys_out = get_mysql_data.get_mysql_data( "127.0.0.1", 3306, "root", "supconit","xuhu",exe_sql)
    #
    # print  len(list_hand_in)
    # print list_hand_in
    # print list_hand_out
    # print list_sys_in
    # print list_sys_out
    #
    # list_sys_in_int=[]
    # for i in range(len(list_sys_in)):
    #     list_sys_in_int.append(int(list_sys_in[i]))
    # print list_sys_in_int
    # ####将系统的数据转换成训练数据格式，array_sys_in矩阵####
    # temp_sys_in=[]
    # for i in range(len(list_sys_in)):
    #     temp=[]
    #     temp.append(int(list_sys_in[i]))
    #     temp_sys_in.append(temp)
    # array_sys_in=np.array(temp_sys_in)
    # # print array_sys_in.shape


    ##########模型加载################
    # train_size=300
    RF=joblib.load('rf.model')
    t0 = time.time()

    para_data = sys.argv[1]
    # print type(para_data)
    list_data = []
    if (type(para_data)==str):
        list_data.append(para_data)
        test_array = np.array(para_data)
        result = RF.predict(test_array)
        svr_predict = time.time() - t0
        print result
    else:
        if len(para_data)>=2:
            # para_data=[35,44]
            for i in range(len(para_data)):
                temp_list = []
                temp_list.append(para_data[i])
                list_data.append(temp_list)
            array_data = np.array(list_data)
            result = RF.predict(array_data)
            svr_predict = time.time() - t0
            print result
        else:
            # list_data.append(para_data)
            test_array = np.array(para_data)
            result = RF.predict(test_array[0])
            svr_predict = time.time() - t0
            print result



        # ##########应用模型进行预测################
    # yyy=RF.predict(array_sys_in[301:-1])
    # svr_predict = time.time() - t0
    # ##########进行模型验证，输出结果test_hand_in，监测时间svr_predict#########
    # #
    # # print array_sys_in.shape
    # # t0 = time.time()
    # # yyy=clf.predict(array_sys_in[301:-1])
    # # svr_predict = time.time() - t0
    #
    # test_hand_in=[]
    # for i in range(len(yyy)):
    #     test_hand_in.append(int(yyy[i]))
    # print "－－矫正后数据－－"
    # print test_hand_in
    # print "－－原始系统数据－－"
    # print list_sys_in_int[301:-1]
    # print "－－手工数据－－"
    # print list_hand_in[301:-1]
    #
    #
    # print "------正确率---"
    # yuanshi_pre = []
    # test_pre = []
    # for i in range(len(test_hand_in)):
    #     yuanshi_pre.append(Calculation(list_hand_in[i + 301],list_sys_in_int[i + 301]))
    #     test_pre.append(Calculation(list_hand_in[i + 301],test_hand_in[i]))
    # print yuanshi_pre
    # print test_pre
    # # 对结果进行显示
    #
    # # 对结果进行显示
    # plt.scatter(list_sys_in[:train_size], list_hand_in[:train_size], c='k', label='data', zorder=1)
    #
    # plt.plot(list_sys_in[301:-1], test_hand_in, c='r',
    #          label='SVR (fit: %.3fs, predict: %.3fs)' % (travel_model.get_svr_fit(), svr_predict))
    #
    # plt.xlabel('data')
    # plt.ylabel('target')
    # plt.title('SVR versus Kernel Ridge')
    #
    # plt.show()