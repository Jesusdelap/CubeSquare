package com.cubesquare.herramientas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class HttpHerramientas {

    public static void Connexion(String url,Net.HttpResponseListener lisener ){
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://localhost:8080/"+url);
        Gdx.net.sendHttpRequest(request, lisener);
    }

    public static void ConnexionUrlCompleta(String url,Net.HttpResponseListener lisener ){
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl(url);
        Gdx.net.sendHttpRequest(request, lisener);
    }

    public static void abrirUrl(String url){
        Gdx.net.openURI(url);
    }


}
