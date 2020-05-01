package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaBienvenida extends PantallaBase {
    private Stage escenario;
    private Label lblBienvenida,lblBienvenida2;
    private Skin skin;
    private Image fondo;

    SpriteBatch batch;
    Texture img;
    private OrthographicCamera camera;

    public PantallaBienvenida(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));

    }

    public void show() {

        Gdx.input.setInputProcessor(escenario);

        //CREAMOS FONDO DE PANTALLA
        fondo = new Image(game.getManager().get("fondoestrella2.png", Texture.class));
        fondo.setFillParent(true);

        //Mensaje De Bienvenida
        lblBienvenida = new Label("¡BIENVENIDO A CUBESQUARE!", skin);
        lblBienvenida.setPosition(escenario.getWidth()/2 - lblBienvenida.getWidth(), escenario.getHeight()/2 - lblBienvenida.getHeight()/2);
        lblBienvenida.setFontScale(2);
        lblBienvenida2 = new Label("Toca la pantalla para comenzar", skin);
        lblBienvenida2.setPosition(escenario.getWidth()/2 - lblBienvenida2.getWidth(), lblBienvenida.getY()-lblBienvenida2.getHeight()*2.4f );
        lblBienvenida2.setFontScale(2);

        //Añadimos los Actores al escenario
        escenario.addActor(fondo);
        escenario.addActor(lblBienvenida);
        escenario.addActor(lblBienvenida2);


        escenario.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                PantallaBienvenida.super.getGame().setScreen(PantallaBienvenida.super.getGame().getPantallaMenu());
            }
        });
    }

    public void render(float delta) {
        // Gdx.gl.glClearColor(0, 2, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        escenario.act();
        /*batch.begin();

        i.render(batch);
        j.render(batch);*/

        escenario.draw();
        //batch.end();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        skin.dispose();
        escenario.dispose();
        //batch.dispose();
    }

}