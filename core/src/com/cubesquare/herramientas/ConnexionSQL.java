package com.cubesquare.herramientas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexionSQL {
    /* Logger
    private static Logger logger;
    static {
        try {
            logger = LogManager.getLogger(ConnexionSQL.class);
        } catch (Throwable e) {
            System.out.println("Logger no funciona");
        }
    }

     */

    Connection connection = null;


    String url = "jdbc:mysql://mysql5017.site4now.net:3306/db_a5772b_cubesqu?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "a5772b_cubesqu";
    String password = "CubeSqu42";


    public ConnexionSQL() {

        try {

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {

            System.out.println("Excepcion SQL: " + e.getMessage());
            System.out.println("Estado SQL: " + e.getSQLState());
            System.out.println("Codigo del Error: " + e.getErrorCode());



        } catch (Exception e) {
            System.out.println("error: "+ e);
            /*
            logger.error("Otro problema: " + E);

             */

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
