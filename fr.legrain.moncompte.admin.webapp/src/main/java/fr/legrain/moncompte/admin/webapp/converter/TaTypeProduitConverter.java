package fr.legrain.moncompte.admin.webapp.converter;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import fr.legrain.bdg.moncompte.service.remote.ITaTypeProduitServiceRemote;
import fr.legrain.moncompte.ejb.lib.osgi.EJBLookup;
import fr.legrain.moncompte.model.TaTypeProduit;


@FacesConverter(forClass=TaTypeProduit.class)
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
//@ManagedBean
//@SessionScoped
public class TaTypeProduitConverter implements Converter {
	
	@EJB ITaTypeProduitServiceRemote  taTCiviliteService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
    	if (valueToRender == null || valueToRender.equals("")) {  
            return "";  
        } else {  
            //return String.valueOf(((User) valueToRender).getNom())+" "+String.valueOf(((User) valueToRender).getPrenom());  
        	return String.valueOf(((TaTypeProduit) valueToRender).getCode());
        }  
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	if (submittedValue.trim().equals("")) {  
    		return null;  
    	} else {  
    		try {  
    			//int number = Integer.parseInt(submittedValue);  

    			//pas d'injection dans les converter avant jsf 2.3
//    			taTCiviliteService = (ITaTCiviliteServiceRemote) new InitialContext().lookup("java:global/fr.legrain.bdg.ear/fr.legrain.bdg.ejb/TaTCiviliteService!fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote");
    			taTCiviliteService = new EJBLookup<ITaTypeProduitServiceRemote>().doLookup(new InitialContext(),EJBLookup.EJB_NAME_TA_TYPE_PRODUIT_SERVICE, ITaTypeProduitServiceRemote.class.getName());

    			return taTCiviliteService.findByCode(submittedValue);
//    			return taTCiviliteService.findById(number);

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