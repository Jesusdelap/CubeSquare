package com.cubesquare.herramientas;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Fabricas;

import java.util.ArrayList;

public class GeneradorMapas {
    private Vector2 posicionRelatiba;
    private World mundo;
    private AssetManager manager;

    public GeneradorMapas(Vector2 posicionRelatiba, World mundo, AssetManager manager) {
        this.posicionRelatiba = posicionRelatiba;
        this.mundo = mundo;
        this.manager = manager;
    }
    public ArrayList<Actor> mapaAleatorio(int numeroObstaculos){
        ArrayList<Actor> mapa = new ArrayList<Actor>();

        mapa.add(Fabricas.sueloFactory(mundo));
        addObstaculosAleatorios(  mapa ,numeroObstaculos);
        return mapa;
    }

    public ArrayList<Actor> mapaFacil(){
        ArrayList<Actor> mapa = new ArrayList<Actor>();

        // -------------FONDO
        //Image fondo = new Image(manager.get("fondoestrella1.png", Texture.class));
        //mapa.add(fondo);
        //Posicion inical recomendada (17,3)

        //creamos suelo base

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 1000, 2,new Vector2(0,0)));
        //creamos primera fase del nivel, Crescendo

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 20.5f, 3,new Vector2(0, posicionRelatiba.y-3)));
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+10.5f;

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12, 1,new Vector2(posicionRelatiba.x, posicionRelatiba.y-1)));
        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 9, 1,posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+3f;
        posicionRelatiba.y++;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 6, 1,posicionRelatiba));
        posicionRelatiba.y++;
        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3, 1,posicionRelatiba));


        // pincho decorativo
        posicionRelatiba.y = posicionRelatiba.y-3;
        posicionRelatiba.x= posicionRelatiba.x+3.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));

        //Dos pinchos cerca
        posicionRelatiba.x= posicionRelatiba.x+8.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+4f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));

        //Suelo+pincho al final
        posicionRelatiba.x= posicionRelatiba.x+4.5f;
        sueloPinchoFinal(mapa);

        // pincho+ subida de suelo
         posicionRelatiba.x= posicionRelatiba.x+8f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+0.5f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1,posicionRelatiba));
        posicionRelatiba.y++;
        posicionRelatiba.x= posicionRelatiba.x+2.3f;

        //Doble Pincho
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+0.7f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));

        //secuencia
        posicionRelatiba.x= posicionRelatiba.x+7f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));

        //Escalon abajo doble y dos pinchos
        posicionRelatiba.y--;
        posicionRelatiba.x= posicionRelatiba.x+8f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1,posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+3f;
        posicionRelatiba.y++;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+3.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x++;

        // Subida y pincho a un metro
        posicionRelatiba.y--;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 11f, 2,posicionRelatiba));
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1,posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+2f;
        posicionRelatiba.y=posicionRelatiba.y+2;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.x= posicionRelatiba.x+5.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.y= posicionRelatiba.y-2f;
        posicionRelatiba.x= posicionRelatiba.x+10f;
        addObstaculosAleatorios( mapa , 50);


        return mapa;
    }




    public ArrayList<Actor> mapavacio(){
        ArrayList<Actor> mapa = new ArrayList<Actor>();
        mapa.add(Fabricas.sueloFactory(mundo));
        return mapa;
    }


    //Metodos para Ayudar a la creacion de niveles

    public void sueloPinchoFinal( ArrayList<Actor> mapa ){

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3.5f, 1,posicionRelatiba));
        posicionRelatiba.y++;
        posicionRelatiba.x= posicionRelatiba.x+3f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
        posicionRelatiba.y--;
    }




    public void addObstaculosAleatorios( ArrayList<Actor> mapa , int numeroObstaculos){

        int incremento_X;
        posicionRelatiba.x = posicionRelatiba.x + Beans.numeroAleatorio(3,2);
        for (int i=0; i<=numeroObstaculos; i++){
            if (Beans.numeroAleatorio(10,0)>=2){
                incremento_X=Beans.numeroAleatorio(2,3);
                mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelatiba));
            }else if(Beans.numeroAleatorio(10,0)==1){
                sueloPinchoFinal(mapa);
                incremento_X=4;
            }else {
                incremento_X = Beans.numeroAleatorio(3,5);
                mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class),incremento_X,1, posicionRelatiba));
                posicionRelatiba.x=posicionRelatiba.x+3;
            }

            posicionRelatiba.x = posicionRelatiba.x +incremento_X+Beans.numeroAleatorio(4,2);
        }

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
