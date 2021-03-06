package com.cubesquare.modelo.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cubesquare.herramientas.Constantes;

public class ActorPincho extends Actor implements Destruible {

    private World mundo;
    private Texture texture;
    private Vector2 vector2;
    private Body body;
    private Fixture fixture;

    public ActorPincho(World world, Texture texture, Vector2 vector2) {
        this.mundo = world;
        this.texture = texture;
        this.vector2 = vector2;

        BodyDef def = new BodyDef();
        def.position.set(vector2.x , vector2.y+0.5f);
        body = world.createBody(def);

        PolygonShape triangulo = new PolygonShape();
        Vector2[] vertices = new Vector2[3];
        vertices[0] = new Vector2(-0.5f, -0.5f);
        vertices[1] = new Vector2(0.5f, -0.5f);
        vertices[2] = new Vector2(0, 0.50f);
        triangulo.set(vertices);

        fixture = body.createFixture(triangulo, 1);
        fixture.setUserData("pincho");
        triangulo.dispose();

        setSize( Constantes.PIXELS_IN_METER_X, Constantes.PIXELS_IN_METER_Y);
        setPosition((vector2.x - 0.5f)*Constantes.PIXELS_IN_METER_X, vector2.y* Constantes.PIXELS_IN_METER_Y);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {
        body.destroyFixture(fixture);
        mundo.destroyBody(body);
    }

    @Override
    public String toString() {
        return "ActorPincho{" +
                "mundo=" + mundo +
                ", texture=" + texture +
                ", vector2=" + vector2 +
                ", body=" + body +
                ", fixture=" + fixture +
                super.toString()+
                '}';
    }
}
