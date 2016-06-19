package com.niuben.mycar.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.niuben.mycar.R;

import mapapi.overlayutil.PoiOverlay;

/**
 * Created by niuben on 2016/5/7.
 */
public class AroundSearchActivity extends Activity implements OnGetPoiSearchResultListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private double lLng;
    private double lLat;
    private String address;
    private Intent intent;
    private TextView add;
    private PoiSearch mPoiSearch = null;
    private int radius=0;
    private ImageView iv_add,iv_back;
    private int load_Index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //接收所在位置的经纬度和位置信息
        intent=getIntent();
        address=intent.getStringExtra("address");
        lLat=intent.getDoubleExtra("Lat", 0.1);
        lLng=intent.getDoubleExtra("Lng",0.1);
       //初始化地图控件
        mMapView = (MapView) findViewById(R.id.mapview_location);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
       //在地图上定位
        add= (TextView) findViewById(R.id.tv_add);
        iv_add= (ImageView) findViewById(R.id.iv_add);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setText(address);
        showMap(lLat, lLng);
       //初始化POI控件
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPoiSearch.destroy();
        mMapView.onDestroy();
        super.onDestroy();
    }
    //通过传入经纬度实现定位
    private void showMap(double latitude, double longtitude) {
        LatLng point = new LatLng(latitude, longtitude);
        BitmapDescriptor bitmap = null;
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.lock);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
        //设置缩放级别为500米
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(15).build()));
    }
   //点击搜索的事件
    public void searchButtonProcess(View v) {
        //查找点击按钮
        EditText editSearchKey = (EditText) findViewById(R.id.searchkey);

        //创建PoiNearbySearch事件
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        //设置搜索内容
        option.keyword(editSearchKey.getText().toString()).pageNum(load_Index);
        option.sortType(PoiSortType.distance_from_near_to_far);
        option.location(new LatLng(lLat, lLng));
        if (radius != 0) {
            option.radius(radius);
        } else {
            //搜索半径两公里
            option.radius(2000);
        }
        option.pageCapacity(20);
        mPoiSearch.searchNearby(option);
    }

   //获得搜索结果
    @Override
    public void onGetPoiResult(PoiResult result) {
        if (result == null
                || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            iv_add.setVisibility(View.GONE);
            add.setText("您所在位置两公里内未找到结果");
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            mBaiduMap.clear();
            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result);
            overlay.addToMap();
            overlay.zoomToSpan();
            iv_add.setVisibility(View.GONE);
            add.setText("已在两公里内为您找到结果，请点击地图上的红色图标查看详情");
            return;
        }
//        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
//
//            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
//            String strInfo = "在";
//            for (CityInfo cityInfo : result.getSuggestCityList()) {
//                strInfo += cityInfo.city;
//                strInfo += ",";
//            }
//            strInfo += "找到结果";
//            Toast.makeText(AroundSearchActivity.this, strInfo, Toast.LENGTH_LONG)
//                    .show();
//        }
    }


    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(AroundSearchActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                    .show();
        } else {
            iv_add.setVisibility(View.VISIBLE);
            iv_add.setImageResource(R.mipmap.real);
            add.setText( result.getName() + ": " + result.getAddress());
        }
    }


    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
            showMap(lLat, lLng);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));

            // }
            return true;
        }
    }
}
