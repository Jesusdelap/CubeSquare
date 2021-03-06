package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.herramientas.GeneradorMapas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.Destruible;

import java.util.ArrayList;

public class PantallaJuego extends PantallaBase {

    private Stage escenario, escenarioControles;
    private World mundo;
    private GeneradorMapas generadorMapas;
    private ActorJugador jugador;
    private static TextButton btnMenu;
    private Skin skin;
    private ArrayList<Actor> arrayMapa;
    private Sound sonidoChoque;
    private Music cancionJuego;

    private float velocidad;
    private float distanciaRecorrida;
    private Image fondo;

    SpriteBatch batch;

    private int tipoDeJuego; // si el tipo es 0 es infinito, si tiene un numero corresponde al nivel

    public PantallaJuego(CubeSquare game) {
        super(game);
        velocidad = 6f * Constantes.PIXELS_IN_METER_X * 0.99446f;

        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        escenarioControles = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundo = new World(new Vector2(0, -12), true); // Nuevo mundo de gravedad en eje Y = -12

        cancionJuego = game.getManager().get("sonidos/cancionjuego.ogg");
        sonidoChoque = game.getManager().get("sonidos/choque.wav");


        System.out.println("pixeles por metro en eje x" + Constantes.PIXELS_IN_METER_X);
        System.out.println("pixeles por metro en eje y" + Constantes.PIXELS_IN_METER_Y);
    }

    public static TextButton getBtnMenu() {
        return btnMenu;
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(escenarioControles);
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));

        //Creamos el fondo
        fondo = new Image(game.getManager().get("fondoestrella2.png", Texture.class));
        fondo.setFillParent(true);

        //ACTIVAMOS SONIDO SI SU VARIABLE DE CONTROL LO INDICA
        if (PantallaMenu.isSonido()) {
            cancionJuego.setVolume(0.5f);
            cancionJuego.play();
        }

        distanciaRecorrida = 0;

        //CREAMOS BOTON MENU Y ASOCIAMOS UN LISTENER QUE PARARA EL JUEGO Y NOS LLEVARA A LA PANTALLA MENU
        btnMenu = new TextButton("Menu", skin);
        btnMenu.setSize(Constantes.PIXELS_IN_METER_X * 1.50f, Constantes.PIXELS_IN_METER_Y * 0.8f);
        btnMenu.setPosition(escenarioControles.getWidth() - (btnMenu.getWidth() + Constantes.PIXELS_IN_METER_X / 5), escenarioControles.getHeight() - (btnMenu.getHeight() + Constantes.PIXELS_IN_METER_Y / 5));
        btnMenu.getLabel().setFontScale(Constantes.TAMAÑOTEXTO/2);
        btnMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("clickeado btnMenu");
                cancionJuego.stop();
                PantallaMenu.setPantallaMenu(true);
                PantallaMenu.primerInicio=false;
                PantallaJuego.super.getGame().setScreen(PantallaJuego.super.getGame().getPantallaMenu());
                return true;
            }
        });


        //CREAMOS JUGADOR Y MAPA Y LOS AÑADIMOS AL ESCENARIO
        jugador = Fabricas.ActorFactory(mundo, game.getManager().get(PantallaSkins.getTipoCubo(), Texture.class));

        //inicializamos el mapa
        generadorMapas = new GeneradorMapas(new Vector2(17, 3), mundo, game.getManager());
        arrayMapa = Fabricas.mapaFactory(tipoDeJuego, generadorMapas);

        //CREAMOS LISTENER PARA CONTROLAR LAS COLISIONES DE LOS ACTORES
        mundo.setContactListener(new ContactListener() {
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
                    jugador.setSaltando(false);
                }

                if (choque(contact, "cubo", "pincho")) {
                    if (PantallaMenu.isSonido()) {
                        cancionJuego.stop();
                        sonidoChoque.play();
                    }
                    jugador.setFin(true);

                    escenario.addAction(
                            Actions.sequence(
                                    Actions.delay(0.5f),
                                    Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            PantallaJuego.super.getGame().setScreen(PantallaJuego.super.getGame().getPantallaDerrota());
                                        }
                                    })
                            )
                    );
                }
            }

            @Override
            public void endContact(Contact contact) {

                if (choque(contact, "cubo", "suelo")) {
                    jugador.setSaltando(true);

                }

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        // Añadimos los actores al escenario
        for (Actor a : arrayMapa) {
            escenario.addActor(a);
        }
        escenario.addActor(jugador);
        escenarioControles.addActor(fondo);
        escenarioControles.addActor(btnMenu);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //ACTUALIZAMOS ESCENARIO Y MOVEMOS LA CAMARA
        escenario.act();
        mundo.step(delta, 6, 2);

        if (!jugador.isFin() /*&& (!PantallaJuego.getBtnMenu().getClickListener().isPressed())*/) {
            escenario.getCamera().translate(velocidad * delta, 0, 0);
        }

        //DIBUJAMOS ESCENARIOS
        escenarioControles.draw();
        escenario.draw();

        distanciaRecorrida = velocidad * delta + distanciaRecorrida;
    }

    @Override
    public void hide() {
        try {
            Gdx.input.setInputProcessor(null);
            jugador.destroy();
            escenario.getCamera().position.set(escenario.getCamera().viewportWidth / 2, escenario.getCamera().viewportHeight / 2, 0);
            escenario.getCamera().update();
            escenario.clear();
            escenarioControles.clear();

            cancionJuego.stop();
            sonidoChoque.stop();

            for (Actor a : arrayMapa) {
                Destruible d = (Destruible) a;
                d.destroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        PantallaMenu.setPantallaMenu(true);
        game.setScreen(game.getPantallaMenu());
        cancionJuego.stop();
    }

    @Override
    public void dispose() {
        jugador.destroy();
        skin.dispose();
        escenario.dispose();
        mundo.dispose();
        escenarioControles.dispose();
        cancionJuego.dispose();
        batch.dispose();
    }

    public float getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setTipoDeJuego(int tipoDeJuego) {
        this.tipoDeJuego = tipoDeJuego;
    }

}