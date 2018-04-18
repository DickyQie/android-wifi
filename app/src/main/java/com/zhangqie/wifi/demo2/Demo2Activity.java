package com.zhangqie.wifi.demo2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqie.wifi.R;

/**
 * Created by zhangqie on 2018/3/16.
 */

public class Demo2Activity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = Demo2Activity.class.getName();

    TextView textView;

    WifiInfo mWifiInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo2);
        initView();
    }


    private void initView(){
        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        WifiManager mWifiManager=(WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo=mWifiManager.getConnectionInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                showBtn1();
                break;
            case R.id.btn2:
                showBtn2();
                break;

        }
    }



    public void showBtn1(){
        try {
            ConnectivityManager connMgr = (ConnectivityManager) Demo2Activity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            //WiFi是否连接
            NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            boolean isWifiConn = networkInfo.isConnected();
            //手机网络是否连接
            networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean isMobileConn = networkInfo.isConnected();
            if (isWifiConn){
                Toast.makeText(this, mWifiInfo.getSSID()+"连接成功", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showBtn2(){
        Log.i(TAG, mWifiInfo.getBSSID());//8c:a6:df:07:7e:90
        Log.i(TAG, mWifiInfo.getSSID()); //"LIAIDI"  名称
        Log.i(TAG, mWifiInfo.getIpAddress()+"M"); //1694607552M
        Log.i(TAG, mWifiInfo.getLinkSpeed()+"S");  //65S
        Log.i(TAG, mWifiInfo.getIpAddress()+"ADD");
        Log.i(TAG, mWifiInfo.getMacAddress());  //44:c3:46:ab:ea:b6
        Log.i(TAG, mWifiInfo.getRssi()+"802.11n网络的信号");
        textView.setText(mWifiInfo.getBSSID() +"---"+mWifiInfo.getSSID() +"---"+mWifiInfo.getMacAddress());
    }


    /***
     *
     getBSSID() 获取BSSID,在手机WIFI中，就是MAC地址
     getSSID() 获取SSID
     getDetailedStateOf() 获取客户端的连通性
     getHiddenSSID() 获得SSID是否被隐藏
     getIpAddress() 获取IP地址
     getLinkSpeed() 获得连接的速度
     getMacAddress() 获得Mac地址
     getRssi() 获得802.11n网络的信号
     getSupplicanState() 返回具体客户端状态的信息

     */

}
