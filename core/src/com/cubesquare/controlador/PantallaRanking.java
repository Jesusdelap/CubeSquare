package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.modelo.Record;

import java.util.ArrayList;


public class PantallaRanking extends PantallaBase{
    private Stage escenario;
    private Skin skin,skin4;
    private TextButton btnMenu;
    private Label alias,distanciaRecorrida;
    private Table table;
    private Image fondo;




    public PantallaRanking(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin4 = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));

        //skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    }


    @Override
    public void show() {

        alias = new Label("Jugador:", skin4);
        distanciaRecorrida = new Label("Distancia:", skin4);
        alias.setFontScale(Constantes.TAMAÑOTEXTO*1.5f);
        distanciaRecorrida.setFontScale(Constantes.TAMAÑOTEXTO*1.5f);
        table = new Table(skin4);
        table.center();
        table.align(0);

        table.add(alias).width(Beans.mettersToPx_X(4));
        table.add(distanciaRecorrida).width(Beans.mettersToPx_X(5));
        table.row();

        ArrayList<Record> recordArrayList = game.getAccesoDatos().listarRecordsConAlias(10);
        System.out.println(recordArrayList+" arr");
        for(int i = 0; i<recordArrayList.size(); i++){

            alias = new Label(recordArrayList.get(i).getAlias(),skin4);
            distanciaRecorrida = new Label(Integer.toString(recordArrayList.get(i).getDistanciaRecorrida()),skin4);
            alias.setFontScale(Constantes.TAMAÑOTEXTO*2);
            distanciaRecorrida.setFontScale(Constantes.TAMAÑOTEXTO*2);

            table.add(alias);
            table.add(distanciaRecorrida);
            table.row();
        }

        table.setFillParent(true);  // lo centraliza al medio siempre

        btnMenu = new TextButton("Atras", skin4);
        btnMenu.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnMenu.setPosition(escenario.getWidth()/30,escenario.getHeight()/14);

        btnMenu.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnMenu");
                getGame().setScreen(getGame().getPantallaMenu());
            }

            ;
        });
        fondo = new Image(game.getManager().get("fondoEspacio.png", Texture.class));
        fondo.setFillParent(true);

        Gdx.input.setInputProcessor(escenario);
        escenario.addActor(fondo);
        escenario.addActor(btnMenu);
        escenario.addActor(table);
    }


    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw();
    }

    @Override
    public void hide() {
        try {
            escenario.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void pause() {
        game.getPantallaMenu().pause();
    }

    @Override
    public void resume() {
        game.getPantallaMenu().resume();
    }



    @Override
    public void dispose(){
        escenario.clear();
        skin.dispose();
        skin4.dispose();
    }
}