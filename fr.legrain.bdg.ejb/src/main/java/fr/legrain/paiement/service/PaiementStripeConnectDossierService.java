package fr.legrain.paiement.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.json.JSONObject;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.PermissionException;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceCollection;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceLineItem;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Plan;
import com.stripe.model.PlanCollection;
import com.stripe.model.Product;
import com.stripe.model.Refund;
import com.stripe.model.SetupIntent;
import com.stripe.model.Source;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionCollection;
import com.stripe.model.SubscriptionItem;
import com.stripe.model.oauth.TokenResponse;
import com.stripe.net.OAuth;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import com.stripe.param.AccountLinkCreateParams.Type;
import com.stripe.param.SubscriptionCancelParams;

import fr.legrain.abonnement.stripe.dto.TaStripeChargeDTO;
import fr.legrain.abonnement.stripe.dto.TaStripeCouponDTO;
import fr.legrain.abonnement.stripe.dto.TaStripeCustomerDTO;
import fr.legrain.abonnement.stripe.dto.TaStripeInvoiceDTO;
import fr.legrain.abonnement.stripe.dto.TaStripePaymentIntentDTO;
import fr.legrain.abonnement.stripe.dto.TaStripePlanDTO;
import fr.legrain.abonnement.stripe.dto.TaStripeProductDTO;
import fr.legrain.abonnement.stripe.dto.TaStripeSourceDTO;
import fr.legrain.abonnement.stripe.model.TaStripeAccount;
import fr.legrain.abonnement.stripe.model.TaStripeCharge;
import fr.legrain.abonnement.stripe.model.TaStripeCustomer;
import fr.legrain.abonnement.stripe.model.TaStripeInvoice;
import fr.legrain.abonnement.stripe.model.TaStripeInvoiceItem;
import fr.legrain.abonnement.stripe.model.TaStripePaymentIntent;
import fr.legrain.abonnement.stripe.model.TaStripePlan;
import fr.legrain.abonnement.stripe.model.TaStripeSource;
import fr.legrain.abonnement.stripe.service.TaStripeBankAccountService;
import fr.legrain.abonnement.stripe.service.TaStripePaymentIntentService;
import fr.legrain.autorisations.ws.FindByCode;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeAccountServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeBankAccountServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeChargeServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeCouponServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeCustomerServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeInvoiceItemServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeInvoiceServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripePaymentIntentServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripePlanServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeProductServiceRemote;
import fr.legrain.bdg.abonnement.stripe.service.remote.ITaStripeSourceServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaAbonnementServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaAcompteServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaAvisEcheanceServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaFactureServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaPanierServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaRReglementServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaReglementServiceRemote;
import fr.legrain.bdg.documents.service.remote.ITaTicketDeCaisseServiceRemote;
import fr.legrain.bdg.dossier.service.remote.ITaPreferencesServiceRemote;
import fr.legrain.bdg.generation.service.remote.IAbstractGenereDocServiceRemote;
import fr.legrain.bdg.lib.server.osgi.BdgProperties;
import fr.legrain.bdg.paiement.service.remote.IPaiementEnLigneDossierService;
import fr.legrain.bdg.servicewebexterne.service.remote.ITaCompteServiceWebExterneServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaCarteBancaireServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaEtatServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaParamEspaceClientServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTPaiementServiceRemote;
import fr.legrain.bdg.tiers.service.remote.ITaTiersServiceRemote;
import fr.legrain.document.dto.IDocumentPayableCB;
import fr.legrain.document.dto.IDocumentTiers;
import fr.legrain.document.model.RetourGenerationDoc;
import fr.legrain.document.model.SWTDocument;
import fr.legrain.document.model.TaAbonnement;
import fr.legrain.document.model.TaAcompte;
import fr.legrain.document.model.TaAvisEcheance;
import fr.legrain.document.model.TaBoncde;
import fr.legrain.document.model.TaEtat;
import fr.legrain.document.model.TaFacture;
import fr.legrain.document.model.TaLFacture;
import fr.legrain.document.model.TaMiseADisposition;
import fr.legrain.document.model.TaPanier;
import fr.legrain.document.model.TaRReglement;
import fr.legrain.document.model.TaReglement;
import fr.legrain.document.model.TaTicketDeCaisse;
import fr.legrain.documents.dao.IFactureDAO;
import fr.legrain.dossier.model.TaPreferences;
import fr.legrain.droits.service.ParamBdg;
import fr.legrain.droits.service.SessionInfo;
import fr.legrain.droits.service.TenantInfo;
import fr.legrain.general.service.remote.ITaParametreServiceRemote;
import fr.legrain.generation.service.CreationDocumentMultiple;
import fr.legrain.generationDocument.model.ParamAfficheChoixGenerationDocument;
import fr.legrain.hibernate.multitenant.ServerTenantInterceptor;
import fr.legrain.lib.data.IHMEtat;
import fr.legrain.lib.data.LibConversion;
import fr.legrain.lib.data.LibDate;
import fr.legrain.paiement.dao.ILogPaiementInternetDAO;
import fr.legrain.paiement.model.PaiementCarteBancaire;
import fr.legrain.paiement.model.RetourPaiementCarteBancaire;
import fr.legrain.paiement.model.TaLogPaiementInternet;
import fr.legrain.servicewebexterne.model.TaCompteServiceWebExterne;
import fr.legrain.servicewebexterne.model.TaTServiceWebExterne;
import fr.legrain.servicewebexterne.model.UtilServiceWebExterne;
import fr.legrain.tiers.dto.TaCarteBancaireDTO;
import fr.legrain.tiers.dto.TaCompteBanqueDTO;
import fr.legrain.tiers.dto.TaParamEspaceClientDTO;
import fr.legrain.tiers.model.TaCarteBancaire;
import fr.legrain.tiers.model.TaParamEspaceClient;
import fr.legrain.tiers.model.TaTiers;

