package beans;

import java.io.Serializable;

public class Paciente implements Serializable {

    private int codigo;
    private String nombre;
    private int iddueno;
    private String dueno;
    private int idveterinario;
    private String veterinario;
    private int codigoenfermedad;
    private String enfermedad;
    private int codigomedicamento;
    private String medicamento;
    private boolean estado;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIddueno() {
        return iddueno;
    }

    public void setIddueno(int iddueno) {
        this.iddueno = iddueno;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdveterinario() {
        return idveterinario;
    }

    public void setIdveterinario(int idveterinario) {
        this.idveterinario = idveterinario;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public int getCodigoenfermedad() {
        return codigoenfermedad;
    }

    public void setCodigoenfermedad(int codigoenfermedad) {
        this.codigoenfermedad = codigoenfermedad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public int getCodigomedicamento() {
        return codigomedicamento;
    }

    public void setCodigomedicamento(int codigomedicamento) {
        this.codigomedicamento = codigomedicamento;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }
    
    
   }
