package com.niuben.mycar.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.baidu.navisdk.adapter.BaiduNaviManager.NaviInitListener;
import com.baidu.navisdk.adapter.BaiduNaviManager.RoutePlanListener;
import com.niuben.mycar.R;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RoadNAVActivity extends Activity implements OnGetGeoCoderResultListener {
	private Button button,button1,button2;
	GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	private  int type;
	private double sLng;
	private double eLat;
	private double eLng;
	private double sLat;
	private double dLat;
	private double dLng;
	private double Lat;
	private double Lng;
	private double lLng;
	private double lLat;
	private String address;
	private Intent intent;
	private TextView add;



	public static List<Activity> activityList = new LinkedList<Activity>();

	private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";

	private Button mWgsNaviBtn = null;
	private Button mWgsNaviBtn1 = null;
//	private Button mGcjNaviBtn = null;
//	private Button mBdmcNaviBtn = null;
//	private Button mDb06ll = null;
	private String mSDCardPath = null;

	public static final String ROUTE_PLAN_NODE = "routePlanNode";
	public static final String SHOW_CUSTOM_ITEM = "showCustomItem";
	public static final String RESET_END_NODE = "resetEndNode";
	public static final String VOID_MODE = "voidMode";
	private EditText editCity,editGeoCodeKey,EndeditCity,EndeditGeoCodeKey,DeditCity,DeditGeoCodeKey;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activityList.add(this);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.road_nav);

		intent=getIntent();
		address=intent.getStringExtra("address");
		lLat=intent.getDoubleExtra("Lat", 0.1);
		lLng=intent.getDoubleExtra("Lng",0.1);
		mWgsNaviBtn = (Button) findViewById(R.id.wgsNaviBtn);
		mWgsNaviBtn1 = (Button) findViewById(R.id.wgsNaviBtn1);

//		mGcjNaviBtn = (Button) findViewById(R.id.gcjNaviBtn);
//		mBdmcNaviBtn = (Button) findViewById(R.id.bdmcNaviBtn);
//		mDb06ll = (Button) findViewById(R.id.mDb06llNaviBtn);
		button = (Button) findViewById(R.id.Sgeocode);
		button1 = (Button) findViewById(R.id.Egeocode);
        button2= (Button) findViewById(R.id.DSgeocode);
		editCity = (EditText) findViewById(R.id.Scity);
		editGeoCodeKey = (EditText) findViewById(R.id.Sgeocodekey);
		EndeditCity = (EditText) findViewById(R.id.Ecity);
		EndeditGeoCodeKey = (EditText) findViewById(R.id.Egeocodekey);
		DeditCity = (EditText) findViewById(R.id.Dcity);
		DeditGeoCodeKey = (EditText) findViewById(R.id.Dgeocodekey);
		add= (TextView) findViewById(R.id.tv_add);
		add.setText(address);
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		initListener();
		if (initDirs()) {
			initNavi();		
		}

	}


	@Override
	protected void onResume() {
		super.onResume();
	}
	public void SearchButtonProcess(View v) {
		if (v.getId() == R.id.Sgeocode) {
			type=1;


			// Geo搜索
			mSearch.geocode(new GeoCodeOption().city(
					editCity.getText().toString()).address(
					editGeoCodeKey.getText().toString()));


		} else if (v.getId() == R.id.Egeocode) {
			type=2;


			// Geo搜索
			mSearch.geocode(new GeoCodeOption().city(
					EndeditCity.getText().toString()).address(
					EndeditGeoCodeKey.getText().toString()));
		}
		else if(v.getId() == R.id.DSgeocode){
			type=3;

			// Geo搜索
			mSearch.geocode(new GeoCodeOption().city(
					DeditCity.getText().toString()).address(
					DeditGeoCodeKey.getText().toString()));
		}
	}


	private void initListener() {
	
		if (mWgsNaviBtn != null) {
			mWgsNaviBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (BaiduNaviManager.isNaviInited()) {
						routeplanToNavi(CoordinateType.BD09LL);
					}
				}

			});
		}
		if (mWgsNaviBtn1 != null) {
			mWgsNaviBtn1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (BaiduNaviManager.isNaviInited()) {
						routeplanToNavi(CoordinateType.GCJ02);
					}
				}

			});
		}
