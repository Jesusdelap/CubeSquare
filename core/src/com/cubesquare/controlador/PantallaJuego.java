package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorPincho;
import com.cubesquare.modelo.entidades.ActorSuelo;

public class PantallaJuego extends PantallaBase {

    private Stage escenario;
    private World mundo;
    private ActorSuelo suelo;
    private ActorJugador jugador;
    private ActorPincho pincho;
    private OrthographicCamera camara;

    public PantallaJuego(Main game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        mundo = new World(new Vector2(0, -10), true); // Nuevo mundo de gravedad en eje Y = -10
        camara = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {

       // mundo.setGravity(new Vector2(0, -10));

        Texture texturaJugador = game.getManager().get("cubo.png");
        Texture texturaPincho = game.getManager().get("spike.png");

        suelo = Fabricas.sueloFactory(mundo);
        jugador = new ActorJugador(mundo, texturaJugador, new Vector2(2.5f, 6));
        pincho = new ActorPincho(mundo, texturaPincho, new Vector2(2, 1));
        escenario.addActor(pincho);
        escenario.addActor(suelo);
        escenario.addActor(jugador);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        mundo.step(delta, 6, 2);
        camara.update();
        escenario.draw();
    }

    @Override
    public void dispose() {
        escenario.dispose();
        suelo.destroy();
        jugador.destroy();
        mundo.dispose();
    }
}
