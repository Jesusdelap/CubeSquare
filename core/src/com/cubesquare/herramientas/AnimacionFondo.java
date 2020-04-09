package com.cubesquare.herramientas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimacionFondo {
    public int x,y;
    private Animation animacion;
    private float tiempo;
    private TextureRegion[] regiones;
    private Texture imagen;
    private TextureRegion frameActual;

    public AnimacionFondo(int x, int y) {
        this.x = x;
        this.y = y;
        //CARGAMOS LA IMAGEN Y LA DIVIDIMOS, ALMACENANDOLA EN UN ARRAY BIDIMENSIONAL DE REGIONES
        imagen = new Texture(Gdx.files.internal("fondoparaanimar.png"));
        TextureRegion [][] tmp = TextureRegion.split(imagen,
                imagen.getWidth()/2,imagen.getHeight());

        //LO ALMACENAMOS EN UN ARRAY UNIDIMENSIONAL
        regiones = new TextureRegion[2];
        for (int i = 0; i <2; i++) {
            regiones[i] = tmp[0][i];
        }

        //CONVIERTIMOS LA ANIMACION A PARTIR DEL ARRAY ANTERIOR Y LE DAMOS UNA VELOCIDAD A LOS FRAMES (SEGUNDOS)
        animacion = new Animation(1/2f, regiones);
        tiempo = 0f;

    }

    //DIBUJAMOS LA ANIMACION A PARTIR DE LOS FRAMES ANTERIORES
    public void render(final SpriteBatch batch) {
        tiempo += Gdx.graphics.getDeltaTime();
        frameActual = (TextureRegion) animacion.getKeyFrame(tiempo,true);
        batch.draw(frameActual,x,y);
    }

}

