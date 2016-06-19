package com.niuben.mycar.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.niuben.mycar.Activitys.NewsTextActivity;
import com.niuben.mycar.Bean.CarNewsBean;
import com.niuben.mycar.Bean.CarNewsTotalBean;
import com.niuben.mycar.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * ViewPagerʵ�ֵ��ֲ�ͼ����Զ�����ͼ���義����ҳ�Ĺ���ֲ�ͼЧ����
 * ��֧���Զ��ֲ�ҳ��Ҳ֧�����ƻ����л�ҳ��
 * 
 *
 */

public class SlideShowView extends FrameLayout {
	
	// ʹ��universal-image-loader�����ȡ����ͼƬ����Ҫ���̵���universal-image-loader-1.8.6-with-sources.jar
	private ImageLoader imageLoader = ImageLoader.getInstance();

    //�ֲ�ͼͼƬ����
    private final static int IMAGE_COUNT = 8;
    //�Զ��ֲ���ʱ����
    private final static int TIME_INTERVAL = 5;
    //�Զ��ֲ����ÿ���
    private final static boolean isAutoPlay = true; 
    
    //�Զ����ֲ�ͼ����Դ
    private String[] imageUrls;
    //���ֲ�ͼƬ��ImageView ��list
    private List<View> imageViewsList;
    //��Բ���View��list
    private List<View> dotViewsList;
    
    private ViewPager viewPager;
    //��ǰ�ֲ�ҳ
    private int currentItem  = 0;
    //��ʱ����
    private ScheduledExecutorService scheduledExecutorService;
    private List<CarNewsBean> imagelist=new ArrayList<>();
    private Context context;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
        
    };
    
    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

		initImageLoader(context);
		
        initData();
        if(isAutoPlay){
            startPlay();
        }
        
    }
    /**
     * ��ʼ�ֲ�ͼ�л�
     */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }
    /**
     * ֹͣ�ֲ�ͼ�л�
     */
    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }
    /**
     * ��ʼ�����Data
     */
    private void initData(){
        imageViewsList = new ArrayList<>();
        dotViewsList = new ArrayList<>();
//        Ion.with(context).load("http://c.m.163.com/nc/article/list/T1348654060988/15-20.html")
//                         .as(new TypeToken<CarMaintainNewsBean>(){})
//                         .setCallback(new FutureCallback<CarMaintainNewsBean>() {
//                             @Override
//                             public void onCompleted(Exception e, CarMaintainNewsBean result) {
//                                 if(result!=null){
//                                     for(int i=0;i<result.getResult().size();i++){
//                                         CarMaintainBean carMaintainBean=new CarMaintainBean();
//                                         CarMaintainNewsBean.ResultBean resultBean=result.getResult().get(i);
//                                         carMaintainBean.setTitle(resultBean.getTitle());
//                                         carMaintainBean.setImg(resultBean.getImg());
//                                         carMaintainBean.setUrl(resultBean.getUrl());
//                                         imagelist.add(carMaintainBean);
//
//                                     }
//                                     initUI(context);
//                                 }
//                             }
//                         });
        Ion.with(context).load("http://c.m.163.com/nc/article/list/T1348654060988/15-20.html")
                         .as(new TypeToken<CarNewsTotalBean>(){}).setCallback(new FutureCallback<CarNewsTotalBean>() {
            @Override
            public void onCompleted(Exception e, CarNewsTotalBean result) {
                if(result!=null){
                    for (int i=1;i<10;i++){
                        CarNewsBean carNewsBean=new CarNewsBean();
                        carNewsBean.setTitle(result.getT1348654060988().get(i).getTitle());
                        carNewsBean.setImgsrc(result.getT1348654060988().get(i).getImgsrc());
                        carNewsBean.setUrl(result.getT1348654060988().get(i).getUrl());
                          imagelist.add(carNewsBean);
                    }
                    initUI(context);
                }

            }
        });

        // һ�������ȡͼƬ
//        new GetListTask().execute("");
    }
    /**
     * ��ʼ��Views��UI
     */
    private void initUI(Context context){
        if(imagelist == null ||imagelist.size() == 0)
            return;

        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);

        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        for (int i = 0; i < imagelist.size(); i++) {
            View view2 = View.inflate(context, R.layout.rotation_item, null);
            imageViewsList.add(view2);
            ImageView dotView =  new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotLayout.addView(dotView, params);
            dotViewsList.add(dotView);
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    
    /**
     * ���ViewPager��ҳ��������
     * 
     */
    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, final int position) {
//        	ImageView imageView = imageViewsList.get(position);
//
//			imageLoader.displayImage(imageView.getTag() + "", imageView);
//
//            ((ViewPager)container).addView(imageViewsList.get(position));
//            return imageViewsList.get(position);
            View view = imageViewsList.get(position);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            ImageView iv_carousel = (ImageView) view.findViewById(R.id.iv_carousel);
            tv_title.setText(imagelist.get(position).getTitle());
//            Log.e("ppp", imagelist.get(position).getTitle());


            Ion.with(context).load(imagelist.get(position).getImgsrc()).intoImageView(iv_carousel);


            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CarNewsBean carNewsBean = imagelist.get(position);
                    String url = carNewsBean.getUrl();
                    if(url.equals("null")){
                        Toast.makeText(context, "抱歉，该条资讯暂不支持阅读，再淘一条吧。", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent intent = new Intent(context, NewsTextActivity.class);
                        intent.putExtra("URL", url);
                        context.startActivity(intent);
                    }

                }
            });
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }
    /**
     * ViewPager�ļ�����
     * ��ViewPager��ҳ���״̬�����ı�ʱ����
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:// ���ƻ�����������
                isAutoPlay = false;
                break;
            case 2:// �����л���
                isAutoPlay = true;
                break;
            case 0:// �������������л���ϻ��߼������
                // ��ǰΪ���һ�ţ���ʱ�������󻬣����л�����һ��
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // ��ǰΪ��һ�ţ���ʱ�������һ������л������һ��
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            
            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.mipmap.roll_yuan);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.mipmap.roll_yuan1);
                }
            }
        }
        
    }
    
    /**
     *ִ���ֲ�ͼ�л�����
     *
     */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    
    /**
     * ����ImageView��Դ�������ڴ�
     * 
     */

 

	/**
	 * �첽����,��ȡ����
	 * 
	 */
