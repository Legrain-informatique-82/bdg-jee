package fr.legrain.bdg.webapp.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import fr.legrain.bdg.conformite.service.remote.ITaModeleConformiteServiceRemote;
import fr.legrain.conformite.model.TaModeleConformite;
import fr.legrain.lib.ejb.EJBLookup;


@FacesConverter(forClass=TaModeleConformite.class)
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
//@Named
//@SessionScoped
public class TaModeleConformiteConverter implements Converter, Serializable {
	
	@EJB ITaModeleConformiteServiceRemote  taModeleConformiteService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
    	if (valueToRender == null || valueToRender.equals("")) {  
            return "";  
        } else {  
            //return String.valueOf(((User) valueToRender).getNom())+" "+String.valueOf(((User) valueToRender).getPrenom());  
        	return String.valueOf(((TaModeleConformite) valueToRender).getLibelleConformite());
        }  
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	if (submittedValue.trim().equals("")) {  
    		return null;  
    	} else {  
    		try {  
    			int number = Integer.parseInt(submittedValue);  

    			//pas d'injection dans les converter avant jsf 2.3
//    			taTCiviliteService = (ITaTCiviliteServiceRemote) new InitialContext().lookup("java:global/fr.legrain.bdg.ear/fr.legrain.bdg.ejb/TaTCiviliteService!fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote");
    			taModeleConformiteService = new EJBLookup<ITaModeleConformiteServiceRemote>().doLookup(new InitialContext(),EJBLookup.EJB_NAME_TA_MODELE_CONFORMITE_SERVICE, ITaModeleConformiteServiceRemote.class.getName());

    			return taModeleConformiteService.findByCode(submittedValue);
//    			return taModeleConformiteService.findById(number);

    		} catch (FinderException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (/*Naming*/Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
		return submittedValue;
    }

}