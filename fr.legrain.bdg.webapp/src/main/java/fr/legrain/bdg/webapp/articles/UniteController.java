package fr.legrain.bdg.webapp.articles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import fr.legrain.article.dto.TaUniteDTO;
import fr.legrain.article.model.TaFamilleUnite;
import fr.legrain.article.model.TaUnite;
import fr.legrain.bdg.article.service.remote.ITaFamilleUniteServiceRemote;
import fr.legrain.bdg.article.service.remote.ITaUniteServiceRemote;
import fr.legrain.bdg.general.service.remote.ThrowableExceptionLgr;
import fr.legrain.bdg.lib.osgi.Const;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote;
import fr.legrain.bdg.webapp.ConstWeb;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.ModeObjetEcranServeur;

@Named
@ViewScoped  
public class UniteController implements Serializable {  

	private List<TaUniteDTO> values; 
	private List<TaUniteDTO> filteredValues; 
	private TaUniteDTO nouveau ;
	private TaUniteDTO selection ;
	
	private TaUnite taTUnite = new TaUnite();
	
	private String modeEcranDefaut;
	private static final String C_DIALOG = "dialog";

	private TaFamilleUnite taFamilleUnite;

	private ModeObjetEcranServeur modeEcran = new ModeObjetEcranServeur();
	
	private @EJB ITaUniteServiceRemote taUniteService;
	
	private @EJB ITaFamilleUniteServiceRemote taFamilleUniteService;
	
	private TaUniteDTO taUnite = new TaUniteDTO();
	
	private LgrDozerMapper<TaUniteDTO,TaUnite> mapperUIToModel  = new LgrDozerMapper<TaUniteDTO,TaUnite>();
	private LgrDozerMapper<TaUnite,TaUniteDTO> mapperModelToUI  = new LgrDozerMapper<TaUnite,TaUniteDTO>();