//	class GetListTask extends AsyncTask<String, Integer, Boolean> {
//
//		@Override
//		protected Boolean doInBackground(String... params) {
//			try {
//				// ����һ����÷���˽ӿڻ�ȡһ���ֲ�ͼƬ�������ǴӰٶ��ҵļ���ͼƬ
//
//				imageUrls = new String[]{
//						"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=汽车保养&step_word=&pn=0&spn=0&di=88946290290&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3744095738%2C474627834&os=3122644319%2C2320668397&simid=0%2C0&adpicid=0&ln=1000&fr=&fmq=1462956241118_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fi6.qhimg.com%2Ft01e4f830ab0b6f50fe.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fkwthj_z%26e3Bf5_z%26e3Bv54AzdH3F15vAzdH3F9mdb0ma-9b989da_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0",
//						"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=汽车保养&step_word=&pn=32&spn=0&di=45581443050&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1321636154%2C2779107596&os=476482853%2C1382338690&simid=0%2C0&adpicid=0&ln=1000&fr=&fmq=1462956241118_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg3.douban.com%2Fview%2Fnote%2Flarge%2Fpublic%2Fp30427694.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B157kwg_z%26e3Bv54AzdH3Fg5pjAzdH3Fcd0cna8mdAzdH3F%3Fpyrj%3Dsthj&gsm=0&rpstart=0&rpnum=0",
//						"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=汽车保养&step_word=&pn=60&spn=0&di=38673551620&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1601390005%2C172656313&os=2737015120%2C2018914656&simid=4086045348%2C529771213&adpicid=0&ln=1000&fr=&fmq=1462956241118_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fhenan.sinaimg.cn%2F2015%2F0304%2FU11803P827DT20150304113102.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fijgwg_z%26e3Bftgw_z%26e3Bv54_z%26e3BvgAzdH3Fqtvijy5g2rtgAzdH3Fywg2i7-s7gpwtAzdH3Fda8c-an-a9AzdH3Fb0l-dcbl_z%26e3Bip4s&gsm=1e&rpstart=0&rpnum=0",
//						"http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=汽车保养&step_word=&pn=37&spn=0&di=101155184130&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3938553341%2C2387353829&os=435621201%2C1175074370&simid=4191090243%2C770013658&adpicid=0&ln=1000&fr=&fmq=1462956241118_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg2.bitauto.com%2Fautoalbum%2Ffiles%2F20131225%2F500%2F14095650052862_3073074_3.JPG&fromurl=ippr_z2C%24qAzdH3FAzdH3F4r_z%26e3Bojtxtg_z%26e3Bqq_z%26e3Bv54AzdH3Ff%3F__ktz%3DMzAcMDA8NDUzOQ%3D%3D%264t1%3Ddaa8b8b8c%26t1x%3D8%26fg%3Dcvu0n9wvuw8madddwublulu8b0ubjwk9&gsm=0&rpstart=0&rpnum=0"
//				};
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//
//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			if (result) {
//		        initUI(context);
//			}
//		}
//	}
	
	/**
	 * ImageLoader ͼƬ�����ʼ��
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																																																																								// for
																																																																								// release
																																																																								// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}