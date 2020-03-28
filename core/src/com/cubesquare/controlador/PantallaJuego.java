package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.cubesquare.herramientas.Fabricas;
import com.cubesquare.modelo.entidades.ActorJugador;
import com.cubesquare.modelo.entidades.ActorPincho;
import com.cubesquare.modelo.entidades.ActorSuelo;

public class PantallaJuego extends PantallaBase {

    private  Stage escenario;
    private World world;
    private ActorSuelo suelo;
    private ActorJugador jugador;
    private ActorPincho pincho;

    public PantallaJuego(Main game) {
        super(game);

    }

    @Override
    public void show() {
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        world = new World(new Vector2(0, 0),true);
        world.setGravity(new Vector2(0,-15));

        suelo = Fabricas.sueloFactory(world);
        jugador = new ActorJugador( world, new Texture("cubo.png"), new Vector2(2,5 ));
        pincho = new ActorPincho( world, new Texture("pincho.png"), new Vector2(2,1 ));
        escenario.addActor(pincho);
        escenario.addActor(suelo);
        escenario.addActor(jugador);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escenario.act();
        world.step(delta, 6, 2);
        escenario.draw();
    }




    @Override
    public void dispose() {
        escenario.dispose();
        suelo.destroy();
        jugador.destroy();
        world.dispose();
    }



}
