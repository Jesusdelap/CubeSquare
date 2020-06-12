package com.cubesquare.datos;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.HttpParametersUtils;

import java.util.HashMap;
import java.util.Map;

public class ConectarMySQL extends ApplicationAdapter implements Net.HttpResponseListener {
    BitmapFont font;
    SpriteBatch batch;


    String url, mensaje="Registrando Puntaje.. espere";
    String httpMethod = Net.HttpMethods.GET;
    String solicitud_variables = null;
    Net.HttpRequest httpsolicitud;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();


        url = "localhost:3036/records";
        solicitud_variables = "&nombre=suscribete&puntaje=222";
        httpsolicitud = new Net.HttpRequest(httpMethod);
        httpsolicitud.setUrl(url);
        httpsolicitud.setContent(solicitud_variables);
        Gdx.net.sendHttpRequest(httpsolicitud, ConectarMySQL.this);

        final String username = "root";
        final String password = "";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("username", username);
        parameters.put("password", password);

        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.setHeader("Upgrade", "HTTP/1.1, HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11");
        httpRequest.setUrl(url);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        httpRequest.setTimeOut(6000);

    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);

        batch.begin();
        font.draw(batch, mensaje, 10, 50);
        batch.end();
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {

        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run () {
                mensaje= "HTTP Procesado con exito :D ";

            }
        });

    }


    @Override
    public void failed(Throwable t) {


    }

    @Override
    public void cancelled() {


    }
}