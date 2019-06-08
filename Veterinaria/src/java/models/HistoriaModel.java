package models;


import beans.Historia;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriaModel {

    private Connection conn;

    public HistoriaModel() {
    }

    public ArrayList<Historia> getHistorias() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select historia.codigo, historia.fecha, historia.codigopaciente, paciente.nombre 'paciente',\n" +
"historia.codigoenfermedad, enfermedad.nombre 'enfermedad',\n" +
"historia.estado from historia\n" +
"inner join paciente on historia.codigopaciente = paciente.codigo\n" +
"inner join enfermedad on paciente.codigoenfermedad = enfermedad.codigo");
            ResultSet rset = prep.executeQuery();

            ArrayList<Historia> historias = new ArrayList<>();
            Historia h = null;
            while (rset.next()) {
                h = new Historia();
                h.setCodigo(rset.getInt("codigo"));
                h.setFecha(rset.getString("fecha"));
                h.setCodigopaciente(rset.getInt("codigopaciente"));
                h.setPaciente(rset.getString("paciente"));
                h.setCodigoenfermedad(rset.getInt("codigoenfermedad"));
                h.setEnfermedad(rset.getString("enfermedad"));
                h.setEstado(rset.getBoolean("estado"));
                historias.add(h);
            }
            return historias;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Historia de la DB Veterinaria!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla Historia ---------------------------------
    public void add(Historia historia) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into historia (fecha, codigopaciente, codigoenfermedad, estado) values (?, ?, ?, ?)");

            
            prep.setString(1, historia.getFecha()); 
            prep.setInt(2, historia.getCodigopaciente());
            prep.setInt(3, historia.getCodigoenfermedad());
            prep.setBoolean(4, historia.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla Historia!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla Historia de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un Historia por su codigo---------------------
    public Historia findById(int codigo) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select historia.codigo, historia.fecha, historia.codigopaciente, paciente.nombre 'paciente',\n" +
"historia.codigoenfermedad, enfermedad.nombre 'enfermedad',\n" +
"historia.estado from historia\n" +
"inner join paciente on historia.codigopaciente = paciente.codigo\n" +
"inner join enfermedad on paciente.codigoenfermedad = enfermedad.codigo where historia.codigo = ?");
            prep.setInt(1, codigo);           
            ResultSet rset = prep.executeQuery();

            Historia h = null;
            if (rset.next()) {
                h = new Historia();
                h.setCodigo(rset.getInt("codigo"));
                h.setFecha(rset.getString("fecha"));
                h.setCodigopaciente(rset.getInt("codigopaciente"));
                h.setPaciente(rset.getString("paciente"));
                h.setCodigoenfermedad(rset.getInt("codigoenfermedad"));
                h.setEnfermedad(rset.getString("enfermedad"));
                h.setEstado(rset.getBoolean("estado"));
                            
                }
            return h;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla medicamento de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla historia ---------------------------------
    public void remove(Historia historia) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from historia where codigo = ?");
            prep.setInt(1, historia.getCodigo());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla historia!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla historia de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla historia ---------------------------------
    public void update(Historia historia) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update historia set fecha = ?, codigopaciente=?, codigoenfermedad=?, estado = ? where codigo = ?");

            
            prep.setString(1, historia.getFecha());
            prep.setInt(2, historia.getCodigopaciente());
            prep.setInt(3, historia.getCodigoenfermedad());            
            prep.setBoolean(4, historia.isEstado());
            prep.setInt(5, historia.getCodigo());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla historia!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla historia de la DB Veterianaria!", ex);
        }
    }  
}