@Stateless
@PaiementLgr(PaiementLgr.TYPE_STRIPE_CONNECT)
public class PaiementStripeConnectDossierService extends PaiementStripeDossierService implements IPaiementEnLigneDossierService {

	private @EJB ITaParametreServiceRemote taParametreService;
	private @Inject ParamBdg paramBdg;

	public RequestOptions initCleStripeProgramme(TaCompteServiceWebExterne configService) {
		boolean modeTest = false;
		modeTest = lgrEnvironnementServeur.isModeTestPaiementDossier();
		
		taParametreService.initParamBdg();

		RequestOptionsBuilder requestOptionsBuilder = RequestOptions.builder();
		if(modeTest) {
			//requestOptionsBuilder.setApiKey(LgrStripe.TEST_SECRET_API_KEY_PROGRAMME);
			requestOptionsBuilder.setApiKey(paramBdg.getTaParametre().getStripeTestSecretApiKeyProgramme());
		} else {
			//requestOptionsBuilder.setApiKey(LgrStripe.LIVE_SECRET_API_KEY_PROGRAMME);
			requestOptionsBuilder.setApiKey(paramBdg.getTaParametre().getStripeLiveSecretApiKeyProgramme());
		}
		
		return requestOptionsBuilder.build();
	}
	
	public RequestOptions initCleStripeDossier(TaCompteServiceWebExterne configService) {
		return initCleStripeDossier(configService,null);
	}
	
