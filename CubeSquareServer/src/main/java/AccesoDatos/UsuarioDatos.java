package AccesoDatos;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import herramientas.ConnexionSQL;
import modelo.Usuario;



public class UsuarioDatos<E> implements InterfazDatos{
	
	static Statement st = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	
	@Override
	public void add(Object e) throws SQLException {
		Usuario u = (Usuario) e;
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		st = c.getConnection().createStatement();
		
		st.executeUpdate("INSERT into usuario (`alias`,`nombreUsuario`,`contrasena`) VALUES ('"+u.getAlias()+"','"+u.getNombreUsuario()+"','"+u.getContrasena()+"');");
		
		st.close();
		c.desconectar();
	}

	@Override
	public void modificar(Object e) throws SQLException{
		Usuario u = (Usuario) e;
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		st = c.getConnection().createStatement();
		String sql = "UPDATE usuario SET `alias`='"+u.getAlias()+"',`nombreUsuario`='"+u.getNombreUsuario()+"',`contrasena` ='"+u.getContrasena()+"' WHERE (`idusuario` = "+u.getIdusuario()+");";
		System.out.println(sql);
		st.executeUpdate(sql);

		st.close();
		c.desconectar();
	}
	
	@Override
	public void eliminar(int id) throws SQLException {
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		st.executeUpdate("DELETE FROM `db_a5772b_cubesqu`.`usuario` WHERE (`idusuario` = "+id+");");
		
		st.close();
		c.desconectar();
	}

	@Override
	public ArrayList<Usuario> listar(String where) throws SQLException {
		ConnexionSQL c = new ConnexionSQL();
		ArrayList<Usuario> usuarr = new ArrayList<Usuario>();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT * FROM usuario ");
		while(rs.next()) {
		usuarr.add(new Usuario( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		st.close();
		rs.close();
		c.desconectar();

		return  usuarr;
	}

	@Override
	public Usuario getById(int id) throws SQLException {
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT * FROM usuario where idusuario =  " + id);
		rs.next();
		Usuario u = new Usuario( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
		
		c.desconectar();
		st.close();
		rs.close();
		return  u;
	}
	
	public Usuario login(String nombreUsuario,String contrasena) throws SQLException{
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		Usuario u;
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT * FROM usuario where nombreUsuario = '"+nombreUsuario+"' and contrasena = '"+contrasena+"' " );
		
		if(rs.next()) {
			System.out.println("Usuario Encontrado");
			u = new Usuario( rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}else {
			System.out.println("Usuario no encontrado");
			 u = new Usuario( 1,"unknown","unknownUser","42");
		}
		
		c.desconectar();
		st.close();
		rs.close();
		return  u;
	}
	
	public boolean isnombrelibre(String nombre) throws SQLException{
		ConnexionSQL c = new ConnexionSQL();
		boolean respuesta;
		c.getConnection();
		Usuario u;
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT COUNT(idusuario) FROM usuario WHERE nombreUsuario = '"+nombre+"'" );
		rs.next();
		if(rs.getInt(1)==1) {
			System.out.println("nombre utilizado");
			respuesta = false;
		}else if (rs.getInt(1)>1){
			System.out.println("ERROR nombre repetido");
			 respuesta = true;
		}else {
			System.out.println("Nombre libre");
			respuesta = true;
		}
		
		c.desconectar();
		st.close();
		rs.close();
		
		return respuesta;
	}
	
	

}
