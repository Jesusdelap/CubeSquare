package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.datos.AccesoDatos;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.modelo.Usuario;

public class PantallaLogIn extends PantallaBase {
    private Stage escenario;
    private Label lblLogIn, lblRegister, lblusuario;
    private TextField logInName, logInPass, registerName, registerAlias, registerPass;
    private Skin skin;
    private Image fondo;
    private TextButton btnSalir, btnLogIn, btnRegister;


    public PantallaLogIn(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
    }

    public void show() {
        final AccesoDatos accesoDatos = new AccesoDatos();

        //fondo y lvls
        fondo = new Image(game.getManager().get("fondoestrella2.png", Texture.class));
        fondo.setFillParent(true);
        lblLogIn = new Label("Log in", skin);
        lblLogIn.setPosition(escenario.getWidth() / 4 - lblLogIn.getWidth(), escenario.getHeight() * 0.90f - lblLogIn.getHeight() / 2);
        lblLogIn.setFontScale(Constantes.TAMAÑOTEXTO);

        lblRegister = new Label("Registro", skin);
        lblRegister.setPosition(escenario.getWidth() * 0.75f - lblRegister.getWidth(), escenario.getHeight() * 0.90f - lblRegister.getHeight() / 2);
        lblRegister.setFontScale(Constantes.TAMAÑOTEXTO);

        //TextField LOG IN
        logInName = new TextField("Usuario", skin);
        logInName.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        logInName.setPosition(lblLogIn.getX() - Constantes.PIXELS_IN_METER_X * 0.75f, lblLogIn.getY() - (Constantes.PIXELS_IN_METER_Y / 2 + logInName.getWidth() / 2));
        logInName.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logInName.setText("");
            }
        });

        //TextField CONTRASEÑA
        logInPass = new TextField("Contrasena", skin);
        logInPass.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        logInPass.setPosition(lblLogIn.getX() - Constantes.PIXELS_IN_METER_X * 0.75f, logInName.getY() - (Constantes.PIXELS_IN_METER_Y / 2 + logInName.getWidth() / 2));
        logInPass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logInPass.setText("");
                logInPass.setPasswordMode(true);
                logInPass.setPasswordCharacter('*');
            }
        });

        btnLogIn = new TextButton("Confirmar", skin);
        btnLogIn.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnLogIn.setPosition(lblLogIn.getX() - Constantes.PIXELS_IN_METER_X * 1.1f, logInPass.getY() - (logInName.getWidth() / 2));
        btnLogIn.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnLogIn.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnLogIn");
                game.setUsuario(accesoDatos.logIn(logInName.getText().trim().toUpperCase(), logInPass.getText().trim()));
                game.setUsuario(accesoDatos.logIn(logInName.getText().trim().toUpperCase(), logInPass.getText().trim()));
                if (!game.getUsuario().getNombreUsuario().equals("unknownUser")) {
                    Beans.popUp(escenario, skin, "LogIn exitoso ", "Bienvenido " + game.getUsuario().getNombreUsuario());
                    System.out.println(logInName.getText() + "   " + logInPass.getText());
                    lblusuario.setText("Bienvenido " + game.getUsuario().getNombreUsuario());
                    lblusuario.setWidth(escenario.getWidth() - (lblusuario.getWidth()));

                } else {
                    Beans.popUp(escenario, skin, "ERROR", "Nombre de usuario o contraseña incorrecta");
                }
            }
        });

        //TextField REGISTER
        registerName = new TextField("Usuario", skin);
        registerName.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        registerName.setPosition(lblRegister.getX() - Constantes.PIXELS_IN_METER_X * 0.75f, lblRegister.getY() - (Constantes.PIXELS_IN_METER_Y / 3 + logInName.getWidth() / 2));
        registerName.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerName.setText("");
            }
        });

        registerAlias = new TextField("Alias", skin);
        registerAlias.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        registerAlias.setPosition(lblRegister.getX() - Constantes.PIXELS_IN_METER_X * 0.75f, registerName.getY() - (Constantes.PIXELS_IN_METER_Y / 3 + logInName.getWidth() / 2));
        registerAlias.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerAlias.setText("");
            }

            ;
        });

        registerPass = new TextField("Contrasena", skin);
        registerPass.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        registerPass.setPosition(lblRegister.getX() - Constantes.PIXELS_IN_METER_X * 0.75f, registerAlias.getY() - (Constantes.PIXELS_IN_METER_Y / 3 + logInName.getWidth() / 2));


        registerPass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                registerPass.setText("");
                registerPass.setPasswordMode(true);
                registerPass.setPasswordCharacter('*');
            }
        });

        btnRegister = new TextButton("Confirmar", skin);
        btnRegister.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnRegister.setPosition(lblRegister.getX() - Constantes.PIXELS_IN_METER_X * 1.1f, registerPass.getY() - (logInName.getWidth() / 2));
        btnRegister.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnRegister.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                btnRegister.setText("");
                System.out.println("clickeado btnRegister");
                accesoDatos.isUsuarioNombreLibre(registerName.getText());
                if (accesoDatos.isUsuarioNombreLibre(registerName.getText())) {
                    accesoDatos.addUsuario(new Usuario(0, registerAlias.getText().toUpperCase(), registerName.getText().toUpperCase(), registerPass.getText().toUpperCase()));
                    Beans.popUp(escenario, skin, "Registro exitoso ", "  Bienvenido " + registerName.getText().toUpperCase() + "\n Haz log in para poder jugar con tu cuenta");
                } else {
                    Beans.popUp(escenario, skin, "ERROR", "Nombre de usuario no disponible");
                }

            }
        });

        //BOTÓN VOLVER AL MENU
        btnSalir = new TextButton("Atras", skin);
        btnSalir.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnSalir.setPosition(escenario.getWidth() / 30, escenario.getHeight() / 14);
        btnSalir.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaMenu());
            }
        });

        lblusuario = new Label("Bienvenido " + game.getUsuario().getNombreUsuario(), skin);
        lblusuario.setFontScale(Constantes.TAMAÑOTEXTO);
        lblusuario.setPosition(escenario.getWidth() - (lblusuario.getWidth() * 2), escenario.getHeight() / 14);

        Gdx.input.setInputProcessor(escenario);
        escenario.addActor(fondo);
        escenario.addActor(lblLogIn);
        escenario.addActor(lblRegister);
        escenario.addActor(logInName);
        escenario.addActor(logInPass);
        escenario.addActor(btnLogIn);
        escenario.addActor(btnSalir);
        escenario.addActor(registerName);
        escenario.addActor(registerAlias);
        escenario.addActor(registerPass);
        escenario.addActor(btnRegister);
        escenario.addActor(lblusuario);

    }

    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        escenario.draw();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        game.getPantallaMenu().pause();
    }

    @Override
    public void resume() {
        game.getPantallaMenu().resume();
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        skin.dispose();
        escenario.dispose();
    }


}
