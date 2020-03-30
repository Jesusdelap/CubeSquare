package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaCarga extends PantallaBase {
    private Stage escenario;
    private Label carga;
    private Skin skin;

    public PantallaCarga(Main game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        carga = new Label("¡Cargando! ¡Espere, por favor!", skin);

    }
    @Override
    public void show() {
        carga.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        carga.setPosition(carga.getWidth(), carga.getHeight());
        carga.setFontScale(2);
        escenario.addActor(carga);
    }
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(game.getManager().isFinished()){
            PantallaCarga.super.getGame().finCarga();
            System.out.println("CARGADO");
        } else {
            int progreso = (int) (game.getManager().getProgress() * 100);
            carga.setText("Cargando... " + progreso + "%");
            System.out.println("cargando");
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
