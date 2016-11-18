package com.tongxingpay.zhangzhao.alertview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zhangzhao on 18/11/2016.
 */

public class CustomAlertView extends View {
    private TextView alertTitle;
    private TextView alertContent;
    private Button alertLeftBtn;
    private Button alertRightBtn;
    private Context mContent;
    private AlertDialog alertDialog;
    private View mView;

    public LeftButtonClickedListener leftButtonClickedListener;
    public RightButtonClickedListener rightButtonClickedListener;

    public CustomAlertView(Context context) {
        super(context);
        mContent = context;
        initView(mContent);
    }

    public CustomAlertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContent = context;
        initView(mContent);

    }

    public CustomAlertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContent = context;
        initView(mContent);

    }

    // 初始化视图
    private void initView(Context context) {
        mView = LayoutInflater.from(mContent).inflate(R.layout.view_alert, null);

        alertTitle = (TextView)mView.findViewById(R.id.alert_title);
        alertContent = (TextView)mView.findViewById(R.id.alert_content);
        alertLeftBtn = (Button)mView.findViewById(R.id.alert_leftBtn);
        alertRightBtn = (Button)mView.findViewById(R.id.alert_rightBtn);

        // 初始化AlertDialog
        alertDialog = new AlertDialog.Builder(mContent).create();

        // 监听
        alertLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                leftButtonClickedListener.onClickedLeftButton(v);
            }
        });

        alertRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                rightButtonClickedListener.onClickedRightButton(v);
            }
        });

    }

    // 配置内容和标题
    public void baseConfiguration(String title, String content) {
        alertTitle.setText(title);
        alertContent.setText(content);
    }

    // 弹出对话框
    public void showAlert() {

        // 以AlertDialog为载体
        alertDialog.setView(mView);
        alertDialog.show();

        // 设置宽度，AlertDialog默认的宽度过于夸张。（可以不修改）
        WindowManager windowManager = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        layoutParams.width = (int)(display.getWidth() * 2 / 3);
        alertDialog.getWindow().setAttributes(layoutParams);

    }

    // 取消对话框
    public void dismissAlert() {
        alertDialog.dismiss();
    }

    public interface LeftButtonClickedListener {
        void onClickedLeftButton(View view);
    }

    public interface RightButtonClickedListener {
        void onClickedRightButton(View view);
    }

    public void setLeftButtonClickedListener(LeftButtonClickedListener leftButtonClickedListener) {
        this.leftButtonClickedListener = leftButtonClickedListener;
    }

    public void setRightButtonClickedListener(RightButtonClickedListener rightButtonClickedListener) {
        this.rightButtonClickedListener = rightButtonClickedListener;
    }
}
