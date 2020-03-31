package com.cubesquare.herramientas;

public class Beans {

    public static int numeroAleatorio(int rango,int numeroIni){
        return (int)(Math.random()*rango+numeroIni);
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
}
