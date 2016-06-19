package com.niuben.mycar.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;


/**
 * �����������Ż����ܵİ�����
 *
 * @author�Ҹ�
 * @data 2013-12-14
 */
public class ViewHolder {
    /**
     * SparseArray��android��Ϊ<Interger,Object>������Hashmap��ר��д����,Ŀ�������Ч�ʣ���������۰���Һ���
     * ��binarySearch������Android�У���������Ҫ����
     * <p/>
     * 1 HashMap<Integer, E> hashMap = new HashMap<Integer, E>();
     * ʱ�����ǿ���ʹ�����µķ�ʽ��ȡ�ø��õ����ܡ�
     * <p/>
     * 1 SparseArray<E> sparseArray = new SparseArray<E>();
     */
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * �õ�һ��ViewHolder����
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * ͨ���ؼ���Id��ȡ���ڵĿؼ������û�������views
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * ΪTextView�����ַ���
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * ΪImageView����ͼƬ
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * ΪImageView����ͼƬ
     *
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * ΪImageView����ͼƬ
     *
     * @param viewId
     * @param
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        Ion.with((ImageView) getView(viewId))
                .load(url);
        return this;
    }

    public ViewHolder setImageByUrl2(int viewId, String url) {
        // ImageLoaderUtil.loaderImage(url, (ImageView) getView(viewId));
        return this;
    }

    public ViewHolder setCircleImage(int viewId, String url) {
        //  ImageLoaderUtil.loaderCircleImage(url, (ImageView) getView(viewId));
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setImageResource(int yin) {

    }
}
