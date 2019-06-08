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
import models.MedicamentoModel;
import net.sf.jasperreports.engine.JRException;




@ManagedBean
@RequestScoped
public class MedicamentoBean {

    private Medicamento medicamento;
    private MedicamentoModel medicamentoModel = new MedicamentoModel();
    private ArrayList<Medicamento> medicamentos = new ArrayList<>(); 
    
    public MedicamentoBean() {
        if (this.medicamento == null) {
           ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
           String idParam = ctx.getRequestParameterMap().get("codigo");
            if (idParam != null && !idParam.equals("")) {
                try {
                    this.medicamento = medicamentoModel.findById(Integer.parseInt(idParam));
                } 
                catch (NumberFormatException e) {
                    
                }
            }
       
            if (this.medicamento == null) this.medicamento = new Medicamento();
        }
    }
     public void inicializar() throws JRException {
    
  }
   	

    public Medicamento getMedicamento() {
        return medicamento;
    }

   public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
        this.medicamento = new Medicamento();
    }

    
     // --- Servicios para medicamento -------------------------------
    public List<Medicamento> getMedicamentos(){
        return medicamentoModel.getMedicamentos();
    }

       public String agregar(){
        medicamentoModel.add(medicamento);
        return "listarM";
    }

       public String eliminar(){
        medicamentoModel.remove(medicamento);
        return "listarM";
    }
   
    public String modificar(){
        medicamentoModel.update(medicamento);
        return "listarM";
    }
    
    public Medicamento buscarMedicamento(int codigo) {
        return medicamentoModel.findById(codigo);
    }
            
   public void delEnfermedad(Enfermedad enfermedad) {
    this.medicamentos.remove(medicamento);
  }
 
}