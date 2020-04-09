package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.AnimacionFondo;

public class PantallaBienvenida extends PantallaBase {
    private Stage escenario;
    private Label lblBienvenida;
    private Skin skin;
    private Image fondo;

    SpriteBatch batch;
    Texture img;
    private OrthographicCamera camera;
    private AnimacionFondo i, j, k;

    public PantallaBienvenida(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    }

    public void show() {

      /* batch = new SpriteBatch();

        i = new AnimacionFondo(0, 0);
        j = new AnimacionFondo(0, 0);*/

        Gdx.input.setInputProcessor(escenario);

        //CREAMOS FONDO DE PANTALLA
        fondo = new Image(game.getManager().get("fondoestrella2.png", Texture.class));
        fondo.setFillParent(true);

        //Mensaje De Bienvenida
        lblBienvenida = new Label(" ¡BIENVENIDO A CUBESQUARE! \n\nToca la pantalla para comenzar", skin);
        lblBienvenida.setSize(escenario.getWidth() * 0.3f, escenario.getHeight() * 0.3f);
        lblBienvenida.setPosition(escenario.getWidth() / 2 - lblBienvenida.getWidth() / 2, escenario.getHeight() / 2 - lblBienvenida.getHeight() / 2);
        lblBienvenida.setFontScale(2);


        //Añadimos los Actores al escenario
        escenario.addActor(fondo);
        escenario.addActor(lblBienvenida);

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