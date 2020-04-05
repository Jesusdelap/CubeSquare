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

public class ActorSuelo extends Actor implements Destruible {

    private Texture texture;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    public ActorSuelo(World world, Texture floor, float width,float height, Vector2 vector2) {
        this.world = world;
        this.texture = floor;

        BodyDef def = new BodyDef();
        def.position.set(vector2.x + width/2,vector2.y+ height/2);
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height/2);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("suelo");
        box.dispose();

        BodyDef leftDef = new BodyDef();
        leftDef.position.set(vector2.x ,(vector2.y+ height/2)-0.02f);
        leftBody = world.createBody(leftDef);

        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.02f, height/2);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("pincho");
        leftBox.dispose();


        setSize(width * Constantes.PIXELS_IN_METER_X, height*Constantes.PIXELS_IN_METER_Y);
        setPosition(vector2.x * Constantes.PIXELS_IN_METER_X, vector2.y*Constantes.PIXELS_IN_METER_Y);

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        leftBody.destroyFixture(leftFixture);
        world.destroyBody(leftBody);
    }

    @Override
    public String toString() {
        return "ActorSuelo{" +
                "floor=" + texture +
                ", world=" + world +
                ", body=" + body +
                ", leftBody=" + leftBody +
                ", fixture=" + fixture +
                ", leftFixture=" + leftFixture +
                super.toString()+'}';
    }
}
