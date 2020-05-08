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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;

public class PantallaSelectorNivel extends PantallaBase{


        private Stage escenario;
        private TextButton btnNivel1;
        private ImageTextButton btnSalir;
        private Skin skin,skin2, skin3,skin4;
        private Music cancionMenu;

        private ActorSuelo suelo;
        private World mundoMenu;
        private ActorJugador trianguloEnfadado;
        private Vector2 posicionCubo;
        private Vector2 posicionSuelo;
        private Image fondo,titulo;

        private Texture btnSonidoActivado, btnSonidoDesactivado;

        public PantallaSelectorNivel(CubeSquare game) {
            super(game);
            escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            mundoMenu = new World(new Vector2(0, -15), true);
            skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
            skin2 = new Skin(Gdx.files.internal("skin/neon/skin/neon-ui.json"));
            skin3 = new Skin(Gdx.files.internal("skin/biological-attack/skin/biological-attack-ui.json"));
            skin4 = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
            cancionMenu = game.getManager().get("sonidos/cancionmenu.ogg");
            cancionMenu.setLooping(true);
        }

    /**
     * El método show permite mostrar la pantalla del selector de niveles. De momento solo
     * está disponible el nivel 1. Declara los botones necesarios para volver al menú
     * o iniciar un nivel. Crea la música de la pantalla. Crea el suelo y por último añade
     * al cubo y enemigos.
     *
     * @author Jesús de la Peña
     *
     * @return void
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


            //BOTÓN NIVEL
            btnNivel1 = new TextButton("Nivel 1", skin4);
            btnNivel1.setSize((float) (escenario.getWidth() * 0.2), (float) (escenario.getHeight() * 0.1));
            btnNivel1.setPosition((escenario.getWidth() / 2) - btnNivel1.getWidth() / 2, titulo.getY() - Constantes.PIXELS_IN_METER_Y);

            btnNivel1.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("clickeado btnJuego");
                    cancionMenu.stop();
                    PantallaJuego p =(PantallaJuego) (game.getPantallaJuego());
                    p.setTipoDeJuego(1);
                    PantallaMenu.setPantallaMenu(false);
                    game.setScreen(game.getPantallaJuego());
                }

                ;
            });

            //BOTÓN VOLVER AL MENU
            btnSalir = new ImageTextButton("Volver al menu", skin4);
            btnSalir.setSize((float) (escenario.getWidth() * 0.2), (float) (escenario.getHeight() * 0.1));
            btnSalir.setPosition(btnNivel1.getX(), (btnNivel1.getY()-btnNivel1.getHeight()) -Constantes.PIXELS_IN_METER_Y/4);
            btnSalir.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("clickeado btnSalir");
                    game.setScreen(game.getPantallaMenu());
                }
            });


            //CREAMOS EL BOTÓN DE SONIDO CON DOS TEXTURAS DISTINTAS
            btnSonidoActivado = game.getManager().get("sonido.png");
            btnSonidoDesactivado = game.getManager().get("sonidodesactivado.png");

            //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
            if (com.cubesquare.controlador.PantallaMenu.isSonido()) {
                cancionMenu.play();
            }

            //CREAMOS SUELO Y CUBO PARA QUE INTERACCIONEN INDEFINIDAMENTE EN LA PANTALLA DE MENÚ
            posicionCubo = new Vector2(5, 1.6F);
            posicionSuelo = new Vector2(0, 1.5f);
            suelo = Fabricas.sueloFactory(mundoMenu, new Texture("sueloTransparente.png"), Gdx.graphics.getWidth(),0, posicionSuelo);
            trianguloEnfadado = Fabricas.ActorFactory(mundoMenu, game.getManager().get("trianguloenfadado.png", Texture.class), posicionCubo);
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
            escenario.addActor(btnNivel1);
            escenario.addActor(btnSalir);
            escenario.addActor(trianguloEnfadado);

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
            trianguloEnfadado.destroy();
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
            cancionMenu.play();
        }

    /**
     * El método dispose pone fin a la pantalla que te permite seleccionar el nivel deseado.
     * Este método se activa siempre que salgamos de la pantalla.
     *
     * @author Jesús Jiménez
     */
        @Override
        public void dispose() {
            trianguloEnfadado.destroy();
            btnSonidoActivado.dispose();
            btnSonidoDesactivado.dispose();
            mundoMenu.dispose();
            escenario.dispose();
            skin.dispose();
        }
    }






