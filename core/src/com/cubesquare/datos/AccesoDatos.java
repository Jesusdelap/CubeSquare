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
    Net.HttpResponseListener httpListener2 = new HttpListener();

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
    private void esperarRespuesta2(){
        HttpListener h = (HttpListener) httpListener2;
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
        HttpListener h = (HttpListener) httpListener2;
            HttpHerramientas.Connexion("ping",httpListener2);
            esperarRespuesta2();
            String r = h.resultado;
            Gdx.app.log("AccesoDatos", "Pingeando");

            return Beans.stringtoBolean(r);
    }

    public void addRecord(Record record){
        HttpListener h = (HttpListener) httpListener;
        String url= "Record/add?idRecord="+record.getIdRecord()+"&idUsuario="+record.getIdUsuario()+"&distanciaRecorrida="+record.getDistanciaRecorrida();
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/AddRecord","url: "+url );

    }

    public ArrayList<Record> listarRecordsConAlias(int limit){
        HttpListener h = (HttpListener) httpListener;
        ArrayList<Record> recordArrayList = new ArrayList<Record>();

        HttpHerramientas.Connexion("Record/listarRecords?limit="+limit,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos", "Listar");
        Gdx.app.log("AccesoDatos", h.resultado);

        return Beans.jsonToRecordArrayList(h.resultado);
    }public void addUsuario(Usuario u){
        HttpListener h = (HttpListener) httpListener;
        String url= "Usuario/add?idusuario=1&alias="+u.getAlias()+"&nombreUsuario="+u.getNombreUsuario()+"&contrasena="+u.getContrasena();
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/AddUsuario", h.resultado);

    }
    public Usuario logIn(String nombreUsuario,String contraseña){
        HttpListener h = (HttpListener) httpListener;
        String url= "Usuario/login?nombreUsuario="+nombreUsuario+"&contrasena="+contraseña;
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/LogIn",h.resultado );

        return Beans.jsonToUsuario(h.resultado);
    }

    public boolean isUsuarioNombreLibre(String nombreUsuario){
        HttpListener h = (HttpListener) httpListener;
        String url= "Usuario/isnombrelibre?nombreUsuario="+nombreUsuario;

        //
        HttpHerramientas.Connexion(url,httpListener);
        esperarRespuesta();
        Gdx.app.log("AccesoDatos/NuevoUsuario","es libre: "+Beans.stringtoBolean(h.resultado) );

        return Beans.stringtoBolean(h.resultado);
    }


}

