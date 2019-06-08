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
import models.DuenoModel;
import net.sf.jasperreports.engine.JRException;


@ManagedBean
@RequestScoped
public class DuenoBean {

    private Dueno dueno;
    private DuenoModel duenoModel = new DuenoModel();
    private ArrayList<Dueno> duenos = new ArrayList<>(); 
    
    public DuenoBean() {
        if (this.dueno == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("idD");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.dueno = duenoModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.dueno == null) this.dueno = new Dueno();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Dueno getDueno() {
        return dueno;
    }

   public void setDueno(Dueno dueno) {
        this.dueno = dueno;
        this.dueno = new Dueno();
    }

    
     // --- Servicios para dueños -------------------------------
    public List<Dueno> getDuenos(){
        return duenoModel.getDuenos();
    }

       public String agregar(){
        duenoModel.add(dueno);
        return "listar";
    }

       public String eliminar(){
        duenoModel.remove(dueno);
        return "listar";
    }
   
    public String modificar(){
        duenoModel.update(dueno);
        return "listar";
    }
    
    public Dueno buscarDueno(int idD) {
        return duenoModel.findById(idD);
    }
            
   public void delDueno(Dueno dueno) {
    this.duenos.remove(dueno);
  }
 
}