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
    private PantallaBase pantallaMenu;
    private PantallaBase pantallaJuego;
    private PantallaBase pantallaDerrota;
    private PantallaBase pantallaCarga;
    private PantallaBase pantallaBienvenida;
    private PantallaBase pantallaSelectorNivel;
    private PantallaBase pantallaTutorial;
    private PantallaBase pantallaTipoJuego;


    private PantallaBase pantallaRanking,pantallaLogIn;
    private AccesoDatos accesoDatos;
    private Usuario usuario;

    /**
     * El método create carga todos los assets del juego, el usuario y la pantalla de carga.
     *
     * @author JesusPeña,Diego Corral,Jesús Jiménez
     */

    @Override
    public void create() {
        manager = new AssetManager();
        manager.load("iconoGit.png", Texture.class);
        manager.load("iconoGithub.png", Texture.class);
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
     * El método finCarga termina de cargar todos los recursos del juego
     *
     * @author JesusPeña,Diego Corral,Jesús Jiménez
     */
    public void finCarga(){

        pantallaMenu = new PantallaMenu(this);
        pantallaJuego = new PantallaJuego(this);
        pantallaDerrota = new PantallaDerrota(this);
        pantallaBienvenida = new PantallaBienvenida(this);
        pantallaSelectorNivel = new PantallaSelectorNivel(this);
        pantallaRanking = new PantallaRanking(this);
        pantallaLogIn = new PantallaLogIn(this);
        pantallaTutorial = new PantallaTutorial(this);
        pantallaTipoJuego = new PantallaTipoJuego(this);
        accesoDatos = new AccesoDatos();

        setScreen(pantallaBienvenida);

    }


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

    public PantallaBase getPantallaRanking() {
        return pantallaRanking;
    }

    public void setPantallaRanking(PantallaBase pantallaRanking) {
        this.pantallaRanking = pantallaRanking;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PantallaBase getPantallaLogIn() {
        return pantallaLogIn;
    }

    public void setPantallaLogIn(PantallaBase pantallaLogIn) {
        this.pantallaLogIn = pantallaLogIn;
    }

    public PantallaBase getPantallaTutorial() {
        return pantallaTutorial;
    }

    public void setPantallaTutorial(PantallaBase pantallaTutorial) {
        this.pantallaTutorial = pantallaTutorial;
    }

    public PantallaBase getPantallaTipoJuego() {
        return pantallaTipoJuego;
    }

    public void setPantallaTipoJuego(PantallaBase pantallaTipoJuego) {
        this.pantallaTipoJuego = pantallaTipoJuego;
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
