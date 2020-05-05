package com.cubesquare.datos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.cubesquare.herramientas.HttpHerramientas;
import com.cubesquare.herramientas.HttpListener;
import com.cubesquare.modelo.Record;
import com.cubesquare.modelo.Usuario;

import java.util.ArrayList;

public class AccesoDatos {

    Net.HttpResponseListener httpListener = new HttpListener();

    private void esperarAntesDeTerminar(){
        HttpListener h = (HttpListener) httpListener;
        while(!h.isTerminada()){
            System.out.println("--");
        }
        Gdx.app.log("AccesoDatos","Carga Terminada");
    }

    public boolean ping(){
        HttpListener h = (HttpListener) httpListener;
        try {
            HttpHerramientas.Connexion("http://localhost:8080/ping",httpListener);
            esperarAntesDeTerminar();
            Gdx.app.log("AccesoDatos", "Pingeando");
            Gdx.app.log("AccesoDatos",h.getHttpResponse().getResultAsString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public ArrayList<Record> listarRecordsConAlias(){
        HttpListener h = (HttpListener) httpListener;
        ArrayList<Record> recordArrayList = new ArrayList<Record>();
        HttpHerramientas.Connexion("http://localhost:8080/Record/listarRecords",httpListener);
        esperarAntesDeTerminar();

        Json json = new Json();
        Usuario usuario = json.fromJson(Usuario.class, h.getHttpResponse().getResultAsStream());
        System.out.println(usuario.toString());

        try {
            Gdx.app.log("AccesoDatos",h.getHttpResponse().getResultAsString());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void nuevoUsuario(Usuario u){
        HttpListener h = (HttpListener) httpListener;
        String url= "http://localhost:8080/Usuario/add?idusuario=2&alias=perico&nombreUsuario=periaserez2&contrasena=123";

            // "http://localhost:8080/Usuario/add?idusuario=1&alias="+u.getAlias()+"&nombreUsuario="+u.getNombreUsuario()+"&contrasena="+u.getContrasena()
            HttpHerramientas.Connexion(url,httpListener);
            esperarAntesDeTerminar();
            Gdx.app.log("AccesoDatos", "NuevoUsuario");
            Gdx.app.log("AccesoDatos",h.getHttpResponse().getResultAsString());
    }
    public void JsonToRecord(String json){



    }








}
