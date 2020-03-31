package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Constantes;

import java.awt.datatransfer.FlavorEvent;

public class PantallaDerrota extends PantallaBase {

    private Stage escenario;
    private TextButton btnReinicio,btnSalir;
    private Skin skin;
    PantallaJuego p;
    Label textoDerrota;
    public PantallaDerrota(Main game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
    }
    @Override
    public void show() {

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        p =(PantallaJuego) (game.getPantallaJuego());
        System.out.println(p.getDistanciaRecorrida());
        textoDerrota = new Label("Distancia recorrida"+Beans.truncarNumeros(Beans.mettersToPx_X(p.getDistanciaRecorrida())),skin);
        textoDerrota.setPosition((escenario.getWidth()/2)-textoDerrota.getWidth(), escenario.getHeight()-150);
        textoDerrota.setFontScale(2);

        btnReinicio = new TextButton("Reintentar",skin);
        btnReinicio.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        btnReinicio.setPosition(escenario.getWidth()/2-btnReinicio.getWidth()-50, escenario.getHeight()*0.3f);

        btnReinicio.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PantallaDerrota.super.getGame().setScreen(PantallaDerrota.super.getGame().getPantallaJuego());
            };
        });

        btnSalir = new TextButton("Salir al menu",skin);
        btnSalir.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        btnSalir.setPosition(btnReinicio.getX() + btnSalir.getWidth()+50, escenario.getHeight()*0.3f);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PantallaDerrota.super.getGame().setScreen(PantallaDerrota.super.getGame().getPantallaMenu());
            };
        });

        escenario.addActor(btnReinicio);
        escenario.addActor(btnSalir);
        escenario.addActor(textoDerrota);
        Gdx.input.setInputProcessor(escenario);
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
        p = null;
        textoDerrota.setText(null);

    }

    @Override
    public void dispose() {
        skin.dispose();
        escenario.dispose();
    }



}
