package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;

public class PantallaSkins extends PantallaBase {


    private static String tipoCubo = "cubo.png";
    private Stage escenario;
    private Button btnSkin1, btnSkin2;
    private ImageTextButton btnSalir;
    private Skin skin;
    private Music cancionMenu;
    private Image fondo, titulo;
    private Texture texturaSkin1, texturaSkin2;
    private Label textoSkin;
    private Table tabla, tablaSkin1, tablaSkin2, tabla4;

    public static String getTipoCubo() {
        return tipoCubo;
    }

    public PantallaSkins(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
        cancionMenu = game.getManager().get("sonidos/cancionmenu.ogg");
        cancionMenu.setLooping(true);
    }


    @Override
    public void show() {
        //ACTIVAMOS EL INPUT PROCESSOR PARA EL ESCENARIO
        Gdx.input.setInputProcessor(escenario);

        //CREAMOS FONDO DE PANTALLA
        fondo = new Image(game.getManager().get("fondoEspacio.png", Texture.class));
        fondo.setFillParent(true);

        //IMAGEN CON EL TÍTULO DEL JUEGO
        titulo = new Image(game.getManager().get("titulo.png", Texture.class));

        //LABEL SKINS
        textoSkin = new Label("Selecciona un cubo ", skin);
        textoSkin.setFontScale(2);

        //BOTÓN SKIN 1
        texturaSkin1 = new Texture("cuboEnfadado.png");
        SpriteDrawable cuboEnfadado = new SpriteDrawable(new Sprite(texturaSkin1));
        cuboEnfadado.setMinSize(Constantes.PIXELS_IN_METER_X*1.5f, Constantes.PIXELS_IN_METER_Y*1.5f);
        btnSkin1 = new Button(new Button.ButtonStyle(cuboEnfadado, cuboEnfadado, cuboEnfadado));
        btnSkin1.setSize(Constantes.PIXELS_IN_METER_X*2, Constantes.PIXELS_IN_METER_Y*2);
        btnSkin1.setPosition(escenario.getWidth() / 2 - btnSkin1.getWidth() - 50, escenario.getHeight() * 0.3f);
        btnSkin1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tipoCubo = "cuboEnfadado.png";
                game.setScreen(game.getPantallaTipoJuego());
            }
        });

        //BOTÓN SKIN 2
        texturaSkin2 = new Texture("cubo.png");
        SpriteDrawable cubeSquare = new SpriteDrawable(new Sprite(texturaSkin2));
        cubeSquare.setMinSize(Constantes.PIXELS_IN_METER_X*1.5f, Constantes.PIXELS_IN_METER_Y*1.5f);
        btnSkin2 = new Button(new Button.ButtonStyle(cubeSquare, cubeSquare, cubeSquare));
        btnSkin2.setSize(Constantes.PIXELS_IN_METER_X*2, Constantes.PIXELS_IN_METER_Y*2);
        btnSkin2.setPosition(btnSkin1.getX() + btnSkin2.getWidth() + 50, escenario.getHeight() * 0.3f);
        btnSkin2.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tipoCubo = "cubo.png";
                game.setScreen(game.getPantallaTipoJuego());
            }
        });

        //BOTÓN VOLVER AL MENU
        btnSalir = new ImageTextButton("Volver", skin);
        btnSalir.setSize((float) (escenario.getWidth() * 0.25), (float) (escenario.getHeight() * 0.1));
        btnSalir.setPosition(btnSkin2.getX(), (btnSkin2.getY() - btnSkin2.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
        btnSalir.getLabel().setFontScale(escenario.getViewport().getScreenWidth() * 0.001f);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaMenu());
            }
        });


       // titulo.setAlign(Align.center);

        tabla = new Table();
        tabla.center();
        tabla.setFillParent(true);
        //tabla.defaults().pad(10F);

        tablaSkin1 = new Table();
        tablaSkin1.add(btnSkin1);

        tablaSkin2 = new Table();
        tablaSkin2.add(btnSkin2);

        tabla.add(titulo).colspan(2).align(Align.center).expand();
        tabla.row();
        tabla.add(textoSkin).colspan(2).align(Align.center).expand();
        tabla.row();
        tabla.add(tablaSkin1).right().padRight(50f).expand();
        tabla.add(tablaSkin2).left().padLeft(50f).expand();
        tabla.row();
        tabla.add(btnSalir).colspan(2).align(Align.center).expand();




        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }

        //AÑADIMOS TODOS LOS ACTORES AL ESCENARIO
        escenario.addActor(fondo);
        escenario.addActor(tabla);
        //escenario.setDebugAll(true);

    }


    @Override
    public void render(float delta) {
        //LIMPIAMOS PANTALLA Y ACTUALIZAMOS EL ESCENARIO EN CADA FOTOGRAMA PARA DIBUJARLO
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }



    @Override
    public void pause() {
        cancionMenu.pause();
    }


    @Override
    public void resume() {
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }
    }


    @Override
    public void dispose() {
        escenario.dispose();
        skin.dispose();
    }
}

