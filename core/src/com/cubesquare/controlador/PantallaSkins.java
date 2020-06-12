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

    /**
     * El método show permite mostrar la pantalla del selector de niveles. De momento solo
     * está disponible el nivel 1. Declara los botones necesarios para volver al menú
     * o iniciar un nivel. Crea la música de la pantalla. Crea el suelo y por último añade
     * al cubo y enemigos.
     *
     * @return void
     * @author Jesús de la Peña
     */
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
        btnSkin1 = new Button(new Button.ButtonStyle(cuboEnfadado, cuboEnfadado, cuboEnfadado));
        btnSkin1.setSize(64, 64);
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
        btnSkin2 = new Button(new Button.ButtonStyle(cubeSquare, cubeSquare, cubeSquare));
        btnSkin2.setSize(64, 64);
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


        /*tabla.center();
        tabla.add(titulo).expand();
        tabla.row();
        tabla.add(textoSkin).expand();
        tabla.row();
        tabla.add(btnSkin1);
        tabla.add(btnSkin2).colspan(1);
        tabla.row();
        tabla.add(btnSalir);*/

        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }

        //AÑADIMOS TODOS LOS ACTORES AL ESCENARIO
        escenario.addActor(fondo);
        escenario.addActor(tabla);
        //escenario.setDebugAll(true);

    }

    /**
     * Se llama cada vez que se actualiza la pantalla (30 o 60 veces por segundo dependiedo del dispositivo)
     *
     * @param delta tiempo de diferencia entre el anterior fotograma y este
     * @return void
     * @author Jesús Peña
     */
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

    /**
     * El método pausa permite pausar o terminar la música del juego.
     *
     * @author Jesús de la Peña, Diego Corral
     */

    @Override
    public void pause() {
        cancionMenu.pause();
    }

    /**
     * El método resume permite iniciar el sonido de la música.
     *
     * @author Jesús de la Peña
     */
    @Override
    public void resume() {
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }
    }

    /**
     * El método dispose pone fin a la pantalla que te permite seleccionar el nivel deseado.
     * Este método se activa siempre que salgamos de la pantalla.
     *
     * @author Jesús Jiménez
     */
    @Override
    public void dispose() {
        escenario.dispose();
        skin.dispose();
    }
}

