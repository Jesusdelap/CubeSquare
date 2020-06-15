package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;

public class PantallaTipoJuego extends PantallaBase{


    private Stage escenario;
    private ImageTextButton btnJuegoInfinito, btnJuegoNiveles, btnSalir;
    private Skin skin;
    private Music cancionMenu;

    private ActorSuelo suelo;
    private World mundoMenu;
    private ActorJugador trianguloEnfadado, cubo;
    private Vector2 posicionCubo,posicionTriangulo, posicionSuelo;
    private Image fondo,titulo;


    public PantallaTipoJuego(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundoMenu = new World(new Vector2(0, -15), true);
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
        titulo.setPosition((escenario.getWidth() / 2) - titulo.getWidth() / 2, escenario.getHeight() - 360);

        //BOTÓN JUEGO INFINITO
        btnJuegoInfinito = new ImageTextButton("Infinito", skin);
        btnJuegoInfinito.setSize(escenario.getWidth() * 0.25f, escenario.getHeight() * 0.1f);
        btnJuegoInfinito.setPosition((escenario.getWidth() / 2) - btnJuegoInfinito.getWidth() / 2, titulo.getY() - Constantes.PIXELS_IN_METER_Y);
        btnJuegoInfinito.getLabel().setFontScale(escenario.getViewport().getScreenWidth()*0.001f);

        btnJuegoInfinito.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuego infinito");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                p.setTipoDeJuego(0);
                cancionMenu.stop();
                PantallaMenu.setPantallaMenu(false);
                getGame().setScreen(PantallaTipoJuego.super.getGame().getPantallaJuego());
            }

            ;
        });

        //BOTÓN JUEGO POR NIVELES
        btnJuegoNiveles = new ImageTextButton("Por niveles", skin);
        btnJuegoNiveles.setSize(escenario.getWidth() * 0.25f, escenario.getHeight() * 0.1f);
        btnJuegoNiveles.setPosition((escenario.getWidth() / 2) - btnJuegoInfinito.getWidth() / 2, (btnJuegoInfinito.getY() - btnJuegoInfinito.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
        btnJuegoNiveles.getLabel().setFontScale(escenario.getViewport().getScreenWidth()*0.001f);

        btnJuegoNiveles.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuegoNiveles");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                p.setTipoDeJuego(1);
                getGame().setScreen(PantallaTipoJuego.super.getGame().getPantallaSelectorNivel());
            }
        });


        //BOTÓN VOLVER AL MENU
        btnSalir = new ImageTextButton("Volver", skin);
        btnSalir.setSize((float) (escenario.getWidth() * 0.25), (float) (escenario.getHeight() * 0.1));
        btnSalir.setPosition(btnJuegoNiveles.getX(), (btnJuegoNiveles.getY()- btnJuegoNiveles.getHeight()) -Constantes.PIXELS_IN_METER_Y/4);
        btnSalir.getLabel().setFontScale(escenario.getViewport().getScreenWidth()*0.001f);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaSkins());
            }
        });

        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }

        //CREAMOS SUELO Y CUBO PARA QUE INTERACCIONEN INDEFINIDAMENTE EN LA PANTALLA DE MENÚ
        posicionSuelo = new Vector2(0, 1.5f);
        posicionCubo = new Vector2(5, 1.6f);
        posicionTriangulo = new Vector2(11,1.6f);

        suelo = Fabricas.sueloFactory(mundoMenu, new Texture("sueloTransparente.png"), Gdx.graphics.getWidth(),0, posicionSuelo);
        cubo = Fabricas.ActorFactory(mundoMenu, game.getManager().get(PantallaSkins.getTipoCubo(), Texture.class), posicionCubo);
        trianguloEnfadado = Fabricas.ActorFactory(mundoMenu, game.getManager().get("trianguloenfadado.png", Texture.class), posicionTriangulo);
        cubo.setSaltando(true);
        trianguloEnfadado.setSaltando(true);

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
                    trianguloEnfadado.setSaltando(false);
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
        escenario.addActor(btnJuegoInfinito);
        escenario.addActor(btnJuegoNiveles);
        escenario.addActor(btnSalir);
        escenario.addActor(trianguloEnfadado);
        escenario.addActor(cubo);

    }


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
        trianguloEnfadado.destroy();
        cubo.destroy();
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
        trianguloEnfadado.destroy();
        cubo.destroy();
        mundoMenu.dispose();
        escenario.dispose();
        skin.dispose();
    }
}
