package uce.edu.ec.appdownloadimageg03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebImageView extends ImageView {
    private Drawable mPlaceholder, mImage;
    public WebImageView(Context context) {
        this(context, null);
    }
    public WebImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public WebImageView(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setPlaceholderImage(Drawable drawable) {
        mPlaceholder = drawable;
        if(mImage == null) {
            setImageDrawable(mPlaceholder);
        }
    }
    public void setPlaceholderImage(int resid) {
        mPlaceholder = getResources().getDrawable(resid);
        if(mImage == null) {
            setImageDrawable(mPlaceholder);
        }
    }
    public void setImageUrl(String url) {
        DownloadTask task = new DownloadTask();
        task.execute(url);
    }
    private class DownloadTask extends
            AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            try {
                URLConnection connection =
                        (new URL(url)).openConnection();
                InputStream is = connection.getInputStream();
                BufferedInputStream bis =
                        new BufferedInputStream(is);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                //ByteArrayBuffer baf = new ByteArrayBuffer(50);
                byte[] data = new byte[50];
                int current = 0;
                //while ((current = bis.read()) != -1) {
                while((current=bis.read(data,0,data.length))!=-1){
                    buffer.write(data,0,current);
                    //baf.append((byte)current);
                }
                byte[] imageData = buffer.toByteArray();
                return BitmapFactory.decodeByteArray(imageData, 0,
                        imageData.length);
            } catch (Exception exc) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            mImage = new BitmapDrawable(getContext().getResources(), result);
            if(mImage != null) {
                setImageDrawable(mImage);
            }
        }
    };
}


