package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PantallaDerrota extends PantallaBase {

    private Stage escenario;


    public PantallaDerrota(Main game) {
        super(game);
    }
    @Override
    public void show() {
        // This method is invoked when a screen is displayed.
    }

    @Override
    public void render (float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw();
    }
    @Override
    public void hide() {
        // This method is invoked when the screen is no more displayed.
    }

    @Override
    public void dispose() {
        // This method is invoked when the game closes.
    }



}