//		if (mBdmcNaviBtn != null) {
//			mBdmcNaviBtn.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View arg0) {
//
//					if (BaiduNaviManager.isNaviInited()) {
//						routeplanToNavi(CoordinateType.BD09_MC);
//					}
//				}
//			});
//		}
//
//		if (mDb06ll != null) {
//			mDb06ll.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					if (BaiduNaviManager.isNaviInited()) {
//						routeplanToNavi(CoordinateType.BD09LL);
//					}
//				}
//			});
//		}


	}


	private boolean initDirs() {
		mSDCardPath = getSdcardDir();
		if (mSDCardPath == null) {
			return false;
		}
		File f = new File(mSDCardPath, APP_FOLDER_NAME);
		if (!f.exists()) {
			try {
				f.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	String authinfo = null;

	private void initNavi() {
		// BaiduNaviManager.getInstance().setNativeLibraryPath(mSDCardPath +
		// "/BaiduNaviSDK_SO");
	
	
		BNOuterTTSPlayerCallback ttsCallback = null;

		BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME, new NaviInitListener() {
			@Override
			public void onAuthResult(int status, String msg) {
				if (0 == status) {
					authinfo = "导航引擎准备成功";
				} else {
					authinfo = "key校验失败, " + msg;
				}
				RoadNAVActivity.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(RoadNAVActivity.this, authinfo, Toast.LENGTH_LONG).show();
					}
				});
			}

			public void initSuccess() {
				Toast.makeText(RoadNAVActivity.this, "请输入您的终点", Toast.LENGTH_SHORT).show();
			}

			public void initStart() {
				Toast.makeText(RoadNAVActivity.this, "导航开始为您准备", Toast.LENGTH_SHORT).show();
			}

			public void initFailed() {
				Toast.makeText(RoadNAVActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
			}

		},  null/* null mTTSCallback */);
	}

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}

	private void routeplanToNavi(CoordinateType coType) {
		BNRoutePlanNode sNode = null;
		BNRoutePlanNode eNode = null;
		switch (coType) {
			case GCJ02: {
				sNode = new BNRoutePlanNode(lLng, lLat, "百度大厦", null, coType);
				eNode = new BNRoutePlanNode(dLng, dLat, "北京天安门", null, coType);
				break;
			}
			case WGS84: {
				sNode = new BNRoutePlanNode(lLng,lLat , "", null, coType);
				eNode = new BNRoutePlanNode(dLng, dLat, "", null, coType);
				break;
			}
			case BD09_MC: {
				sNode = new BNRoutePlanNode(12947471, 4846474, "百度大厦", null, coType);
				eNode = new BNRoutePlanNode(12958160, 4825947, "北京天安门", null, coType);
				break;
			}
			case BD09LL: {
				sNode = new BNRoutePlanNode(sLng, sLat, "", null, coType);
				eNode = new BNRoutePlanNode(eLng, eLat, "", null, coType);
				break;
			}
			default:

			}
			if (sNode != null && eNode != null) {
				List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
				list.add(sNode);
				list.add(eNode);
				BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
			}
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(RoadNAVActivity.this, "抱歉，未能找到结果,请重新输入", Toast.LENGTH_LONG)
					.show();
			return;
		}
		else {

		}
		String strInfo = String.format("纬度：%f 经度：%f",
				result.getLocation().latitude, result.getLocation().longitude);
		Toast.makeText(RoadNAVActivity.this,"输入位置可导", Toast.LENGTH_LONG).show();


		if (type==1){
			sLat=result.getLocation().latitude;
			sLng=result.getLocation().longitude;
			Log.e("ggggggg", String.valueOf(sLat));
			Log.e("ddddddd", String.valueOf(sLng));

		}else if(type==2) {
			eLat=result.getLocation().latitude;
			eLng=result.getLocation().longitude;
			Log.e("bbbbbbb", String.valueOf(eLat));
			Log.e("aaaaaaa", String.valueOf(eLng));

		}
		else {
			dLat=result.getLocation().latitude;
			dLng=result.getLocation().longitude;
			Log.e("ppppppp", String.valueOf(dLat));
			Log.e("ccccccc", String.valueOf(dLng));

		}

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

	}

	public class DemoRoutePlanListener implements RoutePlanListener {

		private BNRoutePlanNode mBNRoutePlanNode = null;

		public DemoRoutePlanListener(BNRoutePlanNode node) {
			mBNRoutePlanNode = node;
		}

		@Override
		public void onJumpToNavigator() {
			/*
			 * 设置途径点以及resetEndNode会回调该接口
			 */
		 
			for (Activity ac : activityList) {
			   
				if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
				 
					return;
				}
			}
			Intent intent = new Intent(RoadNAVActivity.this, BNDemoGuideActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
			intent.putExtras(bundle);
			startActivity(intent);
			
		}

		@Override
		public void onRoutePlanFailed() {
			// TODO Auto-generated method stub
			Toast.makeText(RoadNAVActivity.this, "算路失败，请重新输入地址", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy() {
		mSearch.destroy();


		super.onDestroy();
	}

	private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {

		@Override
		public void stopTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "stopTTS");
		}

		@Override
		public void resumeTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "resumeTTS");
		}

		@Override
		public void releaseTTSPlayer() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "releaseTTSPlayer");
		}

		@Override
		public int playTTSText(String speech, int bPreempt) {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "playTTSText" + "_" + speech + "_" + bPreempt);

			return 1;
		}

		@Override
		public void phoneHangUp() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "phoneHangUp");
		}

		@Override
		public void phoneCalling() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "phoneCalling");
		}

		@Override
		public void pauseTTS() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "pauseTTS");
		}

		@Override
		public void initTTSPlayer() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "initTTSPlayer");
		}

		@Override
		public int getTTSState() {
			// TODO Auto-generated method stub
			Log.e("test_TTS", "getTTSState");
			return 1;
		}
	};

}
