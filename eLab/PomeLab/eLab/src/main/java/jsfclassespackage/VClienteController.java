package jsfclassespackage;

import com.crrb.web.lab.paquetes.elab.entidades.VCliente;
import jsfclassespackage.util.JsfUtil;
import jsfclassespackage.util.JsfUtil.PersistAction;
import sesionbeanpackage.VClienteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
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

@Named("vClienteController")
@SessionScoped
public class VClienteController implements Serializable {

    @EJB
    private sesionbeanpackage.VClienteFacade ejbFacade;
    private List<VCliente> items = null;
    private VCliente selected;

    public VClienteController() {
    }

    public VCliente getSelected() {
        return selected;
    }

    public void setSelected(VCliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VClienteFacade getFacade() {
        return ejbFacade;
    }

    public VCliente prepareCreate() {
        selected = new VCliente();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VClienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VCliente> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public VCliente getVCliente(java.math.BigInteger id) {
        return getFacade().find(id);
    }

    public List<VCliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VCliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VCliente.class)
    public static class VClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VClienteController controller = (VClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vClienteController");
            return controller.getVCliente(getKey(value));
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
            if (object instanceof VCliente) {
                VCliente o = (VCliente) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VCliente.class.getName()});
                return null;
            }
        }

    }

}
