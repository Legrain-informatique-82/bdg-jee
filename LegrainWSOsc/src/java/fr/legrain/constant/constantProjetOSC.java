/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.legrain.constant;

/**
 *
 * @author lee
 */
public class constantProjetOSC {

    /**
     * constant type value string utile
     */
    public static final String INCONNU = "inconnu";
    /*
     * constant paramètre de database.
     */
    public static final String NAME_TABLE_USER = "USER";
    public static final String LOGIN = "usrmajbdg";
    public static final String PASSWORD ="pwd18jbdg03";
    public static final String NAME_DB_OSC_GENBOUCOM = "genboucom";
    public static final String NAME_DB_WS_GENBOUCOM = "ws_genboucom";
    public static final String MYSQL_DB = "mysql";
    public static final String JDBC = "jdbc:";
    public static final String LOCALHOST = "localhost:";
    public static final String PORT_DB_MYSQL = "3306" ;
    public static final String SYSTEME_PROPERTY_JDBC = "jdbc.drivers";
	public static final String DRIVER_JDBC_MYSQL = "com.mysql.jdbc.Driver";

    /*
     * constant Datametadata
     */
    public static final String TABLE_NAME = "TABLE_NAME";
	public static final String COLUMN_NAME = "COLUMN_NAME";
	public static final String DATA_TYPE = "DATA_TYPE";

    /**
     * constant query sql for database ws_genboucom
     */
    public static final String COLUMN_NAME_TA_USER_LOGIN = "login_user";
    public static final String COLUMN_NAME_TA_USER_PASSWORD = "password_user";
    public static final String COLUMN_NAME_TA_NOMBRE_CONNECT = "nombre_connect";
    public static final String NAME_WS_TA_NOMBRE_CONNECT = "ws_ta_nombre_connect";
    
    public static final String SELECT_WS_TA_NOMBRE_CONNECT = "select * from ws_ta_nombre_connect" ;

    //public static final String CREATE_USER = "create table user(id_user int(50) not null auto_increment,login varchar(20)
    //,primary key(id_user))";

