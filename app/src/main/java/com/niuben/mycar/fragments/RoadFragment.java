package com.niuben.mycar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.service.LocationService;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.niuben.mycar.Activitys.AroundSearchActivity;
import com.niuben.mycar.Activitys.BusLineSearchActivity;
import com.niuben.mycar.Activitys.RoadNAVActivity;
import com.niuben.mycar.Activitys.RoutePlanActivity;
import com.niuben.mycar.Bean.WeatherDirtyBean;
import com.niuben.mycar.Bean.WeatherNowBean;
import com.niuben.mycar.Bean.WeatherReportBean;
import com.niuben.mycar.Bean.WeatherTotalBean;
import com.niuben.mycar.Bean.WindBean;
import com.niuben.mycar.LocationApplication;
import com.niuben.mycar.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by niuben on 2016/4/22.
 */
public class RoadFragment extends Fragment  {
    private View view;
    private RelativeLayout navigation,bus,routePlan,aroundSearch,rl_day,rl_night,weatherBackground;
    private TextView location_city,Dates,tv_windDirect,tv_windPower,Temperature,info,tv_dirty,dayTemperature,dayWind,nightTemperature,nightWind,dirty_advise,washCar,clothes,Time;
    private ImageView sunny,iv_day,iv_night;
    private LocationService locationService;
    private String Date;
    private String addess,windDirect,windPower;
    private String city;
    private String dayinfo,nightinfo,time;
    private double Lat;
    private double Lng;
    private WindBean windBean;
    private WeatherNowBean weatherNowBean;
    private WeatherDirtyBean weatherDirtyBean,weatherDirtyBeanWash,weatherDirtyBeanClothes;
    private WeatherTotalBean weatherTotalBeanDay,weatherTotalBeanNight;
    private BDLocationListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.road_serves_fragment,null);
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日  EEEE");
        Date=format.format(date);
        navigation= (RelativeLayout) view.findViewById(R.id.RL_NAV);
        bus= (RelativeLayout) view.findViewById(R.id.bus);
        routePlan= (RelativeLayout) view.findViewById(R.id.routePlan);
        aroundSearch= (RelativeLayout) view.findViewById(R.id.aroundSearch);
        weatherBackground= (RelativeLayout) view.findViewById(R.id.weatherBackground);
        rl_night= (RelativeLayout) view.findViewById(R.id.rl_night);
       //设置背景透明度
        rl_night.getBackground().setAlpha(80);
        rl_day= (RelativeLayout) view.findViewById(R.id.rl_day);
        rl_day.getBackground().setAlpha(80);
        location_city= (TextView) view.findViewById(R.id.location_city);
        Dates= (TextView) view.findViewById(R.id.Date);
        tv_windDirect= (TextView) view.findViewById(R.id.tv_windDirect);
        tv_windPower= (TextView) view.findViewById(R.id.tv_windPower);
        Temperature= (TextView) view.findViewById(R.id.Temperature);
        tv_dirty= (TextView) view.findViewById(R.id.tv_dirty);
        info= (TextView) view.findViewById(R.id.info);
        dirty_advise= (TextView) view.findViewById(R.id.dirty_advise);
        dayTemperature= (TextView) view.findViewById(R.id.dayTemperature);
        dayWind= (TextView) view.findViewById(R.id.dayWind);
        nightTemperature= (TextView) view.findViewById(R.id.nightTemperature);
        nightWind= (TextView) view.findViewById(R.id.nightWind);
        washCar= (TextView) view.findViewById(R.id.washCar);
        clothes= (TextView) view.findViewById(R.id.clothes);
        Time= (TextView) view.findViewById(R.id.Time);
        sunny= (ImageView) view.findViewById(R.id.iv_sun);
        iv_day=(ImageView)view.findViewById(R.id.iv_day);
        iv_night=(ImageView)view.findViewById(R.id.iv_night);
        Dates.setText(Date);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RoadNAVActivity.class);
                intent.putExtra("address", addess);
                intent.putExtra("Lat", Lat);
                intent.putExtra("Lng", Lng);
                getActivity().startActivity(intent);
            }
        });
        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BusLineSearchActivity.class);
                getActivity().startActivity(intent);
            }
        });
        routePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RoutePlanActivity.class);
                intent.putExtra("city", city);
                getActivity().startActivity(intent);
                Log.e("DDDD", city);
            }
        });
        aroundSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AroundSearchActivity.class);
                intent.putExtra("address", addess);
                intent.putExtra("Lat", Lat);
                intent.putExtra("Lng", Lng);
                getActivity().startActivity(intent);
            }
        });
         getCity();
         return view;
    }

    private void getCity() {
        mListener = new BDLocationListener(){

            @Override
            public void onReceiveLocation(BDLocation location) {
                if (null != location && location.getLocType() != BDLocation.TypeServerError){
                    city=location.getCity();
                    location_city.setText(location.getCity());
                    addess=location.getAddrStr();
                    Lat=location.getLatitude();
                    Lng=location.getLongitude();
                    Log.e("Address", addess);
                    Log.e("CCCC",city);
                    getDate();
                }

            }
        };


    }


    private void getDate() {
        String url="http://op.juhe.cn/onebox/weather/query?cityname="+city+"&key=380247419d71c021917abb83a0468628";
        Log.e("URL",url);
        Ion.with(getActivity()).load("http://op.juhe.cn/onebox/weather/query?cityname="+city+"&key=380247419d71c021917abb83a0468628")
                              .as(new TypeToken<WeatherReportBean>() {
                              })
                              .setCallback(new FutureCallback<WeatherReportBean>() {
                                  @Override
                                  public void onCompleted(Exception e, WeatherReportBean result) {
                                      if (result != null) {
                                          //weatherReportBean=result;
                                          //设置更新时间
                                          time=result.getResult().getData().getRealtime().getTime();
                                          Time.setText(time);
                                          //设置当前风向和风速
                                          windBean = new WindBean();
                                          windBean.setDirect(result.getResult().getData().getRealtime().getWind().getDirect());
                                          windBean.setPower(result.getResult().getData().getRealtime().getWind().getPower());
                                          windDirect = windBean.getDirect();
                                          windPower = windBean.getPower();
                                          tv_windDirect.setText(windDirect);
                                          tv_windPower.setText(windPower);
                                          Log.e("WWWWW", windDirect);
                                          Log.e("PPPPP", windPower);
                                          //设置当前温度和天气
                                          weatherNowBean=new WeatherNowBean();
                                          weatherNowBean.setTemperature(result.getResult().getData().getRealtime().getWeather().getTemperature());
                                          weatherNowBean.setInfo(result.getResult().getData().getRealtime().getWeather().getInfo());
                                          Temperature.setText(weatherNowBean.getTemperature());
                                          info.setText(weatherNowBean.getInfo());
                                          String fine=weatherNowBean.getInfo();
                                          //设置天气对应的图片
                                          setImage(fine);

                                          //设置小提醒
                                          weatherDirtyBean=new WeatherDirtyBean();
                                          weatherDirtyBean.setWuran(result.getResult().getData().getLife().getInfo().getWuran());
                                          tv_dirty.setText(weatherDirtyBean.getWuran().get(0));
                                          dirty_advise.setText(weatherDirtyBean.getWuran().get(1));
                                          weatherDirtyBeanWash=new WeatherDirtyBean();
                                          weatherDirtyBeanWash.setXiche(result.getResult().getData().getLife().getInfo().getXiche());
                                          washCar.setText(weatherDirtyBeanWash.getXiche().get(1));
                                          weatherDirtyBeanClothes=new WeatherDirtyBean();
                                          weatherDirtyBeanClothes.setChuanyi(result.getResult().getData().getLife().getInfo().getChuanyi());
                                          clothes.setText(weatherDirtyBeanClothes.getChuanyi().get(1));
                                          //设置白天的天气
                                          weatherTotalBeanDay=new WeatherTotalBean();
                                          weatherTotalBeanDay.setDay(result.getResult().getData().getWeather().get(0).getInfo().getDay());
                                          dayinfo=weatherTotalBeanDay.getDay().get(1);
                                          dayTemperature.setText(weatherTotalBeanDay.getDay().get(2));
                                          dayWind.setText(weatherTotalBeanDay.getDay().get(4));
                                          setDayImage(dayinfo);
                                          //设置晚上的天气
                                          weatherTotalBeanNight=new WeatherTotalBean();
                                          weatherTotalBeanNight.setNight(result.getResult().getData().getWeather().get(0).getInfo().getNight());
                                          nightinfo=weatherTotalBeanNight.getNight().get(1);
                                          nightTemperature.setText(weatherTotalBeanNight.getNight().get(2));
                                          nightWind.setText(weatherTotalBeanNight.getNight().get(4));
                                           setNightImage(nightinfo);
                                      } else {
                                          Toast.makeText(getActivity(), "没数据", Toast.LENGTH_LONG).show();
                                      }
                                  }
                              });

    }
    private void setImage(String fine) {
        if(fine.equals("晴")){
            sunny.setImageResource(R.mipmap.sun);
            weatherBackground.setBackgroundResource(R.drawable.sunny_car);
        }
        else if(fine.equals("雾")){
            sunny.setImageResource(R.mipmap.wu);
            weatherBackground.setBackgroundResource(R.drawable.fog);
        }
        else if(fine.equals("多云")){
            sunny.setImageResource(R.mipmap.duoyun);
            weatherBackground.setBackgroundResource(R.drawable.cloud);
        }
        else if(fine.equals("小雨")){
            sunny.setImageResource(R.mipmap.xiaoyu);
            weatherBackground.setBackgroundResource(R.drawable.little_rain);
        }
        else if(fine.equals("中雨")){
            sunny.setImageResource(R.mipmap.zhongyu);
            weatherBackground.setBackgroundResource(R.drawable.rain);
        }
        else if(fine.equals("大雨")){
            sunny.setImageResource(R.mipmap.dayu);
            weatherBackground.setBackgroundResource(R.drawable.rain);
        }
        else if(fine.equals("暴雨")){
            sunny.setImageResource(R.mipmap.baoyu);
            weatherBackground.setBackgroundResource(R.drawable.rain);
        }
        else if(fine.equals("阵雨")){
            sunny.setImageResource(R.mipmap.zhenyu);
            weatherBackground.setBackgroundResource(R.drawable.zhenyu);
        }
        else if(fine.equals("雷雨")){
            sunny.setImageResource(R.mipmap.leiyu);
            weatherBackground.setBackgroundResource(R.drawable.leidian);
        }
        else if(fine.equals("霾")){
            sunny.setImageResource(R.mipmap.wumai);
            weatherBackground.setBackgroundResource(R.drawable.leidian);
        }
        else {
            sunny.setImageResource(R.mipmap.sun);
            weatherBackground.setBackgroundResource(R.drawable.sunny_car);
        }

    }
    private void setDayImage(String dayinfo) {
        if(dayinfo.equals("晴")){
            iv_day.setImageResource(R.mipmap.sun);
        }
        else if(dayinfo.equals("雾")){
            iv_day.setImageResource(R.mipmap.wu);
        }
        else if(dayinfo.equals("多云")){
            iv_day.setImageResource(R.mipmap.duoyun);
        }
        else if(dayinfo.equals("小雨")){
            iv_day.setImageResource(R.mipmap.xiaoyu);
        }
        else if(dayinfo.equals("中雨")){
            iv_day.setImageResource(R.mipmap.zhongyu);
        }
        else if(dayinfo.equals("大雨")){
            iv_day.setImageResource(R.mipmap.dayu);
        }
        else if(dayinfo.equals("暴雨")){
            iv_day.setImageResource(R.mipmap.baoyu);
        }
        else if(dayinfo.equals("阵雨")){
            iv_day.setImageResource(R.mipmap.zhenyu);
        }
        else if(dayinfo.equals("雷雨")){
            iv_day.setImageResource(R.mipmap.leiyu);
        }
        else if(dayinfo.equals("霾")){
            iv_night.setImageResource(R.mipmap.wumai);
        }
        else {
            iv_day.setImageResource(R.mipmap.sun);
        }

    }
    private void setNightImage(String nightinfo) {
        if(nightinfo.equals("晴")){
            iv_night.setImageResource(R.mipmap.sun);
        }
        else if(dayinfo.equals("雾")){
            iv_night.setImageResource(R.mipmap.wu);
        }
        else if(dayinfo.equals("多云")){
            iv_night.setImageResource(R.mipmap.duoyun);
        }
        else if(dayinfo.equals("小雨")){
            iv_night.setImageResource(R.mipmap.xiaoyu);
        }
        else if(dayinfo.equals("中雨")){
            iv_night.setImageResource(R.mipmap.zhongyu);
        }
        else if(dayinfo.equals("大雨")){
            iv_night.setImageResource(R.mipmap.dayu);
        }
        else if(dayinfo.equals("暴雨")){
            iv_night.setImageResource(R.mipmap.baoyu);
        }
        else if(dayinfo.equals("阵雨")){
            iv_night.setImageResource(R.mipmap.zhenyu);
        }
        else if(dayinfo.equals("雷雨")){
            iv_night.setImageResource(R.mipmap.leiyu);
        }
        else if(dayinfo.equals("霾")){
            iv_night.setImageResource(R.mipmap.wumai);
        }
        else {
            iv_night.setImageResource(R.mipmap.sun);
        }

    }

    @Override
     public void onStop() {
    locationService.unregisterListener(mListener); //注销掉监听
    locationService.stop(); //停止定位服务
    super.onStop();
}

    @Override
    public void onStart() {
        super.onStart();
        locationService = ((LocationApplication) getActivity().getApplication()).locationService;
        locationService.registerListener(mListener);
        locationService.start();
    }


}
