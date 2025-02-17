
package Controllers;

import EJB.LicenciasFacadeLocal;
import Entity.Extranjeros;
import Entity.Licencias;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "managedLicencias")
@SessionScoped
public class managedLicencias implements Serializable{
    
    private List<Licencias> listalicencia;
    private LicenciasFacadeLocal licenciaEJBFacadeLocal;
    private Licencias licencias;
    String mensaje;

    public List<Licencias> getListalicencia() {
        return listalicencia;
    }

    public void setListalicencia(List<Licencias> listalicencia) {
        this.listalicencia = listalicencia;
    }

    public Licencias getLicencias() {
        return licencias;
    }

    public void setLicencias(Licencias licencias) {
        this.licencias = licencias;
    }
    
     @PostConstruct
    public void init() {
        licencias = new Licencias();
    }
    
   public void consultar_licencias() {
        try {
            listalicencia = licenciaEJBFacadeLocal.findAll();
        } catch (Exception e) {
        }

    } 
   
   public void insertar_licencias() {
        try {
           licenciaEJBFacadeLocal.create(licencias);
            this.mensaje = "Insertado Correctamente";
        } catch (Exception e) {
            this.mensaje = "Error al Insertar";
        }
        FacesMessage msg = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(mensaje, msg);

    }
   
      
    public void consultarId_licencias(Licencias licencia){
        try {
            this.licencias=licencia;
        } catch (Exception e) {
        }
    }
    
     public void actualizar_licencias() {
        try {
           licenciaEJBFacadeLocal.edit(licencias);
            this.mensaje = "Actualizado Correctamente";
        } catch (Exception e) {
            this.mensaje = "Error al Actualizar";
        }
        FacesMessage msg = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(mensaje, msg);

    }
     
      public void eliminar_licencias(Licencias licencia){
        this.licencias=licencia;
        try {
            licenciaEJBFacadeLocal.remove(licencias);
            listalicencia=licenciaEJBFacadeLocal.findAll();
            this.mensaje = "Eliminado Correctamente";
        } catch (Exception e) {
            this.mensaje = "Error al Eliminar";
        }
}
   
    
    
}
