package models;


import beans.Medicamento;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoModel {

    private Connection conn;

    public MedicamentoModel() {
    }

    public ArrayList<Medicamento> getMedicamentos() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from medicamento");
            ResultSet rset = prep.executeQuery();

            ArrayList<Medicamento> medicamentos = new ArrayList<>();
            Medicamento m = null;
            while (rset.next()) {
                m = new Medicamento();
                m.setCodigo(rset.getInt("codigo"));
                m.setNombre(rset.getString("nombre"));                
                m.setEstado(rset.getBoolean("estado"));
                medicamentos.add(m);
            }
            return medicamentos;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Medicamento de la DB Veterinaria!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla medicamento ---------------------------------
    public void add(Medicamento medicamento) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into medicamento (nombre, estado) values (?, ?)");

            
            prep.setString(1, medicamento.getNombre());           
            prep.setBoolean(2, medicamento.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla enfermedad!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla enfermedad de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un medicamento por su codigo---------------------
    public Medicamento findById(int codigo) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from medicamento where codigo = ?");
            prep.setInt(1, codigo);           
            ResultSet rset = prep.executeQuery();

            Medicamento m = null;
            if (rset.next()) {
                m = new Medicamento();
                m.setCodigo(rset.getInt("codigo"));
                m.setNombre(rset.getString("nombre"));
                m.setEstado(rset.getBoolean("estado"));
                
                }
            return m;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla medicamento de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla medicamento ---------------------------------
    public void remove(Medicamento medicamento) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from medicamento where codigo = ?");
            prep.setInt(1, medicamento.getCodigo());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla medicamento!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla medicamento de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla medicamento ---------------------------------
    public void update(Medicamento medicamento) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update medicamento set nombre = ?, estado = ? where codigo = ?");

            
            prep.setString(1, medicamento.getNombre());
            prep.setBoolean(2, medicamento.isEstado());
            prep.setInt(3, medicamento.getCodigo());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla medicamento!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla medicamento de la DB Veterianaria!", ex);
        }
    }  
}