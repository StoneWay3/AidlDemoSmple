package com.stone.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stone.aidldemo.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.startTv)
    TextView startTv;
    @BindView(R.id.getint)
    TextView getint;
    @BindView(R.id.voidmethod)
    TextView voidmethod;
    @BindView(R.id.setint)
    TextView setint;
    @BindView(R.id.getstring)
    TextView getstring;
    @BindView(R.id.getparastr)
    TextView getparastr;
    @BindView(R.id.unbind)
    TextView unbind;
    @BindView(R.id.getbean)
    TextView getbean;

    Intent myServiceIntent = null;
    boolean bindService = false;
    IAidlService mIAidlService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.startTv, R.id.getint, R.id.voidmethod, R.id.setint, R.id.getstring, R.id.getparastr, R.id.unbind, R.id.getbean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.startTv:
                myStartService();
                break;
            case R.id.getint:
                if (mIAidlService != null) {
                    try {
                        getint.setText("获取到的int参数值是==" + mIAidlService.getIntCount());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.voidmethod:
                if (mIAidlService != null) {
                    try {
                        mIAidlService.getVoidMethod();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.setint:
                if (mIAidlService != null) {
                    try {
                        mIAidlService.setParas(20);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.getstring:
                if (mIAidlService != null) {
                    try {
                        getstring.setText("获取到的string参数值是==" + mIAidlService.getStringData());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.getparastr:
                if (mIAidlService != null) {
                    try {
                        getparastr.setText("获取到的string参数值是==" + mIAidlService.getStringByString("这是传递的参数哈哈"));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.unbind:
                if (serviceConnection!=null&&bindService){
                    unbindService(serviceConnection);
                    mIAidlService=null;
                    bindService=false;
                }
                break;
            case R.id.getbean:
                if (mIAidlService != null) {
                    try {
                        UserInfoBean userInfoBean = mIAidlService.getUserInfo();
                        getbean.setText("" + userInfoBean.getUserName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void myStartService() {
        if (!MyUtils.isServiceRunning(this, "com.stone.aidldemo.AidlDemoService")) {
            myServiceIntent = new Intent(MainActivity.this, AidlDemoService.class);
            myServiceIntent.setAction("action.com.stone.aidldemo.AidlDemoService");
            startService(myServiceIntent);
        } else {
            // 设置新TASK的方式
            myServiceIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        // 以bindService方法连接绑定服务
        bindService = bindService(myServiceIntent, serviceConnection, BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIAidlService = IAidlService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection!=null&&bindService){
            unbindService(serviceConnection);
            mIAidlService=null;
            bindService=false;
        }
    }
}