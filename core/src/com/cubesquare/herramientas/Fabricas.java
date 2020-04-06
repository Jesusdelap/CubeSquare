package com.cubesquare.herramientas;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorPincho;
import com.cubesquare.modelo.entidades.ActorSuelo;

import java.util.ArrayList;

public class Fabricas {


    public static ActorSuelo sueloFactory(World world, Texture floor, float width,float h,Vector2 vector2){
        return new ActorSuelo(world, floor, width,h, vector2);
    }
    public static ActorSuelo sueloFactory(World world){
        return new ActorSuelo(world,new Texture("floor.png") , 1000,3, new Vector2(0,0));
    }

    public static ActorJugador ActorFactory(World world, Texture texture,Vector2 vector2){
        return new ActorJugador(world,texture, vector2);
    }
    public static ActorJugador ActorFactory(World world,Texture texture){
        return new ActorJugador(world, texture,new Vector2(3,3.5f));
    }
    public static ActorPincho pinchoFactory(World world, Texture texture, Vector2 vector2){
        return new ActorPincho(world, texture,vector2);
    }

    public static ArrayList<Actor> mapaFactory(int tipoDeJuego,GeneradorMapas generadorMapas){

        switch (tipoDeJuego){
            case 0:return generadorMapas.mapaAleatorio(100);
            case 1:return generadorMapas.mapaFacil();
            default: return generadorMapas.mapavacio();
        }

    }
}
