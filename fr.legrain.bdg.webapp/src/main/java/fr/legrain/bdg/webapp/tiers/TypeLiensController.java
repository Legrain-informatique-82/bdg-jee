package fr.legrain.bdg.webapp.tiers;

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

import fr.legrain.bdg.general.service.remote.ThrowableExceptionLgr;
import fr.legrain.bdg.lib.osgi.Const;
import fr.legrain.bdg.model.mapping.LgrDozerMapper;
import fr.legrain.bdg.tiers.service.remote.ITaTCiviliteServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTLiensServiceRemote;
import fr.legrain.bdg.webapp.ConstWeb;
import fr.legrain.lib.data.EnumModeObjet;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.ModeObjetEcranServeur;
import fr.legrain.tiers.dto.TaTLiensDTO;
import fr.legrain.tiers.model.TaTLiens;



@Named
@ViewScoped  
public class TypeLiensController implements Serializable {  

	private List<TaTLiensDTO> values; 
	private List<TaTLiensDTO> filteredValues; 
	private TaTLiensDTO nouveau ;
	private TaTLiensDTO selection ;

	private TaTLiens taTLiens = new TaTLiens();
	
	private String modeEcranDefaut;
	private static final String C_DIALOG = "dialog";

	private ModeObjetEcranServeur modeEcran = new ModeObjetEcranServeur();

	private @EJB ITaTLiensServiceRemote taTLiensService;
	
	private LgrDozerMapper<TaTLiensDTO,TaTLiens> mapperUIToModel  = new LgrDozerMapper<TaTLiensDTO,TaTLiens>();
	private LgrDozerMapper<TaTLiens,TaTLiensDTO> mapperModelToUI  = new LgrDozerMapper<TaTLiens,TaTLiensDTO>();
	
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

	public TypeLiensController() {  
	}  

	public void actInserer(ActionEvent actionEvent){
		nouveau = new TaTLiensDTO();
		modeEcran.setMode(EnumModeObjet.C_MO_INSERTION);
	}

	public void actModifier(ActionEvent actionEvent){
		nouveau = selection;
		modeEcran.setMode(EnumModeObjet.C_MO_EDITION);
	}

	public void actSupprimer(){
		TaTLiens taTLiens = new TaTLiens();
		try {
			if(selection!=null && selection.getId()!=null){
				taTLiens = taTLiensService.findById(selection.getId());
			}

			taTLiensService.remove(taTLiens);
			values.remove(selection);
			
			if(!values.isEmpty()) {
				selection = values.get(0);
			}else {
				selection=new TaTLiensDTO();
			}

			modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);

			if(ConstWeb.DEBUG) {
				FacesContext context = FacesContext.getCurrentInstance();  
				context.addMessage(null, new FacesMessage("Type liens", "actSupprimer"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type liens", e.getCause().getCause().getCause().getCause().getMessage()));
		}
	}
	
	public void actEnregistrer(ActionEvent actionEvent){
		try {
			TaTLiensDTO retour = null;
			taTLiens=new TaTLiens();
			if(nouveau.getId()==null || taTLiensService.findById(nouveau.getId()) == null){
				mapperUIToModel.map(nouveau, taTLiens);
				taTLiens = taTLiensService.merge(taTLiens, ITaTCiviliteServiceRemote.validationContext);
				mapperModelToUI.map(taTLiens, nouveau);
				values= taTLiensService.selectAllDTO();
				nouveau = values.get(0);
				nouveau = new TaTLiensDTO();

				modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);

			}else{
				if(modeEcran.getMode().compareTo(EnumModeObjet.C_MO_EDITION)==0){
					taTLiens = taTLiensService.findById(nouveau.getId());
					mapperUIToModel.map(nouveau, taTLiens);
					taTLiens = taTLiensService.merge(taTLiens, ITaTCiviliteServiceRemote.validationContext);
					mapperModelToUI.map(taTLiens, nouveau);
					values= taTLiensService.selectAllDTO();
					nouveau = values.get(0);
					nouveau = new TaTLiensDTO();

					modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
				}
				else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Code déja utilisé"));
				}
			}
			if(modeEcranDefaut!=null && modeEcranDefaut.equals(C_DIALOG)) {
				PrimeFaces.current().dialog().closeDynamic(taTLiens);
			}
		} catch(Exception e) {
			e=ThrowableExceptionLgr.renvoieCauseRuntimeException(e);
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();  
			//context.addMessage(null, new FacesMessage("Tiers", "actEnregistrer")); 
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Type liens", e.getMessage()));
		}
	}

	public void actAnnuler(ActionEvent actionEvent){
		values= taTLiensService.selectAllDTO();
		if(values.size()>= 1){
			selection = values.get(0);
		}		
		nouveau = new TaTLiensDTO();
		modeEcran.setMode(EnumModeObjet.C_MO_CONSULTATION);
	}

	public List<TaTLiensDTO> getValues(){  
		return values;

	}

	public TaTLiensDTO getNouveau() {
		return nouveau;
	}

	public void setNouveau(TaTLiensDTO newTaTLiens) {
		this.nouveau = newTaTLiens;
	}

	public TaTLiensDTO getSelection() {
		return selection;
	}

