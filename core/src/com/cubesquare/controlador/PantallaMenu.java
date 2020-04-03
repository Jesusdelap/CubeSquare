package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;


public class PantallaMenu extends PantallaBase {

    private Stage escenario;
    private TextButton btnJuego, btnSalir;
    private Skin skin;
    private Music cancionMenu;

    private ActorSuelo suelo;
    private World mundoMenu;
    private ActorJugador cubo;
    private Vector2 posicionCubo;
    private Vector2 posicionSuelo;
    private Image fondo, titulo;

    private Texture btnSonidoActivado, btnSonidoDesactivado;
    private Button btnSonido;
    private static boolean sonido = true;
    private static boolean pantallaMenu = true;

    public static void setPantallaMenu(boolean pantallaMenu) {
        PantallaMenu.pantallaMenu = pantallaMenu;
    }

    public static boolean isPantallaMenu() {
        return pantallaMenu;
    }

    public static boolean isSonido() {
        return sonido;
    }

    public PantallaMenu(Main game) {
        super(game);

        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundoMenu = new World(new Vector2(0, -15), true);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        cancionMenu = game.getManager().get("sonidos/cancionmenu.ogg");
        cancionMenu.setLooping(true);

        System.out.println("Resolucion: " + Gdx.graphics.getWidth() + "x" + Gdx.graphics.getHeight());
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
        titulo.setPosition((escenario.getWidth() / 2) - titulo.getWidth() / 2, escenario.getHeight() - 320);


        //BOTÓN JUEGO
        btnJuego = new TextButton("Play", skin);
        btnJuego.setSize((float) (escenario.getWidth() * 0.2), (float) (escenario.getHeight() * 0.1));
        btnJuego.setPosition((escenario.getWidth() / 2) - btnJuego.getWidth() / 2, titulo.getY() - 150);

        btnJuego.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuego");
                cancionMenu.stop();
                pantallaMenu = false;
                PantallaMenu.super.getGame().setScreen(PantallaMenu.super.getGame().getPantallaJuego());
            }

            ;
        });


        //BOTÓN SALIR
        btnSalir = new TextButton("Salir", skin);
        btnSalir.setSize((float) (escenario.getWidth() * 0.2), (float) (escenario.getHeight() * 0.1));
        btnSalir.setPosition(btnJuego.getX(), btnJuego.getY() - 150);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                Gdx.app.exit();
            }
        });


        //CREAMOS EL BOTÓN DE SONIDO CON DOS TEXTURAS DISTINTAS
        btnSonidoActivado = game.getManager().get("sonido.png");
        btnSonidoDesactivado = game.getManager().get("sonidodesactivado.png");
        SpriteDrawable activado = new SpriteDrawable(new Sprite(btnSonidoActivado));
        SpriteDrawable desactivado = new SpriteDrawable(new Sprite(btnSonidoDesactivado));

        //SI LA VARIABLE DE CONTROL DE SONIDO ES TRUE, CREAMOS EL BOTÓN DE SONIDO. EN CASO CONTRARIO, CREAMOS EL BOTÓN MUTE
        if (sonido) {
            btnSonido = new Button(new Button.ButtonStyle(activado, desactivado, desactivado));
        } else {
            btnSonido = new Button(new Button.ButtonStyle(desactivado, activado, activado));
        }
        btnSonido.setSize(50, 50);
        btnSonido.setPosition(btnSalir.getX() + 580, btnSalir.getY());

        btnSonido.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (sonido) {
                    sonido = false;
                    cancionMenu.stop();
                } else {
                    sonido = true;
                    cancionMenu.play();
                }
            }
        });

        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        //cancionMenu.setVolume(0.1f);
        if (sonido) {
            cancionMenu.play();
        }

        //CREAMOS SUELO Y CUBO PARA QUE INTERACCIONEN INDEFINIDAMENTE EN LA PANTALLA DE MENÚ
        posicionCubo = new Vector2(5, 10);
        posicionSuelo = new Vector2(0, 1.5f);
        suelo = Fabricas.sueloFactory(mundoMenu, new Texture("sueloTransparente.png"), Gdx.graphics.getWidth(), posicionSuelo);
        cubo = Fabricas.ActorFactory(mundoMenu, game.getManager().get("cubo.png", Texture.class), posicionCubo);
        cubo.setSaltando(true);

        mundoMenu.setContactListener(new ContactListener() {

            private boolean choque(Contact contact, Object userA, Object userB) {
                Object userDataA = contact.getFixtureA().getUserData();
                Object userDataB = contact.getFixtureB().getUserData();
                if (userDataA == null || userDataB == null) {
                    return false;
                }
                return (userDataA.equals(userA) && userDataB.equals(userB)) ||
                        (userDataA.equals(userB) && userDataB.equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (choque(contact, "cubo", "suelo")) {
                    cubo.setSaltando(false);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        //AÑADIMOS TODOS LOS ACTORES AL ESCENARIO
        escenario.addActor(fondo);
        escenario.addActor(suelo);
        escenario.addActor(titulo);
        escenario.addActor(btnJuego);
        escenario.addActor(btnSalir);
        escenario.addActor(cubo);
        escenario.addActor(btnSonido);

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
        //Gdx.gl.glClearColor(0, 0.2f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        mundoMenu.step(delta, 6, 2);
        escenario.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        cubo.destroy();
    }


    @Override
    public void dispose() {
        cubo.destroy();
        btnSonidoActivado.dispose();
        btnSonidoDesactivado.dispose();
        mundoMenu.dispose();
        escenario.dispose();
        skin.dispose();
    }
}
