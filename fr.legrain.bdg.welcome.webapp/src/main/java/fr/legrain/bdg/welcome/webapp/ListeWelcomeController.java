package fr.legrain.bdg.welcome.webapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped 
public class ListeWelcomeController implements Serializable{
	
	private static String F_ACTIVE = "<i class=\"fa fa-check\" style=\"color:green;\"></i>"; 
	private static String F_ENCOURS = "<i class=\"fa fa-hourglass-half\"></i>"; 
	private static String F_BIENTOT = "<i class=\"fa fa-lightbulb-o\"></i>"; 
	
    
	private List<DescFonctionnalites> listetiers;
    private List<DescFonctionnalites> listearticle;
    private List<DescFonctionnalites> listefacture;
    private List<DescFonctionnalites> listedevis;
    private List<DescFonctionnalites> listeavoir;
    private List<DescFonctionnalites> listebonlivraison;
    private List<DescFonctionnalites> listeboncommande;
    private List<DescFonctionnalites> listeproforma;
    private List<DescFonctionnalites> listeacompte;
    private List<DescFonctionnalites> listeapporteur;
    private List<DescFonctionnalites> listeavisprelevement;
    private List<DescFonctionnalites> listesreception;
    private List<DescFonctionnalites> listeslotstracabilite;
    private List<DescFonctionnalites> listestocksinventaire;
    private List<DescFonctionnalites> listefabrication;
    private List<DescFonctionnalites> listeparamtiers;
    private List<DescFonctionnalites> listeparamarticles;
    private List<DescFonctionnalites> listeparamdocuments;
    private List<DescFonctionnalites> listeparamsolstyce;
    






    public List<DescFonctionnalites> getListeDevis() {
		return listedevis;
	}

	public void setListeDevis(List<DescFonctionnalites> listedevis) {
		this.listedevis = listedevis;
	}

	public List<DescFonctionnalites> getListeFabrication() {
		return listefabrication;
	}

	public void setListeFabrication(List<DescFonctionnalites> listefabrication) {
		this.listefabrication = listefabrication;
	}

	public List<DescFonctionnalites> getListeArticle() {
		return listearticle;
	}

	public void setListeArticle(List<DescFonctionnalites> listearticle) {
		this.listearticle = listearticle;
	}

	@PostConstruct
    public void init() {
    	createTiers();
    	createArticle();
    	createFacture();
    	createDevis();
    	createAvoir();
    	createBonCommande();
    	createBonLivraison();
    	createProForma();
    	createAcompte();
    	createApporteur();
    	createAvisPrelevement();
    	createReception();
    	createLotsTracabilite();
    	createStocksInventaire();
    	createParamTiers();
    	createParamArticles();
    	createParamDocuments();
    	createParamSolstyce();
    	createFabrication();
    }    

    public List<DescFonctionnalites> getListeTiers() {
		return listetiers;
	}




	public void setListeTiers(List<DescFonctionnalites> listetiers) {
		this.listetiers = listetiers;
	}    


	public List<DescFonctionnalites> getListeFacture() {
		return listefacture;
	}


