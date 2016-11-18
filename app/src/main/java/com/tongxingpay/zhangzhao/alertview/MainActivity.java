package com.tongxingpay.zhangzhao.alertview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化
        final CustomAlertView customAlertView = new CustomAlertView(MainActivity.this);
        customAlertView.baseConfiguration("提示", "今天是星期五吗");

        Button showBtn = (Button)findViewById(R.id.main_show_btn);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显现
                Log.i(TAG, "onClick: 显现");
                customAlertView.showAlert();
            }
        });

        customAlertView.setLeftButtonClickedListener(new CustomAlertView.LeftButtonClickedListener() {
            @Override
            public void onClickedLeftButton(View view) {
                // 取消
                Log.i(TAG, "onClickedLeftButton: 取消");
                customAlertView.dismissAlert();
            }
        });

        customAlertView.setRightButtonClickedListener(new CustomAlertView.RightButtonClickedListener() {
            @Override
            public void onClickedRightButton(View view) {
                // 确定
                Log.i(TAG, "onClickedRightButton: 确定");

                /** 此处处理一些事物 **/

                // 取消
                customAlertView.dismissAlert();

            }
        });





    }
}