	@PostConstruct
	public void postConstruct(){
		
		try {
			refresh();
			if(values != null && !values.isEmpty()){
				selection = values.get(0);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setFilteredValues(values);
	}
	
	public UniteController() {  
	} 

	public void refresh(){
		values = taUniteService.selectAllDTO();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		modeEcranDefaut = params.get("modeEcranDefaut");
		if(modeEcranDefaut!=null && modeEcranDefaut.equals(modeEcran.modeString(EnumModeObjet.C_MO_INSERTION))) {
			modeEcranDefaut = C_DIALOG;
			actInserer(null);
		} else if(modeEcranDefaut!=null && modeEcranDefaut.equals(modeEcran.modeString(EnumModeObjet.C_MO_EDITION))) {
			modeEcranDefaut = C_DIALOG;
			if(params.get("idEntity")!=null) {
				try {
					selection = taUniteService.findByIdDTO(LibConversion.stringToInteger(params.get("idEntity")));
				} catch (FinderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			actModifier(null);
		} else {
			modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
		}
	}
	
	public void actFermerDialog(ActionEvent actionEvent) {
		PrimeFaces.current().dialog().closeDynamic(null);
	}
	
	public void actAnnuler(ActionEvent actionEvent){
		if(values.size()>= 1){
			selection = values.get(0);
		}		
		nouveau = new TaUniteDTO();

		modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
	}

	
	public void autoCompleteMapUIToEntity() {
		if(taFamilleUnite!=null) {
			validateUIField(Const.C_CODE_FAMILLE,taFamilleUnite.getCodeFamille());
			taTUnite.setTaFamilleUnite(taFamilleUnite);
		}
		
	}
	
	public void autoCompleteMapEntitytoUI() {
		try {
			taFamilleUnite = null;
			
			if(taTUnite.getTaFamilleUnite()!=null ) {
				taFamilleUnite = taFamilleUniteService.findByCode(taTUnite.getTaFamilleUnite().getCodeFamille());
			}
			
		} catch (FinderException e) {
			e.printStackTrace();
		}
	}
	
	public void actEnregistrer(ActionEvent actionEvent){
		try {
			TaUniteDTO retour = null;
			taTUnite=new TaUnite();
			if(nouveau.getId()==null || taUniteService.findById(nouveau.getId()) == null){
				mapperUIToModel.map(nouveau, taTUnite);
				taTUnite = taUniteService.merge(taTUnite, ITaTCiviliteServiceRemote.validationContext);
				mapperModelToUI.map(taTUnite, nouveau);
				values= taUniteService.selectAllDTO();
				nouveau = values.get(0);
				nouveau = new TaUniteDTO();

				modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);

			}else{
				if(modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION)==0){
					taTUnite = taUniteService.findById(nouveau.getId());
					mapperUIToModel.map(nouveau, taTUnite);
					taTUnite = taUniteService.merge(taTUnite, ITaTCiviliteServiceRemote.validationContext);
					mapperModelToUI.map(taTUnite, nouveau);
					values= taUniteService.selectAllDTO();
					nouveau = values.get(0);
					nouveau = new TaUniteDTO();

					modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
				}
				else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Code déja utilisé"));
				}
			}
			if(modeEcranDefaut!=null && modeEcranDefaut.equals(C_DIALOG)) {
				PrimeFaces.current().dialog().closeDynamic(taTUnite);
			}
		} catch(Exception e) {
			e=ThrowableExceptionLgr.renvoieCauseRuntimeException(e);
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "TypeUnite", e.getMessage()));
		}
	}

	public void actInserer(ActionEvent actionEvent)
	{

		nouveau = new TaUniteDTO();
		modeEcran.setMode(EnumModeObjet.C_MO_INSERTION);

	}

	public void actModifier(ActionEvent actionEvent){

		nouveau = selection;
		modeEcran.setMode(EnumModeObjet.C_MO_EDITION);
	}

	public void actSupprimer(){
		TaUnite taUnite = new TaUnite();
		try {
			if(selection!=null && selection.getId()!=null){
				taUnite = taUniteService.findById(selection.getId());
			}

			taUniteService.remove(taUnite);
			values.remove(selection);
			
			if(!values.isEmpty()) {
				selection = values.get(0);
			}else {
				selection=new TaUniteDTO();
			}

			modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);

			if(ConstWeb.DEBUG) {
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage("unité", "actSupprimer"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "unité", e.getCause().getCause().getCause().getCause().getMessage()));
		}
	}
	
	public void onRowSelect (){
		if(modeEcran.getMode().compareTo(EnumModeObjet.C_MO_CONSULTATION)==0) {
			nouveau = selection;
		} 
	}
	
	public boolean editable() {
		return !modeEcran.dataSetEnModif();
	}
	
	public void autcompleteSelection(SelectEvent event) {
		Object value = event.getObject();
		
		String nomChamp =  (String) event.getComponent().getAttributes().get("nomChamp");

		validateUIField(nomChamp,value);
	}
	
	
	public boolean validateUIField(String nomChamp,Object value) {
		boolean changement=false;
		try {				
			if(nomChamp.equals(Const.C_CODE_FAMILLE)) {
				TaFamilleUnite entity = new TaFamilleUnite();
				if(value!=null){
					if(value instanceof TaFamilleUnite){
						entity=(TaFamilleUnite)value;
					}else{
						entity = taFamilleUniteService.findByCode((String)value);
					}
					taTUnite.setTaFamilleUnite(entity);															
				}else {
					nouveau.setCodeFamille("");
				}
			}
			if(nomChamp.equals(Const.C_CODE_UNITE)) {
				if(selection.getCodeUnite()!=null && value!=null && !selection.getCodeUnite().equals(""))
				{
					if(value instanceof TaUnite)
						changement=((TaUnite) value).getCodeUnite().equals(selection.getCodeUnite());
					else if(value instanceof String)
					changement=!value.equals(selection.getCodeUnite());
				}
				if(changement && modeEcran.dataSetEnModeModification()){
					FacesContext context = FacesContext.getCurrentInstance();  
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Unité", Const.C_MESSAGE_CHANGEMENT_CODE));
				}
			}			
			return false;
		} catch (Exception e) {
		}
		return false;
	}
	
	public boolean etatBouton(String bouton) {
		boolean retour = true;
		switch (modeEcran.getMode()) {
		case C_MO_INSERTION:
			switch (bouton) {
			case "annuler":
			case "enregistrer":
			case "fermer":
				retour = false;
				break;
			default:
				break;
			}
			break;
		case C_MO_EDITION:
			switch (bouton) {
			case "annuler":
			case "enregistrer":
			case "fermer":
				retour = false;
				break;
			default:
				break;
			}
			break;
		case C_MO_CONSULTATION:
			switch (bouton) {
			case "supprimer":
			case "modifier":
			case "inserer":
			case "imprimer":
			case "fermer":
				retour = false;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return retour;
	}

	public List<TaUniteDTO> getValues(){  
		return values;
	}
	
	public List<TaFamilleUnite> typeFamilleUniteAutoComplete(String query) {
        List<TaFamilleUnite> allValues = taFamilleUniteService.selectAll();
        List<TaFamilleUnite> filteredValues = new ArrayList<TaFamilleUnite>();
        
        TaFamilleUnite civ = new TaFamilleUnite();
        civ.setCodeFamille(Const.C_AUCUN);
        filteredValues.add(civ);
        for (int i = 0; i < allValues.size(); i++) {
        	 civ = allValues.get(i);
            if(query.equals("*") || civ.getCodeFamille().toLowerCase().contains(query.toLowerCase())) {
                filteredValues.add(civ);
            }
        }
        return filteredValues;
    }

	public TaUniteDTO getNouveau() {
		return nouveau;
	}

	public void setNouveau(TaUniteDTO newTaUnite) {
		this.nouveau = newTaUnite;
	}

	public TaUniteDTO getSelection() {
		return selection;
	}

	public void setSelection(TaUniteDTO selectedTaUnite) {
		this.selection = selectedTaUnite;
	}

	public void setValues(List<TaUniteDTO> values) {
		this.values = values;
	}

	public String getModeEcranDefaut() {
		return modeEcranDefaut;
	}

	public void setModeEcranDefaut(String modeEcranDefaut) {
		this.modeEcranDefaut = modeEcranDefaut;
	}

	public ModeObjetEcranServeur getModeEcran() {
		return modeEcran;
	}

	public void setModeEcran(ModeObjetEcranServeur modeEcran) {
		this.modeEcran = modeEcran;
	}

	public ITaUniteServiceRemote getTaUniteService() {
		return taUniteService;
	}

	public void setTaUniteService(
			ITaUniteServiceRemote taUniteService) {
		this.taUniteService = taUniteService;
	}

	public static String getcDialog() {
		return C_DIALOG;
	}

	public List<TaUniteDTO> getFilteredValues() {
	    return filteredValues;
	}

	public void setFilteredValues(List<TaUniteDTO> filteredValues) {
	    this.filteredValues = filteredValues;
	}
	
	public TaFamilleUnite getTaFamilleUnite() {
		return taFamilleUnite;
	}

	public void setTaFamilleUnite(TaFamilleUnite taFamilleUnite) {
		this.taFamilleUnite = taFamilleUnite;
	}

	public ITaFamilleUniteServiceRemote getTaFamilleUniteService() {
		return taFamilleUniteService;
	}

	public void setTaFamilleUniteService(
			ITaFamilleUniteServiceRemote taFamilleUniteService) {
		this.taFamilleUniteService = taFamilleUniteService;
	}

	public TaUniteDTO getTaUnite() {
		return taUnite;
	}

	public void setTaUnite(TaUniteDTO taUnite) {
		this.taUnite = taUnite;
	}

	// Dima - Début
	public void validateObject(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//		String msg = "";
//		try {
//			String nomChamp =  (String) component.getAttributes().get("nomChamp");
//			validateUIField(nomChamp,value);
//			TaUniteDTO temp=new TaUniteDTO();
//			PropertyUtils.setProperty(temp, nomChamp, value);
//			taUniteService.validateEntityProperty(temp, nomChamp, ITaUniteServiceRemote.validationContext );
//		} catch(Exception e) {
//			msg += e.getMessage();
//			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
//		}
		String messageComplet = "";
		try {
			String nomChamp =  (String) component.getAttributes().get("nomChamp");
			//validation automatique via la JSR bean validation
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Set<ConstraintViolation<TaUniteDTO>> violations = factory.getValidator().validateValue(TaUniteDTO.class,nomChamp,value);
			if (violations.size() > 0) {
				messageComplet = "Erreur de validation : ";
				for (ConstraintViolation<TaUniteDTO> cv : violations) {
					messageComplet+=" "+cv.getMessage()+"\n";
				}
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, messageComplet, messageComplet));
			} else {
				//aucune erreur de validation "automatique", on déclanche les validations du controller
				validateUIField(nomChamp,value);
			}
		} catch(Exception e) {
			//messageComplet += e.getMessage();
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, messageComplet, messageComplet));
		}
	}

	public void actDialogTypes(ActionEvent actionEvent) {
	    
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 500);
        
        Map<String,List<String>> params = new HashMap<String,List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(modeEcran.modeString(EnumModeObjet.C_MO_INSERTION));
        params.put("modeEcranDefaut", list);
        
        PrimeFaces.current().dialog().openDynamic("articles/dialog_type_unite", options, params);
        
	}
	
	public void handleReturnDialogTypes(SelectEvent event) {
		if(event!=null && event.getObject()!=null) {
			taTUnite = (TaUnite) event.getObject();
			
		}
		refresh();
	}
	
	public void actDialogModifier(ActionEvent actionEvent){
		
		nouveau = selection;
//		modeEcran.setMode(EnumModeObjet.C_MO_EDITION);
		
		Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("draggable", true);
        options.put("resizable", false);
        options.put("contentHeight", 500);
        options.put("modal", true);
        
        Map<String,List<String>> params = new HashMap<String,List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(modeEcran.modeString(EnumModeObjet.C_MO_EDITION));
        params.put("modeEcranDefaut", list);
        List<String> list2 = new ArrayList<String>();
        list2.add(LibConversion.integerToString(selection.getId()));
        params.put("idEntity", list2);
        
        PrimeFaces.current().dialog().openDynamic("articles/dialog_type_unite", options, params);

	}
	// Dima -  Fin
}  
