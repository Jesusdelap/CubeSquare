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

import javax.xml.soap.Text;

public class ActorJugador extends Actor implements Destruible {

    private World world;
    private Texture texture;
    private Vector2 vector2;
    private Body body;
    private Fixture fixture;

    public ActorJugador(World world, Texture texture, Vector2 vector2) {
        this.world = world;
        this.texture = texture;
        this.vector2 = vector2;

        BodyDef def = new BodyDef();
        def.position.set(vector2.x +0.5f,vector2.y- 0.5f);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(1, 1);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("cube");
        box.dispose();

        setSize( 100, 100);
        setPosition(vector2.x*100, vector2.y*100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setPosition((body.getPosition().x - 0.5f) * 100, (body.getPosition().y - 0.5f) * 100);

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
    public void jump() {

        Vector2 position = body.getPosition();
       // body.applyLinearImpulse(0, es.danirod.jddprototype.game.Constants.IMPULSE_JUMP, position.x, position.y, true);

    }
}
