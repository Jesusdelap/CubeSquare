package com.cubesquare.herramientas;

import com.badlogic.gdx.Gdx;
import com.cubesquare.modelo.Record;
import com.cubesquare.modelo.Usuario;

import java.util.ArrayList;

public class Beans {

    public static int numeroAleatorio(int rango,int numeroIni){
        return (int)(Math.random()*rango+numeroIni);
    }
    public static int parseint(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (Exception e) {
            Gdx.app.error("Beans/ParseInt", "Error en el parseo");
            return -1;
        }
    }
    public static int truncarNumeros(float numero){
        try{
            return (int) Math.floor(numero);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static float pxToMetters_X(float px){
        return px/Constantes.PIXELS_IN_METER_X;
    }
    public static float pxToMetters_Y(float px){
        return px/Constantes.PIXELS_IN_METER_Y;
    }
    public static float mettersToPx_X(float metros){
        return metros*Constantes.PIXELS_IN_METER_Y;
    }
    public static float mettersToPx_Y(float metros){
        return metros*Constantes.PIXELS_IN_METER_Y;
    }
    public static boolean stringtoBolean(String s){
        if (s.trim().equals("true")){
            return true;
        }else{
            return false;
        }
    }
    public static Record jsonToRecord(String json){

        String trocitos[] = json.split(":");

        String idRec[] = trocitos[1].split(",");
        int idRecord = parseint(idRec[0].trim());

        String idUsu[] = trocitos[2].split(",");
        int idUsuario = parseint(idUsu[0].trim());

        String distR[] = trocitos[3].split(",");
        int distanciaRecorrida = parseint(idUsu[0].trim());

        String ali[] = trocitos[4].split("}");
        String alias = ali[0].replace("\""," ").trim();

        return new Record(idUsuario,idRecord,distanciaRecorrida,alias);
    }
    public static ArrayList<Record> jsonToRecordArrayList(String json){
        ArrayList<Record> recordArrayList = null;

        String stringarr[] =json.split("\\{");
        for (String s:stringarr){
            jsonToRecord(s);

        }


        return recordArrayList;
    }

    public static Usuario jsonToUsuario(String json){

        String trocitos[] = json.split(":");

        String idUsu[] = trocitos[1].split(",");
        int idusuario = parseint(idUsu[0].trim());

        String ali[] = trocitos[2].split(",");
        String alias = ali[0].replace("\""," ").trim();

        String nombreU[] = trocitos[3].split(",");
        String nombreUsuario = nombreU[0].replace("\""," ").trim();

        String cont[] = trocitos[4].split("}");
        String contrasena = cont[0].replace("\""," ").trim();

        return new Usuario(idusuario,alias,nombreUsuario,contrasena);
    }


}