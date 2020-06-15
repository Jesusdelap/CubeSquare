package com.cubesquare.controlador;

import com.badlogic.gdx.Screen;
/**
 * Pantalla base
 * <br/><br/>
 * Esta clase abtracta es la base del resto de
 * pantallas
 */
public abstract class PantallaBase implements Screen {


    protected CubeSquare game;

    public PantallaBase(CubeSquare game) {
        this.game = game;
    }

    /**
     * Metodo que se llama al crear la ventana
     * */
    @Override
    public void show() {
        // This method is invoked when a screen is displayed.
    }
    /**
     * Metodo que se llama al renderizar la ventana(30 veces o 60 veces por segundo)
     *
     * @param delta diferencia de tiempo entre la anterior llamada y esta
     * */
    @Override
    public void render(float delta) {
        // This method is invoked when a screen has to be rendered in a frame.
        // delta is the amount of seconds (order of 0.01 or so) between this and last frame.
    }

    /**
     * Metodo que se llama al camibar la resolucion, este metodo solo se utiliza
     * en la app de escritorio
     * */
    @Override
    public void resize(int width, int height) {
        // This method is invoked when the game is resized (desktop).
    }
    /**
     * Metodo que se llama al pausar la app, al minimizar la app
     * tanto en escritorio como en android se llama
     * */
    @Override
    public void pause() {
        // This method is invoked when the game is paused.
    }
    /**
     * Metodo que se llama una vez que ha terminado la pausa en
     * la app
     * */
    @Override
    public void resume() {
        // This method is invoked when the game is resumed.
    }
    /**
     * Metodo que se llama al mostrar la ventana
     * */
    @Override
    public void hide() {
        // This method is invoked when the screen is no more displayed.
    }
    /**
     * Metodo que se llama al destruir la clase
     * */
    @Override
    public void dispose() {
        // This method is invoked when the game closes.
    }

    public CubeSquare getGame() {
        return game;
    }

    public void setGame(CubeSquare game) {
        this.game = game;
    }
}