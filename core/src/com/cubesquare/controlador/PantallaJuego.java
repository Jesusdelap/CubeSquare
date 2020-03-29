package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorPincho;
import com.cubesquare.modelo.entidades.ActorSuelo;
import com.cubesquare.modelo.entidades.Destruible;

import java.util.ArrayList;

public class PantallaJuego extends PantallaBase {

    private Stage escenario;
    private World mundo;
    private ActorSuelo suelo;
    private ActorJugador jugador;
    private ActorPincho pincho;
    private OrthographicCamera camara;
    private TextButton btnJuego;
    private Skin skin;
    private Body body;
    private float distancia;
    private ArrayList<Actor> arrayMapa;

    public PantallaJuego(Main game) {
        super(game);
        System.out.println("pixeles por metro en eje x"+Constantes.PIXELS_IN_METER_X);
        System.out.println("pixeles por metro en eje y"+Constantes.PIXELS_IN_METER_Y);
        escenario = new Stage(new FitViewport( Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        mundo = new World(new Vector2(0, -10), true); // Nuevo mundo de gravedad en eje Y = -10

    }

    @Override
    public void show() {

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        btnJuego = new TextButton("Menu", skin);
        btnJuego.setSize(90, 40);
        btnJuego.setPosition(Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-50);
        btnJuego.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnMenu");
                PantallaJuego.super.getGame().setScreen(PantallaJuego.super.getGame().getPantallaMenu());
            };
        });

        // mundo.setGravity(new Vector2(0, -10));

        Texture texturaJugador = game.getManager().get("cubo.png");
        suelo = Fabricas.sueloFactory(mundo);
        jugador = new ActorJugador(mundo, texturaJugador, new Vector2(1, 1.5f));


        arrayMapa= Fabricas.mapaFactory(10,new Vector2(5f, 1),mundo,game.getManager());

        for (Actor a:arrayMapa) {
            escenario.addActor(a);
        }


        escenario.addActor(suelo);
        escenario.addActor(jugador);
        escenario.addActor(btnJuego);
        Gdx.input.setInputProcessor(escenario);

        mundo.setContactListener(new ContactListener() {
            private boolean choque(Contact contact, Object userA, Object userB) {
                Object userDataA = contact.getFixtureA().getUserData();
                Object userDataB = contact.getFixtureB().getUserData();
                if (userDataA == null || userDataB == null) {
                    return false;
                }
                return (userDataA.equals(userA) && userDataB.equals(userB))||
                        (userDataA.equals(userB) && userDataB.equals(userA));}
            @Override
            public void beginContact(Contact contact) {
                if(choque(contact,"cubo","suelo")){
                    jugador.setSaltando(false);
                    if(Gdx.input.isTouched()){
                        jugador.setSaltoContinuo(true);
                    }
                }
                if(choque(contact,"cubo","pincho")){
                    jugador.setFin(true);


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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        mundo.step(delta, 6, 2);


        if(!jugador.isFin()){
            distancia = 2f*delta*Constantes.PIXELS_IN_METER_X+distancia;
            escenario.getCamera().translate(2f*delta*Constantes.PIXELS_IN_METER_X,0,0);
        }
        escenario.draw();

    }
    @Override
    public void hide() {
        escenario.getCamera().translate(-distancia,0,0);
        distancia=0;
        escenario.clear();
        suelo.destroy();
        jugador.destroy();
        for (Actor a:arrayMapa) {
            Destruible d = (Destruible) a;
            d.destroy();
        }

    }

    @Override
    public void dispose() {
        skin.dispose();
        escenario.dispose();
        mundo.dispose();

    }
}