    public static final String[] ARRAY_CREATE_WS_TA = {constantProjetOSC.CREATE_WS_TA_ACCES, constantProjetOSC.CREATE_WS_TA_ADRESSE,
                                                       constantProjetOSC.CREATE_WS_TA_ARTICLE, constantProjetOSC.CREATE_WS_TA_AVOIR,
                                                       constantProjetOSC.CREATE_WS_TA_BANQUE, constantProjetOSC.CREATE_WS_TA_BONCDE,
                                                       constantProjetOSC.CREATE_WS_TA_BONLIV, constantProjetOSC.CREATE_WS_TA_COMMTAIRE,
                                                       constantProjetOSC.CREATE_WS_TA_COMPL, constantProjetOSC.CREATE_WS_TA_COM_DOC,
                                                       constantProjetOSC.CREATE_WS_TA_C_PAIEMENT, constantProjetOSC.CREATE_WS_TA_DEVIS,
                                                       constantProjetOSC.CREATE_WS_TA_EMAIL, constantProjetOSC.CREATE_WS_TA_ENTREPRISE,
                                                       constantProjetOSC.CREATE_WS_TA_FACTURE, constantProjetOSC.CREATE_WS_TA_FAMILLE,
                                                       constantProjetOSC.CREATE_WS_TA_INFOS_BONLIV, constantProjetOSC.CREATE_WS_TA_INFOS_DEVIS,
                                                       constantProjetOSC.CREATE_WS_TA_INFOS_FACTURE, constantProjetOSC.CREATE_WS_TA_INFO_ENTREPRISE,
                                                       constantProjetOSC.CREATE_WS_TA_L_AVOIR, constantProjetOSC.CREATE_WS_TA_L_BONCDE,
                                                       constantProjetOSC.CREATE_WS_TA_L_BONLIV, constantProjetOSC.CREATE_WS_TA_L_BONLIV_TEMP,
                                                       constantProjetOSC.CREATE_WS_TA_L_DEVIS, constantProjetOSC.CREATE_WS_TA_L_DEVIS_TEMP,
                                                       constantProjetOSC.CREATE_WS_TA_L_FACTURE, constantProjetOSC.CREATE_WS_TA_L_FACTURE_TEMP,
                                                       constantProjetOSC.CREATE_WS_TA_L_MODELE, constantProjetOSC.CREATE_WS_TA_MAIL_MAJ,
                                                       constantProjetOSC.CREATE_WS_TA_MODELE, constantProjetOSC.CREATE_WS_TA_MODIF,
                                                       constantProjetOSC.CREATE_WS_TA_PRIX, constantProjetOSC.CREATE_WS_TA_REF_PRIX,
                                                       constantProjetOSC.CREATE_WS_TA_REPORT_STOCK, constantProjetOSC.CREATE_WS_TA_R_ADR,
                                                       constantProjetOSC.CREATE_WS_TA_R_ADR_T_ADR, constantProjetOSC.CREATE_WS_TA_R_COMMERCIAL,
                                                       constantProjetOSC.CREATE_WS_TA_R_DOCUMENT, constantProjetOSC.CREATE_WS_TA_R_EMAIL,
                                                       constantProjetOSC.CREATE_WS_TA_R_TEL, constantProjetOSC.CREATE_WS_TA_R_TEL_T_TEL,
                                                       constantProjetOSC.CREATE_WS_TA_R_WEB, constantProjetOSC.CREATE_WS_TA_SIEA,
                                                       constantProjetOSC.CREATE_WS_TA_STOCK, constantProjetOSC.CREATE_WS_TA_TELEPHONE,
                                                       constantProjetOSC.CREATE_WS_TA_TVA, constantProjetOSC.CREATE_WS_TA_TIERS,
                                                       constantProjetOSC.CREATE_WS_TA_ADR, constantProjetOSC.CREATE_WS_TA_T_CIVILITE,
                                                       constantProjetOSC.CREATE_WS_TA_T_DOC, constantProjetOSC.CREATE_WS_TA_T_ENTITE,
                                                       constantProjetOSC.CREATE_WS_TA_T_L_LIGNE, constantProjetOSC.CREATE_WS_TA_T_PAIEMENT,
                                                       constantProjetOSC.CREATE_WS_TA_T_TEL, constantProjetOSC.CREATE_WS_TA_T_TIERS,
                                                       constantProjetOSC.CREATE_WS_TA_T_TVA, constantProjetOSC.CREATE_WS_TA_UNITE,
                                                       constantProjetOSC.CREATE_WS_TA_VERSION, constantProjetOSC.CREATE_WS_TA_VISUALISATION,
                                                       constantProjetOSC.CREATE_WS_TA_WEB};
    
    public static final String CREATE_WS_TA_ACCES = "create table ws_ta_acces(id_acces int not null auto_increment, " +
                                                    "password_acces varchar(50) not null default 'password'," +
                                                    "ip_acces varchar(50) not null default 'current_connection'," +
                                                    "user_acces varchar(50) not null default 'user',primary key (id_acces));";
    
    public static final String CREATE_WS_TA_ADRESSE = "create table ws_ta_adresse(id_adresse int not null auto_increment," +
                                                      "adresse1_adresse varchar(100),adresse2_adresse varchar(100)," +
                                                      "adresse3_adresse varchar(100),codepostal_adresse varchar(5)," +
                                                      "ville_adresse varchar(100) not null,pays_adresse varchar(100) not null default 'france'," +
                                                      "primary key (id_adresse));";
    
    public static final String CREATE_WS_TA_ARTICLE = "create table ws_ta_article(id_article int not null auto_increment,id_article_osc int not null," +
                                                      "code_article varchar(20) not null,libellec_article varchar(100) not null," +
                                                      "libellel_article varchar(255),id_famille int,id_tva int,id_t_tva int," +
                                                      "numcpt_article varchar(8),divers_article varchar(255),commentaire_article varchar(255)," +
                                                      "stock_min_article decimal(15,2),quand_add datetime,primary key (id_article);";

    public static final String CREATE_WS_TA_AVOIR = "create table ws_ta_avoir(id_avoir int not null auto_increment,primary key (id_avoir));";
    
    public static final String CREATE_WS_TA_BANQUE = "create table ws_ta_banque(id_banque int not null auto_increment," +
                                                     "code_banque varchar(20) not null,libc_banque varchar(100) not null," +
                                                     "libl_banque varchar(100),primary key (id_banque));";

