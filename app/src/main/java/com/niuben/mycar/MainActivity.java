package com.niuben.mycar;

import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.niuben.mycar.Activitys.BaseActivity;
import com.niuben.mycar.fragments.CarFragment;
import com.niuben.mycar.fragments.NewsFragment;
import com.niuben.mycar.fragments.RoadFragment;

public class MainActivity extends BaseActivity {
    private ImageView icon_road_serves;
    private TextView tv_car_serves,tv_news_serves,tv_road_serves,tv_title_bar;
    private RelativeLayout RL_car_serves,RL_news_serves;
    private FrameLayout FL_main;
    private RoadFragment roadFragment;
    private CarFragment carFragment;
    private NewsFragment newsFragment;
    private android.support.v4.app.FragmentManager fm;
    private android.support.v4.app.FragmentTransaction ft;
    private long firstTime=0;

    @Override
    protected void setContent() {
        //加载布局
        setContentView(R.layout.activity_main);
        //查找到FrameLayout 并且开启fragment的管理器管理各个fragment，并且把road fragment作为默认显示
        FL_main= (FrameLayout) findViewById(R.id.FL_main);
        roadFragment=new RoadFragment();
        carFragment=new CarFragment();
        newsFragment=new NewsFragment();
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.add(R.id.FL_main,roadFragment)
          .add(R.id.FL_main,carFragment)
          .add(R.id.FL_main,newsFragment)
          .show(roadFragment).hide(carFragment).hide(newsFragment)
          .commit();

    }

    @Override
    protected void findView() {
        icon_road_serves= (ImageView) findViewById(R.id.icon_road_serves);
        tv_title_bar= (TextView) findViewById(R.id.tv_title_bar);
        tv_car_serves= (TextView) findViewById(R.id.tv_car_serves);
        tv_news_serves= (TextView) findViewById(R.id.tv_news_serves);
        tv_road_serves= (TextView) findViewById(R.id.tv_road_serves);
        RL_car_serves= (RelativeLayout) findViewById(R.id.RL_car_serves);
        RL_news_serves= (RelativeLayout) findViewById(R.id.RL_news_serves);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        icon_road_serves.setOnClickListener(this);
        RL_car_serves.setOnClickListener(this);
        RL_news_serves.setOnClickListener(this);

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.RL_car_serves:
                initColor();
                tv_car_serves.setTextColor(getResources().getColor(R.color.yellow));
                tv_title_bar.setText("爱车管理");
                ft=fm.beginTransaction();
                ft.show(carFragment).hide(roadFragment).hide(newsFragment).commit();
                break;
            case R.id.icon_road_serves:
                initColor();
                tv_road_serves.setTextColor(getResources().getColor(R.color.yellow));
                tv_title_bar.setText("路上服务");
                ft=fm.beginTransaction();
                ft.show(roadFragment).hide(carFragment).hide(newsFragment).commit();
                break;
            case R.id.RL_news_serves:
                initColor();
                tv_news_serves.setTextColor(getResources().getColor(R.color.yellow));
                tv_title_bar.setText("汽车资讯");
                ft=fm.beginTransaction();
                ft.show(newsFragment).hide(roadFragment).hide(carFragment).commit();
                break;

        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
       switch (keyCode){
           case KeyEvent.KEYCODE_BACK:
               long secondTime=System.currentTimeMillis();
               if(secondTime-firstTime>2000){
                   Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_LONG).show();
                   firstTime=secondTime;
                   return true;
               }else {
                   System.exit(0);
               }
               break;
       }
        return super.onKeyUp(keyCode, event);
    }

    private void initColor() {
        tv_car_serves.setTextColor(getResources().getColor(R.color.black));
        tv_news_serves.setTextColor(getResources().getColor(R.color.black));
        tv_road_serves.setTextColor(getResources().getColor(R.color.black));

    }


}
