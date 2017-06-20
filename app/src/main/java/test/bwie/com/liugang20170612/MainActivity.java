package test.bwie.com.liugang20170612;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * @ Description:主业务操作类
 * @ Date:2017/6/12
 * @ Author:刘刚
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomProgress customProgress, customProgress2, customProgress3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                customProgress2.setProgress(msg.arg1);
            }

            super.handleMessage(msg);
        }
    };
    private Button mRecycle;
    private Button mPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customProgress2 = (CustomProgress) findViewById(R.id.custom_progress2);
        mRecycle = (Button) findViewById(R.id.button);
        customProgress2.setmTotalProgress(100);
        mRecycle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:

                play();
                break;


        }
    }



    private void play() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {


                    for (int i = 1; i <= 100; i++) {
                        Thread.sleep(50);
                        Message message = handler.obtainMessage();
                        message.what = 2;
                        message.arg1 = i;
                        handler.sendMessage(message);
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