    public static final String CREATE_WS_TA_BONCDE = "create table ws_ta_boncde(id_boncde int not null auto_increment,primary key (id_boncde));";
    
    public static final String CREATE_WS_TA_BONLIV = "create table ws_ta_bonliv(id_bonliv int not null auto_increment," +
                                                     "code_bonliv varchar(20) not null,date_bonliv datetime,date_liv_bonliv datetime," +
                                                     "libelle_bonliv varchar(255),id_adresse int,id_adresse_liv int,id_tiers int," +
                                                     "id_t_paiement int,id_c_paiement int,rem_ht_bonliv decimal(15,2) default 0," +
                                                     "tx_rem_ht_bonliv decimal(15,2) default 0,rem_ttc_bonliv decimal(15,2) default 0," +
                                                     "tx_rem_ttc_bonliv decimal(15,2) default 0,nb_e_bonliv int,ttc tinyint not null default 0," +
                                                     "commentaire varchar(255),quand_add datetime,primary key (id_bonliv));";

    public static final String CREATE_WS_TA_COMMTAIRE= "create table ws_ta_commentaire(id_commentaire int not null auto_increment," +
                                                       "libl_commentaire varchar(255),id_tiers int,primary key (id_commentaire));";

    public static final String CREATE_WS_TA_COMPL = "create table ws_ta_compl(id_compl int not null auto_increment,id_tiers int," +
                                                    "tva_i_com_compl varchar(50),siret_compl varchar(50),primary key (id_compl));";

    public static final String CREATE_WS_TA_COM_DOC = "create table ws_ta_com_doc(id_com_doc int not null auto_increment,id_t_doc int," +
                                                      "id_document int,note_com_doc varchar(255),primary key (id_com_doc));";

    public static final String CREATE_WS_TA_C_PAIEMENT = "create table ws_ta_c_paiement(id_c_paiement int not null auto_increment," +
                                                         "code_c_paiement varchar(50) not null,lib_c_paiement varchar(255),id_tiers int," +
                                                         "report_c_paiement int default 0,fin_mois_c_paiement int default 0," +
                                                         "primary key (id_c_paiement));";

    public static final String CREATE_WS_TA_DEVIS = "create table ws_ta_devis(id_devis int not null auto_increment,code_devis varchar(20) not null," +
                                                    "date_devis datetime,date_ech_devis datetime,date_liv_devis datetime,libelle_devis varchar(255)," +
                                                    "id_adresse int,id_adresse_liv int,id_tiers int,id_t_paiement int,id_c_paiement int," +
                                                    "rem_ht_devis decimal(15,2) default 0,tx_rem_ht_devis decimal(15,2) default 0," +
                                                    "rem_ttc_devis decimal(15,2) default 0,tx_rem_ttc_devis decimal(15,2) default 0,nb_e_devis int," +
                                                    "ttc tinyint not null default 0,export tinyint not null default 0,commentaire varchar(255)," +
                                                    "primary key (id_devis));";

    public static final String CREATE_WS_TA_EMAIL = "create table ws_ta_email(id_email int not null auto_increment,adresse_email varchar(255)," +
                                                    "primary key (id_email));";

    public static final String CREATE_WS_TA_ENTREPRISE = "create table ws_ta_entreprise(id_entreprise int not null auto_increment," +
                                               "code_entreprise varchar(20) not null,libl_entreprise varchar(100) not null,id_t_entite int," +
                                               "id_i_resp_tiers int,primary key (id_entreprise));";

    public static final String CREATE_WS_TA_FACTURE = "create table ws_ta_facture(id_facture int not null auto_increment,id_facture_osc int not null," +
                                                      "code_facture varchar(20) not null,date_facture datetime,date_ech_facture datetime," +
                                                      "date_liv_facture datetime,libelle_facture varchar(255),id_adresse int,id_adresse_liv int," +
                                                      "id_tiers int,id_t_paiement int,id_c_paiement int,regle_facture decimal(15,2) default 0," +
                                                      "rem_ht_facture decimal(15,2) default 0,tx_rem_ht_facture decimal(15,2) default 0," +
                                                      "rem_ttc_facture decimal(15,2) default 0,tx_rem_ttc_facture decimal(15,2) default 0," +
                                                      "nb_e_facture int default 0,ttc tinyint not null default 0,export tinyint not null default 0," +
                                                      "commentaire varchar(255),quand_add datetime,primary key (id_facture));";

