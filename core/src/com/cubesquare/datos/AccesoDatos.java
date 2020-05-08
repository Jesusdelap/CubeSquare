package com.cubesquare.datos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.HttpHerramientas;
import com.cubesquare.herramientas.HttpListener;
import com.cubesquare.modelo.Record;
import com.cubesquare.modelo.Usuario;

import java.util.ArrayList;

public class AccesoDatos {
    Net.HttpResponseListener httpListener = new HttpListener();

    private void esperarRespuesta(){
        HttpListener h = (HttpListener) httpListener;
        while(!h.isTerminada()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Gdx.app.log("AccesoDatos","Carga Terminada");
    }

    public boolean ping(){
        HttpListener h = (HttpListener) httpListener;
        try {
            HttpHerramientas.Connexion("ping",httpListener);
            esperarRespuesta();
            Gdx.app.log("AccesoDatos", "Pingeando");
            Gdx.app.log("AccesoDatos",h.getHttpResponse().getResultAsString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public ArrayList<Record> listarRecordsConAlias(int limit){
        HttpListener h = (HttpListener) httpListener;
        ArrayList<Record> recordArrayList = new ArrayList<Record>();
        HttpHerramientas.Connexion("Record/listarRecords?limit="+limit,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos",h.resultado);


        return Beans.jsonToRecordArrayList(h.resultado);
    }

    public void nuevoUsuario(Usuario u){
        HttpListener h = (HttpListener) httpListener;
        String url= "Usuario/add?idusuario=1&alias="+u.getAlias()+"&nombreUsuario="+u.getNombreUsuario()+"&contrasena="+u.getContrasena();

        //
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/NuevoUsuario","UsuarioAñadido" );

    }
    public Usuario logIn(String nombreUsuario,String contraseña){
        HttpListener h = (HttpListener) httpListener;
        String url= "Usuario/login?nombreUsuario="+nombreUsuario+"&contrasena="+contraseña;

        //
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/NuevoUsuario","UsuarioAñadido" );

        return null;
    }


}

