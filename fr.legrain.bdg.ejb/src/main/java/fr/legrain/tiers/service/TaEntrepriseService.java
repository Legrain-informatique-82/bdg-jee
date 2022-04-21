package fr.legrain.tiers.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;

import org.apache.log4j.Logger;
import org.hibernate.OptimisticLockException;

import fr.legrain.bdg.model.mapping.mapper.TaEntrepriseMapper;
import fr.legrain.bdg.tiers.service.remote.ITaEntrepriseServiceRemote;
import fr.legrain.data.AbstractApplicationDAOServer;
import fr.legrain.hibernate.multitenant.ServerTenantInterceptor;
import fr.legrain.tiers.dao.IEntrepriseDAO;
import fr.legrain.tiers.dto.TaEntrepriseDTO;
import fr.legrain.tiers.model.TaEntreprise;

/**
 * Session Bean implementation class TaEntrepriseBean
 */
@Stateless
@DeclareRoles("admin")
@Interceptors(ServerTenantInterceptor.class)
public class TaEntrepriseService extends AbstractApplicationDAOServer<TaEntreprise, TaEntrepriseDTO> implements ITaEntrepriseServiceRemote {

	static Logger logger = Logger.getLogger(TaEntrepriseService.class);

	@Inject private IEntrepriseDAO dao;

	/**
	 * Default constructor. 
	 */
	public TaEntrepriseService() {
		super(TaEntreprise.class,TaEntrepriseDTO.class);
	}
	
	//	private String defaultJPQLQuery = "select a from TaEntreprise a";

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										ENTITY
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void persist(TaEntreprise transientInstance) throws CreateException {
		persist(transientInstance, null);
	}

	//@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@WebMethod(operationName = "persistValidationContext")
	public void persist(TaEntreprise transientInstance, String validationContext) throws CreateException {

		validateEntity(transientInstance, validationContext);

		dao.persist(transientInstance);
	}

	public void remove(TaEntreprise persistentInstance) throws RemoveException {
		try {
			dao.remove(findById(persistentInstance.getIdEntreprise()));
		} catch (FinderException e) {
			logger.error("", e);
		}
	}
	
	public TaEntreprise merge(TaEntreprise detachedInstance) {
		return merge(detachedInstance, null);
	}

	@Override
	@WebMethod(operationName = "mergeValidationContext")
	public TaEntreprise merge(TaEntreprise detachedInstance, String validationContext) {
		validateEntity(detachedInstance, validationContext);

		return dao.merge(detachedInstance);
	}

	public TaEntreprise findById(int id) throws FinderException {
		return dao.findById(id);
	}