    public static final String CREATE_WS_TA_FAMILLE = "create table ws_ta_famille(id_famille int not null auto_increment," +
                                                      "code_famille varchar(20) not null,libc_famille varchar(50) not null," +
                                                      "libl_famille varchar(255),primary key (id_famille));";

    public static final String CREATE_WS_TA_INFOS_BONLIV = "create table ws_ta_infos_bonliv(id_infos_bonliv int not null auto_increment," +
                                                           "id_bonliv int,adresse1 varchar(100),adresse2 varchar(100),adresse3 varchar(100)," +
                                                           "codepostal varchar(5),ville varchar(100),pays varchar(100),adresse1_liv varchar(100)," +
                                                           "adresse2_liv varchar(100),adresse3_liv varchar(100),codepostal_liv varchar(5)," +
                                                           "ville_liv varchar(100),pays_liv varchar(100),code_compta varchar(8),compte varchar(8)," +
                                                           "nom_tiers varchar(100),prenom_tiers varchar(100),surnom_tiers varchar(20)," +
                                                           "code_t_civilite varchar(20) not null,code_t_entite varchar(20) not null," +
                                                           "tva_i_com_compl varchar(50),code_c_paiement varchar(20) not null," +
                                                           "lib_c_paiement varchar(255),report_c_paiement int,fin_mois_c_paiement int," +
                                                           "primary key (id_infos_bonliv));";

    public static final String CREATE_WS_TA_INFOS_DEVIS = "create table ws_ta_infos_devis(id_infos_devis int not null auto_increment,id_devis int,adresse1 varchar(100),adresse2 varchar(100),adresse3 varchar(100),codepostal varchar(5)," +
                                                    "ville varchar(100),pays varchar(100),adresse1_liv varchar(100),adresse2_liv varchar(100)," +
                                                    "adresse3_liv varchar(100),codepostal_liv varchar(5),ville_liv varchar(100),pays_liv varchar(100)," +
                                                    "code_compta varchar(8),compte varchar(8),nom_tiers varchar(100),prenom_tiers varchar(100)," +
                                                    "surnom_tiers varchar(20),code_t_civilite varchar(20) not null,code_t_entite varchar(20) not null," +
                                                    "tva_i_com_compl varchar(50),code_c_paiement varchar(20) not null,lib_c_paiement varchar(255)," +
                                                    "report_c_paiement int,fin_mois_c_paiement int,primary key (id_infos_devis));";

    public static final String CREATE_WS_TA_INFOS_FACTURE = "create table ws_ta_infos_facture(id_infos_facture int not null auto_increment," +
                                                            "id_facture int,adresse1 varchar(100),adresse2 varchar(100),adresse3 varchar(100)," +
                                                            "codepostal varchar(5),ville varchar(100),pays varchar(100),adresse1_liv varchar(100)," +
                                                            "adresse2_liv varchar(100),adresse3_liv varchar(100),codepostal_liv varchar(5)," +
                                                            "ville_liv varchar(100),pays_liv varchar(100),code_compta varchar(8),compte varchar(8)," +
                                                            "nom_tiers varchar(100),prenom_tiers varchar(100),surnom_tiers varchar(20)," +
                                                            "code_t_civilite varchar(20) not null,code_t_entite varchar(20) not null," +
                                                            "tva_i_com_compl varchar(50),code_c_paiement varchar(20) not null," +
                                                            "lib_c_paiement varchar(255),report_c_paiement int,fin_mois_c_paiement int," +
                                                            "primary key (id_infos_facture));";

