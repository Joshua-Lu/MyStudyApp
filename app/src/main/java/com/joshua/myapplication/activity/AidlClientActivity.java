package com.joshua.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import com.joshua.myapplication.R;
import com.lhf.aidlservice.IMyAidlInterface;
import com.lhf.aidlservice.User;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Joshua on 2020-4-19 22:00:24.
 */
public class AidlClientActivity extends AppCompatActivity {

    private static final String TAG = "AidlClientActivity";
    private IMyAidlInterface iMyAidlInterface;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_client);
        result = findViewById(R.id.result);

        Intent intent = new Intent();
        // android 5.0之后，必须显示启动
        intent.setClassName("com.lhf.aidlservice", "com.lhf.aidlservice.MyService");
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d("lhf-" + TAG, "onServiceConnected() called with: name = [" + name + "], service = [" + service + "]");
                // service是BinderProxy对象
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                // iMyAidlInterface是Stub对象还是Proxy对象?是Proxy对象
                Log.d(TAG, "onServiceConnected: iMyAidlInterface = [" + iMyAidlInterface.getClass() + "]");
                try {
                    String data = iMyAidlInterface.getData();
                    Log.d("lhf-" + TAG, "onServiceConnected: data = [" + data + "]");
                    User user = iMyAidlInterface.getUser();
                    Log.d("lhf-" + TAG, "onServiceConnected: user = [" + user + "]");
                    result.setText(data + "\n This is a custom type data from service: " + user);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("lhf-" + TAG, "onServiceDisconnected() called with: name = [" + name + "]");

            }
        };
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }
}
