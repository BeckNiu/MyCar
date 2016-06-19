package com.niuben.mycar.Activitys;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.niuben.mycar.Bean.UserBean;
import com.niuben.mycar.R;
import com.niuben.mycar.utils.FileUtil;

import java.io.File;

/**
 * Created by niuben on 2016/5/16.
 */
public class SingUpActivity extends Activity implements View.OnClickListener {
    private EditText et_user_name, user_pass, et_carname,et_cartime;
    private TextView tv_sign_up;
    private ImageView iv_head;
    private Window window;
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";
    /**
     * 使用照相机拍照获取图片
     */
    public static final int SELECT_PIC_BY_TACK_PHOTO = 0;
    /**
     * 使用相册中的图片
     */
    public static final int SELECT_PIC_BY_PICK_PHOTO = 1;
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记
    private String urlpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        findView();
    }

    private void findView() {
        et_user_name= (EditText) findViewById(R.id.et_userNAME);
        user_pass= (EditText) findViewById(R.id.et_userPass);
        et_carname= (EditText) findViewById(R.id.et_carName);
        et_cartime= (EditText) findViewById(R.id.et_carTime);
        tv_sign_up= (TextView) findViewById(R.id.tv_sign_up);
        iv_head= (ImageView) findViewById(R.id.iv_head);
        tv_sign_up.setOnClickListener(this);
        iv_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                if (!TextUtils.isEmpty(et_user_name.getText().toString().trim()) & !TextUtils.isEmpty(user_pass.getText().toString().trim()) & !TextUtils.isEmpty(et_carname.getText().toString().trim()) & !TextUtils.isEmpty(et_cartime.getText().toString().trim())&!TextUtils.isEmpty(urlpath)) {
                    UserBean user = new UserBean();
                    user.setUser_name(et_user_name.getText().toString().trim());
                    user.setUser_pass(user_pass.getText().toString().trim());
                    user.setCar_name(et_carname.getText().toString().trim());
                    user.setCar_time(et_cartime.getText().toString().trim());
                    user.setUser_image(urlpath);
                    user.save();
                    Toast.makeText(SingUpActivity.this, "注册成功,请您登录", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(SingUpActivity.this, "请您先补全信息再注册！", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.iv_head:
                showDialog();
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 点击取消按钮
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // 可以使用同一个方法，这里分开写为了防止以后扩展不同的需求
        switch (requestCode) {
            case SELECT_PIC_BY_PICK_PHOTO:// 如果是直接从相册获取
                startPhotoZoom(data.getData());
                break;
            case SELECT_PIC_BY_TACK_PHOTO:// 如果是调用相机拍照时
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            urlpath = FileUtil.saveFile(this, "sdcard/avatar/", System.currentTimeMillis() + "head.jpg", photo);
            Toast.makeText(this, urlpath, Toast.LENGTH_LONG).show();
            iv_head.setImageDrawable(drawable);
        }
    }
    private void showDialog() {
        View view = View.inflate(this, R.layout.photo_choose, null);
        Dialog dialog = new Dialog(this, R.style.dialog_style);
        dialog.setContentView(view);
        window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        setOptions(view, dialog);
    }

    private void setOptions(View view, final Dialog dialog) {
        view.findViewById(R.id.bt_photos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
                dialog.dismiss();
            }

            /**
             * 选择相册照片
             */
            private void choosePhoto() {
                Intent intent = new Intent();
                // 如果要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);

            }
        });
        view.findViewById(R.id.bt_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                dialog.dismiss();
            }

            /**
             * 拍摄照片
             */
            private void takePhoto() {
                Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //下面这句指定调用相机拍照后的照片存储的路径
                takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                startActivityForResult(takeIntent, SELECT_PIC_BY_TACK_PHOTO);
            }
        });
        view.findViewById(R.id.bt_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
