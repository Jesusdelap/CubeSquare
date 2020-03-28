package com.cubesquare.herramientas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.cubesquare.modelo.entidades.ActorSuelo;

public class Fabricas {


    public static ActorSuelo sueloFactory(World world, Texture floor, float x, float width, float y){
        return new ActorSuelo(world, floor, width, new Vector2(x,y));
    }
    public static ActorSuelo sueloFactory(World world){
        return new ActorSuelo(world,new Texture("floor.png") , 1000, new Vector2(0,1));
    }
}
