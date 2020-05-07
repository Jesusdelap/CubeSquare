package com.cubesquare.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PantallaCarga extends PantallaBase {
    private Stage escenario;
    private Label carga;
    private Skin skin;

    /**
     * Constructor de la clase PantallaCarga. Nos permite declarar todos los parámetros,
     * siendo el parámetro que se hereda game. Cargamos también el gráfico de la pantalla.
     *
     * @autor Jesús Jiménez
     * @param game
     */

    public PantallaCarga(CubeSquare game) {
        super(game);
        escenario = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("skin/star-soldier/skin/star-soldier-ui.json"));
        carga = new Label("¡Cargando! ¡Espere, por favor!", skin);

    }

    /**
     * El método show muestra la pantalla de carga. Añade el escenario y escala
     * las letras y botones que se puedan poner en la pantalla.
     *
     * @author Jesús Jiménez
     */

    @Override
    public void show() {
        carga.setSize((float) (escenario.getWidth()*0.2), (float) (escenario.getHeight()*0.1));
        carga.setPosition(carga.getWidth(), carga.getHeight());
        carga.setFontScale(2);
        escenario.addActor(carga);
    }
    /**
     * El método render es el que se encarga de que la pantalla de
     * carga haga una imagen (frame).
     * Llama al parametro delta, que es la cantidad de segundos entre imágenes. (frames)
     * Este método se encarga de poner el color de la fuente y un mensaje de cargando que progresa
     * según los recursos estén disponibles.
     *
     * @author Jesús Jiménez
     * @param delta
     */
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(game.getManager().update()){
            game.finCarga();
        } else {
            int progreso = (int) (game.getManager().getProgress() * 100);
            carga.setText("Cargando... " + progreso + "%");
            System.out.println("cargando");
        }

        escenario.act();
        escenario.draw();
    }
    @Override
    public void hide() {

    }
    /**
     * El método dispose pone fin  la pantalla de carga cuando todos los recursos
     * están disponibles, saltando a la siguiente pantalla.
     *
     * @author Jesús Jiménez
     */
    @Override
    public void dispose() {
        skin.dispose();
        escenario.dispose();
    }

}