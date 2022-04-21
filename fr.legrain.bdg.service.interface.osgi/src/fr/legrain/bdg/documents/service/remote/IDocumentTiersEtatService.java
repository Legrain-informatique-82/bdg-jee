package fr.legrain.bdg.documents.service.remote;

import java.util.Date;
import java.util.List;

public interface IDocumentTiersEtatService<T> {
	
	public List<T> rechercheDocumentEtat(Date dateDeb, Date datefin, String codeEtat);
	
	public List<T> rechercheDocumentEtat(Date dateDeb, Date datefin, String codeEtat, String codeTiers);

}
