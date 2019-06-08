package models;


import beans.Enfermedad;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnfermedadModel {

    private Connection conn;

    public EnfermedadModel() {
    }

    public ArrayList<Enfermedad> getEnfermedades() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from enfermedad");
            ResultSet rset = prep.executeQuery();

            ArrayList<Enfermedad> enfermedades = new ArrayList<>();
            Enfermedad e = null;
            while (rset.next()) {
                e = new Enfermedad();
                e.setCodigo(rset.getInt("codigo"));
                e.setNombre(rset.getString("nombre"));                
                e.setEstado(rset.getBoolean("estado"));
                enfermedades.add(e);
            }
            return enfermedades;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Enfermedad de la DB Veterinaria!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla enfermedades ---------------------------------
    public void add(Enfermedad enfermedad) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into enfermedad (nombre, estado) values (?, ?)");

            
            prep.setString(1, enfermedad.getNombre());           
            prep.setBoolean(2, enfermedad.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla enfermedad!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla enfermedad de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un enfermedad por su codigo---------------------
    public Enfermedad findById(int codigo) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from enfermedad where codigo = ?");
            prep.setInt(1, codigo);           
            ResultSet rset = prep.executeQuery();

            Enfermedad e = null;
            if (rset.next()) {
                e = new Enfermedad();
                e.setCodigo(rset.getInt("codigo"));
                e.setNombre(rset.getString("nombre"));
                e.setEstado(rset.getBoolean("estado"));
                
                }
            return e;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla enfermedad de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla enfermedad ---------------------------------
    public void remove(Enfermedad enfermedad) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from enfermedad where codigo = ?");
            prep.setInt(1, enfermedad.getCodigo());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla enfermedad!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla enfermedad de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla enfermedad ---------------------------------
    public void update(Enfermedad enfermedad) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update enfermedad set nombre = ?, estado = ? where codigo = ?");

            
            prep.setString(1, enfermedad.getNombre());
            prep.setBoolean(2, enfermedad.isEstado());
            prep.setInt(3, enfermedad.getCodigo());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla enfermedad!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla enfermedad de la DB Veterianaria!", ex);
        }
    }  
}