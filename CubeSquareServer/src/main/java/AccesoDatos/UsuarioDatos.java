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
		
		st.executeUpdate("INSERT into usuario (`alias`,`nombreUsuario`,`contraseña`) VALUES ('"+u.getAlias()+"','"+u.getNombreUsuario()+"','"+u.getContrasena()+"');");
		
		st.close();
		c.desconectar();
	}

	@Override
	public void modificar(Object e) throws SQLException{
		Usuario u = (Usuario) e;
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		st = c.getConnection().createStatement();
		String sql = "UPDATE usuario SET `alias`='"+u.getAlias()+"',`nombreUsuario`='"+u.getNombreUsuario()+"',`contraseña` ='"+u.getContrasena()+"' WHERE (`idusuario` = "+u.getIdusuario()+");";
		//UPDATE `db_a5772b_cubesqu`.`usuario` SET `alias` = 'wasaç', `nombreUsuario` = 'nsaddull', `contraseña` = 'nulldsadsa' WHERE (`idusuario` = '2');
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
	

}
