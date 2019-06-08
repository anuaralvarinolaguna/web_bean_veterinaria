package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

  private static Connection conn = null;

  public static Connection getConexion() {
    try {
      if (conn == null) {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/veterinaria", "root", "");
      }
      return conn;
    } catch (ClassNotFoundException | SQLException ex) {
      throw new RuntimeException("Error al crear la conexion!", ex);
    }
  }
}
