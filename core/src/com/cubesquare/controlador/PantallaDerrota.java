package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Beans;

public class PantallaDerrota extends PantallaBase {

    private Stage escenario;
    private TextButton btnReinicio,btnSalir;
    private Skin skin;
    PantallaJuego p;
    private Image fondo, tituloGameOver;
    private Label textoDerrota;
    private Sound sonidoGameOver;

    public PantallaDerrota(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        sonidoGameOver = game.getManager().get("sonidos/gameover.wav");
    }
    @Override
    public void show() {
        p =(PantallaJuego) (game.getPantallaJuego());

        if(PantallaMenu.isSonido()){
            sonidoGameOver.play();
        }

        fondo = new Image(game.getManager().get("fondoEspacio.png", Texture.class));
        fondo.setFillParent(true);

        tituloGameOver = new Image(game.getManager().get("gameover.png", Texture.class));
        tituloGameOver.setPosition((escenario.getWidth() / 2) - tituloGameOver.getWidth() / 2, escenario.getHeight() - 360);

        textoDerrota = new Label("Distancia recorrida: "+Beans.truncarNumeros(Beans.pxToMetters_X(p.getDistanciaRecorrida())),skin);
        textoDerrota.setPosition(((escenario.getWidth() / 2 -tituloGameOver.getWidth() / 4)), tituloGameOver.getY() -2);
        textoDerrota.setFontScale(3);
        textoDerrota.setColor(1,0,0,1);

        btnReinicio = new TextButton("Reintentar",skin);
        btnReinicio.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        btnReinicio.setPosition(escenario.getWidth()/2-btnReinicio.getWidth()-50, escenario.getHeight()*0.3f);

        btnReinicio.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sonidoGameOver.stop();
                PantallaDerrota.super.getGame().setScreen(PantallaDerrota.super.getGame().getPantallaJuego());
            };
        });

        btnSalir = new TextButton("Salir al menu",skin);
        btnSalir.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        btnSalir.setPosition(btnReinicio.getX() + btnSalir.getWidth()+50, escenario.getHeight()*0.3f);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sonidoGameOver.stop();
                PantallaMenu.setPantallaMenu(true);
                PantallaDerrota.super.getGame().setScreen(PantallaDerrota.super.getGame().getPantallaMenu());
            };
        });

        escenario.addActor(fondo);
        escenario.addActor(tituloGameOver);
        escenario.addActor(textoDerrota);
        escenario.addActor(btnReinicio);
        escenario.addActor(btnSalir);
        Gdx.input.setInputProcessor(escenario);
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw();
    }

    @Override
    public void pause() {
        sonidoGameOver.stop();
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
        sonidoGameOver.dispose();
    }



}
