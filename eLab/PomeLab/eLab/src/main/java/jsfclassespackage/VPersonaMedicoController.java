package jsfclassespackage;

import com.crrb.web.lab.paquetes.elab.entidades.VPersonaMedico;
import jsfclassespackage.util.JsfUtil;
import jsfclassespackage.util.JsfUtil.PersistAction;
import sesionbeanpackage.VPersonaMedicoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("vPersonaMedicoController")
@SessionScoped
public class VPersonaMedicoController implements Serializable {

    @EJB
    private sesionbeanpackage.VPersonaMedicoFacade ejbFacade;
    private List<VPersonaMedico> items = null;
    private VPersonaMedico selected;

    public VPersonaMedicoController() {
    }

    public VPersonaMedico getSelected() {
        return selected;
    }

    public void setSelected(VPersonaMedico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VPersonaMedicoFacade getFacade() {
        return ejbFacade;
    }

    public VPersonaMedico prepareCreate() {
        selected = new VPersonaMedico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "VPersonaMedicoCreated");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "VPersonaMedicoUpdated");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "VPersonaMedicoDeleted");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VPersonaMedico> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, "PersistenceErrorOccured");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, "PersistenceErrorOccured");
            }
        }
    }

    public VPersonaMedico getVPersonaMedico(java.math.BigInteger id) {
        return getFacade().find(id);
    }

    public List<VPersonaMedico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VPersonaMedico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VPersonaMedico.class)
    public static class VPersonaMedicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VPersonaMedicoController controller = (VPersonaMedicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vPersonaMedicoController");
            return controller.getVPersonaMedico(getKey(value));
        }

        java.math.BigInteger getKey(String value) {
            java.math.BigInteger key;
//            key = value;
            return null;
        }

        String getStringKey(java.math.BigInteger value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VPersonaMedico) {
                VPersonaMedico o = (VPersonaMedico) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VPersonaMedico.class.getName()});
                return null;
            }
        }

    }

}