    public static final String CREATE_WS_TA_INFO_ENTREPRISE = "create table ws_ta_info_entreprise(id_info_entreprise int not null auto_increment," +
                                                              "nom_info_entreprise varchar(100),adresse1_info_entreprise varchar(100)," +
                                                              "adresse2_info_entreprise varchar(100),adresse3_info_entreprise varchar(100)," +
                                                              "codepostal_info_entreprise varchar(5),ville_info_entreprise varchar(100) not null," +
                                                              "pays_info_entreprise varchar(100) not null default 'france'," +
                                                              "siret_info_entreprise varchar(100),capital_info_entreprise varchar(100)," +
                                                              "ape_info_entreprise varchar(100),tva_intra_info_entreprise varchar(100)," +
                                                              "tel_info_entreprise varchar(20) not null,fax_info_entreprise varchar(20) not null," +
                                                              "email_info_entreprise varchar(100),web_info_entreprise varchar(100)," +
                                                              "rcs_info_entreprise varchar(100),datedeb_info_entreprise datetime," +
                                                              "datefin_info_entreprise datetime,codexo_info_entreprise varchar(2) not null," +
                                                              "primary key (id_info_entreprise));";

    public static final String CREATE_WS_TA_L_AVOIR = "create table ws_ta_l_avoir(id_l_avoir int not null auto_increment,primary key (id_l_avoir));";

    public static final String CREATE_WS_TA_L_BONCDE = "create table ws_ta_l_boncde(id_l_boncde int not null auto_increment,primary key (id_l_boncde));";

    public static final String CREATE_WS_TA_L_BONLIV = "create table ws_ta_l_bonliv(id_l_bonliv int not null auto_increment,id_bonliv int not null," +
                                                       "id_t_ligne int,id_article int,num_ligne_l_bonliv int,lib_l_bonliv varchar(255)," +
                                                       "qte_l_bonliv decimal(15,2),u1_l_bonliv varchar(20),u2_l_bonliv varchar(20)," +
                                                       "prix_u_l_bonliv decimal(15,2),taux_tva_l_bonliv decimal(15,4),compte_l_bonliv varchar(8)," +
                                                       "code_tva_l_bonliv varchar(20),code_t_tva_l_bonliv varchar(1),mt_ht_l_bonliv decimal(15,2)," +
                                                       "mt_ttc_l_bonliv decimal(15,2),rem_tx_l_bonliv decimal(15,2),rem_ht_l_bonliv decimal(15,2)," +
                                                       "primary key (id_l_bonliv));";

    public static final String CREATE_WS_TA_L_BONLIV_TEMP = "create table ws_ta_l_bonliv_temp(id_l_bonliv int,id_bonliv int,id_t_ligne int," +
                                                            "id_article int,num_ligne_l_bonliv int,lib_l_bonliv varchar(255)," +
                                                            "qte_l_bonliv decimal(15,2),u1_l_bonliv varchar(20),u2_l_bonliv varchar(20)," +
                                                            "prix_u_l_bonliv decimal(15,2),taux_tva_l_bonliv decimal(15,4)," +
                                                            "compte_l_bonliv varchar(8),code_tva_l_bonliv varchar(20)," +
                                                            "code_t_tva_l_bonliv varchar(1),mt_ht_l_bonliv decimal(15,2)," +
                                                            "mt_ttc_l_bonliv decimal(15,2),rem_tx_l_bonliv decimal(15,2)," +
                                                            "rem_ht_l_bonliv decimal(15,2));";

    public static final String CREATE_WS_TA_L_DEVIS = "create table ws_ta_l_devis(id_l_devis int not null auto_increment,id_devis int," +
                                                      "id_t_ligne int,id_article int,num_ligne_l_devis int,lib_l_devis varchar(255)," +
                                                      "qte_l_devis decimal(15,2),u1_l_devis varchar(20),u2_l_devis varchar(20)," +
                                                      "prix_u_l_devis decimal(15,2),taux_tva_l_devis decimal(15,4),compte_l_devis varchar(8)," +
                                                      "code_tva_l_devis varchar(20),code_t_tva_l_devis varchar(1),mt_ht_l_devis decimal(15,2)," +
                                                      "mt_ttc_l_devis decimal(15,2),rem_tx_l_devis decimal(15,2),rem_ht_l_devis decimal(15,2)," +
                                                      "primary key (id_l_devis));";

