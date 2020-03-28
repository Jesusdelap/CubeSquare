package com.cubesquare.controlador;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Main extends Game {

    private AssetManager manager;
    private PantallaBase pantallaMenu,pantallaJuego;



    @Override
    public void create() {
        //Se cargan los assets
        manager = new AssetManager();
        manager.load("cubo.png", Texture.class);
        manager.finishLoading();

        //inicializas las pantallas
        pantallaMenu = new PantallaMenu(this);
        pantallaJuego = new PantallaJuego(this);

        //cargas el menu
        setScreen( pantallaMenu);

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



}