	public RequestOptions initCleStripeDossier(TaCompteServiceWebExterne configService, String connectedAccountId) {
		return super.initCleStripeDossier(configService,connectedAccountId);
//		UtilServiceWebExterne util = new UtilServiceWebExterne();
//		TaCompteServiceWebExterne config = taCompteServiceWebExterneService.findCompteDefautPourAction(TaTServiceWebExterne.TYPE_PAIEMENT);
//		if(config!=null) {
//			config = util.decrypter(config);
//			/*
//			 * TODO
//			 * A l'int??rieur de BDG
//			 * 		Dans les methodes de paiement du AbstractApplcationDAOServeur => Si pas de compte serviceWebExterne de paiement, v??rifier si lien Stripe Connect
//			 * 
//			 * booleen modeConnect ici et dans PaiementCbController => voir comment on peu le controler
//			 * 
//			 * Pour les autres appels qui passe directement par les service stripe sans test d'injection
//			 * refaire une verif ici sur l'existance d'un compte serviceWebExterne de paiement et si pas de compte v??rifier si lien Stripe Connect
//			 * 
//			 * voir la priorit?? entre serviceWebExterne et connect pour stripe
//			 * 
//			 * si on utilise pas le oauth pour connect, voir si on met la cl?? du compte principal (legrain) dans le dossier legrain82 ou une table programme ou en dur
//			 * 
//			 * Finaliser la cr??ation/liaison de compte Stripe
//			 * API : cr??ation de compte en double mais plus "souplesse" d??connection par BDG impossible
//			 * OAuth : pas de compte en double, mais aucun param??trage de creation de compte, d??connexion depuis BDG possible
//			 * 
//			 * OU =====================
//			 * 
//			 * TODO
//			 * Arriver a faire fonctionner ensemble les compte service web externe et le syst??me de connexion OAuth
//			 * Ce qui ??tait pr??vu ?? la base via le champs type d'autentification
//			 * pour du oauth il faudrait faire apparaitre un bouton ?? la place des champs
//			 * 
//			 */
//		}
//		
//		
//		System.out.println("PaiementStripeDossierService cl?? 1 "+ config.getApiKey1());	
//		System.out.println("PaiementStripeDossierService cl?? 2 "+ config.getApiKey2());
//		System.out.println("PaiementStripeDossierService cl?? 3 "+ config.getValeur1());
//		System.out.println("PaiementStripeDossierService cl?? 4 "+ config.getValeur2());
//		
//		boolean modeTest = false;
//		boolean modeStripeConnect = false;
//		modeTest = lgrEnvironnementServeur.isModeTestPaiementDossier();
//
//		/*
//		 * TODO
//		 * POUR L'INSTANT la cl?? utiliser est celle utiliser dans le dossier COURANT,
//		 * Mais si c'est pour du CONNECT il faudrait aller chercher les cl?? PUBLIQUE et PRIVEE dans une base programme/plateforme 
//		 * => Pour l'instant test avec des contantes dans LgrStripe mais pas assez s??curis??
//		 * A FAIRE SUREMENT ici et au moins dans PaiementCbController pour la cl?? publique et dans EspaceClientRestMultiTenantProxy.clePubliqueStripe()
//		 */
////		String API_KEY_DOSSIER_SK_LIVE = config.getApiKey1();
//			
//		RequestOptionsBuilder requestOptionsBuilder = RequestOptions.builder();
//		/*************************************************MODIF CONNECT **/
//		if(connectedAccountId!=null && !connectedAccountId.equals("")) {
//			modeStripeConnect = true;
//		}
//		if(!modeStripeConnect) { 
//			//TODO
//			//il n'y a pas de compte stripe connect en param??tre, donc a priori on ne vient pas d'un webhook
//			//par contre POUR L'INSTANT on FORCE stripe connect si un compte connect est param??tr?? dans le dossier
//			/*
//			 * A verifier dans PaiementCbController aussi
//			 * 
//			 * TODO pour les paiement connect exterieur (appli mobile et angular) il faudra pr??voir de r??cuprer ces cl??s "plateforme" ?? la place de celle du dossier
//			 */
//			TaStripeAccount taStripeAccount = taStripeAccountService.findInstance();
//			if(taStripeAccount.getIdExterne()!=null && !taStripeAccount.getIdExterne().equals("")) {
//				connectedAccountId = taStripeAccount.getIdExterne();
//				modeStripeConnect = true;
//			}
//		}
//		if(modeStripeConnect) {
//			boolean problemeCompteConnect = false;
//			TaStripeAccount taStripeAccount = taStripeAccountService.findInstance();
//			if(connectedAccountId!=null && !connectedAccountId.equals("")) {
//				//connectedAccountId vient probablement d'un appel WebHook, il faut v??rifier la concordance avec le compte connect du dossier
//				if(taStripeAccount.getIdExterne().equals(connectedAccountId)) {
//					//OK
//				}
//			}
//			/*
//			 * TODO Verifier la liaison chez stripe
//			 * TODO mettre a jour
//			 * TODO v??rifier les que le compte stripe est valide (document)
//			 * TODO SINON .... EXCEPTION et bloquer le paiement
//			 * 
//			 * TODO Si connect peut ??tre essayer de r??cup??rer ici les frais par rapport au tiers et au service consern?? s'il y en a 
//			 */
//			if(liaisonBDGCompteOKChezStripe(connectedAccountId)) {
//				if(!taStripeAccount.getChargesEnabled() || !taStripeAccount.getDetailsSubmitted()) {
//					//compte non verifi?? par stripe
//					problemeCompteConnect = true;
//				}
//			} else {
//				//compte non li??
//				problemeCompteConnect = true;
//			}
//			if(problemeCompteConnect) {
//				//exception
//			}
//			//**
//			//connect, donc utilisation des cl??s programmes
//			if(modeTest) {
//				requestOptionsBuilder.setApiKey(LgrStripe.TEST_SECRET_API_KEY_PROGRAMME);
//			} else {
//				requestOptionsBuilder.setApiKey(LgrStripe.LIVE_SECRET_API_KEY_PROGRAMME);
//			}
//			requestOptionsBuilder.setStripeAccount(taStripeAccount.getIdExterne());
//		} else {
//			//utilisation des cl?? API du dossier s'il y en a, sinon erreur
//			if(config!=null && config.getApiKey1()!=null) {
//				String API_KEY_DOSSIER_SK_LIVE = config.getApiKey1();
//				requestOptionsBuilder.setApiKey(API_KEY_DOSSIER_SK_LIVE);
//			} else {
//				//TODO exception, pas de compte connect et aucun cle dans le dossier
//			}
//			
//		}
//		/*************************************************MODIF CONNECT **/
//		
//		RequestOptions requestOptions = requestOptionsBuilder.build();
//		
//		return requestOptions;
	}
	
