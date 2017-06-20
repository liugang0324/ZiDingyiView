package test.bwie.com.liugang20170612;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {


    private GradientProgressBar gp;
    private Button btn1;
    private Button btn2;
    boolean flag=false;
   Handler handler=new Handler(){

       private int mPercent;

       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           switch (msg.what){
               case 0:
                   mPercent = gp.getPercent();
                   gp.setPercent(++mPercent);
                   if (mPercent >=100){
                       gp.setPercent(100);
                       if (flag){
                           handler.removeMessages(0);
                           handler.sendEmptyMessageDelayed(1,100);
                       }
                   }else {
                       handler.sendEmptyMessageDelayed(0,100);
                   }
                   break;
               case 1:
                   gp.setPercent(mPercent--);
                   if (mPercent<=0){
                       handler.removeMessages(1);
                       gp.setPercent(0);
                       handler.sendEmptyMessageDelayed(0,100);
                   }else {
                       handler.sendEmptyMessageDelayed(1,100);
                   }
                   break;
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
    }


    private void initView() {
        gp = (GradientProgressBar) findViewById(R.id.gp);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        handler.sendEmptyMessageDelayed(0,100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                flag=true;
                  handler.sendEmptyMessageDelayed(0,100);

                break;
            case R.id.btn2:
                 handler.removeMessages(0);
                handler.removeMessages(1);
                break;
        }
    }
}
