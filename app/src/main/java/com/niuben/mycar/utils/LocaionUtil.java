package com.niuben.mycar.utils;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

/**
 * Created by niuben on 2016/5/10.
 */
public class LocaionUtil {
    private  static  String city;
    private  static BDLocationListener mListener;
    public  static String Getctiy(){
        mListener = new BDLocationListener(){
            @Override
            public void onReceiveLocation(BDLocation location) {
                if (null != location && location.getLocType() != BDLocation.TypeServerError){
                  city=location.getCity();
//                    location_city.setText(location.getCity());
//                    addess=location.getAddrStr();
//                    Lat=location.getLatitude();
//                    Lng=location.getLongitude();
//                    Log.e("Address", addess);
                    Log.e("CCCC",city);
                }

            }
        };


        return city;
    }
}
