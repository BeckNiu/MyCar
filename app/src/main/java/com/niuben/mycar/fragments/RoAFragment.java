package com.niuben.mycar.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by niuben on 2016/5/16.
 */
public class RoAFragment extends Fragment {
//    private View view;
//    private ListView lv_News;
//    private ArrayList<CarMaintainBean> list=new ArrayList<>();
//
//    private CommonAdapter<CarMaintainBean> adapter;
//
//    private RelativeLayout rl_fixCar,rl_fixCars,rl_fixBus;
//    private String url;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view=inflater.inflate(R.layout.news_serves_fragment,null);
////        rl_fixCar= (RelativeLayout) view.findViewById(R.id.rl_fixCar);
////        rl_fixCars= (RelativeLayout) view.findViewById(R.id.rl_fixCars);
////        rl_fixBus= (RelativeLayout) view.findViewById(R.id.rl_fixBus);
////        url="http://c.m.163.com/nc/article/list/T1348654060988/10-20.html";
////        initListView();
////        getDate(url);
////        rl_fixCar.setOnClickListener(this);
////        rl_fixCars.setOnClickListener(this);
////        rl_fixBus.setOnClickListener(this);
//        return view;
//    }
//
//    private void initListView() {
//        lv_News= (ListView) view.findViewById(R.id.lv_News);
//        adapter=new CommonAdapter<CarMaintainBean>(getActivity(),list,R.layout.item_news_list) {
//            @Override
//            public void convert(ViewHolder helper, CarMaintainBean item) {
//                helper.setText(R.id.Title,item.getTitle());
//                helper.setText(R.id.from,item.getSrc());
//                helper.setText(R.id.update, item.getPdate());
//                if(item.getImg().equals("")){
//                    helper.setImageResource(R.drawable.yin);
//                }
//                else {
//                    Ion.with(helper.<ImageView>getView(R.id.iv_news)).load(item.getImg());
//                }
//
//            }
//        };
//
//        lv_News.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CarMaintainBean carMaintainBean = list.get(position);
//                String url = carMaintainBean.getUrl();
//                Intent intent = new Intent(getActivity(), NewsTextActivity.class);
//                intent.putExtra("URL", url);
//                getActivity().startActivity(intent);
//            }
//        });
//        lv_News.setAdapter(adapter);
//    }
//
//    private void getDate(String url) {
//        Ion.with(getActivity()).load(url)
//                .as(new TypeToken<CarMaintainNewsBean>(){})
//                .setCallback(new FutureCallback<CarMaintainNewsBean>() {
//                    @Override
//                    public void onCompleted(Exception e, CarMaintainNewsBean result) {
//                        if(result!=null){
//                            for(int i=0;i<result.getResult().size();i++){
//                                CarMaintainBean carMaintainBean=new CarMaintainBean();
//                                CarMaintainNewsBean.ResultBean resultBean=result.getResult().get(i);
//                                carMaintainBean.setTitle(resultBean.getTitle());
//                                carMaintainBean.setImg(resultBean.getImg());
//                                carMaintainBean.setSrc(resultBean.getSrc());
//                                carMaintainBean.setPdate(resultBean.getPdate());
//                                carMaintainBean.setUrl(resultBean.getUrl());
//                                list.add(carMaintainBean);
//
//                            }
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//
//                });
//    }
//
//    @Override
//    public void onClick(View v) {
////        switch (v.getId()){
////            case R.id.rl_fixCar:
////                list.clear();
////                url="http://op.juhe.cn/onebox/news/query?key=d35e208913973840ae60ef67a6ed5981&q=奇瑞";
////                getDate(url);
////                initListView();
////                break;
////            case R.id.rl_fixCars:
////                list.clear();
////                url="http://op.juhe.cn/onebox/news/query?key=d35e208913973840ae60ef67a6ed5981&q=大众宝马";
////                getDate(url);
////                initListView();
////                break;
////            case R.id.rl_fixBus:
////                list.clear();
////                url="http://op.juhe.cn/onebox/news/query?key=d35e208913973840ae60ef67a6ed5981&q=说车";
////                getDate(url);
////                initListView();
////                break;
////
////        }
//   }
}
