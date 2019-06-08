package models;


import beans.Veterinario;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioModel {

    private Connection conn;

    public VeterinarioModel() {
    }

    public ArrayList<Veterinario> getVeterinarios() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from veterinario");
            ResultSet rset = prep.executeQuery();

            ArrayList<Veterinario> veterinarios = new ArrayList<>();
            Veterinario v = null;
            while (rset.next()) {
                v = new Veterinario();
                v.setId(rset.getInt("id"));
                v.setCedula(rset.getInt("cedula"));
                v.setNombre(rset.getString("nombre"));                
                v.setEstado(rset.getBoolean("estado"));
                veterinarios.add(v);
            }
            return veterinarios;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Veterinario de la DB Dueño!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla veterinario ---------------------------------
    public void add(Veterinario veterinario) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into veterinario (cedula, nombre, estado) values (?, ?, ?)");

            prep.setInt(1, veterinario.getCedula());
            prep.setString(2, veterinario.getNombre());           
            prep.setBoolean(3, veterinario.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla veterinario!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla veterinario de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un veterinario por su Id---------------------
    public Veterinario findById(int idV) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from veterinario where id = ?");
            prep.setInt(1, idV);           
            ResultSet rset = prep.executeQuery();

            Veterinario v = null;
            if (rset.next()) {
                v = new Veterinario();
                v.setId(rset.getInt("id"));
                v.setCedula(rset.getInt("cedula"));
                v.setNombre(rset.getString("nombre"));
                v.setEstado(rset.getBoolean("estado"));
                
                }
            return v;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Veterinario de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla dueño ---------------------------------
    public void remove(Veterinario veterinario) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from veterinario where id = ?");
            prep.setInt(1, veterinario.getId());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla veterinario!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla veterinario de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla veterinario ---------------------------------
    public void update(Veterinario veterinario) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update veterinario set cedula = ?, nombre = ?, estado = ? where id = ?");

            prep.setInt(1, veterinario.getCedula());
            prep.setString(2, veterinario.getNombre());
            prep.setBoolean(3, veterinario.isEstado());
            prep.setInt(4, veterinario.getId());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla veterinario!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla veterinario de la DB Veterianaria!", ex);
        }
    }  
}