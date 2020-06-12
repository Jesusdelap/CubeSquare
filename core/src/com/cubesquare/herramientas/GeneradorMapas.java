package com.cubesquare.herramientas;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

public class GeneradorMapas {
    private Vector2 posicionRelativa;
    private World mundo;
    private AssetManager manager;

    public GeneradorMapas(Vector2 posicionRelatiba, World mundo, AssetManager manager) {
        this.posicionRelativa = posicionRelatiba;
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

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 1000, 2,new Vector2(0,0)));
        //creamos primera fase del nivel, Crescendo

        mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class), 20.5f, 3,new Vector2(0, posicionRelativa.y-3)));
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("trianguloenfadado.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+10.5f;

        mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class), 12, 1,new Vector2(posicionRelativa.x, posicionRelativa.y-1)));
        posicionRelativa.x= posicionRelativa.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 9, 1, posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+3f;
        posicionRelativa.y++;
        mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class), 6, 1, posicionRelativa));
        posicionRelativa.y++;
        posicionRelativa.x= posicionRelativa.x+3f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3, 1, posicionRelativa));


        // pincho decorativo
        posicionRelativa.y = posicionRelativa.y-3;
        posicionRelativa.x= posicionRelativa.x+3.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));

        //Dos pinchos cerca
        posicionRelativa.x= posicionRelativa.x+8.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+4f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));

        //Suelo+pincho al final
        posicionRelativa.x= posicionRelativa.x+4.5f;
        sueloPinchoFinal(mapa);

        // pincho+ subida de suelo
         posicionRelativa.x= posicionRelativa.x+8f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+0.5f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1, posicionRelativa));
        posicionRelativa.y++;
        posicionRelativa.x= posicionRelativa.x+2.3f;

        //Doble Pincho
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+0.7f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));

        //secuencia
        posicionRelativa.x= posicionRelativa.x+7f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));

        //Escalon abajo doble y dos pinchos
        posicionRelativa.y--;
        posicionRelativa.x= posicionRelativa.x+8f;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1, posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+3f;
        posicionRelativa.y++;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+3.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x++;

        // Subida y pincho a un metro
        posicionRelativa.y--;
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 11f, 2, posicionRelativa));
        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 12.5f, 1, posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+2f;
        posicionRelativa.y= posicionRelativa.y+2;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.x= posicionRelativa.x+5.5f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.y= posicionRelativa.y-2f;
        posicionRelativa.x= posicionRelativa.x+10f;
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

        mapa.add(Fabricas.sueloFactory(mundo, manager.get("floor.png", Texture.class), 3.5f, 1, posicionRelativa));
        posicionRelativa.y++;
        posicionRelativa.x= posicionRelativa.x+3f;
        mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
        posicionRelativa.y--;
    }




    public void addObstaculosAleatorios( ArrayList<Actor> mapa , int numeroObstaculos){

        int incremento_X;
        posicionRelativa.x = posicionRelativa.x + Beans.numeroAleatorio(3,2);
        for (int i=0; i<=numeroObstaculos; i++){
            if (Beans.numeroAleatorio(10,0)>=2){
                incremento_X=Beans.numeroAleatorio(2,3);
                mapa.add(Fabricas.pinchoFactory(mundo,manager.get("spike.png",Texture.class), posicionRelativa));
            }else if(Beans.numeroAleatorio(10,0)==1){
                sueloPinchoFinal(mapa);
                incremento_X=4;
            }else {
                incremento_X = Beans.numeroAleatorio(3,5);
                mapa.add(Fabricas.sueloFactory(mundo,manager.get("floor.png", Texture.class),incremento_X,1, posicionRelativa));
                posicionRelativa.x= posicionRelativa.x+3;
            }

            posicionRelativa.x = posicionRelativa.x +incremento_X+Beans.numeroAleatorio(4,2);
        }

    }



    public Vector2 getPosicionRelativa() {
        return posicionRelativa;
    }

    public void setPosicionRelativa(Vector2 posicionRelativa) {
        this.posicionRelativa = posicionRelativa;
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
                "posicionInicial=" + posicionRelativa +
                ", mundo=" + mundo +
                ", manager=" + manager +
                '}';
    }
}
