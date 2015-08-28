package com.emedinaa.belatrix.desafio2;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emedinaa.belatrix.desafio2.request.OnImageRequest;
import com.emedinaa.belatrix.desafio2.request.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
    Desafío 2

    ¿Cual es el resultado al finalizar la ejecución del metodo onCreate? Argumente.

    Aclaraciones:

    - initializeUiComponents() inicializa las variables de instancia mProgressBar y mImageView
    - el metodo getImageFromServer() devuelve un objeto del tipo Drawable, no nulo.
    - el dispositivo dispone de una conexión a internet estable.

 */
public class MainActivity extends ActionBarActivity {

    private final String IMAGE_URL = "http://www.belatrixsf.com/images/logo.png";

    @Bind(R.id.mImageView) ImageView mImageView;
    @Bind(R.id.mProgressBar)ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUIComponents();
        /*mProgressBar.setVisibility(View.VISIBLE);
        Drawable drawable= RestClient.getInstance(this).getImageFromServer(IMAGE_URL);
        mImageView.setImageDrawable(drawable);
        mProgressBar.setVisibility(View.GONE);*/
        //loadImage();
        //loadImageWithoutProgress();
        loadImageProgress();

        /*
        Nombre: Eduardo José Medina Alfaro
        Email: emedinaa@gmail.com

        Respuesta
            1. Se necesita tener habilitado el permiso de internet     <uses-permission android:name="android.permission.INTERNET" />
            2. Si el método  "getImageFromServer" es asíncrono el resultado sería que no se muestra el progressbar y cuando termine de cargar tampoco se  muestra la imágen.
            3. Si el método  "getImageFromServer" no es asíncrono, la pantalla queda congelada hasta que termine de cargar la imágen. Esto no es recomendable.
         */
    }

    private void loadImage() {
        mProgressBar.setVisibility(View.VISIBLE);
        Drawable drawable= RestClient.getInstance(this).getImageFromServer(IMAGE_URL);
        mImageView.setImageDrawable(drawable);
        mProgressBar.setVisibility(View.GONE);
    }

    /*
     Con este método se lográ mostrar la imagen cargada , enviando como parámetro adicional el ImageView sin el Progress
     */
    private void loadImageWithoutProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        RestClient.getInstance(this).getImageFromServerOk(IMAGE_URL, mImageView);
        mProgressBar.setVisibility(View.GONE);
    }

    /*
     Con este método se lográ mostrar la imagen cargada , enviando como parámetro adicional el ImageView y un listener para que escuche cuando termine este proceso.
     Adicional muestra el Progress al inicio y desaparece cuando termina de cargar
     */
    private void loadImageProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        RestClient.getInstance(this).getImageFromServerProgress(IMAGE_URL, mImageView,onImageRequest);
    }

    private OnImageRequest onImageRequest= new OnImageRequest() {
        @Override
        public void imageSuccess() {
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void imageError() {
            mProgressBar.setVisibility(View.GONE);
        }
    };

    private void initializeUIComponents() {
        ButterKnife.bind(this);
    }

    private void events() {

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
