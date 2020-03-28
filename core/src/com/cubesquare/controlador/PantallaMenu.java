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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaMenu extends PantallaBase {

	private Stage escenario;
	private Texture cubo;
	private Image img;
	private TextButton btnJuego;
	private Skin skin;


	public PantallaMenu(Main game) {
		super(game);
		escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));

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

		img = new Image(super.getGame().getManager().get("cubo.png",Texture.class));
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		btnJuego = new TextButton("Play", skin);
		btnJuego.setSize(200, 80);
		btnJuego.setPosition(40, 140);

		btnJuego.addListener( new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clickeado");
				PantallaMenu.super.getGame().setScreen(PantallaMenu.super.getGame().getPantallaJuego());
			};
		});




		escenario.addActor(btnJuego);
		escenario.addActor(img);
		Gdx.input.setInputProcessor(escenario);
	}

	/**
	 *Se llama cada vez que se actualiza la pantalla 30 o 60 veces por segundo dependiedo del dispositivo
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
	public void dispose () {
		cubo.dispose();
		escenario.dispose();
		skin.dispose();
	}
}
