package com.cubesquare.controlador;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.cubesquare.datos.AccesoDatos;
import com.cubesquare.modelo.Usuario;

//Main
public class CubeSquare extends Game {

    private AssetManager manager;
    private PantallaBase pantallaMenu,pantallaJuego,pantallaDerrota,pantallaCarga,pantallaBienvenida,pantallaSelectorNivel;
    private AccesoDatos accesoDatos;
    private Usuario usuario;

    /**
     * El método create carga todos los assets del juego, el usuario y la pantalla de carga.
     * Esto ayudará a Android Studio a procesar toda la información y que podamos ver el juego.
     *
     * @author Jesús Jiménez
     */

    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("cubo.png", Texture.class);
        manager.load("floor.png", Texture.class);
        manager.load("spike.png", Texture.class);
        manager.load("pincho.png", Texture.class);
        manager.load("trianguloenfadado.png", Texture.class);
        manager.load("sonido.png", Texture.class);
        manager.load("sonidodesactivado.png", Texture.class);
        manager.load("fondoEspacio.png", Texture.class);
        manager.load("gameover.png", Texture.class);
        manager.load("titulo.png", Texture.class);
        manager.load("sueloTransparente.png", Texture.class);
        manager.load("fondoestrella2.png", Texture.class);
        manager.load("fondoparaanimar.png", Texture.class);
        manager.load("sonidos/cancionmenu.ogg", Music.class);
        manager.load("sonidos/cancionjuego.ogg", Music.class);
        manager.load("sonidos/choque.wav", Sound.class);
        manager.load("sonidos/gameover.wav", Sound.class);

        usuario = new Usuario(1,"unknown","unknownUser","42");

        pantallaCarga = new PantallaCarga(this);
        setScreen(pantallaCarga);
    }
    /**
     * El método finCarga termina de inicializar las demás pantallas del juego.
     * También llama al usuario conectado y hace que podamos ver la pantalla de bienvenida.
     *
     * @author Jesús Jiménez
     */
    public void finCarga(){

        pantallaMenu = new PantallaMenu(this);
        pantallaJuego = new PantallaJuego(this);
        pantallaDerrota = new PantallaDerrota(this);
        pantallaBienvenida = new PantallaBienvenida(this);
        pantallaSelectorNivel = new PantallaSelectorNivel(this);
        accesoDatos = new AccesoDatos();
        setScreen(pantallaBienvenida);
        /*
        System.out.println("ping: "+accesoDatos.ping());
        System.out.println("Listar Records:"+accesoDatos.listarRecordsConAlias(5));
        accesoDatos.nuevoUsuario(new Usuario(1,"LESGEREE","eeee","123"));

         */
    }

    /**
     * Todos los getters y setters y el toString que usaremos más adelante.
     * @return manager
     * @return pantallaMenu
     * @return pantallaJuego
     * @return pantallaDerrota
     * @return pantallaSelectorNivel
     * @return screen
     * @return pantallaBienvenida
     * @return pantallaCarga
     * @return accesoDatos
     *
     * @author Jesús Jiménez
     */

    public AssetManager getManager() {
        return manager;
    }


    public PantallaBase getPantallaMenu() {
        return pantallaMenu;
    }


    public PantallaBase getPantallaJuego() {
        return pantallaJuego;
    }


    public PantallaBase getPantallaDerrota() {
        return pantallaDerrota;
    }


    public PantallaBase getPantallaSelectorNivel() {
        return pantallaSelectorNivel;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }

    public void setPantallaMenu(PantallaBase pantallaMenu) {
        this.pantallaMenu = pantallaMenu;
    }

    public void setPantallaJuego(PantallaBase pantallaJuego) {
        this.pantallaJuego = pantallaJuego;
    }

    public void setPantallaDerrota(PantallaBase pantallaDerrota) {
        this.pantallaDerrota = pantallaDerrota;
    }

    public PantallaBase getPantallaCarga() {
        return pantallaCarga;
    }

    public void setPantallaCarga(PantallaBase pantallaCarga) {
        this.pantallaCarga = pantallaCarga;
    }

    public PantallaBase getPantallaBienvenida() {
        return pantallaBienvenida;
    }

    public void setPantallaBienvenida(PantallaBase pantallaBienvenida) {
        this.pantallaBienvenida = pantallaBienvenida;
    }

    public void setPantallaSelectorNivel(PantallaBase pantallaSelectorNivel) {
        this.pantallaSelectorNivel = pantallaSelectorNivel;
    }

    public AccesoDatos getAccesoDatos() {
        return accesoDatos;
    }

    public void setAccesoDatos(AccesoDatos accesoDatos) {
        this.accesoDatos = accesoDatos;
    }

    @Override
    public String toString() {
        return "CubeSquare{" +
                "manager=" + manager +
                ", pantallaMenu=" + pantallaMenu +
                ", pantallaJuego=" + pantallaJuego +
                ", pantallaDerrota=" + pantallaDerrota +
                ", pantallaCarga=" + pantallaCarga +
                ", pantallaBienvenida=" + pantallaBienvenida +
                ", pantallaSelectorNivel=" + pantallaSelectorNivel +
                ", accesoDatos=" + accesoDatos +
                ", screen=" + screen +
                '}';
    }
}
