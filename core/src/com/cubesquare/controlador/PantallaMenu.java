package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaMenu extends PantallaBase {

	private Stage escenario;
	private Main juego;
	Texture cubo;
	private Image img;
	private TextButton btnJuego;
	private Skin skin;


	public PantallaMenu(Main game) {
		super(game);
		this.juego=game;
		escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));

	}

	@Override
	public void show() {
		cubo = new Texture("cubo.png");
		img = new Image(cubo);
		escenario.addActor(img);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

		btnJuego = new TextButton("Play", skin);
		btnJuego.setSize(200, 80);
		btnJuego.setPosition(40, 140);

		btnJuego.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// Take me to the game screen!
				juego.setScreen(juego.gameScreen);
			}
		});
	}

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
	}
}
