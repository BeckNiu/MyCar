package com.niuben.mycar.Adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.niuben.mycar.Activitys.CarShowActivity;
import com.niuben.mycar.Bean.MyCarBean;
import com.niuben.mycar.R;

import java.util.ArrayList;

/**
 * Created by niuben on 2016/5/12.
 */
public class CarServesAdapter extends BaseAdapter {
    private ArrayList<MyCarBean> list;
    private FragmentActivity activity;
    public CarServesAdapter(FragmentActivity customFragment, ArrayList<MyCarBean> list) {
        this.list=list;
        this.activity=customFragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View a;
        ViewHolder viewHolder=new ViewHolder();
        if(convertView==null){
            a=View.inflate(activity, R.layout.item_car_list,null);
            TextView carType =(TextView) a.findViewById(R.id.carType);
            TextView carAddress =(TextView) a.findViewById(R.id.carAddress);
            TextView carTime =(TextView) a.findViewById(R.id.carTime);
            ImageView iv_car_type= (ImageView) a.findViewById(R.id.iv_car_type);
            viewHolder.Type=carType;
            viewHolder.Address=carAddress;
            viewHolder.Time=carTime;
            viewHolder.car_type=iv_car_type;
            a.setTag(viewHolder);
        }else {
            a=convertView;
            viewHolder=(ViewHolder) a.getTag();
        }
        final MyCarBean bean = list.get(position);
        //Log.e("名字", bean.getPeopleName() + "/" + "号码" + bean.getPeopleNumber());
        viewHolder.Type.setText(bean.getCar_type());
        viewHolder.Address.setText(bean.getAddress());
        viewHolder.Time.setText(bean.getTime());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, CarShowActivity.class);
                intent.putExtra("bean",bean);
                activity.startActivity(intent);
            }
        });
        return a;
    }
    class ViewHolder{
        TextView Type;
        TextView Address;
        TextView Time;
        ImageView car_type;
    }
}
