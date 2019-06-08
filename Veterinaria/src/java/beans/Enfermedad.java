package beans;

import java.io.Serializable;

public class Enfermedad implements Serializable {

    private int codigo;
    private String nombre;
    private boolean estado;

    public Enfermedad() {
    }

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}

   
    