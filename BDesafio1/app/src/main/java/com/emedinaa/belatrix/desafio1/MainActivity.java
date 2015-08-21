package com.emedinaa.belatrix.desafio1;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {

    private final String WEB_SEVICE_URL = "http://developer.android.com/index.html";
    private final int DEFAULT_DELAY=1000;

    @Bind(R.id.btnDesafio)View btnDesafio;
    @Bind(R.id.tviDesafio)TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        events();
    }

    private void events() {
        btnDesafio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRestService();
            }
        });
    }
    private void callRestService()
    {
        AsyncTask call = new AsyncTask<Object,Integer,String>() {
            @Override
            protected String doInBackground(Object... params) {
                /*
                textView.setText("");
                RestClient client= new RestClient();
                String result = client.executeRequest(WEB_SEVICE_URL);
                textView.setText("Web Service call ended successfully");*/
                textView.setText("");git sgit
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(DEFAULT_DELAY);
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    }
                }
                String result="Web Service call ended successfully";
                textView.setText(result);
                return result;
            }
        };
        call.execute();
    }
    private void callRestServiceOk()
    {
        AsyncTask call = new AsyncTask<Object,Integer,String>() {
            @Override
            protected String doInBackground(Object... params) {
                /*RestClient client= new RestClient();
                String result = client.executeRequest(WEB_SEVICE_URL);
                textView.setText("Web Service call ended successfully");*/

                return "";
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textView.setText("");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                textView.setText("Web Service call ended successfully");
            }
        };
        call.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }
}
