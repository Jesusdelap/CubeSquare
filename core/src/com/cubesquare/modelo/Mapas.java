package com.cubesquare.modelo;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Fabricas;

import java.util.ArrayList;

public class Mapas {
    private Vector2 posicionRelatiba;
    private World mundo;
    private AssetManager manager;

    public Mapas(Vector2 posicionRelatiba, World mundo, AssetManager manager) {
        this.posicionRelatiba = posicionRelatiba;
        this.mundo = mundo;
        this.manager = manager;
    }
    public ArrayList<Actor> mapaAleatorio(int numeroObstaculos){
        ArrayList<Actor> mapa = new ArrayList<Actor>();
        int incremento_X;
        mapa.add(Fabricas.sueloFactory(mundo));
        posicionRelatiba.x = posicionRelatiba.x + Beans.numeroAleatorio(6,6);
        for (int i=0; i<=numeroObstaculos; i++){
            if (Beans.numeroAleatorio(10,0)>=2){
                mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
                incremento_X=Beans.numeroAleatorio(2,3);
            }else{
                incremento_X = Beans.numeroAleatorio(3,2);
                mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class),incremento_X,1, posicionRelatiba));
            }
            posicionRelatiba.x = posicionRelatiba.x +incremento_X+Beans.numeroAleatorio(4,2);

        }
        return mapa;
    }

    public ArrayList<Actor> mapaFacil(){

        //Posicion inical recomendada (17,3)
        ArrayList<Actor> mapa = new ArrayList<Actor>();
        //creamos suelo base
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 1000, 2,new Vector2(0,0)));
        //creamos primera fase del nivel, Crescendo

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 20.5f, 3,new Vector2(0, posicionRelatiba.y-3)));
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+10.5f;

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y-1)));
        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 9, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y)));
        posicionRelatiba.x= posicionRelatiba.x+3f;
        posicionRelatiba.y++;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 6, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y)));
        posicionRelatiba.y++;
        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y)));
        // rompe

        // pincho decorativo
        posicionRelatiba.y = posicionRelatiba.y-3;
        posicionRelatiba.x= posicionRelatiba.x+3.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        //Dos pinchos cerca
        posicionRelatiba.x= posicionRelatiba.x+8f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+4f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        //Suelo+pincho al final
        posicionRelatiba.x= posicionRelatiba.x+5f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3.5f, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y)));
        posicionRelatiba.y++;

        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        // pincho+ subida de suelo
         posicionRelatiba.x= posicionRelatiba.x+6f;
        posicionRelatiba.y--;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x++;
         mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 50, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y)));
         posicionRelatiba.y++;
          posicionRelatiba.x= posicionRelatiba.x+4.3f;
         //Doble Pincho
          mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
         posicionRelatiba.x= posicionRelatiba.x+0.7f;
         mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));

        return mapa;
    }

    public ArrayList<Actor> mapavacio(){
        ArrayList<Actor> mapa = new ArrayList<Actor>();
        mapa.add(Fabricas.sueloFactory(mundo));
        return mapa;
    }











    public Vector2 getPosicionRelatiba() {
        return posicionRelatiba;
    }

    public void setPosicionRelatiba(Vector2 posicionRelatiba) {
        this.posicionRelatiba = posicionRelatiba;
    }

    public World getMundo() {
        return mundo;
    }

    public void setMundo(World mundo) {
        this.mundo = mundo;
    }

    public AssetManager getManager() {
        return manager;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Mapas{" +
                "posicionInicial=" + posicionRelatiba +
                ", mundo=" + mundo +
                ", manager=" + manager +
                '}';
    }
}