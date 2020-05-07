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
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;


public class PantallaMenu extends PantallaBase {

    private Stage escenario;
    private TextButton btnJuego;
    private TextButton btnJuegoNiveles, btnSalir;
    private Skin skin, skin2, skin3, skin4;
    private Music cancionMenu;

    private ActorSuelo suelo;
    private World mundoMenu;
    private ActorJugador cubo;
    private Vector2 posicionCubo;
    private Vector2 posicionSuelo;
    private Image fondo, titulo;

    private Texture btnSonidoActivado, btnSonidoDesactivado;
    private Button btnSonido;
    private boolean cayendo = true;
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

    /**
     * El constructor, declaramos los parámetros de la clase PantallaMenu. También iniciamos
     * las canciones en el menú y la opción de desactivarlas.
     *
     * @author Jesús Jiménez
     * @param game
     */
    public PantallaMenu(CubeSquare game) {
        super(game);

        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundoMenu = new World(new Vector2(0, -15), true);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin2 = new Skin(Gdx.files.internal("skin/comic/skin/comic-ui.json"));
        skin3 = new Skin(Gdx.files.internal("skin/quantum-horizon/skin/quantum-horizon-ui.json"));
        skin4 = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
        cancionMenu = game.getManager().get("sonidos/cancionmenu.ogg");
        cancionMenu.setLooping(true);

        System.out.println("Resolucion: " + Gdx.graphics.getWidth() + " X:" + Gdx.graphics.getHeight());

    }

    /**
     * El método show se encarga de mostrar la pantalla. Carga el escenario, crea el fondo,
     * pone la imagen del fondo y también  crea los botones del menú. Algo importante que hace
     * este método es mendiante una variable booleana activar el sonido de la canción de intro
     * o en su defecto desactivarlo.
     *
     * @author Jesús Jiménez
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
        titulo.setPosition((escenario.getWidth() / 2) - titulo.getWidth() / 2, escenario.getHeight() - 360);

        /*atlasUiPadrao = new TextureAtlas("ui/uiPadrao.pack");
        skinPadrao = new Skin(atlasUiPadrao);
        fireButtonStyle = new ImageButtonStyle();*/

        //Instaciate fireButtonStyle.up = skinPadrao.getDrawable("uFireUpI");
        // Set image for not pressed button fireButtonStyle.down = skinPadrao.getDrawable("uFireDownI");
        // Set image for pressed fireButtonStyle.over = skinPadrao.getDrawable("uFireOverI");
        // set image for mouse over fireButtonStyle.pressedOffsetX = 1;
        // fireButtonStyle.pressedOffsetY = -1;



        btnJuego = new TextButton("Juego infinito", skin4);
        //BOTÓN JUEGO
        //btnJuego = new TextButton("Juego infinito", skin2);
        btnJuego.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnJuego.setPosition((escenario.getWidth() / 2) - btnJuego.getWidth() / 2, titulo.getY() - Constantes.PIXELS_IN_METER_Y);

        btnJuego.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuego infinito");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                p.setTipoDeJuego(0);
                cancionMenu.stop();
                pantallaMenu = false;
                getGame().setScreen(PantallaMenu.super.getGame().getPantallaJuego());
            }

            ;
        });
        //BOTÓN JUEGONIVELES
        btnJuegoNiveles = new TextButton("Juego por niveles", skin4);
        btnJuegoNiveles.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnJuegoNiveles.setPosition((escenario.getWidth() / 2) - btnJuego.getWidth() / 2, (btnJuego.getY() - btnJuego.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);

        btnJuegoNiveles.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuegoNiveles");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                p.setTipoDeJuego(1);
                getGame().setScreen(PantallaMenu.super.getGame().getPantallaSelectorNivel());
            }

            ;
        });


        //BOTÓN SALIR
        btnSalir = new TextButton("Salir", skin4);
        btnSalir.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnSalir.setPosition(btnJuego.getX(), (btnJuegoNiveles.getY() - btnJuegoNiveles.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);

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
        cancionMenu.setVolume(0.5f);
        if (sonido) {
            cancionMenu.play();
        }

        //CREAMOS SUELO Y CUBO PARA QUE INTERACCIONEN INDEFINIDAMENTE EN LA PANTALLA DE MENÚ
        if (cayendo) {
            posicionCubo = new Vector2(5, 10);
            cayendo = false;
        } else {
            posicionCubo = new Vector2(5, 1.6f);
        }

        posicionSuelo = new Vector2(0, 1.5f);
        suelo = Fabricas.sueloFactory(mundoMenu, new Texture("sueloTransparente.png"), Gdx.graphics.getWidth(), 0, posicionSuelo);
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
        escenario.addActor(btnJuegoNiveles);
        escenario.addActor(btnSalir);
        escenario.addActor(cubo);
        escenario.addActor(btnSonido);

    }

    /**
     * Se llama cada vez que se actualiza la pantalla (30 o 60 veces por segundo dependiendo del dispositivo)
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
    public void pause() {
        cancionMenu.pause();
    }

    @Override
    public void resume() {
        if (sonido) {
            cancionMenu.play();
        }
    }

    /**
     * La función del método es poner fin a la pantalla cuando se seleccione una determinada acción.
     * Cuando eso ocurra, el escenario, el sonido y el menú desaparecerán momentánamente
     * hasta que volvamos a llamarlos.
     *
     * @author Jesús Jiménez
     */

    @Override
    public void dispose() {
        cubo.destroy();
        btnSonidoActivado.dispose();
        btnSonidoDesactivado.dispose();
        mundoMenu.dispose();
        escenario.dispose();
        skin.dispose();
        cancionMenu.dispose();

    }
}
