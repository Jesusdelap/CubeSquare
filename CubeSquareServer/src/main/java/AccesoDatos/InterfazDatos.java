package AccesoDatos;


import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Usuario;

public interface InterfazDatos<E> {

    /**
     * Añade un objeto en la bd
     *
     * @param e Objeto a añadir
     * @return
     * @throws SQLException 
     */
    public void add(E e) throws SQLException;
    /**
     * Modifica un objeto en la bd
     *
     * @param e objeto con los campos modificados
     * @param id id del objeto a modificar
     * @return
     */
    public void modificar(E e) throws SQLException;
    /**
     *
     *
     * @param id id del objeto a eliminar
     * @return
     * @throws SQLException 
     */
    public void eliminar(int id) throws SQLException;
    /**
     * lista todos los objetos de la bd
     *
     * @param where parametros para listar
     * @return  ArrayList<E> Lista todos los objetos
     * @throws SQLException 
     */
    public ArrayList<E> listar(String where) throws SQLException;
    /**
     *busca un objeto por su id
     *
     * @param id id del objeto
     * @return E  objeto que recupera
     * @throws SQLException 
     */
    public E getById(int id) throws SQLException;
}
