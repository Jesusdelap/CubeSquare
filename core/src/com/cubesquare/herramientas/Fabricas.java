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


    public static ActorSuelo sueloFactory(World world, Texture floor, float width,Vector2 vector2){
        return new ActorSuelo(world, floor, width, vector2);
    }
    public static ActorSuelo sueloFactory(World world){
        return new ActorSuelo(world,new Texture("floor.png") , 1000, new Vector2(0,1));
    }

    public static ActorJugador ActorFactory(World world, Texture texture,Vector2 vector2){
        return new ActorJugador(world,texture, vector2);
    }
    public static ActorJugador ActorFactory(World world,Texture texture){
        return new ActorJugador(world, texture,new Vector2(3,1.5f));
    }
    public static ActorPincho pinchoFactory(World world, Texture texture, Vector2 vector2){
        return new ActorPincho(world, texture,vector2);
    }

    public static ArrayList<Actor> mapaFactory(int numeroObstaculos, Vector2 posicionInicial,World world, AssetManager manager){
        ArrayList<Actor> mapa = new ArrayList<Actor>();

        mapa.add(Fabricas.sueloFactory(world));
        posicionInicial.y =posicionInicial.y +1;
        mapa.add(sueloFactory(world,manager.get("floor.png",Texture.class),4,posicionInicial));
        posicionInicial.x =posicionInicial.x +6;
        posicionInicial.y =posicionInicial.y -1;
        for (int i=0; i<=numeroObstaculos; i++){
            posicionInicial.x =posicionInicial.x + Beans.numeroAleatorio(12,6);
            mapa.add(pinchoFactory(world,manager.get("spike.png",Texture.class),posicionInicial));
        }
        return mapa;
    }

}
