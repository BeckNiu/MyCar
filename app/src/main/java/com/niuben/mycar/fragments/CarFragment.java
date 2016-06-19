package com.niuben.mycar.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.niuben.mycar.Activitys.CarRecordActivity;
import com.niuben.mycar.Activitys.LandActivity;
import com.niuben.mycar.Adapter.CarServesAdapter;
import com.niuben.mycar.Bean.MyCarBean;
import com.niuben.mycar.Bean.UserBean;
import com.niuben.mycar.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 * Created by niuben on 2016/4/22.
 */
public class CarFragment extends Fragment implements View.OnClickListener {

    private View view;
    private IntentFilter filter;
    private TextView userName, car_name, car_date;
    private RelativeLayout rl_add;
    private BroadcastReceiver receiver;
    private int userId = -1;
    private UserBean user;
    private ImageView iv_user_head;
    private ArrayList<MyCarBean> carlist = new ArrayList<>();
    private CarServesAdapter adapter;
    private ListView car_listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.car_serves_fragment, null);
        car_name = (TextView) view.findViewById(R.id.car_name);
        car_date = (TextView) view.findViewById(R.id.car_date);
        car_listView = (ListView) view.findViewById(R.id.car_listView);
        rl_add = (RelativeLayout) view.findViewById(R.id.rl_add);
        getData();
        initView();
        return view;
    }


    private void getData() {
        filter = new IntentFilter();
        filter.addAction("Login");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                userId = intent.getIntExtra("userId", -1);
                if (userId != -1) {
                    initData();
                } else {
                    userName.setText("未登录");
                }
            }
        };
        getContext().registerReceiver(receiver, filter);

    }

    private void initData() {
        user = DataSupport.find(UserBean.class, userId);
        userName.setText(user.getUser_name());
        iv_user_head.setImageURI(Uri.parse(user.getUser_image()));
        car_name.setVisibility(View.VISIBLE);
        car_date.setVisibility(View.VISIBLE);
        car_name.setText(user.getCar_name());
        car_date.setText(user.getCar_time());
        carlist = (ArrayList<MyCarBean>) DataSupport.where("userId=?", userId + "").find(MyCarBean.class);
        adapter = new CarServesAdapter(getActivity(), carlist);
        car_listView.setAdapter(adapter);
    }

    private void initView() {
        iv_user_head = (ImageView) view.findViewById(R.id.iv_user_head);
        iv_user_head.setOnClickListener(this);
        rl_add.setOnClickListener(this);
        userName = (TextView) view.findViewById(R.id.user_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_head:
                Intent intent = new Intent(getActivity(), LandActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_add:
                if (userId == -1) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent1 = new Intent(getActivity(), CarRecordActivity.class);
                    intent1.putExtra("userId", userId);
                    startActivityForResult(intent1, 100);
                }

                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 100) {
                MyCarBean bean = (MyCarBean) intent.getSerializableExtra("bean");
                carlist.add(bean);
                adapter.notifyDataSetChanged();
            }
        }
    }
}






