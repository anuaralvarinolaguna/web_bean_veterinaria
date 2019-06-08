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
import models.PacienteModel;
import net.sf.jasperreports.engine.JRException;




@ManagedBean
@RequestScoped
public class PacienteBean {

    private Paciente paciente;
    private PacienteModel pacienteModel = new PacienteModel();
    private ArrayList<Paciente> pacientes = new ArrayList<>(); 
    private DuenoModel duenoModel = new DuenoModel ();
    
    public PacienteBean() {
        if (this.paciente == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("codigo");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.paciente = pacienteModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.paciente == null) this.paciente = new Paciente();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Paciente getPaciente() {
        return paciente;
    }

   public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.paciente = new Paciente();
    }

    
     // --- Servicios para paciente -------------------------------
    public List<Paciente> getPacientes(){
        return pacienteModel.getPacientes();
    }

       public String agregar(){
        pacienteModel.add(paciente);
        return "listarP";
    }

       public String eliminar(){
        pacienteModel.remove(paciente);
        return "listarP";
    }
   
    public String modificar(){
        pacienteModel.update(paciente);
        return "listarP";
    }
    
    public Paciente buscarPaciente(int codigo) {
        return pacienteModel.findById(codigo);
    }
            
   public void delPaciente(Paciente paciente) {
    this.pacientes.remove(paciente);
  }
 
   public List<Dueno> getDuenos(){
        return duenoModel.getDuenos();
    }
}