	public String createAccount() {		
		RequestOptions requestOptions = initCleStripeProgramme(null);
		
		AccountCreateParams params = AccountCreateParams.builder()
		    .setType(AccountCreateParams.Type.STANDARD)
		    .build();

		try {
			Account account = Account.create(params,requestOptions);
			return account.getId();
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String createAccountLink(String idAccount, String refreshUrl, String returnUrl, String param) {

		RequestOptions requestOptions = initCleStripeProgramme(null);
		
		AccountLinkCreateParams params =
		  AccountLinkCreateParams.builder()
		    .setAccount(idAccount) //acct_1032D82eZvKYlo2C
		    .setRefreshUrl(refreshUrl+"?state="+param)
		    .setReturnUrl(returnUrl+"?state="+param)
		    .setType(Type.ACCOUNT_ONBOARDING)//"account_onboarding"
		    .build();

		try {
			AccountLink accountLink = AccountLink.create(params,requestOptions);
			return accountLink.getUrl();
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean liaisonBDGCompteOKChezStripe(String idAccount) {
		try {
			RequestOptions requestOptions = initCleStripeProgramme(null);
			
			boolean liaisonBDGCompteOKChezStripe = false;
			Account account = Account.retrieve(idAccount,requestOptions);
			liaisonBDGCompteOKChezStripe = true; 
			if(liaisonBDGCompteOKChezStripe) {
				//updateAccountFromStripe(null,idAccount);//mise a jour du compte deja fait dans les initCleDossier (a chaque appel stripe)
			}
			return liaisonBDGCompteOKChezStripe;
		} catch (PermissionException e) {
			e.printStackTrace();
			return false;
		} catch (StripeException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String autorizationTokenOAuthAccount(String authorisationCode) {
		try {
			RequestOptions requestOptions = initCleStripeProgramme(null);

			Map<String, Object> params = new HashMap<>();
			params.put("grant_type", "authorization_code");
			params.put("code", authorisationCode);

			TokenResponse rep = OAuth.token(params, requestOptions);

			// Access the connected account ID in the response
			String accountId = rep.getStripeUserId();
			
//			 JSONObject o = new JSONObject(rep.getLastResponse().body());
			 return rep.getLastResponse().body();
		} catch (PermissionException e) {
			e.printStackTrace();
			return null;
		} catch (StripeException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deauthorizeStripeOAuthAccount(String clientIdSecretKey, String idAccount) {
		try {
			RequestOptions requestOptions = initCleStripeProgramme(null);
			
			Map<String, Object> params = new HashMap<>();
			params.put("client_id", clientIdSecretKey);
			params.put("stripe_user_id", idAccount);
	
			OAuth.deauthorize(params, requestOptions);
			return true;
		} catch (PermissionException e) {
			e.printStackTrace();
			return false;
		} catch (StripeException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
