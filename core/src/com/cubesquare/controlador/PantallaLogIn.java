package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Constantes;

public class PantallaLogIn extends PantallaBase {
    private Stage escenario;
    private Label lblLogIn,lblRegister;
    private TextField logInName,logInPass, registerName, registerAlias, registerPass;
    private Skin skin;
    private Image fondo;
    private TextButton btnSalir;


    public PantallaLogIn(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));

    }

    public void show() {


        //fondo y lvls
        fondo = new Image(game.getManager().get("fondoestrella2.png", Texture.class));
        fondo.setFillParent(true);
        lblLogIn = new Label("Log In", skin);
        lblLogIn.setPosition(escenario.getWidth()/4 - lblLogIn.getWidth(), escenario.getHeight()*0.90f - lblLogIn.getHeight()/2);
        lblLogIn.setFontScale(2);


        lblRegister = new Label("Log In", skin);
        lblRegister.setPosition(escenario.getWidth()/4 - lblRegister.getWidth(), escenario.getHeight()*0.90f - lblRegister.getHeight()/2);
        lblRegister.setFontScale(2);

        //TextField LOG IN

        logInName = new TextField("Usuario",skin);
        logInName.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        logInName.setPosition(lblLogIn.getX()-Constantes.PIXELS_IN_METER_X*0.75f, lblLogIn.getY()-(Constantes.PIXELS_IN_METER_Y+logInName.getWidth()/2));

        logInPass = new TextField("Contraseña",skin);
        logInPass.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        logInPass.setPosition(lblLogIn.getX()-Constantes.PIXELS_IN_METER_X*0.75f, logInName.getY()-(Constantes.PIXELS_IN_METER_Y+logInPass.getWidth()/2));





        //BOTÓN VOLVER AL MENU
        btnSalir = new TextButton("Volver al menu", skin);
        btnSalir.setSize(escenario.getWidth()*0.2f, escenario.getHeight()*0.1f);
        btnSalir.setPosition(escenario.getWidth()/30,escenario.getHeight()/14);
        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaMenu());
            }
        });
        Gdx.input.setInputProcessor(escenario);


        escenario.addActor(fondo);
        escenario.addActor(lblLogIn);
        escenario.addActor(lblRegister);

        escenario.addActor(logInName);
        escenario.addActor(logInPass);


        escenario.addActor(btnSalir);

    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw(); }

    @Override
    public void hide() {

    }


    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        skin.dispose();
        escenario.dispose();
    }


}
