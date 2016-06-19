package com.niuben.mycar.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.niuben.mycar.R;

/**
 * Created by niuben on 2016/5/11.
 */
public class NewsTextActivity extends BaseActivity {
    private WebView wv_NewsTxt;
    private ProgressDialog dialog ;
    private String content;
    private Intent intent;
    private ImageView iv_back;
    @Override
    protected void setContent() {
        setContentView(R.layout.news_text);
        intent=getIntent();
        content=intent.getStringExtra("URL");

    }

    @Override
    protected void findView() {
        wv_NewsTxt= (WebView) findViewById(R.id.wv_NewsTxt);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        //wv_NewsTxt.loadDataWithBaseURL("",getHtml(content),"text/html","UTF-8","");

       // wv_NewsTxt.loadUrl(content);

    }

    @Override
    protected void initView() {
        wv_NewsTxt.loadUrl(content);
        wv_NewsTxt.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog = ProgressDialog.show(NewsTextActivity.this, null, "页面加载中，请稍后..");
            }


            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                dialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });





    }

//    private void loadUrl(String url) {
//
//           wv_NewsTxt.loadUrl(url);
//        dialog=ProgressDialog.show(this,null,"页面加载中，请稍后..");
//           // wv_NewsTxt.reload();
//
//
//    }

    @Override
    protected void setListener() {
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setData() {

    }

    @Override
    public void onClick(View v) {
        finish();

    }
















    private static String getHtml( String content) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("<h2 class=\"thetitle\">");
        sb.append("<a>");
        sb.append("</a>");
        sb.append("</h2>");
        sb.append("</div>");
        sb.append("<div class=\"entry\">");
        sb.append(content);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}
