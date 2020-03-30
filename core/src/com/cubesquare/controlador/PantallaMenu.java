package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaMenu extends PantallaBase {

	private Stage escenario;
	private Texture cubo;
	private Image img;
	private TextButton btnJuego,btnSalir;
	private Skin skin;

	public PantallaMenu(Main game) {
		super(game);
		escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
		System.out.println("Resolucion: "+Gdx.graphics.getWidth()+"x"+Gdx.graphics.getHeight());
	}

	/**
	 *Este metodo se ejecuta al iniciar la pantalla
	 *
	 * @author Jesús Peña
	 *
	 * @return void
	 */
	@Override
	public void show() {

		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		Label titulo =new Label(" CubeSquare",skin);
		titulo.setPosition((escenario.getWidth()/2)-titulo.getWidth()/2, escenario.getHeight()-150);
		titulo.setFontScale(2);

		btnJuego = new TextButton("Play", skin);
		btnJuego.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getWidth()*0.1));
		btnJuego.setPosition(titulo.getX(), titulo.getY()-150);

		img = new Image(super.getGame().getManager().get("cubo.png",Texture.class));
		img.setPosition(btnJuego.getX()-150,btnJuego.getY()-50);

        btnJuego.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
				System.out.println("clickeado btnJuego");
				PantallaMenu.super.getGame().setScreen(PantallaMenu.super.getGame().getPantallaJuego());
			};
		});

        btnSalir  = new TextButton("Salir", skin);
        btnSalir.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getWidth()*0.1));
        btnSalir.setPosition(btnJuego.getX(), btnJuego.getY()-150);

        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clickeado btnSalir");
                System.exit(0);
            };
        });

		escenario.addActor(titulo);
		escenario.addActor(btnJuego);
        escenario.addActor(btnSalir);
		escenario.addActor(img);
		Gdx.input.setInputProcessor(escenario);
	}

	/**
	 *Se llama cada vez que se actualiza la pantalla (30 o 60 veces por segundo dependiedo del dispositivo)
	 *
	 * @author Jesús Peña
	 *
	 * @param delta tiempo de diferencia entre el anterior fotograma y este
	 * @return void
	 */
	@Override
	public void render (float delta) {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		escenario.act();
		escenario.draw();
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	/**
     *Este metodo se ejecuta al cerrar la pantalla,
     * destrulle todos los recursos utilizados
     *
     * @author Jesús Peña
     *
     * @return void
     */
	@Override
	public void dispose () {
		cubo.dispose();
		escenario.dispose();
		skin.dispose();
	}
}
