package fr.legrain.bdg.webapp.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;

import fr.legrain.article.model.TaCategorieArticle;
import fr.legrain.bdg.article.service.remote.ITaCategorieArticleServiceRemote;
//import fr.legrain.article.model.TaFamille;
//import fr.legrain.bdg.article.service.remote.ITaFamilleServiceRemote;
//import fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote;
//import fr.legrain.bdg.tiers.service.remote.ITaTTiersServiceRemote;
import fr.legrain.lib.ejb.EJBLookup;
//import fr.legrain.tiers.model.TaTTiers;


@FacesConverter(forClass=TaCategorieArticle.class)
//http://stackoverflow.com/questions/13156671/how-can-i-inject-in-facesconverter
//@Named
//@SessionScoped
public class TaCategorieArticleConverter implements Converter, Serializable {
	
	@EJB ITaCategorieArticleServiceRemote  taFamilleService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
    	if (valueToRender == null || valueToRender.equals("")) {  
            return "";  
        } else {  
            //return String.valueOf(((User) valueToRender).getNom())+" "+String.valueOf(((User) valueToRender).getPrenom());  
        	return String.valueOf(((TaCategorieArticle) valueToRender).getCodeCategorieArticle());
        }  
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	if (submittedValue.trim().equals("")) {  
    		return null;  
    	} else {  
    		try {  
//    			int number = Integer.parseInt(submittedValue);  

    			//pas d'injection dans les converter avant jsf 2.3
//    			taTCiviliteService = (ITaTCiviliteServiceRemote) new InitialContext().lookup("java:global/fr.careco.ear/fr.careco.ejb.mvn/UsersService!fr.legrain.bdg.tiers.service.remote.IUserServiceRemote");
    			taFamilleService = new EJBLookup<ITaCategorieArticleServiceRemote>().doLookup(new InitialContext(),EJBLookup.EJB_NAME_TA_CATEGORIE_ARTICLE_SERVICE, ITaCategorieArticleServiceRemote.class.getName());

    			return taFamilleService.findByCode(submittedValue);
//    			return taFamilleService.findById(number);

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