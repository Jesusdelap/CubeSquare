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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.controlador.CubeSquare;
import com.cubesquare.controlador.PantallaBase;
import com.cubesquare.controlador.PantallaJuego;
import com.cubesquare.controlador.PantallaMenu;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorSuelo;

import javax.xml.soap.Text;

public class PantallaTutorial extends PantallaBase {

    private Stage escenario;
    private ImageTextButton btnSalir;
    private Skin skin,skin2, skin3,skin4;
    private Music cancionMenu;

    private World mundoMenu;
    private Image fondo,titulo;

    private Label textoTutorial, textoInstrucciones;

    private Table tabla;

    public PantallaTutorial(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundoMenu = new World(new Vector2(0, -15), true);
        skin4 = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
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

        //TEXTO TUTORIAL
        textoTutorial=new Label("COMO JUGAR",skin4);
        textoTutorial.setFontScale(2);

        //TEXTO INSTRUCCIONES
        textoInstrucciones= new Label("\n\n #  Toca la pantalla para saltar. Manten para seguir saltando." +
                "\n\n #  Salta para evitar chocar con los triangulos y escalones." +
                "\n\n #  Pulsa el boton menu para salir del juego y volver al menu." +
                "\n\n #  Para hacer el juego mas dificil, no se puede pausar. ",skin4);
        textoInstrucciones.setFontScale(escenario.getViewport().getScreenWidth()*0.001f);

        //BOTÓN VOLVER AL MENU
        btnSalir = new ImageTextButton("Volver", skin4);
        btnSalir.setSize( escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnSalir.getLabel().setFontScale(escenario.getViewport().getScreenWidth()*0.001f);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaMenu());
            }
        });


        //TABLA QUE CONTIENE LAS INSTRUCCIONES Y EL BOTON
        tabla = new Table(skin4);
        tabla.padBottom(100);
        tabla.setFillParent(true);
        tabla.center();
        tabla.add(titulo);
        tabla.row();
        tabla.add(textoTutorial);
        tabla.row();
        tabla.add(textoInstrucciones);
        tabla.row();
        tabla.add(btnSalir);

        //ACTIVAMOS EL SONIDO SÓLO SI SU VARIABLE DE CONTROL ESTÁ ACTIVA. EN CASO CONTRARIO, EL LOGO DE SONIDO ES EL DE DESACTIVADO
        if (PantallaMenu.isSonido()) {
            cancionMenu.play();
        }

        //AÑADIMOS TODOS LOS ACTORES AL ESCENARIO
        escenario.addActor(fondo);
        escenario.addActor(tabla);

    }

    @Override
    public void render(float delta) {
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
        cancionMenu.play();
    }


    @Override
    public void dispose() {
        escenario.dispose();
        skin4.dispose();
    }
}