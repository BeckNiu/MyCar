package com.niuben.mycar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.niuben.mycar.Activitys.NewsTextActivity;
import com.niuben.mycar.Adapter.CommonAdapter;
import com.niuben.mycar.Adapter.ViewHolder;
import com.niuben.mycar.Bean.CarNewsBean;
import com.niuben.mycar.Bean.CarNewsTotalBean;
import com.niuben.mycar.R;

import java.util.ArrayList;

/**
 * Created by niuben on 2016/4/22.
 */
public class NewsFragment extends Fragment {
    private View view;
    private View view1;
    private ListView lv_News;
    private ArrayList<CarNewsBean>list1=new ArrayList<>();
    private CommonAdapter<CarNewsBean> adapter;

    private String url;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_serves_fragment,null);
        url="http://c.m.163.com/nc/article/list/T1348654060988/10-20.html";
        initListView();
        getDate(url);
        return view;
    }

    private void initListView() {
        lv_News= (ListView) view.findViewById(R.id.lv_News);
        view1= View.inflate(getActivity(),R.layout.news_head,null);
        lv_News.addHeaderView(view1,null,false);
        adapter=new CommonAdapter<CarNewsBean>(getActivity(),list1,R.layout.item_news_list) {
            @Override
            public void convert(ViewHolder helper, CarNewsBean item) {
                helper.setText(R.id.Title,item.getTitle());
                helper.setText(R.id.from,item.getFrom());
                Ion.with(helper.<ImageView>getView(R.id.iv_news)).load(item.getImgsrc());

            }
        };
        lv_News.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               CarNewsBean carNewsBean = list1.get(position - 1);
                String url = carNewsBean.getUrl();
                if(url.equals("null")){
                    Toast.makeText(getActivity(), "抱歉，该条资讯暂不支持阅读，再淘一条吧。", Toast.LENGTH_LONG).show();
                }
                else { Intent intent = new Intent(getActivity(), NewsTextActivity.class);
                    intent.putExtra("URL", url);
                    getActivity().startActivity(intent);}

            }
        });
        lv_News.setAdapter(adapter);
    }

    private void getDate(String url) {
      Ion.with(getActivity()).load(url).as(new TypeToken<CarNewsTotalBean>(){}).setCallback(new FutureCallback<CarNewsTotalBean>() {
          @Override
          public void onCompleted(Exception e, CarNewsTotalBean result) {
              if(result!=null){
                  for (int i=1;i<result.getT1348654060988().size();i++){
                      CarNewsBean carNewsBean=new CarNewsBean();
                      carNewsBean.setTitle(result.getT1348654060988().get(i).getTitle());
                      carNewsBean.setImgsrc(result.getT1348654060988().get(i).getImgsrc());
                      carNewsBean.setUrl(result.getT1348654060988().get(i).getUrl());
                      carNewsBean.setFrom(result.getT1348654060988().get(i).getSource());
                      list1.add(carNewsBean);
                  }

              }
              adapter.notifyDataSetChanged();

          }
      });
    }


}
