package models;

import beans.Paciente;
import helpers.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteModel {

    private Connection conn;

    public PacienteModel() {
    }

    public ArrayList<Paciente> getPacientes() {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select paciente.codigo, paciente.nombre, paciente.iddueno, dueno.nombre 'dueno',  paciente.idveterinario, veterinario.nombre 'veterinario', paciente.codigoenfermedad, enfermedad.nombre 'enfermedad', paciente.codigomedicamento, medicamento.nombre 'medicamento', paciente.estado from paciente inner join dueno on paciente.iddueno = dueno.idd inner join veterinario on paciente.idveterinario = veterinario.id inner join enfermedad on paciente.codigoenfermedad = enfermedad.codigo inner join medicamento on paciente.codigomedicamento = medicamento.codigo");
            ResultSet rset = prep.executeQuery();

            ArrayList<Paciente> pacientes = new ArrayList<>();
            Paciente p = null;
            while (rset.next()) {
                p = new Paciente();
                p.setCodigo(rset.getInt("codigo"));
                p.setNombre(rset.getString("nombre"));
                p.setIddueno(rset.getInt("iddueno"));
                p.setDueno(rset.getString("dueno"));
                p.setIdveterinario(rset.getInt("idveterinario"));
                p.setVeterinario(rset.getString("veterinario"));
                p.setCodigoenfermedad(rset.getInt("codigoenfermedad"));
                p.setEnfermedad(rset.getString("enfermedad"));
                p.setCodigomedicamento(rset.getInt("codigomedicamento"));
                p.setMedicamento(rset.getString("medicamento"));
                p.setEstado(rset.getBoolean("estado"));
                pacientes.add(p);
            }
            return pacientes;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla paciente de la DB Veterinaria!", ex);
        }
    
    }
    // --- Servicio agregar en la tabla Paciente ---------------------------------
    public void add(Paciente paciente) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("insert into paciente (nombre, iddueno, idveterinario, codigoenfermedad, codigomedicamento, estado) values (?, ?, ?, ?, ?, ?)");
            
            prep.setString(1, paciente.getNombre()); 
            prep.setInt(2, paciente.getIddueno());
            prep.setInt(3, paciente.getIdveterinario());
            prep.setInt(4, paciente.getCodigoenfermedad());
            prep.setInt(5, paciente.getCodigomedicamento());
            prep.setBoolean(6, paciente.isEstado());
                             
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha insertado ningun registro en la tabla Paciente!");
            }

        } catch (SQLException | RuntimeException ex) {
            throw new RuntimeException("Error al insertar en la tabla Paciente de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio para buscar en la tabla un Paciente por su codigo---------------------
    public Paciente findById(int codigo) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("select paciente.codigo, paciente.nombre, paciente.iddueno, "
                    + "dueno.nombre 'dueno', paciente.idveterinario, "
                    + "veterinario.nombre 'veterinario', paciente.codigoenfermedad, enfermedad.nombre 'enfermedad', paciente.codigomedicamento, medicamento.nombre 'medicamento', "
                    + "paciente.estado from paciente inner join dueno "
                    + "on paciente.iddueno = dueno.idd inner join veterinario on "
                    + "paciente.idveterinario = veterinario.id inner join enfermedad on "
                    + "paciente.codigoenfermedad = enfermedad.codigo inner join medicamento "
                    + "on paciente.codigomedicamento = medicamento.codigo where paciente.codigo = ?");
            prep.setInt(1, codigo);           
            ResultSet rset = prep.executeQuery();

            Paciente p = null;
            if (rset.next()) {
                p = new Paciente();
                p.setCodigo(rset.getInt("codigo"));
                p.setNombre(rset.getString("nombre"));
                p.setIddueno(rset.getInt("iddueno"));
                p.setDueno(rset.getString("dueno"));
                p.setIdveterinario(rset.getInt("idveterinario"));
                p.setVeterinario(rset.getString("veterinario"));
                p.setCodigoenfermedad(rset.getInt("codigoenfermedad"));
                p.setEnfermedad(rset.getString("enfermedad"));
                p.setCodigomedicamento(rset.getInt("codigomedicamento"));
                p.setMedicamento(rset.getString("medicamento"));
                p.setEstado(rset.getBoolean("estado"));
                                          
                }
            return p;
        } catch (Exception ex) {
            throw new RuntimeException("Error al consultar la tabla Paciente de la DB Veterinaria!", ex);
        }
    }
     // --- Servicio eliminar de la tabla Paciente ---------------------------------
    public void remove(Paciente paciente) {
        try {
            conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("delete from paciente where codigo = ?");
            prep.setInt(1, paciente.getCodigo());
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha eliminar ningun registro en la tabla Paciente!");
            }
       } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar de la tabla Paciente de la DB Veterinaria!", ex);
        }
    }
    // --- Servicio modificar en la tabla Paciente ---------------------------------
    public void update(Paciente paciente) {
        try {
           conn = Conexion.getConexion();
            PreparedStatement prep = conn.prepareStatement("update paciente set nombre = ?, iddueno = ?, idveterinario=?, codigoenfermedad=?, codigomedicamento=?, estado = ? where codigo = ?");
            
            prep.setString(1, paciente.getNombre());
            prep.setInt(2, paciente.getIddueno());
            prep.setInt(3, paciente.getIdveterinario());
            prep.setInt(4, paciente.getCodigoenfermedad());
            prep.setInt(5, paciente.getCodigomedicamento());
            prep.setBoolean(6, paciente.isEstado());
            prep.setInt(7, paciente.getCodigo());
            int i = 0;
            int r = prep.executeUpdate();
            if (r != 1) {
                throw new RuntimeException("No se ha modificado ningun registro en la tabla paciente!");
            }
        } 
        catch (Exception ex) {
            throw new RuntimeException("Error al modificar en la tabla paciente de la DB Veterianaria!", ex);
        }
    }  
}