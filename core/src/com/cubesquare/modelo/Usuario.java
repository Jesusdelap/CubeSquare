package com.cubesquare.modelo;

public class Usuario {

    private int idusuario;
    private String alias;
    private String nombreUsuario;
    private String contrasena;
    public Usuario(int idusuario, String alias, String nombreUsuario, String contrasena) {
        super();
        this.idusuario = idusuario;
        this.alias = alias;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
    public Usuario() {
        super();
    }
    public int getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    @Override
    public String toString() {
        return "Usuario [idusuario=" + idusuario + ", alias=" + alias + ", nombreUsuario=" + nombreUsuario
                + ", contrasena=" + contrasena + "]";
    }



}
