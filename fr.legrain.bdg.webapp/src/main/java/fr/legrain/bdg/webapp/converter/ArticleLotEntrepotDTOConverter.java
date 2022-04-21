package fr.legrain.bdg.webapp.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.legrain.article.dto.ArticleLotEntrepotDTO;
import fr.legrain.bdg.webapp.solstyce.FabricationController;
import fr.legrain.lib.data.LibConversion;

@Named
@ViewScoped

//@FacesConverter(forClass=ArticleLotEntrepotDTO.class)
public class ArticleLotEntrepotDTOConverter implements Converter, Serializable {
	
	@Inject @Named(value="fabricationController")
	private FabricationController fabricationControllerConverter;
	
	//@EJB ITaArticleServiceRemote  taArticleService;

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueToRender) {
    	if (valueToRender == null || valueToRender.equals("")) {  
            return "";  
        } else {  
            //return String.valueOf(((User) valueToRender).getNom())+" "+String.valueOf(((User) valueToRender).getPrenom());  
        	return String.valueOf(((ArticleLotEntrepotDTO) valueToRender).getIdALE());
//        	return String.valueOf(((ArticleLotEntrepotDTO) valueToRender).getNumLot());
        }  
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        // Convert String to MyObj.
    	if (submittedValue!=null && (submittedValue.trim().equals("")||submittedValue.trim().equals("null"))) {  
    		return null;  
    	} else {  
    		try {  

    			//taArticleService = new EJBLookup<ITaArticleServiceRemote>().doLookup(new InitialContext(),EJBLookup.EJB_NAME_TA_ARTICLE_SERVICE, ITaArticleServiceRemote.class.getName(),true);

    			if(submittedValue!=null && fabricationControllerConverter.getListArticleLotEntrepot()!=null) {
    			
	    			//return taArticleService.findByCode(submittedValue);
	    			ArticleLotEntrepotDTO a = null;
	    			for(ArticleLotEntrepotDTO l : fabricationControllerConverter.getListArticleLotEntrepot()) {
	    				if(l.getIdALE().intValue()==LibConversion.stringToInteger(submittedValue)) {
	    					a = l;
	    				}
	    			}
	    			return a;
    			} else {
    				return null;
    			}

//    		} catch (FinderException e) {
//    			e.printStackTrace();
    		} catch (/*Naming*/Exception e) {
    			e.printStackTrace();
    		}
    	}
		return submittedValue;
    }

	public FabricationController getFabricationControllerConverter() {
		return fabricationControllerConverter;
	}

	public void setFabricationControllerConverter(
			FabricationController fabricationControllerConverter) {
		this.fabricationControllerConverter = fabricationControllerConverter;
	}

}