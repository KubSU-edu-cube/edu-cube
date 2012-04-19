package edu.kubsu.fpm.converter;

import edu.kubsu.fpm.entity.Country;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by IntelliJ IDEA.
 * User: anna
 * Date: 19.04.12
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
@FacesConverter("edu.kubsu.fpm.converter.SelectOneConverter")
public class SelectOneConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        String a = s.intern();
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Country country = (Country)o;
        return country.getName();  //To change body of implemented methods use File | Settings | File Templates.
    }
}
