package com.ProjectSpartan.demo.controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import AccesoDatos.RecordDatos;
import AccesoDatos.UsuarioDatos;
import herramientas.ConnexionSQL;
import modelo.Record;
import modelo.Usuario;

/**
 * Servicios rest 
 * <br/><br/>
 * Esta clase crea servicios rest los cuales porporcionan 
 * informacion de la base de datos
 * <br/>
 * la url para llamarlos es la siguiente 
 * http://(La ip Correspondiete):8080/(Maping concreto)
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RestService {
	
	private UsuarioDatos<Usuario> usuDatos = new UsuarioDatos();
	
	/**
	 *metodo al que llamar para saber si tienes conexion
	 *con el servidor 
	 *
	 *Maping: /ping
	 *
	 *@return boolean siempre es true
	 */
	@GetMapping(value = "/ping")
	public boolean ping() {
		System.out.println("/ping");
		return true;
	}
	/**
	 *Debuelve el usuario segun id
	 *
	 *Maping: Usuario/getById
	 *
	 *@param id la id del usuairo que se busca 
	 *@return Usuario
	 */
	@GetMapping(value = "Usuario/getById")
	public Usuario usuGetById(@RequestParam("id")  int id) throws SQLException{
		
		Usuario u = usuDatos.getById(id);
		
		System.out.println("Usuario/getById   "+u);
		
		return   u;
		} 
	/**
	 *Debuelve el usuario segun id
	 *
	 *Maping: Usuario/add
	 *
	 *@param id la id del usuairo que se busca 
	 *@return boolean siempre es true
	 */
	@GetMapping(value = "Usuario/add")
	public boolean usuAdd(int idusuario, String alias, String nombreUsuario, String contrasena) throws SQLException {
		usuDatos.add(new Usuario(idusuario,alias,nombreUsuario,contrasena));
		
		System.out.println( "Usuario/add");
		return true;
	
	}
	/**
	 *Modifica un usuario
	 *
	 *
	 *Maping: Usuario/modificar
	 *
	 *@param idusuario la id del usuario a modificar
	 *@param alias el nuevo valor del alias
	 *@param nombreUsuario el nuevo valor del nombreUsuario
	 *@param contrasena el nuevo valor de la contraseña
	 */
	@GetMapping(value = "Usuario/modificar")
	public void usuModificar(int idusuario, String alias, String nombreUsuario, String contrasena) throws SQLException {
		usuDatos.modificar(new Usuario(idusuario,alias,nombreUsuario,contrasena));
		
		System.out.println( "Usuario/modificar");
	
	}
	/**
	 *Elimina el usuario por id
	 *
	 *Maping: Usuario/eliminar
	 *
	 *@param id la id del usuairo que se busca 
	 */
	@GetMapping(value = "Usuario/eliminar")
	public void usuEliminar(@RequestParam("id")  int id) throws SQLException {
		usuDatos.eliminar(id);
		
		System.out.println( "Usuario/eliminar   "+id);
	
	}
	/**
	 *Lista todos los usuarios
	 *
	 *Maping: Usuario/listar
	 *
	 *@return Lista de todos los usuarios
	 *	 
	 */
	@GetMapping(value = "Usuario/listar")
	public ArrayList<Usuario> usuListar()throws SQLException{
		System.out.println( "Usuario/listar");
		return usuDatos.listar(null);
	
	}
	/**
	 *le pasas el nombre de usuario y la contraseña
	 *y devuelve el usuario completo, si el usuario
	 *o la contraseña es incorrecta devuelve el
	 *usuario 0
	 *
	 *Maping: Usuario/login
	 *
	 *@param nombreUsuario
	 *@param contrasena
	 *
	 *@return  Usuario completo
	 *	 
	 */
	@GetMapping(value = "Usuario/login")
	public Usuario login(String nombreUsuario,String contrasena)throws SQLException{
		System.out.println( "Usuario/login");
		return usuDatos.login(nombreUsuario, contrasena);
	
	}
	
	/**
	 *Si el nombre de usuario esta libre
	 *devolvera true 
	 *
	 *Maping: Usuario/isnombrelibre
	 *

	 *
	 *@return si el nombre de usuario esta libre 
	 *	 
	 */
	@GetMapping(value = "Usuario/isnombrelibre")
	public boolean isnombrelibre(String nombreUsuario)throws SQLException{
		System.out.println( "Usuario/login");
		return usuDatos.isnombrelibre(nombreUsuario);
	
	}
	
	

	
	private RecordDatos<Record> recDatos = new RecordDatos();
	/**
	 *Debuelve el record segun id
	 *
	 *Maping: Record/getById
	 *
	 *@param id la id del record que se busca 
	 *@return Record
	 */
	@GetMapping(value = "Record/getById")
	public Record recGetById(@RequestParam("id")  int id) throws SQLException{
		
		Record u = recDatos.getById(id);
		
		System.out.println("Record/getById   "+u);
		
		return   u;
		}
	/**
	 *Debuelve el record segun id
	 *
	 *Maping: Record/add
	 *
	 *@param id la id del record que se busca 
	 */
	@GetMapping(value = "Record/add")
	public void reAdd(int idRecord, int idUsuario, int distanciaRecorrida) throws SQLException {
		recDatos.add(new Record ( idRecord,idUsuario,distanciaRecorrida));
		
		System.out.println( "Record/add   ");

	
	}
	/**
	 *Modifica un usuario
	 *
	 *
	 *Maping: Usuario/modificar
	 *
	 *@param idRecord la id del record a modificar
	 *@param idUsuario el nuevo valor de idUsuario
	 *@param distanciaRecorrida el nuevo valor de distanciaRecorrida
	 */
	@GetMapping(value = "Record/modificar")
	public void reModificar(int idRecord, int idUsuario, int distanciaRecorrida) throws SQLException {
		recDatos.modificar(new Record ( idRecord,idUsuario,distanciaRecorrida));
		
		System.out.println( "Record/modificar");
	
	}
	/**
	 *Elimina el usuario por id
	 *
	 *Maping: Usuario/eliminar
	 *
	 *@param id la id del usuairo que se busca 
	 */
	@GetMapping(value = "Record/eliminar")
	public void reEliminar(@RequestParam("id")  int id) throws SQLException {
		recDatos.eliminar(id);
		
		System.out.println( "Record/eliminar   "+id);
	
	
	}
	/**
	 *Lista todos los records
	 *
	 *Maping: Record/listar
	 *
	 *@return Lista de todos los records
	 *	 
	 */
	@GetMapping(value = "Record/listar")
	public ArrayList<Record> reListar()throws SQLException{
		System.out.println( "Record/listar");
		return recDatos.listar(null);
	
	}
	/**
	 *Lista todos los records con el alias del usuario
	 *
	 *Maping: Record/listarRecords
	 *
	 *@return Lista de todos los records
	 *	 
	 */
	@GetMapping(value = "Record/listarRecords")
	public ArrayList<Record> relistarRecords(int limit)throws SQLException{
		System.out.println( "Record/listarRecords");
		return recDatos.listarRecords(limit);
	
	}
	
}