	public TaEntreprise findByCode(String code) throws FinderException {
		return dao.findByCode(code);
	}

//	@RolesAllowed("admin")
	public List<TaEntreprise> selectAll() {
		return dao.selectAll();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// 										DTO
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<TaEntrepriseDTO> findWithNamedQueryDTO(String queryName) throws FinderException {
		return null;
	}

	@Override
	public List<TaEntrepriseDTO> findWithJPQLQueryDTO(String JPQLquery) throws FinderException {
		List<TaEntreprise> entityList = dao.findWithJPQLQuery(JPQLquery);
		List<TaEntrepriseDTO> l = null;
		if(entityList!=null) {
			l = listEntityToListDTO(entityList);
		}
		return l;
	}

	public TaEntrepriseDTO entityToDTO(TaEntreprise entity) {
//		TaEntrepriseDTO dto = new TaEntrepriseDTO();
//		dto.setId(entity.getIdTCivilite());
//		dto.setCodeTCivilite(entity.getCodeTCivilite());
//		return dto;
		TaEntrepriseMapper mapper = new TaEntrepriseMapper();
		return mapper.mapEntityToDto(entity, null);
	}

	public List<TaEntrepriseDTO> listEntityToListDTO(List<TaEntreprise> entity) {
		List<TaEntrepriseDTO> l = new ArrayList<TaEntrepriseDTO>();

		for (TaEntreprise taTCivilite : entity) {
			l.add(entityToDTO(taTCivilite));
		}

		return l;
	}

//	@RolesAllowed("admin")
	public List<TaEntrepriseDTO> selectAllDTO() {
		System.out.println("List of TaEntrepriseDTO EJB :");
		ArrayList<TaEntrepriseDTO> liste = new ArrayList<TaEntrepriseDTO>();

		List<TaEntreprise> projects = selectAll();
		for(TaEntreprise project : projects) {
			liste.add(entityToDTO(project));
		}

		return liste;
	}

	public TaEntrepriseDTO findByIdDTO(int id) throws FinderException {
		return entityToDTO(findById(id));
	}

	public TaEntrepriseDTO findByCodeDTO(String code) throws FinderException {
		return entityToDTO(findByCode(code));
	}

	@Override
	public void error(TaEntrepriseDTO dto) {
		throw new EJBException("Test erreur EJB");
	}

	@Override
	public int selectCount() {
		return dao.selectAll().size();
		//return 0;
	}
	
	public void mergeDTO(TaEntrepriseDTO dto) throws EJBException {
		mergeDTO(dto, null);
	}
	

	@Override
	@WebMethod(operationName = "mergeDTOValidationContext")
	public void mergeDTO(TaEntrepriseDTO dto, String validationContext) throws EJBException {
		try {
			TaEntrepriseMapper mapper = new TaEntrepriseMapper();
			TaEntreprise entity = null;
			if(dto.getId()!=null) {
				entity = dao.findById(dto.getId());
				if(dto.getVersionObj()!=entity.getVersionObj()) {
					throw new OptimisticLockException(entity,
							"L'objet à été modifié depuis le dernier accés. Client ID : "+dto.getId()+" - Client Version objet : "+dto.getVersionObj()+" -Serveur Version Objet : "+entity.getVersionObj());
				} else {
					 entity = mapper.mapDtoToEntity(dto,entity);
				}
			}
			
			//dao.merge(entity);
			dao.detach(entity); //pour passer les controles
			enregistrerMerge(entity, validationContext);
		} catch (Exception e) {
			e.printStackTrace();
			//throw new CreateException(e.getMessage());
			throw new EJBException(e.getMessage());
		}
	}
	
	public void persistDTO(TaEntrepriseDTO dto) throws CreateException {
		persistDTO(dto, null);
	}

	@Override
	@WebMethod(operationName = "persistDTOValidationContext")
	public void persistDTO(TaEntrepriseDTO dto, String validationContext) throws CreateException {
		try {
			TaEntrepriseMapper mapper = new TaEntrepriseMapper();
			TaEntreprise entity = mapper.mapDtoToEntity(dto,null);
			//dao.persist(entity);
			enregistrerPersist(entity, validationContext);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CreateException(e.getMessage());
		}
	}

	@Override
	public void removeDTO(TaEntrepriseDTO dto) throws RemoveException {
		try {
			TaEntrepriseMapper mapper = new TaEntrepriseMapper();
			TaEntreprise entity = mapper.mapDtoToEntity(dto,null);
			//dao.remove(entity);
			supprimer(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
	}

	@Override
	protected TaEntreprise refresh(TaEntreprise persistentInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(operationName = "validateEntityValidationContext")
	public void validateEntity(TaEntreprise value, String validationContext) /*throws ExceptLgr*/ {
		try {
			if(validationContext==null) validationContext="";
			validateAll(value,validationContext,false); //ancienne validation, extraction de l'annotation et appel
			//dao.validate(value); //validation automatique via la JSR bean validation
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateEntityPropertyValidationContext")
	public void validateEntityProperty(TaEntreprise value, String propertyName, String validationContext) {
		try {
			if(validationContext==null) validationContext="";
			validate(value, propertyName, validationContext);
			//dao.validateField(value,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateDTOValidationContext")
	public void validateDTO(TaEntrepriseDTO dto, String validationContext) {
		try {
			TaEntrepriseMapper mapper = new TaEntrepriseMapper();
			TaEntreprise entity = mapper.mapDtoToEntity(dto,null);
			validateEntity(entity,validationContext);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TaEntrepriseDTO> validator = new BeanValidator<TaEntrepriseDTO>(TaEntrepriseDTO.class);
//			validator.validate(dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}
	}

	@Override
	@WebMethod(operationName = "validateDTOPropertyValidationContext")
	public void validateDTOProperty(TaEntrepriseDTO dto, String propertyName, String validationContext) {
		try {
			TaEntrepriseMapper mapper = new TaEntrepriseMapper();
			TaEntreprise entity = mapper.mapDtoToEntity(dto,null);
			validateEntityProperty(entity,propertyName,validationContext);
			
			//validation automatique via la JSR bean validation
//			BeanValidator<TaEntrepriseDTO> validator = new BeanValidator<TaEntrepriseDTO>(TaEntrepriseDTO.class);
//			validator.validateField(dto,propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EJBException(e.getMessage());
		}

	}
	
	@Override
	@WebMethod(operationName = "validateDTO")
	public void validateDTO(TaEntrepriseDTO dto) {
		validateDTO(dto,null);
		
	}

	@Override
	@WebMethod(operationName = "validateDTOProperty")
	public void validateDTOProperty(TaEntrepriseDTO dto, String propertyName) {
		validateDTOProperty(dto,propertyName,null);
		
	}

	@Override
	@WebMethod(operationName = "validateEntity")
	public void validateEntity(TaEntreprise value) {
		validateEntity(value,null);
	}

	@Override
	@WebMethod(operationName = "validateEntityProperty")
	public void validateEntityProperty(TaEntreprise value, String propertyName) {
		validateEntityProperty(value,propertyName,null);
		
	}

}