    public static final String CREATE_WS_TA_L_DEVIS_TEMP = "create table ws_ta_l_devis_temp(id_l_devis int,id_devis int,id_t_ligne int,id_article int," +
                                                           "num_ligne_l_devis int,lib_l_devis varchar(255),qte_l_devis decimal(15,2)," +
                                                           "u1_l_devis varchar(20),u2_l_devis varchar(20),prix_u_l_devis decimal(15,2)," +
                                                           "taux_tva_l_devis decimal(15,4),compte_l_devis varchar(8)," +
                                                           "code_tva_l_devis varchar(20),code_t_tva_l_devis varchar(1)," +
                                                           "mt_ht_l_devis decimal(15,2),mt_ttc_l_devis decimal(15,2),rem_tx_l_devis decimal(15,2)," +
                                                           "rem_ht_l_devis decimal(15,2));";

    public static final String CREATE_WS_TA_L_FACTURE = "create table ws_ta_l_facture(id_l_facture int not null auto_increment,id_facture int," +
                                                        "id_t_ligne int,id_article int,num_ligne_l_facture int,lib_l_facture varchar(255)," +
                                                        "qte_l_facture decimal(15,2),u1_l_facture varchar(20),u2_l_facture varchar(20)," +
                                                        "prix_u_l_facture decimal(15,2),taux_tva_l_facture decimal(15,4),compte_l_facture varchar(8)," +
                                                        "code_tva_l_facture varchar(20),code_t_tva_l_facture varchar(1),mt_ht_l_facture decimal(15,2)," +
                                                        "mt_ttc_l_facture decimal(15,2),rem_tx_l_facture decimal(15,2),rem_ht_l_facture decimal(15,2)," +
                                                        "primary key (id_l_facture));";

    public static final String CREATE_WS_TA_L_FACTURE_TEMP = "create table ws_ta_l_facture_temp(id_l_facture int,id_facture int,id_t_ligne int," +
                                                             "id_article int,num_ligne_l_facture int,lib_l_facture varchar(255)," +
                                                             "qte_l_facture decimal(15,2),u1_l_facture varchar(20),u2_l_facture varchar(20)," +
                                                             "prix_u_l_facture decimal(15,2),taux_tva_l_facture decimal(15,4)," +
                                                             "compte_l_facture varchar(8),code_tva_l_facture varchar(20)," +
                                                             "code_t_tva_l_facture varchar(1),mt_ht_l_facture decimal(15,2)," +
                                                             "mt_ttc_l_facture decimal(15,2),rem_tx_l_facture decimal(15,2)," +
                                                             "rem_ht_l_facture decimal(15,2));";

    public static final String CREATE_WS_TA_L_MODELE = "create table ws_ta_l_modele(id_l_modele int not null auto_increment,primary key (id_l_modele));";
    
    public static final String CREATE_WS_TA_MAIL_MAJ = "create table ws_ta_mail_maj(id_mail_maj int not null auto_increment,sujet_mail_maj varchar(255)," +
                                               "nom_site_mail_maj varchar(255),url_mail_maj varchar(255),fait_mail_maj datetime," +
                                               "a_faire_mail_maj tinyint not null default 0,from_mail_maj varchar(255),date_mail_maj date," +
                                               "primary key (id_mail_maj));";
    public static final String CREATE_WS_TA_MODELE = "create table ws_ta_modele(id_modele int not null auto_increment,primary key (id_modele));";

    public static final String CREATE_WS_TA_MODIF = "create table ws_ta_modif(id_modif int not null auto_increment,table_modif varchar(255)," +
                                               "champ_modif varchar(255),valeur_modif varchar(255),primary key (id_modif));";

    public static final String CREATE_WS_TA_PRIX = "create table ws_ta_prix(id_prix int not null auto_increment,id_article int,id_unite int," +
                                               "prix_prix decimal(15,2),prixttc_prix decimal(15,2),primary key (id_prix));";

    public static final String CREATE_WS_TA_REF_PRIX = "create table ws_ta_ref_prix(id_ref_prix int not null auto_increment,id_article int,id_prix int," +
                                                       "primary key (id_ref_prix));";

    public static final String CREATE_WS_TA_REPORT_STOCK = "create table ws_ta_report_stock(id_report_stock int not null auto_increment," +
                                                           "id_article_report_stock int,date_deb_report_stock datetime," +
                                                           "date_fin_report_stock datetime,qte_report_stock decimal(15,2)," +
                                                           "unite_report_stock varchar(20) not null,primary key (id_report_stock));";

