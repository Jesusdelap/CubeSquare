package com.cubesquare.modelo.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cubesquare.controlador.PantallaJuego;
import com.cubesquare.controlador.PantallaMenu;
import com.cubesquare.herramientas.Constantes;

public class ActorJugador extends Actor implements Destruible {

    private World mundo;
    private Texture texture;
    private Vector2 vector2;
    private Body body;
    private Fixture fixture;
    private boolean saltando;
    //private boolean saltoContinuo;
    private boolean fin;


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
        fixture = body.createFixture(box, 2.6f);
        fixture.setUserData("cubo");
        box.dispose();

        //mide 1*1 el personaje
        setSize( Constantes.PIXELS_IN_METER_X, Constantes.PIXELS_IN_METER_Y);

    }
    @Override
    public void act(float delta) {

        if (!PantallaMenu.isPantallaMenu()){
            if(!fin && (!PantallaJuego.getBtnMenu().getClickListener().isPressed())){
                body.setLinearVelocity(6f, body.getLinearVelocity().y);
                if (Gdx.input.isTouched() ) {
                    //saltoContinuo = false;
                    cuboSalto();
                }
            }
            if (PantallaJuego.getBtnMenu().getClickListener().isPressed()){
                body.setLinearVelocity(0,body.getLinearVelocity().y);
            }
            //if (this.isSaltando()){ al quitar este if se soluciona el problema de que flote en la caida
                body.applyForceToCenter(0,-20*1.2f,true);
            //}
        }else {
            cuboSalto();
        }

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - 0.5f) * Constantes.PIXELS_IN_METER_X, (body.getPosition().y - 0.5f) *Constantes.PIXELS_IN_METER_Y);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void destroy() {
        body.destroyFixture(fixture);
        mundo.destroyBody(body);
    }


    public void cuboSalto() {
        if(!saltando){
            Vector2 vectorBody = body.getPosition();
            body.applyLinearImpulse(0,20,vectorBody.x,vectorBody.y,true);
            saltando = true;
        }
    }

    public boolean isSaltando() {
        return saltando;
    }

    public void setSaltando(boolean saltando) {
        this.saltando = saltando;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }


    @Override
    public String toString() {
        return "ActorJugador{" +
                "mundo=" + mundo +
                ", texture=" + texture +
                ", vector2=" + vector2 +
                ", body=" + body +
                ", fixture=" + fixture +
                ", saltando=" + saltando +
                ", fin=" + fin +
                 super.toString()+
                 '}';
    }
}
