package AccesoDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import herramientas.ConnexionSQL;
import modelo.Record;
import modelo.Usuario;

public class RecordDatos<E> implements InterfazDatos {
	
	static Statement st = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;

	@Override
	public void add(Object e) throws SQLException {
		Record r = (Record) e;
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		st = c.getConnection().createStatement();
		
		st.executeUpdate("INSERT into record (`idusuario`,`distanciaRecorrida`) VALUES ("+r.getIdUsuario()+","+r.getDistanciaRecorrida()+");");
		
		st.close();
		c.desconectar();
		
	}

	@Override
	public void modificar(Object e) throws SQLException {
		Record r = (Record) e;
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		st = c.getConnection().createStatement();
		String sql = "UPDATE usuario SET `distanciaRecorrida`="+r.getDistanciaRecorrida()+" WHERE (`idusuario` = "+r.getIdRecord()+");";
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
		st.executeUpdate("DELETE FROM `db_a5772b_cubesqu`.`record` WHERE (`idrecord` = "+id+");");
		
		st.close();
		c.desconectar();
		
	}

	@Override
	public ArrayList<Record> listar(String where) throws SQLException {
		ConnexionSQL c = new ConnexionSQL();
		ArrayList<Record> arr = new ArrayList<Record>();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT idRecord, u.idUsuario,  distanciaRecorrida , alias FROM usuario u, record r WHERE u.idusuario =r.idusuario");
		while(rs.next()) {
		arr.add(new Record( rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
		}
		st.close();
		rs.close();
		c.desconectar();

		return  arr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Record getById(int id) throws SQLException {
		ConnexionSQL c = new ConnexionSQL();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT * FROM record where idrecord =  " + id);
		rs.next();
		Record r = new Record( rs.getInt(1),rs.getInt(1),rs.getInt(3));
		
		c.desconectar();
		st.close();
		rs.close();
		return  r;
	}
	
	
	public ArrayList<Record> listarRecords() throws SQLException{
		ConnexionSQL c = new ConnexionSQL();
		ArrayList<Record> arr = new ArrayList<Record>();
		c.getConnection();
		
		st = c.getConnection().createStatement();
		rs = st.executeQuery("SELECT idRecord,u.idUsuario, distanciaRecorrida, alias FROM `db_a5772b_cubesqu`.usuario u, `db_a5772b_cubesqu`.record r WHERE u.idusuario =r.idusuario ORDER BY distanciaRecorrida DESC");
		while(rs.next()) {
		arr.add(new Record( rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
		}
		st.close();
		rs.close();
		c.desconectar();

		return  arr;
		
	}

}