	public void setSelection(TaTLiensDTO selectedTaTLiens) {
		this.selection = selectedTaTLiens;
	}

	public void setValues(List<TaTLiensDTO> values) {
		this.values = values;
	}

	public void onRowSelect(SelectEvent event) {  
		if(modeEcran.getMode().compareTo(EnumModeObjet.C_MO_CONSULTATION)==0) {
			nouveau = selection;
		}
	}

	public List<TaTLiensDTO> getFilteredValues() {
		return filteredValues;
	}

	public void setFilteredValues(List<TaTLiensDTO> filteredValues) {
		this.filteredValues = filteredValues;
	}

	public void refresh(){
		values = taTLiensService.selectAllDTO();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		modeEcranDefaut = params.get("modeEcranDefaut");
		if(modeEcranDefaut!=null && modeEcranDefaut.equals(modeEcran.modeString(EnumModeObjet.C_MO_INSERTION))) {
			modeEcranDefaut = C_DIALOG;
			actInserer(null);
		} else if(modeEcranDefaut!=null && modeEcranDefaut.equals(modeEcran.modeString(EnumModeObjet.C_MO_EDITION))) {
			modeEcranDefaut = C_DIALOG;
			if(params.get("idEntity")!=null) {
				try {
					selection = taTLiensService.findByIdDTO(LibConversion.stringToInteger(params.get("idEntity")));
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

	public ModeObjetEcranServeur getModeEcran() {
		return modeEcran;
	}

	public String getModeEcranDefaut() {
		return modeEcranDefaut;
	}

	public void setModeEcranDefaut(String modeEcranDefaut) {
		this.modeEcranDefaut = modeEcranDefaut;
	}
	
	// Dima - Début
		public void validateObject(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//			String msg = "";
//			try {
//				String nomChamp =  (String) component.getAttributes().get("nomChamp");
//				validateUIField(nomChamp,value);
//				TaTLiensDTO temp=new TaTLiensDTO();
//				PropertyUtils.setProperty(temp, nomChamp, value);
//				taTLiensService.validateEntityProperty(temp, nomChamp, ITaTLiensServiceRemote.validationContext );
//			} catch(Exception e) {
//				msg += e.getMessage();
//				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
//			}
			String messageComplet = "";
			try {
				String nomChamp =  (String) component.getAttributes().get("nomChamp");
				//validation automatique via la JSR bean validation
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Set<ConstraintViolation<TaTLiensDTO>> violations = factory.getValidator().validateValue(TaTLiensDTO.class,nomChamp,value);
				if (violations.size() > 0) {
					messageComplet = "Erreur de validation : ";
					for (ConstraintViolation<TaTLiensDTO> cv : violations) {
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

		public boolean validateUIField(String nomChamp,Object value) {
			try {
				if(nomChamp.equals(Const.C_CODE_T_LIENS)) {
						boolean changement=false;
						if(selection.getCodeTLiens()!=null && value!=null && !selection.getCodeTLiens().equals(""))
						{
							if(value instanceof TaTLiens)
								changement=((TaTLiens) value).getCodeTLiens().equals(selection.getCodeTLiens());
							else if(value instanceof String)
							changement=!value.equals(selection.getCodeTLiens());
						}
						if(changement && modeEcran.dataSetEnModeModification()){
							FacesContext context = FacesContext.getCurrentInstance();  
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Type de liens", Const.C_MESSAGE_CHANGEMENT_CODE));
						}
					}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		public void actDialogTypes(ActionEvent actionEvent) {
		    
	        Map<String,Object> options = new HashMap<String, Object>();
	        options.put("modal", true);
	        options.put("draggable", false);
	        options.put("resizable", false);
	        options.put("contentHeight", 320);
	        options.put("modal", true);
	        
	        Map<String,List<String>> params = new HashMap<String,List<String>>();
	        List<String> list = new ArrayList<String>();
	        list.add(modeEcran.modeString(EnumModeObjet.C_MO_INSERTION));
	        params.put("modeEcranDefaut", list);
	        
	        PrimeFaces.current().dialog().openDynamic("tiers/dialog_type_liens", options, params);
	        
		}
		
		public void handleReturnDialogTypes(SelectEvent event) {
			if(event!=null && event.getObject()!=null) {
				taTLiens = (TaTLiens) event.getObject();
				
			}
			refresh();
		}
		
		public void actDialogModifier(ActionEvent actionEvent){
			
			nouveau = selection;
//			modeEcran.setMode(EnumModeObjet.C_MO_EDITION);
			
			Map<String,Object> options = new HashMap<String, Object>();
	        options.put("modal", true);
	        options.put("draggable", false);
	        options.put("resizable", false);
	        options.put("contentHeight", 320);
	        options.put("modal", true);
	        
	        Map<String,List<String>> params = new HashMap<String,List<String>>();
	        List<String> list = new ArrayList<String>();
	        list.add(modeEcran.modeString(EnumModeObjet.C_MO_EDITION));
	        params.put("modeEcranDefaut", list);
	        List<String> list2 = new ArrayList<String>();
	        list2.add(LibConversion.integerToString(selection.getId()));
	        params.put("idEntity", list2);
	        
	        PrimeFaces.current().dialog().openDynamic("tiers/dialog_type_liens", options, params);

		}
		// Dima -  Fin
}  