	public void createTiers() {
	    listetiers = new ArrayList<DescFonctionnalites>();

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Fiches contacts :</b> cr??ation, modification, suppression ou d??sactivation. Le nombre de fiche est illimit??.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Liste contacts :</b> pour acc??der ?? vos fiches contacts, vous disposez d???une liste d???acc??s rapide.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des codes contacts :</b> vous pouvez soit laisser le programme g??n??rer les codes contacts et personnaliser cette codification, soit saisir un code quelconque ?? condition qu???il soit unique.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Classement par famille :</b> associez vos contacts ?? des familles de contacts pour cr??er des groupes facilement identifiables. Les types de familles sont enti??rement param??trables.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Type de contact :</b> en associant un type (client, fournisseur, organisme, etc ..) ?? vos contacts, vous d??finissez ?? quel groupe appartient ce contact.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Saisie de coordonn??es :</b> pour chaque contact, vous disposez d???une fiche compl??te pour la saisie de l???ensemble de ses coordonn??es.",
	    		F_ACTIVE,
	    		""));

	    listetiers.add(new DescFonctionnalites(
	    		"<b>Codes comptable :</b> liaison entre votre contact et le compte comptable et/ou le compte g??n??ral. Cette information est utilis??e lors de l???exportation en comptabilit??.",
	    		F_ACTIVE,
	    		""));
	    
	    listetiers.add(new DescFonctionnalites(
	    		"<b>Param??tres :</b> diff??rents param??tres peuvent ??tre saisie dans la fiche contact, comme le mode de saisie (TTC), la localisation TVA, le type et les conditions de paiement ou encore, si c???est un client, l???utilisation du compte client.",
	    		F_ACTIVE,
	    		""));
	    
	    listetiers.add(new DescFonctionnalites(
	    		"<b>D??sactiver un contact :</b> si vous n???avez plus de relation avec un contact, vous pouvez d??sactiver sa fiche.",
	    		F_ACTIVE,
	    		""));
	    
	    listetiers.add(new DescFonctionnalites(
	    		"<b>Espace client en ligne :</b> avec le Bureau de Gestion, vos clients disposent d???un compte client personnel ?? partir duquel ils peuvent r??cup??rer leurs factures sous format pdf et voir l???historique des factures.",
	    		F_ACTIVE,
	    		""));
	    listetiers.add(new DescFonctionnalites(
	    		"<b>Envoi des factures par Email :</b> D'un clic, envoyez vos factures par email.",
	    		F_ACTIVE,
	    		""));
	    listetiers.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ACTIVE,
	    		""));
	    

	}
	
	public void createArticle() {
	    listearticle = new ArrayList<DescFonctionnalites>();

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Fiches Articles :</b> cr??ation, modification, suppression ou d??sactivation. Le nombre de fiche est illimit??.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Liste Articles :</b> pour acc??der ?? vos fiches articles, vous disposez d???une liste d???acc??s rapide.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des codes Articles :</b> vous pouvez soit laisser le programme g??n??rer les codes articles et personnaliser cette codification, soit saisir un code quelconque ?? condition qu???il soit unique.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Classement par famille :</b> associez vos articles ?? des familles d???articles pour cr??er des groupes facilement identifiables. Les types de familles d???articles sont enti??rement param??trable.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Gestion des lots:</b> Vous pouvez activer ou non la gestion des lots pour chaque article. La gestion des lots va de pair avec le module Lots / Stocks.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Code Barre :</b> Renseignez ce champ pour attribuer un code barre ?? chacun de vos articles.",
	    		F_ENCOURS,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Tra??abilit?? :</b> Vous pouvez renseigner les sections Fabrication et Date limite de consommation afin que le Bureau de Gestion calcule la DDM/DLC lors de vos fabrications.",
	    		F_ACTIVE,
	    		""));
	    
	    listearticle.add(new DescFonctionnalites(
	    		"<b>Tarifs de r??f??rence :</b> indiquez le prix par d??faut et le code TVA pour l???unit?? par d??faut. ces informations seront propos??es lors de la saisie des factures ou autres documents.",
	    		F_ACTIVE,
	    		""));
	    
	    listearticle.add(new DescFonctionnalites(
	    		"<b>Multi-Tarifs :</b> Vous pouvez d??finir plusieurs tarifs pour un article en fonction du groupe de client et du groupe de tarifs.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Unit??s :</b> Vous d??finissez les unit??s correspondantes ?? votre articles. Un calcul automatique peut ??tre d??fini entre les deux unit??s, si c???est le cas. La formule de calcul est personnalisable.",
	    		F_ACTIVE,
	    		""));
	    
	    listearticle.add(new DescFonctionnalites(
	    		"<b>Alerte stock :</b> Indiquez une limite basse pour ??tre alert?? par le programme lorsque le seuil de stock de marchandise est atteint.",
	    		F_ENCOURS,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Marque :</b> Vous pouvez renseigner le nom d???une marque pour un article de votre catalogue. Cette information est reprise dans certaines ??ditions.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Fournisseurs de l'article :</b> Associez facilement les fournisseurs aupr??s de qui vous faites vos achats de marchandises. Cette liste peut ??tre compl??t??e soit manuellement depuis la fiche article, soit automatiquement en collectant les informations issues des bons de r??ception.",
	    		F_ACTIVE,
	    		""));
	    
	    listearticle.add(new DescFonctionnalites(
	    		"<b>Codes comptable :</b> liaison entre votre article et le compte comptable et/ou compte g??n??ral. Cette information est utilis??e lors de l???exportation en comptabilit??.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>D??sactiver un article :</b> si vous n???utilisez plus un article, vous pouvez d??sactiver sa fiche.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Contr??les qualit??s :</b> d??finissez ici les diff??rents contr??les qui sont ?? v??rifier pour l???article courant lors d???une r??ception ou d???une fabrication. Vous pouvez utiliser des contr??les pr??d??finis dans les mod??les de contr??les afin de gagner du temps.",
	    		F_ACTIVE,
	    		""));

	    listearticle.add(new DescFonctionnalites(
	    		"<b>Recettes :</b> Indiquez les ingr??dients qui sont utilis??s lors de la fabrication de l???article courant.",
	    		F_ACTIVE,
	    		""));
	    
	    listearticle.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ACTIVE,
	    		""));
	}

	public void setListeFacture(List<DescFonctionnalites> listefacture) {
		this.listefacture = listefacture;
	}

	public void createFacture() {
    listefacture = new ArrayList<DescFonctionnalites>();

    listefacture.add(new DescFonctionnalites(
    		"<b>G??n??ration automatique num??ro facture param??trable : </b>les num??ro de factures sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Pr??-remplissage des champs de l???ent??te de la facture :</b> d??s que avez choisi le client a facturer, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de la facture, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Saisie en mode TTC ou HT en fonction des param??tres du client</b> et modifiable lors de la saisie. Tr??s pratique si l???on doit facturer des professionnels et des particuliers.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Pr??-remplissage automatique des lignes de facture</b> suite au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Gestion des lots :</b> si le module gestion des lots est actif, vous pouvez choisir le lot d???origine pour le produit vendu.",
    		F_ACTIVE,
    		""));
    
    listefacture.add(new DescFonctionnalites(
    		"<b>Gestion des stocks :</b> les quantit??s factur??es sont enlev??es des stocks, sauf si un bon de livraison, de commande ou devis est li?? ?? la facture.",
    		F_ACTIVE,
    		""));
    
    listefacture.add(new DescFonctionnalites(
    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
    		F_ACTIVE,
    		""));
    
    listefacture.add(new DescFonctionnalites(
    		"<b>Synth??se de la facture en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>R??glement de la facture en un clic</b> avec pr??-remplissage du moyen de paiement et formule de politesse",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Mise ?? disposition de la facture dans le compte client en ligne :</b> les factures sont pour l???instant les seuls documents que vos clients peuvent t??l??charger depuis l???espace client Bureau de Gestion.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>G??n??ration de la facture sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Les factures peuvent servir de mod??les</b> pour n???importe quel autre document",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Les factures peuvent ??tre li??es depuis</b> les Bon de livraison, Devis, Bon de commande, Proforma, etc ???",
    		F_ACTIVE,
    		""));

    listefacture.add(new DescFonctionnalites(
    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
    		F_ACTIVE,
    		""));
     
}

	public void createDevis() {
	    listedevis = new ArrayList<DescFonctionnalites>();


	    listedevis.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique num??ro devis param??trable :</b> les num??ro de devis sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te du devis :</b> d??s que avez choisi le client a deviser, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de la devis, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT</b> en fonction des param??tres du client et modifiable lors de la saisie. Tr??s pratique si l???on doit faire des devis des professionnels et des particuliers.",
	    		F_ACTIVE,
	    		""));
	    
	    listedevis.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));
	    
	    listedevis.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes de devis suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));
	    
	    listedevis.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Synth??se des devis en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition des devis dans le compte client :</b> tout comme les factures, les devis pourront ??tre mis ?? disposition depuis l???espace client Bureau de Gestion pour que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>G??n??ration des devis sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Les devis peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Les devis peuvent g??n??rer</b> des Factures, Bons de livraison, Bons de commande, Proforma, etc ???",
	    		F_ACTIVE,
	    		""));

	    listedevis.add(new DescFonctionnalites(
	    		"<b>Tableaux de bord devis : </b>d'un seul coup d'oeil vous connaissez l'??tat de transformation de vos devis et de ceux ?? relancer.",
	    		F_ACTIVE,
	    		""));
	    
	    listedevis.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ACTIVE,
	    		""));
	     
	}

	public void createAvoir() {
	    listeavoir = new ArrayList<DescFonctionnalites>();


	    listeavoir.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des num??ros d???avoir param??trable :</b> les num??ro des avoirs sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te de l???avoir :</b> d??s que avez choisi le client pour lequel vous devez faire un avoir, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de l???avoir, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT</b> en fonction des param??tres du client et modifiable lors de la saisie. Tr??s pratique si l???on doit faire des avoirs pour des professionnels et des particuliers.",
	    		F_ACTIVE,
	    		""));
	    
	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));
	    
	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes de l???avoir suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));
	    
	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Synth??se de l???avoir en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition des avoirs dans le compte client :</b> tout comme les factures, les avoirs pourront ??tre mis ?? disposition depuis l???espace client Bureau de Gestion pour que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>G??n??ration des avoirs sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Les devis peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Les avoirs peuvent ??tre g??n??r??</b> depuis les Factures, les Bons de livraison, les Bons de commande, Proforma, etc ???",
	    		F_ACTIVE,
	    		""));

	    listeavoir.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}
	
	public void createBonCommande() {
	    listeboncommande = new ArrayList<DescFonctionnalites>();


	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique num??ro bon de commande param??trable :</b> les num??ro de bon de commande sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te du bon de commande :</b> d??s que avez choisi le client, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? du bon de commande, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT</b> en fonction des param??tres du client et modifiable lors de la saisie. Tr??s pratique si l???on doit faire des bons de commande pour des professionnels ou des particuliers.",
	    		F_ACTIVE,
	    		""));
	    
	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));
	    
	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes du bon de commande suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));
	    
	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Synth??se du bon de commande en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition des bons de commande dans le compte client :</b> tout comme les factures, les bons de commande pourront ??tre mis ?? disposition depuis l???espace client Bureau de Gestion pour que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>G??n??ration des bons de commande sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Les bons de commande peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Les bons de commande peuvent g??n??rer</b> les Factures, Bon de livraison, Proforma, etc ???",
	    		F_ACTIVE,
	    		""));

	    listeboncommande.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}

	public void createBonLivraison() {
	    listebonlivraison = new ArrayList<DescFonctionnalites>();


	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique num??ro bon de livraison param??trable :</b> les num??ro de bon de livraison sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te du bon de livraison :</b> d??s que avez choisi le client, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? du bon de livraison, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT</b> en fonction des param??tres du client et modifiable lors de la saisie. Tr??s pratique si l???on doit faire des bons de livraison pour des professionnels ou des particuliers.",
	    		F_ACTIVE,
	    		""));
	    
	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));
	    
	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes du bon de livraison suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));
	    
	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Synth??se du bon de livraison en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition des bons de livraison dans le compte client :</b> tout comme les factures, les bons de livraison pourront ??tre mis ?? disposition depuis l???espace client Bureau de Gestion pour que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>G??n??ration des bons de livraison sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Les bons de livraison peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Les bons de livraison peuvent g??n??rer</b> les Factures, Proforma, etc ???",
	    		F_ACTIVE,
	    		""));

	    listebonlivraison.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}

	public void createProForma() {
	    listeproforma = new ArrayList<DescFonctionnalites>();

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique num??ro facture pro forma param??trable :</b> les num??ro de factures pro forma sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te de la facture pro forma :</b> d??s que avez choisi le client ?? facturer, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de la facture, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT</b> en fonction des param??tres du client et modifiable lors de la saisie. Tr??s pratique si l???on doit facturer des professionnels et des particuliers.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes de facture pro forma suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));
	    
	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Synth??se de la facture pro forma en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>R??glement de la facture pro forma en un clic</b> avec pr??-remplissage du moyen de paiement et formule de politesse",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition de la facture pro forma dans le compte client :</b> les factures pro forma pourront ??tre mis ?? disposition dans l???espace client Bureau de Gestion afin que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>G??n??ration de la facture pro forma sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Les factures pro forma peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Les factures pro forma peuvent ??tre li??es</b> depuis les Bon de livraison, Devis, Bon de commande,etc ??? et g??n??rer des factures.",
	    		F_ACTIVE,
	    		""));

	    listeproforma.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}


	public void createAcompte() {
    listeacompte = new ArrayList<DescFonctionnalites>();

    listeacompte.add(new DescFonctionnalites(
    		"<b>G??n??ration automatique des num??ros des factures d???acomptes param??trable :</b> les num??ro de factures d???acomptes sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Pr??-remplissage des champs de l???ent??te de la facture d???acompte :</b> d??s que avez choisi le client ?? facturer, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de la facture, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Saisie en mode TTC ou HT en fonction des param??tres du client</b> et modifiable lors de la saisie. Tr??s pratique si l???on doit facturer des professionnels et des particuliers.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Pr??-remplissage automatique des lignes des factures d???acomptes suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
    		F_ACTIVE,
    		""));
    
    listeacompte.add(new DescFonctionnalites(
    		"<b>Synth??se de la facture d???acompte en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>R??glement de la facture d???acompte en un clic</b> avec pr??-remplissage du moyen de paiement et formule de politesse",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Mise ?? disposition de la facture d???acompte dans le compte client :</b> les factures pro forma pourront ??tre mis ?? disposition dans l???espace client Bureau de Gestion afin que vos clients puissent les t??l??charger.",
    		F_ENCOURS,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>G??n??ration de la facture d???acompte sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Les factures d???acompte peuvent servir de mod??les</b> pour n???importe quel autre document",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Les factures d???acomptes peuvent ??tre g??n??r??es</b> depuis les Bons de livraison, Devis, Bons de commande,etc ??? et g??n??rer des factures.",
    		F_ACTIVE,
    		""));

    listeacompte.add(new DescFonctionnalites(
    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
    		F_ACTIVE,
    		""));
     
}
	
	public void createApporteur() {
	    listeapporteur = new ArrayList<DescFonctionnalites>();

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des num??ros des factures apporteurs param??trables :</b> les num??ro de factures apporteurs sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te de la facture apporteur:</b> d??s que avez choisi le partenaire destinataire de la facture apporteur, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell?? de la facture, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT en fonction des param??tres du client</b> et modifiable lors de la saisie. Tr??s pratique si l???on doit facturer des professionnels et des particuliers.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes des factures apporteurs suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));
	    
	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Synth??se de la facture apporteur en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>R??glement de la facture apporteur en un clic</b> avec pr??-remplissage du moyen de paiement et formule de politesse",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition de la facture apporteurs dans le compte client :</b> les factures apporteurs pourront ??tre mis ?? disposition dans l???espace client Bureau de Gestion afin que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>G??n??ration de la facture apporteur sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Les factures apporteurs peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Les factures apporteurs peuvent ??tre g??n??r??es</b> depuis les Bons de livraison, Devis, Bons de commande,etc ....",
	    		F_ACTIVE,
	    		""));

	    listeapporteur.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}
	
	
	public void createAvisPrelevement() {
	    listeavisprelevement = new ArrayList<DescFonctionnalites>();

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des num??ros des avis de pr??l??vements param??trables :</b> les num??ro des avis de pr??l??vements  sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche client intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage des champs de l???ent??te des avis de pr??l??vements :</b> d??s que avez choisi le client qui doit ??tre pr??lev??, le Bureau de Gestion remplit automatique tous les champs de l???ent??te (libell??, coordonn??es du client, adresse de livraison). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Saisie en mode TTC ou HT en fonction des param??tres du client</b> et modifiable lors de la saisie. Tr??s pratique si l???on doit facturer des professionnels et des particuliers.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes des avis de pr??l??vements suite</b> au choix de l???article (libell??, prix ht, tva, ttc, unit?? 1 et unit?? 2, quantit?? ?? 1 par d??faut). Vous pouvez modifier ce qui ne convient pas.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la TVA :</b> pour chaque ligne d???article, la tva et le ttc sont calcul??s automatiquement, ainsi que les totaux de synth??se.",
	    		F_ACTIVE,
	    		""));
	    
	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Synth??se de l???avis de pr??l??vement en fin de saisie :</b> un ??cran r??capitulatif vous est propos?? en fin de saisie avant validation et envoi.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Mise ?? disposition des avis de pr??l??vements dans le compte client :</b> les avis de pr??l??vements pourront ??tre mis ?? disposition dans l???espace client Bureau de Gestion afin que vos clients puissent les t??l??charger.",
	    		F_ENCOURS,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>G??n??ration des avis de pr??l??vements sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Les avis de pr??l??vements  peuvent servir de mod??les</b> pour n???importe quel autre document",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Les avis de pr??l??vements peuvent ??tre g??n??r??es</b> depuis les Bons de livraison, Devis, Bons de commande,etc ....",
	    		F_ACTIVE,
	    		""));

	    listeavisprelevement.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	     
	}
	
	public void createReception() {
	    listesreception = new ArrayList<DescFonctionnalites>();

	    listesreception.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique des num??ros des bons de r??ceptions param??trables :</b> les num??ro des bons de r??ceptions sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche fournisseur intuitive :</b> le programme affiche une liste d??roulante en fonction de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage du libell?? de la r??ception :</b> d??s que avez choisi le fournisseur qui vous livre, le Bureau de Gestion remplit automatiquement le libell?? avec le num??ro de r??ception et le nom du fournisseur.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Notez la date et l???heure d???arriv??e de vos marchandises </b> en deux clics, vous pouvez ajuster la date et l???heure d???arriv??e.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Prestation de services : </b> la notion de prestation de service permet de bloquer la fabrication pour un client en particulier. De fait, vous ne pourrez facturer cette fabrication qu????? ce client.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Saisie et recherche article intuitive :</b> affichage des articles dans une liste d??roulante en fonction de votre saisie.",
	    		F_ACTIVE,
	    		""));

	    listesreception.add(new DescFonctionnalites(
	    		"<b>Pr??-remplissage automatique des lignes des bons de r??ceptions </b>suite au choix de l???article, le programme pr??-rempli le libell??, g??n??re un num??ro de lot, calcule la date de p??remption. Vous pouvez modifier ce qui ne convient pas et affecter un entrep??t, un emplacement, les quantit??s, et aussi saisir vos contr??les qualit??s.",
	    		F_ACTIVE,
	    		""));
	    
	    listesreception.add(new DescFonctionnalites(
	    		"<b>G??n??ration des bons de r??ceptions sous format pdf</b> pour ??tre envoy??e par Email ou par courrier.",
	    		F_ACTIVE,
	    		""));
	    
	    listesreception.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ACTIVE,
	    		""));
	    
	    listesreception.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	}

	public void createLotsTracabilite() {
	    listeslotstracabilite = new ArrayList<DescFonctionnalites>();

	    listeslotstracabilite.add(new DescFonctionnalites(
	    		" <b>Gestion des lots et tra??abilit??s :</b> consultez facilement la liste de vos lots de marchandises en cours ou termin??. Consultez ??galement l???utilisation des lots, dans quelle fabrication, ou de quels lots est constitu?? une fabrication. Pr??sent?? sous forme d???arbre g??n??alogique, vous saurez tout sur vos fabrications concernant les lots.",
	    		F_ACTIVE,
	    		""));
	    listeslotstracabilite.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	}
	

	public void createStocksInventaire() {
	    listestocksinventaire = new ArrayList<DescFonctionnalites>();

	    listestocksinventaire.add(new DescFonctionnalites(
	    		" <b>Gestion des lots et tra??abilit??s :</b> consultez facilement la liste de vos lots de marchandises en cours ou termin??. Consultez ??galement l???utilisation des lots, dans quelle fabrication, ou de quels lots est constitu?? une fabrication. Pr??sent?? sous forme d???arbre g??n??alogique, vous saurez tout sur vos fabrications concernant les lots.",
	    		F_ACTIVE,
	    		""));
	    listestocksinventaire.add(new DescFonctionnalites(
	    		" <b>Gestion des lots et tra??abilit??s :</b> consultez facilement la liste de vos lots de marchandises en cours ou termin??. Consultez ??galement l???utilisation des lots, dans quelle fabrication, ou de quels lots est constitu?? une fabrication. Pr??sent?? sous forme d???arbre g??n??alogique, vous saurez tout sur vos fabrications concernant les lots.",
	    		F_ACTIVE,
	    		""));
	    listestocksinventaire.add(new DescFonctionnalites(
	    		"<b>Aide contextuelle :</b> vous disposez d???une aide contextuelle partout o?? cela est n??cessaire. Cette aide vous donne des indications sur l???utilisation de l?????cran et la fa??on de remplir certaines zones de saisie.",
	    		F_ENCOURS,
	    		""));
	    }

	public void createFabrication() {
	    listefabrication = new ArrayList<DescFonctionnalites>();

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Fiches Fabrications :</b> cr??ation, modification, suppression ou d??sactivation. Le nombre de fiche est illimit??.",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>G??n??ration automatique num??ro fabrications param??trable :</b> les num??ros des fabrications sont g??n??r??s automatiquement et vous pouvez personnaliser cette num??rotation ?? l???aide d???une formule.",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Cr??ation de mod??le ?? partir d???une fabrication :</b> Depuis la liste des fabrications, vous pouvez g??n??rer des mod??les de fabrications qui pourront ??tre utilis??s lors de futures fabrications.",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Gestion des lots Produits Finis :</b> Pour chaque Produit Fini, un num??ro de lot est g??n??r?? automatiquement par le programme et les stocks mis ?? jour (entrep??t, emplacement, quantit??).",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Calcul automatique de la date de DLC/DDM : </b> en fonction des crit??res pr??sents dans l???article fabriqu??, le programme propose une date de DLC/DDM. Cette date peut ??tre modifi??e lors de la saisie.",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Mati??res Premi??res : </b> vous pouvez soit s??lectionner un ?? un les ingr??dients utilis??s dans votre fabrication, soit affecter en un clic l???ensemble des ingr??dients d??finis dans la recette du produit fini que vous allez fabriquer. Dans ce cas, les quantit??s de mati??res premi??res sont calcul??es automatiquement, mais vous devez choisir les lots ?? utiliser. Vous pouvez aussi panacher les deux m??thodes.",
	    		F_ACTIVE,
	    		""));

	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Prestation de services : </b> la notion de prestation de service permet de bloquer la fabrication pour un client en particulier. De fait, vous ne pourrez facturer cette fabrication qu????? ce client.",
	    		F_ACTIVE,
	    		""));
	    
	    listefabrication.add(new DescFonctionnalites(
	    		"<b>Contr??les qualit??s : </b> saisissez les valeurs pour les contr??les qualit??s lors de la fabrication. Des indicateurs vous donne l?????tat de vos contr??les.",
	    		F_ACTIVE,
	    		""));
	    
	    listefabrication.add(new DescFonctionnalites(
	    		"<b>R??dacteur et contr??leur : </b> choisissez ici quels sont les utilisateurs qui r??digent ou contr??lent la fabrication.",
	    		F_ACTIVE,
	    		""));
	    
	}

	public void createParamTiers() {
	    listeparamtiers = new ArrayList<DescFonctionnalites>();

	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type adresse :</b> D??finissez des types d???adresses comme de facturation, de livraison, de correspondance, de domicile, et tout autre type que vous jugerez n??cessaire.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type civilit?? :</b> Monsieur, Madame, Mademoiselle, Monsieur le Directeur. D??finissez tous les types de civilit??s, et cela de fa??on homog??ne pour vos correspondance.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type entit?? :</b> L?? aussi, vous pouvez homog??n??iser l?????criture des formes juridiques de vos partenaires. Et cela peut ??tre utile pour des ciblages en fonction des formes juridique.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type t??l??phone :</b> Associer un type de t??l??phone aux num??ros de vos correspondants peut vous faire gagner un temps pr??cieux lorsque vous devez appeler vos clients. (Bureau, Domicile, portable personnel, professionnel, accueil)",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type tiers :</b> Les types de Tiers permettent d???identifier facilement vos Clients, vos Fournisseurs, vos Contacts, vos Prospects, etc ???.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type site web :</b> Vos Tiers poss??dent une ou plusieurs adresses de sites internet ? Identifiez les plus facilement en indiquant s???il s???agit d???un site vitrine, d???une boutique ou bien encore d???une adresse extranet pour passer une commande, ou tout autre chose ...",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Type Email :</b> Vu le nombre d???emails que nous devons g??rer pour certains de nos correspondants, un petit classement par type ne peut pas faire de mal.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Conditions de paiement :</b> D??finissez les diff??rents types de conditions de paiement que vous pouvez proposer ?? vos clients ou que vous avez aupr??s des diff??rents fournisseurs.",
	    		F_ACTIVE,
	    		""));
	    listeparamtiers.add(new DescFonctionnalites(
	    		"<b>Famille :</b> Regrouper vos Tiers par famille vous permet de constituer des groupes homog??nes en fonction d???un crit??re ou d???un autre.",
	    		F_ACTIVE,
	    		""));
	    }

	public void createParamArticles() {
		listeparamarticles = new ArrayList<DescFonctionnalites>();

		listeparamarticles.add(new DescFonctionnalites(
	    		" <b>Famille :</b> D??finissez les familles auxquelles appartiennent vos articles.",
	    		F_ACTIVE,
	    		""));
		listeparamarticles.add(new DescFonctionnalites(
	    		"<b>Code TVA :</b> G??rez facilement les Taux de TVA avec leur Code, qui seront associ??s aux articles de votre catalogue.",
	    		F_ACTIVE,
	    		""));
		listeparamarticles.add(new DescFonctionnalites(
	    		"<b>Unit??s :</b> La notion d???unit??s et de quantit??s est importante dans votre activit?? ? Cr??ez autant d???unit??s que n??cessaires avec le gestionnaire d???unit??s.",
	    		F_ACTIVE,
	    		""));
		listeparamarticles.add(new DescFonctionnalites(
	    		"<b>Coefficient d???unit??s :</b> D??finissez les diff??rents ratios entre vos unit??s. Et pour une meilleure gestion des stocks, vous pouvez aussi utiliser des unit??s de r??f??rences.",
	    		F_ACTIVE,
	    		""));
		listeparamarticles.add(new DescFonctionnalites(
	    		"<b>Famille d???unit??s :</b> Regrouper les unit??s par famille peut ??tre utile si vous utilisez beaucoup d???unit??s dans votre activit?? pour une meilleure gestion, ou pour tout autre usage qui vous semblera pertinent.",
	    		F_ACTIVE,
	    		""));
		listeparamarticles.add(new DescFonctionnalites(
	    		"<b>Marque :</b> Vous pouvez d??finir des marques pour les associer ?? certains de vos articles.",
	    		F_ACTIVE,
	    		""));
		}

	public void createParamDocuments() {
	    listeparamdocuments = new ArrayList<DescFonctionnalites>();

	    listeparamdocuments.add(new DescFonctionnalites(
	    		"<b>Type paiement :</b> les types de paiements peuvent ??tre associ?? ?? vos fiches de clients. Et par exemple, lors de la facturation, le type de paiement associ?? ?? la fiche du client sera automatiquement s??lectionn??, et ajustable si besoin est.",
	    		F_ACTIVE,
	    		""));
	    listeparamdocuments.add(new DescFonctionnalites(
	    		"<b>Paiement - ??ch??ance :</b> d??finissez les dates soit pour les conditions de paiement, soit pour la dur??e de validit?? de vos documents( devis, bon de commande, ).",
	    		F_ACTIVE,
	    		""));
	    }
	
	public void createParamSolstyce() {
	    listeparamsolstyce = new ArrayList<DescFonctionnalites>();

	    listeparamsolstyce.add(new DescFonctionnalites(
	    		"<b>Entrep??t :</b> Saisissez la liste des entrep??ts o?? sont stock??s vos marchandises.",
	    		F_ACTIVE,
	    		""));
	    listeparamsolstyce.add(new DescFonctionnalites(
	    		"<b>Type fabrication :</b> Classez et regroupez vos fabrications en fonction de vos crit??res.",
	    		F_ACTIVE,
	    		""));
	    listeparamsolstyce.add(new DescFonctionnalites(
	    		"<b>Type r??ception :</b> vos r??ceptions de marchandises peuvent elles aussi ??tre class??es et regroup??es.",
	    		F_ACTIVE,
	    		""));
	    listeparamsolstyce.add(new DescFonctionnalites(
	    		"<b>Type code barre :</b> vos codes barres peuvent avoir un type, toujours pour les classements et recherches",
	    		F_ACTIVE,
	    		""));
	    listeparamsolstyce.add(new DescFonctionnalites(
	    		"<b>Groupe de contr??les :</b> les contr??les qualit??s aussi peuvent ??tre associ??s ?? un groupe.",
	    		F_ACTIVE,
	    		""));
	    }
	
	
	public List<DescFonctionnalites> getListeBonCommande() {
		return listeboncommande;
	}

	public void setListeBonCommande(List<DescFonctionnalites> listeboncommande) {
		this.listeboncommande = listeboncommande;
	}

	public List<DescFonctionnalites> getListeBonLivraison() {
		return listebonlivraison;
	}

	public void setListeBonLivraison(List<DescFonctionnalites> listebonlivraison) {
		this.listebonlivraison = listebonlivraison;
	}

	public List<DescFonctionnalites> getListeAvoir() {
		return listeavoir;
	}

	public void setListeAvoir(List<DescFonctionnalites> listeavoir) {
		this.listeavoir = listeavoir;
	}

	public List<DescFonctionnalites> getListeProForma() {
		return listeproforma;
	}

	public void setListeProForma(List<DescFonctionnalites> listeproforma) {
		this.listeproforma = listeproforma;
	}

	public List<DescFonctionnalites> getListeAcompte() {
		return listeacompte;
	}

	public void setListeAcompte(List<DescFonctionnalites> listeacompte) {
		this.listeacompte = listeacompte;
	}

	public List<DescFonctionnalites> getListeApporteur() {
		return listeapporteur;
	}

	public void setListeApporteur(List<DescFonctionnalites> listeapporteur) {
		this.listeapporteur = listeapporteur;
	}

	public List<DescFonctionnalites> getListeAvisPrelevement() {
		return listeavisprelevement;
	}

	public void setListeAvisPrelevement(List<DescFonctionnalites> listeavisprelevement) {
		this.listeavisprelevement = listeavisprelevement;
	}

	public List<DescFonctionnalites> getListeReception() {
		return listesreception;
	}

	public void setListeReception(List<DescFonctionnalites> listesreception) {
		this.listesreception = listesreception;
	}

	public List<DescFonctionnalites> getListesLotsTracabilite() {
		return listeslotstracabilite;
	}

	public void setListesLotsTracabilite(List<DescFonctionnalites> listeslotstracabilite) {
		this.listeslotstracabilite = listeslotstracabilite;
	}

	public List<DescFonctionnalites> getListeStocksInventaire() {
		return listestocksinventaire;
	}

	public void setListeStocksInventaire(List<DescFonctionnalites> listestocksinventaire) {
		this.listestocksinventaire = listestocksinventaire;
	}

	public List<DescFonctionnalites> getListeParamTiers() {
		return listeparamtiers;
	}

	public void setListeParamTiers(List<DescFonctionnalites> listeparamtiers) {
		this.listeparamtiers = listeparamtiers;
	}

	public List<DescFonctionnalites> getListeParamArticles() {
		return listeparamarticles;
	}

	public void setListeParamArticles(List<DescFonctionnalites> listeparamarticles) {
		this.listeparamarticles = listeparamarticles;
	}

	public List<DescFonctionnalites> getListeParamDocuments() {
		return listeparamdocuments;
	}

	public void setListeParamDocuments(List<DescFonctionnalites> listeparamdocuments) {
		this.listeparamdocuments = listeparamdocuments;
	}

	public List<DescFonctionnalites> getListeParamSolstyce() {
		return listeparamsolstyce;
	}

	public void setListeParamSolstyce(List<DescFonctionnalites> listeparamsolstyce) {
		this.listeparamsolstyce = listeparamsolstyce;
	}
}
 
 
