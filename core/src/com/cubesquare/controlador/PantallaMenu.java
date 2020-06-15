package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.herramientas.HttpHerramientas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.color;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class PantallaMenu extends PantallaBase {

    private Stage escenario;
    private TextButton btnJuego;
    private ImageTextButton btnTutorial;
    private TextButton btnSalir, btnRanking, btnLogIn;
    private Skin skin, skin4;
    private Music cancionMenu;
    private Label creditos;

    private ActorSuelo suelo;
    private World mundoMenu;
    private ActorJugador cubo;
    private Vector2 posicionCubo;
    private Vector2 posicionSuelo;
    private Image fondo, titulo;

    private Texture btnSonidoActivado, btnSonidoDesactivado, texturaGit, texturaLibgdx;
    private Button btnSonido, btnGit, btnLibgdx;
    public static boolean primerInicio = true;
    private static boolean sonido = false;
    private static boolean pantallaMenu = true;

    private Container contenedor;
    private Table tablaMenu;

    public static void setPantallaMenu(boolean pantallaMenu) {
        PantallaMenu.pantallaMenu = pantallaMenu;
    }

    public static boolean isPantallaMenu() {
        return pantallaMenu;
    }

    public static boolean isSonido() {
        return sonido;
    }

    public PantallaMenu(CubeSquare game) {
        super(game);

        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundoMenu = new World(new Vector2(0, -15), true);
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        skin4 = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
        cancionMenu = game.getManager().get("sonidos/cancionmenu.ogg");
        cancionMenu.setLooping(true);

        System.out.println("Resolucion: " + Gdx.graphics.getWidth() + " X:" + Gdx.graphics.getHeight());

    }



    @Override
    public void show() {
        //ACTIVAMOS EL INPUT PROCESSOR PARA EL ESCENARIO
        Gdx.input.setInputProcessor(escenario);

        //CREAMOS FONDO DE PANTALLA
        fondo = new Image(game.getManager().get("fondoEspacio.png", Texture.class));
        fondo.setFillParent(true);

        //TABLA QUE CONTENDRA TODOS LOS ACTORES DEL MENU
        tablaMenu = new Table();
        tablaMenu.setFillParent(true);
        tablaMenu.center();

        //IMAGEN CON EL TÍTULO DEL JUEGO
        titulo = new Image(game.getManager().get("titulo.png", Texture.class));

        //BOTÓN JUEGO
        btnJuego = new TextButton("Jugar", skin4);
        btnJuego.setSize(tablaMenu.getWidth() * 0.2f, tablaMenu.getHeight() * 0.1f);
        btnJuego.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);

        btnJuego.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnJuego infinito");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                getGame().setScreen(PantallaMenu.super.getGame().getPantallaSkins());
            }
        });

        //BOTÓN TUTORIAL
        btnTutorial = new ImageTextButton("Tutorial", skin4);
        btnTutorial.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnTutorial.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);

        btnTutorial.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnTutorial");
                PantallaJuego p = (PantallaJuego) (game.getPantallaJuego());
                getGame().setScreen(PantallaMenu.super.getGame().getPantallaTutorial());
            }

            ;
        });

        //BOTÓN Ranking
        btnRanking = new TextButton("ranking", skin4);
        btnRanking.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnRanking.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnRanking.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnRanking");

                if (game.getAccesoDatos().ping()) {
                    getGame().setScreen(getGame().getPantallaRanking());
                }else{
                    Beans.popUp(escenario,skin4,"ERROR","Sin Conexion");
                    Gdx.app.log("PantallaMenu/btnRanking","no Connexion");
                }
            }

        });



        //BOTÓN LogIn
        btnLogIn = new TextButton("LogIn", skin4);
        btnLogIn.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnLogIn.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnLogIn.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnRanking");
                if (game.getAccesoDatos().ping()) {
                    getGame().setScreen(getGame().getPantallaLogIn());
                }else{
                    Gdx.app.log("PantallaMenu/btnLogIn","no Connexion");
                    Beans.popUp(escenario,skin4,"ERROR","Sin Conexion");
                }
            }

            ;
        });

        //BOTÓN SALIR
        btnSalir = new TextButton("Salir", skin4);
        btnSalir.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnSalir.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                Gdx.app.exit();
            }
        });

        //LABEL DE CRÉDITOS
        creditos = new Label("Desarrollado por:\nDIEGO CORRAL GONZALEZ, JESUS DE LA PENA Y JESUS JIMENEZ COZAR", skin);
        creditos.setFontScale(Constantes.TAMAÑOTEXTO / 1.50f);
        creditos.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HttpHerramientas.abrirUrl("https://github.com/Jesusdelap/CubeSquare.git");
            }
        });

        //ICONO ENLACE GIT
        texturaGit = game.getManager().get("iconoGithub.png");
        SpriteDrawable git = new SpriteDrawable(new Sprite(texturaGit));
        btnGit = new Button(new Button.ButtonStyle(git, git, git));
        ;
        btnGit.setSize(Constantes.PIXELS_IN_METER_X*0.75f, Constantes.PIXELS_IN_METER_Y*0.75f);
        btnGit.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HttpHerramientas.abrirUrl("https://github.com/Jesusdelap/CubeSquare.git");
            }
        });

        //ICONO ENLACE LIBGDX
        texturaLibgdx = game.getManager().get("iconoLibgdx.png");
        SpriteDrawable libgdx = new SpriteDrawable(new Sprite(texturaLibgdx));
        btnLibgdx = new Button(new Button.ButtonStyle(libgdx, libgdx, libgdx));
        ;
        btnLibgdx.setSize(Constantes.PIXELS_IN_METER_X*1.5f, Constantes.PIXELS_IN_METER_Y*0.75f);
        btnLibgdx.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HttpHerramientas.abrirUrl("https://libgdx.badlogicgames.com/");
            }
        });

        //ANIMAMOS LOS CRÉDITOS EN EL PRIMER INICIO DE LA APP
        if (primerInicio) {
            creditos.addAction(sequence(moveTo(Gdx.graphics.getWidth() - (creditos.getMinWidth() + btnGit.getWidth() + btnLibgdx.getWidth()+15), 10, 2.5f), color(new Color(1.08f,0.7f,1.17f,1), 3), delay(0.5f)));
            btnGit.addAction(sequence(moveTo((Gdx.graphics.getWidth() - (btnGit.getWidth() + btnLibgdx.getWidth()+15)), 23, 2.5f), color(new Color(1.08f,0.7f,1.17f,1), 3), delay(0.5f)));
            btnLibgdx.addAction(sequence(moveTo(Gdx.graphics.getWidth() - (btnLibgdx.getWidth() + 10), 23, 2.5f)));
            primerInicio = false;
        } else {
            creditos.setPosition(Gdx.graphics.getWidth() - (creditos.getMinWidth() + btnGit.getWidth() + btnLibgdx.getWidth()+15), 10);
            creditos.setColor(1.08f,0.7f,1.17f,1);
            btnGit.setPosition(Gdx.graphics.getWidth() - (btnGit.getWidth() + btnLibgdx.getWidth()+15), 23);
            btnGit.setColor(1.08f,0.7f,1.17f,1);
            btnLibgdx.setPosition(Gdx.graphics.getWidth() - (btnLibgdx.getWidth()+10), 23);
        }
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

        contenedor = new Container(tablaMenu);
        contenedor.setActor(tablaMenu);
        contenedor.setActor(creditos);
        contenedor.setActor(btnLibgdx);
        contenedor.setActor(btnGit);
        contenedor.setFillParent(true);
        contenedor.center();

        tablaMenu.setFillParent(true);
        tablaMenu.add(titulo).colspan(2);
        tablaMenu.row();
        tablaMenu.add(btnJuego).width(escenario.getWidth() * 0.2f).height(escenario.getHeight() * 0.1f).colspan(2);
        tablaMenu.row();
        tablaMenu.add(btnTutorial).width(escenario.getWidth() * 0.2f).height(escenario.getHeight() * 0.1f).colspan(2);
        tablaMenu.row();
        tablaMenu.add(btnLogIn).width(escenario.getWidth() * 0.2f).height(escenario.getHeight() * 0.1f).colspan(2);
        tablaMenu.row();
        tablaMenu.add(btnRanking).width(escenario.getWidth() * 0.2f).height(escenario.getHeight() * 0.1f).expandX().padLeft(350);
        tablaMenu.add(btnSonido).width(50).height(50).padRight(300);
        tablaMenu.row();
        tablaMenu.add(btnSalir).width(escenario.getWidth() * 0.2f).height(escenario.getHeight() * 0.1f).colspan(2);
        tablaMenu.row();



        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        cancionMenu.setVolume(0.5f);
        if (sonido) {
            cancionMenu.play();
        }

        //CREAMOS SUELO Y CUBO PARA QUE INTERACCIONEN INDEFINIDAMENTE EN LA PANTALLA DE MENÚ
        if (primerInicio) {
            posicionCubo = new Vector2(5, 10);
            primerInicio = false;
        } else {
            posicionCubo = new Vector2(5, 1.6f);
        }

        posicionSuelo = new Vector2(0, 1.5f);
        suelo = Fabricas.sueloFactory(mundoMenu, new Texture("sueloTransparente.png"), Gdx.graphics.getWidth(), 0, posicionSuelo);
        cubo = Fabricas.ActorFactory(mundoMenu, game.getManager().get(PantallaSkins.getTipoCubo(), Texture.class), posicionCubo);
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
        escenario.addActor(contenedor);
        escenario.addActor(fondo);
        escenario.addActor(suelo);
        escenario.addActor(tablaMenu);

        escenario.addActor(cubo);
        escenario.addActor(creditos);
        escenario.addActor(btnGit);
        escenario.addActor(btnLibgdx);


    }



    @Override
    public void render(float delta) {
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

//titulo.setPosition((escenario.getWidth() / 2) - titulo.getWidth() / 2, escenario.getHeight() - 360);
//btnJuego.setPosition((escenario.getWidth() / 2) - btnJuego.getWidth() / 2, titulo.getY() - Constantes.PIXELS_IN_METER_Y);
//btnTutorial.setPosition((escenario.getWidth() / 2) - btnJuego.getWidth() / 2, (btnJuego.getY() - btnJuego.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
//btnLogIn.setPosition((escenario.getWidth() / 2) - btnRanking.getWidth() / 2, (btnRanking.getY() - btnRanking.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
//btnSalir.setPosition(btnLogIn.getX(), (btnLogIn.getY() - btnLogIn.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
//btnRanking.setPosition((escenario.getWidth() / 2) - btnTutorial.getWidth() / 2, (btnTutorial.getY() - btnTutorial.getHeight()) - Constantes.PIXELS_IN_METER_Y / 4);
//btnSonido.setSize(50, 50);
//btnSonido.setPosition(Gdx.graphics.getWidth() - 200, 150);

/**/
        /*escenario.addActor(btnSonido);
        escenario.addActor(creditos);
        escenario.addActor(btnGit);
        escenario.addActor(btnLibgdx);
        escenario.addActor(btnJuego);
        escenario.addActor(btnSalir);
        escenario.addActor(titulo);
        escenario.addActor(btnRanking);
        escenario.addActor(btnLogIn);
        escenario.addActor(btnTutorial);*/
//tabla.add(btnSonido.right());
//tabla.row();
//tabla.add(creditos,btnGit,btnLibgdx).right();
//LIMPIAMOS PANTALLA Y ACTUALIZAMOS EL ESCENARIO EN CADA FOTOGRAMA PARA DIBUJARLO
//Gdx.gl.glClearColor(0, 0.2f, 0.8f, 1);
/*tablaMenu.add(creditos).width(30).height(30).right();
        tablaMenu.add(btnGit).width(40).height(40).right();
        tablaMenu.add(btnLibgdx).width(60).height(20).right();*/