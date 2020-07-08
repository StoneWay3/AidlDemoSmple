package com.stone.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.stone.aidldemo.bean.UserInfoBean;

/**
 * create by StoneWay on 2020/7/8
 */
public class AidlDemoService extends Service {

    private int intCount = 0;
    int voidCount = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        //todo:初始化一些东西
        Toast.makeText(AidlDemoService.this, "服务已经启动", Toast.LENGTH_LONG).show();
    }

    private IAidlService.Stub stub = new IAidlService.Stub() {
        @Override
        public int getIntCount() throws RemoteException {
            return intCount;
        }

        @Override
        public void getVoidMethod() throws RemoteException {
            Toast.makeText(AidlDemoService.this, "调用这个void方法了" + voidCount + "次", Toast.LENGTH_LONG).show();
            voidCount += 1;
        }

        @Override
        public void setParas(int count) throws RemoteException {
            intCount += count;
            Toast.makeText(AidlDemoService.this, "参数已经接收", Toast.LENGTH_LONG).show();
        }

        @Override
        public String getStringData() throws RemoteException {
            return "调用的void字符串";
        }

        @Override
        public String getStringByString(String str) throws RemoteException {
            return "哈哈你传过来的参数是：" + str;
        }

        @Override
        public UserInfoBean getUserInfo() throws RemoteException {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setAge(intCount);
            userInfoBean.setGenderBoy(true);
            userInfoBean.setUserName("Stone");
            return userInfoBean;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
