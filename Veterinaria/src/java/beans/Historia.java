package beans;

import java.io.Serializable;

public class Historia implements Serializable {

    private int codigo;
    private String fecha;
    private int codigopaciente;
    private String paciente;
    private int codigoenfermedad;
    private String enfermedad;            
    private boolean estado;

    public Historia() {
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodigopaciente() {
        return codigopaciente;
    }

    public void setCodigopaciente(int codigopaciente) {
        this.codigopaciente = codigopaciente;
    }

    public int getCodigoenfermedad() {
        return codigoenfermedad;
    }

    public void setCodigoenfermedad(int codigoenfermedad) {
        this.codigoenfermedad = codigoenfermedad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

   
}

   
    