    public static final String CREATE_WS_TA_R_ADR = "create table ws_ta_r_adr(id_r_adr int not null auto_increment,id_tiers int,id_adresse int," +
                                                    "primary key (id_r_adr));";

    public static final String CREATE_WS_TA_R_ADR_T_ADR = "create table ws_ta_r_adr_t_adr(id_r_adr_t_adr int not null auto_increment,id_t_adr int," +
                                                          "id_adresse int,primary key (id_r_adr_t_adr));";

    public static final String CREATE_WS_TA_R_COMMERCIAL = "create table ws_ta_r_commercial(id_r_commercial int not null auto_increment," +
                                                           "id_tiers int,id_tiers_com int,primary key (id_r_commercial));";

    public static final String CREATE_WS_TA_R_DOCUMENT = "create table ws_ta_r_document(id_r_document int not null auto_increment,id_facture int," +
                                                         "id_devis int,id_boncde int,id_bonliv int,id_avoir int,id_l_facture int,id_l_devis int," +
                                                         "id_l_boncde int,id_l_avoir int,id_l_bonliv int,primary key (id_r_document));";

    public static final String CREATE_WS_TA_R_EMAIL = "create table ws_ta_r_email(id_r_email int not null auto_increment,id_tiers int,id_email int," +
                                                      "primary key (id_r_email));";

    public static final String CREATE_WS_TA_R_TEL = "create table ws_ta_r_tel(id_r_tel int not null auto_increment,id_tiers int,id_telephone int," +
                                                    "primary key (id_r_tel));";

    public static final String CREATE_WS_TA_R_TEL_T_TEL = "create table ws_ta_r_tel_t_tel(id_r_tel_t_tel int not null auto_increment,id_telephone int," +
                                                          "id_t_tel int,primary key (id_r_tel_t_tel));";

    public static final String CREATE_WS_TA_R_WEB = "create table ws_ta_r_web(id_r_web int not null auto_increment,id_tiers int,id_web int," +
                                                    "primary key (id_r_web));";

    public static final String CREATE_WS_TA_SIEA = "create table ws_ta_siea(id_client int not null auto_increment,client varchar(255)," +
                                                   "civilite varchar(255),libcivilite varchar(255),nom varchar(255),prenom varchar(255)," +
                                                   "adresse1 varchar(255),adresse2 varchar(255),region varchar(255),codepostal varchar(255)," +
                                                   "ville varchar(255),pays varchar(255),tel varchar(20),fax varchar(20),tarif varchar(10)," +
                                                   "tri varchar(255),repres varchar(10),contact datetime,commande varchar(10),cpt varchar(10)," +
                                                   "fact varchar(10),cpt_banque1 varchar(10),cpt_banque2 varchar(10),cpt_banque3 varchar(10)," +
                                                   "cpt_banque_cle varchar(10),divers1 varchar(255),divers2 varchar(255),ref varchar(255)," +
                                                   "quand_add datetime,primary key (id_client));";

    public static final String CREATE_WS_TA_STOCK = "create table ws_ta_stock(id_stock int not null auto_increment,id_article_stock int," +
                                                    "mouv_stock varchar(1) not null default 'e', date_stock datetime,libelle_stock varchar(255)," +
                                                    "qte1_stock decimal(15,2),un1_stock varchar(20) not null,qte2_stock decimal(15,2)," +
                                                    "un2_stock varchar(20) not null,primary key (id_stock));";

    public static final String CREATE_WS_TA_TELEPHONE = "create table ws_ta_telephone(id_telephone int not null auto_increment,numero_telephone varchar(20) not null," +
                                               "poste_telephone varchar(20) not null,id_i_tiers int,primary key (id_telephone));";

    public static final String CREATE_WS_TA_TIERS = "create table ws_ta_tiers(id_tiers int not null auto_increment,id_tiers_osc int not null,code_tiers varchar(20) not null," +
                                                    "code_compta varchar(7),compte varchar(8),nom_tiers varchar(100) not null,prenom_tiers varchar(100)," +
                                                    "surnom_tiers varchar(20),actif_tiers tinyint not null default '1'," +
                                                    "ttc_tiers tinyint not null default '0',id_t_civilite int,id_entreprise int,id_t_tiers int," +
                                                    "id_t_entite int,id_i_banque int,quand_add datetime,primary key (id_tiers));";

