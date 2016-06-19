package com.niuben.mycar.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.niuben.mycar.Bean.UserBean;
import com.niuben.mycar.R;

import org.litepal.crud.DataSupport;

/**
 * Created by niuben on 2016/5/15.
 */
public class LandActivity extends Activity implements View.OnClickListener {
    private EditText et_user_name;
    private EditText user_pass;
   // private List<UserBean> userList = new ArrayList<>();
    private  UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        et_user_name = (EditText) findViewById(R.id.et_user_name);
        user_pass = (EditText) findViewById(R.id.user_pass);
       // userList = DataSupport.findAll(UserBean.class);

    }

    private void checkData() {


        if (!TextUtils.isEmpty(et_user_name.getText().toString().trim()) & !TextUtils.isEmpty(user_pass.getText().toString().trim())) {
//            for (int i = 0; i < userList.size(); i++) {
//                if (TextUtils.equals(et_user_name.getText().toString().trim(), userList.get(i).getUser_name())) {
//                    if (TextUtils.equals(user_pass.getText().toString().trim(), userList.get(i).getUser_pass())) {
//                        Toast.makeText(LandActivity.this, "登录成功", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent();
//                        intent.setAction("Login");
//                        intent.putExtra("userId", userList.get(i).getId());
//                        sendBroadcast(intent);
//                        finish();
//                    } else {
//
//                        Toast.makeText(LandActivity.this, "密码错误", Toast.LENGTH_LONG);
//                    }
//                } else {
//                    Toast.makeText(LandActivity.this, "用户名不存在", Toast.LENGTH_LONG);
//                }
//            }
            userBean=new UserBean();
            if(DataSupport.where("user_name=?",et_user_name.getText().toString().trim()).find(UserBean.class).isEmpty()){
                Toast.makeText(LandActivity.this, "用户名不存在", Toast.LENGTH_LONG).show();
            }else if(!TextUtils.equals(user_pass.getText().toString().trim(), (DataSupport.where("user_name=?",et_user_name.getText().toString().trim()).find(UserBean.class)).get(0).getUser_pass())){
                Toast.makeText(LandActivity.this, "密码错误", Toast.LENGTH_LONG).show();
            }
            else if(TextUtils.equals(user_pass.getText().toString().trim(), (DataSupport.where("user_name=?",et_user_name.getText().toString().trim()).find(UserBean.class)).get(0).getUser_pass()))
            {
                userBean=DataSupport.where("user_name=?",et_user_name.getText().toString().trim()).find(UserBean.class).get(0);
                Toast.makeText(LandActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setAction("Login");
                        intent.putExtra("userId", userBean.getId());
                        sendBroadcast(intent);
                        finish();
            }
        } else {
            Toast.makeText(LandActivity.this, "请补全信息再登录", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                Intent intent = new Intent(LandActivity.this, SingUpActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login:
                checkData();
                break;
            case R.id.iv_back:
                finish();
                break;
        }

    }


}
