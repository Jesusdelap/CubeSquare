package herramientas;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionSQL {


    Connection connection = null;
    
    String url = "jdbc:mysql://localhost:3306/cubesquare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String password = "root";


    public ConnexionSQL() {

        try {

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            System.out.println("Excepcion SQL: " + e.getMessage());
            System.out.println("Estado SQL: " + e.getSQLState());
            System.out.println("Codigo del Error: " + e.getErrorCode());



        } catch (Exception e) {
            System.out.println("NO SQL ERROR :\n "+ e);

        }
    }

    /*
     * Este metodo obtiene la conexion con la base de datos
     *
     * @param none
     *
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }

    public void desconectar() {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
