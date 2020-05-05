package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class PantallaRanking extends PantallaBase{
    Label nameLabel;
    private Stage escenario;
    private Skin skin;
    //TextField nameText;
    Label addressLabel;
    //TextField addressText;
    Label nameText;
    Label addressText;
    Table table;
    ShapeRenderer borde = new ShapeRenderer();

    public PantallaRanking(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    }

    @Override
    public void show() {

        nameLabel = new Label("Jugador:", skin);
        addressLabel = new Label("Distancia recorrida:", skin);
        table = new Table();

        table.add(nameLabel).width(100);
        table.add(addressLabel).width(100);
        table.row();
        for(int i = 0; i<10; i++){
            //nameText = new TextField("keonda", skin);
            //addressText = new TextField("xd",skin);
            nameText = new Label("xd",skin);
            addressText = new Label("el chocu rodrigues",skin);
            table.add(nameText).center();
            table.add(addressText).width(100);
            //table.add(nameText).padLeft(10).width(100);
            //table.add(addressText).padLeft(10).width(100);
            table.row();
        }
        /*table.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));     // estos dos metodos hacen situarlo en cualquier lado
        table.setPosition(escenario.getWidth()/2-table.getWidth()-50, escenario.getHeight()*0.3f);*/  // estos dos metodos hacen situarlo en cualquier lado
        table.setFillParent(true);  // lo centraliza al medio siempre
        //table.setClip(true);
        //table.setBackground(skin.getDrawable(""));
        //borde.rect(30,30,100,100);
        table.drawDebug(borde);
        //table.setDebug(true);

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
    public void dispose(){
        escenario.clear();
        skin.dispose();
    }
}