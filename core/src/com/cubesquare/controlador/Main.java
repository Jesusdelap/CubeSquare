package com.cubesquare.controlador;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
//Main
public class Main extends Game {

    private AssetManager manager;
    private PantallaBase pantallaMenu,pantallaJuego,pantallaDerrota,pantallaCarga,pantallaBienvenida;



    @Override
    public void create() {
        pantallaCarga = new PantallaCarga(this);
        setScreen(pantallaCarga);

        //Se cargan los assets
        manager = new AssetManager();
        manager.load("cubo.png", Texture.class);
        manager.load("floor.png", Texture.class);
        manager.load("spike.png", Texture.class);
        manager.load("pincho.png", Texture.class);




    }
    public void finCarga(){
        manager.finishLoading();
        pantallaMenu = new PantallaMenu(this);
        pantallaJuego = new PantallaJuego(this);
        pantallaDerrota = new PantallaDerrota(this);
        pantallaBienvenida = new PantallaBienvenida(this);
        setScreen(pantallaBienvenida);
    }


    public AssetManager getManager() {
        return manager;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }

    public PantallaBase getPantallaMenu() {
        return pantallaMenu;
    }

    public void setPantallaMenu(PantallaBase pantallaMenu) {
        this.pantallaMenu = pantallaMenu;
    }

    public PantallaBase getPantallaJuego() {
        return pantallaJuego;
    }

    public void setPantallaJuego(PantallaBase pantallaJuego) {
        this.pantallaJuego = pantallaJuego;
    }

    public PantallaBase getPantallaDerrota() {
        return pantallaDerrota;
    }

    public void setPantallaDerrota(PantallaBase pantallaDerrota) {
        this.pantallaDerrota = pantallaDerrota;
    }

    public PantallaBase getPantallaCarga() {
        return pantallaCarga;
    }

    public void setPantallaCarga(PantallaBase pantallaCarga) {
        this.pantallaCarga = pantallaCarga;
    }

}
