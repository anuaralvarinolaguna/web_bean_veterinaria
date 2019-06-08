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
import models.VeterinarioModel;
import net.sf.jasperreports.engine.JRException;




@ManagedBean
@RequestScoped
public class VeterinarioBean {

    private Veterinario veterinario;
    private VeterinarioModel veterinarioModel = new VeterinarioModel();
    private ArrayList<Veterinario> veterinarios = new ArrayList<>(); 
    
    public VeterinarioBean() {
        if (this.veterinario == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("id");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.veterinario = veterinarioModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.veterinario == null) this.veterinario = new Veterinario();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Veterinario getVeterinario() {
        return veterinario;
    }

   public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
        this.veterinario = new Veterinario();
    }

    
     // --- Servicios para dueños -------------------------------
    public List<Veterinario> getVeterinarios(){
        return veterinarioModel.getVeterinarios();
    }

       public String agregar(){
        veterinarioModel.add(veterinario);
        return "listarV";
    }

       public String eliminar(){
        veterinarioModel.remove(veterinario);
        return "listarV";
    }
   
    public String modificar(){
        veterinarioModel.update(veterinario);
        return "listarV";
    }
    
    public Veterinario buscarVeterinario(int idV) {
        return veterinarioModel.findById(idV);
    }
            
   public void delVeterinario(Veterinario veterinario) {
    this.veterinarios.remove(veterinario);
  }
 
}