package jsfclassespackage;

import com.crrb.web.lab.paquetes.elab.entidades.Imagenes;
import java.io.ByteArrayInputStream;
import jsfclassespackage.util.JsfUtil;
import jsfclassespackage.util.JsfUtil.PersistAction;
import sesionbeanpackage.ImagenesFacade;

import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("imagenesController")
@SessionScoped
public class ImagenesController implements Serializable {

//--se debe crear este metodo en este caso es de la factura
    public StreamedContent getContent() {
        try {

            BigDecimal bd;
            bd = selected.getId();
            onPrerender(bd);

        } catch (Exception e) {
        }
        return content;
    }

    private StreamedContent content;

    public void onPrerender(BigDecimal id) {
        id = (id == null) ? new BigDecimal("-1") : id;
        Imagenes find;
        find = ejbFacade.find(id);
        if (find == null) {
            find = new Imagenes();
            content = new ByteArrayContent();
        } else {

            byte[] bytes = find.getImage().getBytes();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            content = new DefaultStreamedContent(byteArrayInputStream, "image/svg+xml");
        }

    }

    @EJB
    private sesionbeanpackage.ImagenesFacade ejbFacade;
    private List<Imagenes> items = null;
    private Imagenes selected;

    public ImagenesController() {
    }

    public Imagenes getSelected() {
        return selected;
    }

    public void setSelected(Imagenes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagenesFacade getFacade() {
        return ejbFacade;
    }

    public Imagenes prepareCreate() {
        selected = new Imagenes();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImagenesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImagenesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImagenesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Imagenes> getItems() {
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

    public Imagenes getImagenes(java.math.BigDecimal id) {
        return getFacade().find(id);
    }

    public List<Imagenes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Imagenes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Imagenes.class)
    public static class ImagenesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagenesController controller = (ImagenesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagenesController");
            return controller.getImagenes(getKey(value));
        }

        java.math.BigDecimal getKey(String value) {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        }

        String getStringKey(java.math.BigDecimal value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Imagenes) {
                Imagenes o = (Imagenes) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Imagenes.class.getName()});
                return null;
            }
        }

    }

}