    public static final String CREATE_WS_TA_TVA = "create table ws_ta_tva(id_tva int not null auto_increment,code_tva varchar(20) not null," +
                                                  "libelle_tva varchar(255), taux_tva decimal(15,2),numcpt_tva varchar(8),primary key (id_tva));";

    public static final String CREATE_WS_TA_ADR = "create table ws_ta_t_adr(id_t_adr int not null auto_increment,code_t_adr varchar(20) not null," +
                                               "libl_t_adr varchar(100) not null, primary key (id_t_adr));";

    public static final String CREATE_WS_TA_T_CIVILITE = "create table ws_ta_t_civilite(id_t_civilite int not null auto_increment," +
                                                         "code_t_civilite varchar(20) not null,primary key (id_t_civilite));";

    public static final String CREATE_WS_TA_T_DOC = "create table ws_ta_t_doc(id_t_doc int not null auto_increment,code_t_doc varchar(50) not null," +
                                                    "lib_t_doc varchar(255),primary key (id_t_doc));";

    public static final String CREATE_WS_TA_T_ENTITE = "create table ws_ta_t_entite(id_t_entite int not null auto_increment,code_t_entite varchar(20) not null," +
                                                       "libl_t_entite varchar(100),primary key (id_t_entite));";

    public static final String CREATE_WS_TA_T_L_LIGNE = "create table ws_ta_t_ligne(id_t_ligne int not null auto_increment,code_t_ligne varchar(50) not null," +
                                                        "lib_t_ligne varchar(255),primary key (id_t_ligne));";

    public static final String CREATE_WS_TA_T_PAIEMENT = "create table ws_ta_t_paiement(id_t_paiement int not null auto_increment," +
                                                         "code_t_paiement varchar(50) not null,lib_t_paiement varchar(255)," +
                                                         "primary key (id_t_paiement));";

    public static final String CREATE_WS_TA_T_TEL = "create table ws_ta_t_tel(id_t_tel int not null auto_increment,code_t_tel varchar(20) not null," +
                                                    "libl_t_tel varchar(100),primary key (id_t_tel));";

    public static final String CREATE_WS_TA_T_TIERS = "create table ws_ta_t_tiers(id_t_tiers int not null auto_increment,code_t_tiers varchar(20) not null," +
                                               "libelle_t_tiers varchar(100) not null,compte_t_tiers varchar(8),primary key (id_t_tiers));";

    public static final String CREATE_WS_TA_T_TVA = "create table ws_ta_t_tva(id_t_tva int not null auto_increment,code_t_tva varchar(1) not null," +
                                               "lib_t_tva varchar(255),primary key (id_t_tva));";

    public static final String CREATE_WS_TA_UNITE = "create table ws_ta_unite(id_unite int not null auto_increment,code_unite varchar(20) not null," +
                                                    "libl_unite varchar(255),primary key (id_unite));";

    public static final String CREATE_WS_TA_VERSION = "create table ws_ta_version(id_version int not null auto_increment,num_version varchar(255)," +
                                                      "old_version varchar(255),date_version datetime,ip_acces int,primary key (id_version));";

    public static final String CREATE_WS_TA_VISUALISATION = "create table ws_ta_visualisation(id int not null,code_requete varchar(255),requete varchar(2000)," +
                                                            "champs varchar(2000),titre_champs varchar(2000),taille_champs varchar(255),module varchar(255)," +
                                                            "impression varchar(255),identifiant varchar(255),id_editeur varchar(255),id_plugin varchar(255)," +
                                                            "groupby varchar(2000),proc tinyint not null default 0,param varchar(255));";

    public static final String CREATE_WS_TA_WEB= "create table ws_ta_web(id_web int not null auto_increment,adresse_web varchar(255)," +
                                                 "primary key (id_web));";

    public static final String CREATE_WS_TA_NOMBRE_CONNECT = "create table ws_ta_nombre_connect(id int not null auto_increment," +
                                                             "nombre_connect int not null default 0,login_user varchar(20),password_user varchar(20)," +
                                                             "quand_connect datetime,primary key(id));";




}
