package com.emedinaa.belatrix.desafio2.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by emedinaa on 28/08/15.
 */
public class RestClient {

    private static final String TAG ="RestClient" ;
    private static RestClient instance = null;

    private Context context;
    private  Drawable drawable;

    protected RestClient(Context context) {
        this.context= context;
    }

    public static RestClient getInstance(Context context) {
        if(instance == null) {
            instance = new RestClient(context);
        }
        return instance;
    }
    public Drawable getImageFromServer(String imageURL)
    {
        drawable= new ShapeDrawable();
        RequestQueue queue = Volley.newRequestQueue(context);

        ImageRequest imageRequest = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.d(TAG, "onResponse bitmap " + bitmap);
                if(bitmap!=null)
                {
                    try
                    {
                        //imageView.setImageBitmap(bitmap);
                        drawable= new BitmapDrawable(bitmap);
                    }catch (Exception e)
                    {
                        Log.d(TAG, "exception "+e);
                    }
                }
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError!=null)
                {
                    Log.d(TAG, "onErrorResponse "+volleyError.getMessage());
                }else
                {
                    Log.d(TAG, "onErrorResponse "+volleyError);
                }
            }
        });

        imageRequest.setTag("ImageRequest");
        queue.add(imageRequest);
        return drawable;
    };

    public void getImageFromServerOk(String imageURL,final ImageView imageView)
    {
        drawable= new ShapeDrawable();
        RequestQueue queue = Volley.newRequestQueue(context);

        ImageRequest imageRequest = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.d(TAG, "onResponse bitmap " + bitmap);
                if(bitmap!=null)
                {
                    try
                    {
                        //imageView.setImageBitmap(bitmap);
                        drawable= new BitmapDrawable(bitmap);
                        imageView.setImageDrawable(drawable);
                    }catch (Exception e)
                    {
                        Log.d(TAG, "exception "+e);
                    }
                }
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError!=null)
                {
                    Log.d(TAG, "onErrorResponse "+volleyError.getMessage());
                }else
                {
                    Log.d(TAG, "onErrorResponse "+volleyError);
                }
                imageView.setImageDrawable(new ShapeDrawable());
            }
        });

        imageRequest.setTag("ImageRequest");
        queue.add(imageRequest);
    };

    public void getImageFromServerProgress(String imageURL,final ImageView imageView,final OnImageRequest onImageRequest)
    {
        drawable= new ShapeDrawable();
        RequestQueue queue = Volley.newRequestQueue(context);

        ImageRequest imageRequest = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                Log.d(TAG, "onResponse bitmap " + bitmap);
                if(bitmap!=null)
                {
                    try
                    {
                        //imageView.setImageBitmap(bitmap);
                        drawable= new BitmapDrawable(bitmap);
                        imageView.setImageDrawable(drawable);
                        onImageRequest.imageSuccess();
                    }catch (Exception e)
                    {
                        Log.d(TAG, "exception "+e);
                        onImageRequest.imageError();
                    }
                }else
                {
                    onImageRequest.imageError();
                }
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError!=null)
                {
                    Log.d(TAG, "onErrorResponse "+volleyError.getMessage());
                }else
                {
                    Log.d(TAG, "onErrorResponse "+volleyError);
                }
                imageView.setImageDrawable(new ShapeDrawable());
                onImageRequest.imageError();
            }
        });

        imageRequest.setTag("ImageRequest");
        queue.add(imageRequest);
    };
}
