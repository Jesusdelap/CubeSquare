package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.datos.AccesoDatos;
import com.cubesquare.herramientas.Beans;
import com.cubesquare.herramientas.Constantes;
import com.cubesquare.modelo.Usuario;

public class PantallaLogIn extends PantallaBase {
    private Stage escenario;
    private Label lblLogIn,lblRegister,lblusuario;
    private TextField logInName,logInPass, registerName, registerAlias, registerPass;
    private Skin skin;
    private Image fondo;
    private TextButton btnSalir,btnLogIn,btnRegister;


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
        lblLogIn = new Label("Log In", skin);
        lblLogIn.setPosition(escenario.getWidth()/4 - lblLogIn.getWidth(),escenario.getHeight()*0.90f - lblLogIn.getHeight()/2);
        lblLogIn.setFontScale(Constantes.TAMAÑOTEXTO);

        lblRegister = new Label("Register", skin);
        lblRegister.setPosition(escenario.getWidth()*0.75f - lblRegister.getWidth(),escenario.getHeight()*0.90f - lblRegister.getHeight()/2);
        lblRegister.setFontScale(Constantes.TAMAÑOTEXTO);

        //TextField LOG IN
        logInName = new TextField("Usuario",skin);
        logInName.setSize(escenario.getWidth() * 0.15f, escenario.getHeight() * 0.07f);
        logInName.setPosition(lblLogIn.getX()-Constantes.PIXELS_IN_METER_X*0.75f, lblLogIn.getY()-(Constantes.PIXELS_IN_METER_Y/2+logInName.getWidth()/2));

        logInPass = new TextField("Contraseña",skin);
        logInPass.setSize(escenario.getWidth()*0.15f, escenario.getHeight()*0.07f);
        logInPass.setPosition(lblLogIn.getX()-Constantes.PIXELS_IN_METER_X*0.75f, logInName.getY()-(Constantes.PIXELS_IN_METER_Y/2+logInName.getWidth()/2));

        btnLogIn = new TextButton("LogIn", skin);
        btnLogIn.setSize(escenario.getWidth()*0.15f,escenario.getHeight()*0.1f);
        btnLogIn.setPosition(lblLogIn.getX()-Constantes.PIXELS_IN_METER_X*0.75f, logInPass.getY()-(logInName.getWidth()/2));
        btnLogIn.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnLogIn.addCaptureListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnLogIn");
                game.setUsuario(accesoDatos.logIn(logInName.getText().trim(),logInPass.getText().trim()));
                if(!game.getUsuario().getNombreUsuario().equals("unknownUser")){
                    Beans.popUp(escenario,skin,"LogIn exitoso ","Bienvenido "+game.getUsuario().getNombreUsuario());
                    System.out.println(logInName.getText()+"   "+logInPass.getText());
                }else{
                    Beans.popUp(escenario,skin,"ERROR","Nombre de usuario o contraseña incorrecta");
                }
            }
        });

        //TextField REGISTER
        registerName = new TextField("Usuario",skin);
        registerName.setSize(escenario.getWidth()*0.15f, escenario.getHeight()*0.07f);
        registerName.setPosition(lblRegister.getX()-Constantes.PIXELS_IN_METER_X*0.75f, lblRegister.getY()-(Constantes.PIXELS_IN_METER_Y/3+logInName.getWidth()/2));

        registerAlias = new TextField("Alias",skin);
        registerAlias.setSize(escenario.getWidth()*0.15f,escenario.getHeight()*0.07f);
        registerAlias.setPosition(lblRegister.getX()-Constantes.PIXELS_IN_METER_X*0.75f, registerName.getY()-(Constantes.PIXELS_IN_METER_Y/3+logInName.getWidth()/2));

        registerPass = new TextField("Password",skin);
        registerPass.setSize(escenario.getWidth()*0.15f, escenario.getHeight()*0.07f);
        registerPass.setPosition(lblRegister.getX()-Constantes.PIXELS_IN_METER_X*0.75f, registerAlias.getY()-(Constantes.PIXELS_IN_METER_Y/3+logInName.getWidth()/2));

        btnRegister = new TextButton("Register", skin);
        btnRegister.setSize(escenario.getWidth()*0.15f, escenario.getHeight()*0.1f);
        btnRegister.setPosition(lblRegister.getX()-Constantes.PIXELS_IN_METER_X*0.75f, registerPass.getY()-(logInName.getWidth()/2));
        btnRegister.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnRegister.addCaptureListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("clickeado btnRegister");
                if(accesoDatos.isUsuarioNombreLibre(registerName.getText())){
                    accesoDatos.addUsuario(new Usuario(0,registerAlias.getText(),registerName.getText(),registerPass.getText()));
                    //game.setUsuario(accesoDatos.logIn(registerName.getText().trim(),registerPass.getText().trim()));
                    Beans.popUp(escenario,skin,"Registro exitoso ","Bienvenido "+game.getUsuario().getNombreUsuario());
                }else{
                    Beans.popUp(escenario,skin,"ERROR","Nombre de usuario o contraseña incorrecta");
                }

            }
        });

        //BOTÓN VOLVER AL MENU
        btnSalir = new TextButton("Volver al menu", skin);
        btnSalir.setSize(escenario.getWidth() * 0.2f, escenario.getHeight() * 0.1f);
        btnSalir.setPosition(escenario.getWidth()/30,escenario.getHeight()/14);
        btnSalir.getLabel().setFontScale(Constantes.TAMAÑOTEXTO);
        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                game.setScreen(game.getPantallaMenu());
            }
        });

        lblusuario = new Label("Bienvenido "+game.getUsuario().getNombreUsuario(),skin);
        lblusuario.setFontScale(Constantes.TAMAÑOTEXTO);
        lblusuario.setPosition(escenario.getWidth()-lblusuario.getWidth()-Constantes.PIXELS_IN_METER_X/2,escenario.getHeight()/14);

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
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        skin.dispose();
        escenario.dispose();
    }


}
