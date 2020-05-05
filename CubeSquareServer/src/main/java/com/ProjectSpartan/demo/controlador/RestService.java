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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RestService {
	
	private UsuarioDatos<Usuario> usuDatos = new UsuarioDatos();
	

	@GetMapping(value = "/ping")
	public boolean ping() {
		System.out.println("/ping");
		return true;
	}
	
	@GetMapping(value = "Usuario/getById")
	public Usuario usuGetById(@RequestParam("id")  int id) throws SQLException{
		
		Usuario u = usuDatos.getById(id);
		
		System.out.println("Usuario/getById   "+u);
		
		return   u;
		} 
	
	@GetMapping(value = "Usuario/add")
	public boolean usuAdd(int idusuario, String alias, String nombreUsuario, String contrasena) throws SQLException {
		usuDatos.add(new Usuario(idusuario,alias,nombreUsuario,contrasena));
		
		System.out.println( "Usuario/add");
		return true;
	
	}

	@GetMapping(value = "Usuario/modificar")
	public void usuModificar(int idusuario, String alias, String nombreUsuario, String contrasena) throws SQLException {
		usuDatos.modificar(new Usuario(idusuario,alias,nombreUsuario,contrasena));
		
		System.out.println( "Usuario/modificar   ");
	
	}
	
	@GetMapping(value = "Usuario/eliminar")
	public void usuEliminar(@RequestParam("id")  int id) throws SQLException {
		usuDatos.eliminar(id);
		
		System.out.println( "Usuario/eliminar   "+id);
	
	
	}
	@GetMapping(value = "Usuario/listar")
	public ArrayList<Usuario> usuListar()throws SQLException{
		System.out.println( "Usuario/listar");
		return usuDatos.listar(null);
	
	}

	
	private RecordDatos<Record> recDatos = new RecordDatos();
	
	@GetMapping(value = "Record/getById")
	public Record recGetById(@RequestParam("id")  int id) throws SQLException{
		
		Record u = recDatos.getById(id);
		
		System.out.println("Record/getById   "+u);
		
		return   u;
		} 
	
	@GetMapping(value = "Record/add")
	public void reAdd(int idRecord, int idUsuario, int distanciaRecorrida) throws SQLException {
		usuDatos.add(new Record ( idRecord,idUsuario,distanciaRecorrida));
		
		System.out.println( "Record/add   ");

	
	}

	@GetMapping(value = "Record/modificar")
	public void reModificar(int idRecord, int idUsuario, int distanciaRecorrida) throws SQLException {
		recDatos.modificar(new Record ( idRecord,idUsuario,distanciaRecorrida));
		
		System.out.println( "Record/modificar");
	
	}
	
	@GetMapping(value = "Record/eliminar")
	public void reEliminar(@RequestParam("id")  int id) throws SQLException {
		recDatos.eliminar(id);
		
		System.out.println( "Record/eliminar   "+id);
	
	
	}
	@GetMapping(value = "Record/listar")
	public ArrayList<Record> reListar()throws SQLException{
		System.out.println( "Record/listar");
		return recDatos.listar(null);
	
	}
	
	@GetMapping(value = "Record/listarRecords")
	public ArrayList<Record> relistarRecords()throws SQLException{
		System.out.println( "Record/listarRecords");
		return recDatos.listarRecords();
	
	}

	
	
}
