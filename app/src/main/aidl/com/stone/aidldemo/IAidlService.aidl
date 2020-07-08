package com.stone.aidldemo;

import com.stone.aidldemo.bean.UserInfoBean;

interface IAidlService{
    int getIntCount();
    void getVoidMethod();
    void setParas(int count);
    String getStringData();
    String getStringByString(String str);
    UserInfoBean getUserInfo();
}