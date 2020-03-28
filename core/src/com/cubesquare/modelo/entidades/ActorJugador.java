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

public class ActorJugador extends Actor implements Destruible {

    private World mundo;
    private Texture texture;
    private Vector2 vector2;
    private Body body;
    private Fixture fixture;

    public ActorJugador(World world, Texture texture, Vector2 vector2) {
        this.mundo = world;
        this.texture = texture;
        this.vector2 = vector2;

        BodyDef def = new BodyDef();
        def.position.set(vector2.x,vector2.y);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 3);
        fixture.setUserData("cubo");
        box.dispose();

        setSize( 100, 100);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * 100, (body.getPosition().y - 0.5f) * 100);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {
        body.destroyFixture(fixture);
        mundo.destroyBody(body);
    }

   /* @Override
    public void act(float delta) {
        float velocidadEjeY = body.getLinearVelocity().y;
        body.setLinearVelocity(8, velocidadEjeY);
    }*/

    public void jump() {
        Vector2 position = body.getPosition();
       // body.applyLinearImpulse(0, es.danirod.jddprototype.game.Constants.IMPULSE_JUMP, position.x, position.y, true);

    }
}
