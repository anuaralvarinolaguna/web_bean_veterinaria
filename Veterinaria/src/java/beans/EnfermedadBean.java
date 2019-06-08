package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import models.EnfermedadModel;
import net.sf.jasperreports.engine.JRException;




@ManagedBean
@RequestScoped
public class EnfermedadBean {

    private Enfermedad enfermedad;
    private EnfermedadModel enfermedadModel = new EnfermedadModel();
    private ArrayList<Enfermedad> enfermedades = new ArrayList<>(); 
    
    public EnfermedadBean() {
        if (this.enfermedad == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("codigo");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.enfermedad = enfermedadModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.enfermedad == null) this.enfermedad = new Enfermedad();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

   public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
        this.enfermedad = new Enfermedad();
    }

    
     // --- Servicios para Enfermedad -------------------------------
    public List<Enfermedad> getEnfermedades(){
        return enfermedadModel.getEnfermedades();
    }

       public String agregar(){
        enfermedadModel.add(enfermedad);
        return "listarE";
    }

       public String eliminar(){
        enfermedadModel.remove(enfermedad);
        return "listarE";
    }
   
    public String modificar(){
        enfermedadModel.update(enfermedad);
        return "listarE";
    }
    
    public Enfermedad buscarEnfermedad(int codigo) {
        return enfermedadModel.findById(codigo);
    }
            
   public void delEnfermedad(Enfermedad enfermedad) {
    this.enfermedades.remove(enfermedad);
  }
 
}