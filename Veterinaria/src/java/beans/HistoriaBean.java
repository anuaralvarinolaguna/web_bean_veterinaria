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
import models.HistoriaModel;
import models.PacienteModel;
import net.sf.jasperreports.engine.JRException;




@ManagedBean
@RequestScoped
public class HistoriaBean {

    private Historia historia;
    private HistoriaModel historiaModel = new HistoriaModel();
    private ArrayList<Historia> historias = new ArrayList<>(); 
     private DuenoModel duenoModel = new DuenoModel ();
      private PacienteModel pacienteModel = new PacienteModel();
    
    public HistoriaBean() {
        if (this.historia == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("codigo");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.historia = historiaModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.historia == null) this.historia = new Historia();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Historia getHistoria() {
        return historia;
    }

   public void setHistoria(Historia historia) {
        this.historia = historia;
        this.historia = new Historia();
    }

    
     // --- Servicios para historia -------------------------------
    public List<Historia> getHistorias(){
        return historiaModel.getHistorias();
    }

       public String agregar(){
        historiaModel.add(historia);
        return "listarH";
    }

       public String eliminar(){
        historiaModel.remove(historia);
        return "listarH";
    }
   
    public String modificar(){
        historiaModel.update(historia);
        return "listarH";
    }
    
    public Historia buscarMedicamento(int codigo) {
        return historiaModel.findById(codigo);
    }
            
   public void delHistoria(Historia historia) {
    this.historias.remove(historia);
  }
  public List<Dueno> getDuenos(){
        return duenoModel.getDuenos();
    }
 public List<Paciente> getPacientes(){
        return pacienteModel.getPacientes();
    }

}