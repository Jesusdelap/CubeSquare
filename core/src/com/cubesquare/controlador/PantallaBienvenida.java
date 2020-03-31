package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaBienvenida extends PantallaBase {
    private Stage escenario;
    private Label carga;
    private Skin skin;
    public PantallaBienvenida(Main game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        carga = new Label("¡Bienvenido! Pulsa click o Enter para continuar.", skin);
    }
    public void show(){
        carga.setSize((float) (escenario.getWidth()*0.3), (float) (escenario.getHeight()*0.3));
        carga.setPosition(carga.getWidth(), carga.getHeight());
        carga.setFontScale(2);
        escenario.addActor(carga);
    }
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 2, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched()) {
            PantallaBienvenida.super.getGame().setScreen(PantallaBienvenida.super.getGame().getPantallaMenu());
        }

        escenario.act();
        escenario.draw();
    }
    @Override
    public void hide() {

    }
    @Override
    public void dispose() {
        skin.dispose();
        escenario.dispose();
    }

}