package com.cubesquare.herramientas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class HttpListener implements Net.HttpResponseListener {

    public String resultado;
    private String estado = null;
    private Net.HttpResponse httpResponse;
    private boolean terminada =false;

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        terminada=false;
        resultado = httpResponse.getResultAsString();
        estado = "exitosa";
        Gdx.app.log("HTTP",estado);
        terminada=true;
    }

    @Override
    public void failed(Throwable t) {
        terminada=true;
        estado = "fallida";
        Gdx.app.error("HTTP",estado);

    }

    @Override
    public void cancelled() {
        terminada=true;
        estado = "cancelada";
        Gdx.app.error("HTTP",estado);

    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Net.HttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(Net.HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public boolean isTerminada() {
        return terminada;
    }

    public void setTerminada(boolean terminada) {
        this.terminada = terminada;
    }
}
