package models;

import beans.Dueno;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuenoModel {

    private Connection conn;

    public DuenoModel() {
    }

    public ArrayList<Dueno> getDuenos() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from dueno");
            ResultSet rset = prep.executeQuery();

            ArrayList<Dueno> duenos = new ArrayList<>();
            Dueno d = null;
            while (rset.next()) {
                d = new Dueno();
                d.setId(rset.getInt("idD"));
                d.setCedula(rset.getInt("cedula"));
                d.setNombre(rset.getString("nombre"));
                d.setDirrecion(rset.getString("direccion"));
                d.setTelefono(rset.getString("telefono"));
                d.setEmail(rset.getString("email"));
                d.setEstado(rset.getBoolean("estado"));
                duenos.add(d);
            }
            return duenos;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla producto de la DB Dueño!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla Dueno ---------------------------------
    public void add(Dueno dueno) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into dueno (cedula, nombre, direccion, telefono, email, estado) values (?, ?, ?, ?, ?, ?)");

            prep.setInt(1, dueno.getCedula());
            prep.setString(2, dueno.getNombre());
            prep.setString(3, dueno.getDirrecion());
            prep.setString(4, dueno.getTelefono());
            prep.setString(5, dueno.getEmail());
            prep.setBoolean(6, dueno.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla Dueño!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla Dueño de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un Dueno por su Id---------------------
    public Dueno findById(int idD) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select * from dueno where idD = ?");
            prep.setInt(1, idD);           
            ResultSet rset = prep.executeQuery();

            Dueno d = null;
            if (rset.next()) {
                d = new Dueno();
                d.setId(rset.getInt("idD"));
                d.setCedula(rset.getInt("cedula"));
                d.setNombre(rset.getString("nombre"));
                d.setDirrecion(rset.getString("direccion"));
                d.setTelefono(rset.getString("telefono"));
                d.setEmail(rset.getString("email"));
                d.setEstado(rset.getBoolean("estado"));
                
                }
            return d;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Dueño de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla dueño ---------------------------------
    public void remove(Dueno dueno) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from dueno where idD = ?");
            prep.setInt(1, dueno.getId());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla dueño!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla Dueño de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla dueño ---------------------------------
    public void update(Dueno dueno) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update dueno set cedula = ?, nombre = ?, direccion = ?, telefono = ?, email = ?, estado = ? where idD = ?");

            prep.setInt(1, dueno.getCedula());
            prep.setString(2, dueno.getNombre());
            prep.setString(3, dueno.getDirrecion());
            prep.setString(4, dueno.getTelefono());
            prep.setString(5, dueno.getEmail());
            prep.setBoolean(6, dueno.isEstado());
            prep.setInt(7, dueno.getId());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla Dueño!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla producto de la DB Dueño!", ex);
        }
    }  
}