package com.cubesquare.controlador;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
//Main
public class CubeSquare extends Game {

    private AssetManager manager;
    private PantallaBase pantallaMenu,pantallaJuego,pantallaDerrota,pantallaCarga,pantallaBienvenida,pantallaSelectorNivel;


    @Override
    public void create() {

        //Se cargan los assets
        manager = new AssetManager();
        manager.load("cubo.png", Texture.class);
        manager.load("floor.png", Texture.class);
        manager.load("spike.png", Texture.class);
        manager.load("pincho.png", Texture.class);
        manager.load("trianguloenfadado.png", Texture.class);
        manager.load("sonido.png", Texture.class);
        manager.load("sonidodesactivado.png", Texture.class);
        manager.load("fondoEspacio.png", Texture.class);
        manager.load("gameover.png", Texture.class);
        manager.load("titulo.png", Texture.class);
        manager.load("sueloTransparente.png", Texture.class);
        //manager.load("fondoestrella1.png", Texture.class);
        manager.load("fondoestrella2.png", Texture.class);
        manager.load("sonidos/cancionmenu.ogg", Music.class);
        manager.load("sonidos/cancionjuego.ogg", Music.class);
        manager.load("sonidos/choque.wav", Sound.class);
        manager.load("sonidos/gameover.wav", Sound.class);

        pantallaCarga = new PantallaCarga(this);
        setScreen(pantallaCarga);
    }

    public void finCarga(){
        pantallaMenu = new PantallaMenu(this);
        pantallaJuego = new PantallaJuego(this);
        pantallaDerrota = new PantallaDerrota(this);
        pantallaBienvenida = new PantallaBienvenida(this);
        pantallaSelectorNivel = new PantallaSelectorNivel(this);
        setScreen(pantallaBienvenida);
    }


    public AssetManager getManager() {
        return manager;
    }


    public PantallaBase getPantallaMenu() {
        return pantallaMenu;
    }


    public PantallaBase getPantallaJuego() {
        return pantallaJuego;
    }


    public PantallaBase getPantallaDerrota() {
        return pantallaDerrota;
    }


    public PantallaBase getPantallaSelectorNivel() {
        return pantallaSelectorNivel;
    }
}
