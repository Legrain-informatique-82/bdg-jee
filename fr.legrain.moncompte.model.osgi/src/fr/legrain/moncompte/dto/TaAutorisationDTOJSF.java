package fr.legrain.moncompte.dto;

// Generated Mar 1, 2007 11:26:10 AM by Hibernate Tools 3.2.0.b9

import java.util.Date;

import fr.legrain.moncompte.commun.model.ModelObject;
import fr.legrain.moncompte.model.TaClient;
import fr.legrain.moncompte.model.TaProduit;


/**
 * SWTInfoEntreprise generated by hbm2java
 */
public class TaAutorisationDTOJSF extends ModelObject implements java.io.Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 876238881738553794L;

	private TaAutorisationDTO dto;

	private TaProduit taProduit;
	private TaClient taClient;
	

	public void updateDTO() {
		if(dto!=null) {
			if(taProduit!=null) {
				dto.setCode(taProduit.getCode());
				dto.setIdProduit(taProduit.getIdProduit());
			}
			if(taClient!=null) {
				dto.setIdClient(taClient.getId());
			}
			
		}
	}

	public TaAutorisationDTOJSF() {
	}



	public TaAutorisationDTO getDto() {
		return dto;
	}



	public void setDto(TaAutorisationDTO dto) {
		this.dto = dto;
	}



	public TaProduit getTaProduit() {
		return taProduit;
	}



	public void setTaProduit(TaProduit taProduit) {
		this.taProduit = taProduit;
	}



	public TaClient getTaClient() {
		return taClient;
	}



	public void setTaClient(TaClient taClient) {
		this.taClient = taClient;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dto == null) ? 0 : dto.hashCode());
		result = prime * result
				+ ((taClient == null) ? 0 : taClient.hashCode());
		result = prime * result
				+ ((taProduit == null) ? 0 : taProduit.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaAutorisationDTOJSF other = (TaAutorisationDTOJSF) obj;
		if (dto == null) {
			if (other.dto != null)
				return false;
		} else if (!dto.equals(other.dto))
			return false;
		if (taClient == null) {
			if (other.taClient != null)
				return false;
		} else if (!taClient.equals(other.taClient))
			return false;
		if (taProduit == null) {
			if (other.taProduit != null)
				return false;
		} else if (!taProduit.equals(other.taProduit))
			return false;
		return true;
	}




	




}
