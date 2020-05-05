package com.cubesquare.modelo;

public class Record {

    private int idRecord;
    private int idUsuario;
    private int distanciaRecorrida;
    private String alias;



    public Record(int idRecord, int idUsuario, int distanciaRecorrida, String alias) {
        super();
        this.idRecord = idRecord;
        this.idUsuario = idUsuario;
        this.distanciaRecorrida = distanciaRecorrida;
        this.alias = alias;
    }
    public Record(int idRecord, int idUsuario, int distanciaRecorrida) {
        super();
        this.idRecord = idRecord;
        this.idUsuario = idUsuario;
        this.distanciaRecorrida = distanciaRecorrida;
    }
    public Record() {
        super();
    }
    public int getIdRecord() {
        return idRecord;
    }
    public void setIdRecord(int idRecord) {
        this.idRecord = idRecord;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
    public void setDistanciaRecorrida(int distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }




}
