INSERT INTO TA_FACTURE (ID_FACTURE, CODE_FACTURE, DATE_FACTURE, DATE_ECH_FACTURE, DATE_LIV_FACTURE, LIBELLE_FACTURE, ID_ADRESSE, ID_ADRESSE_LIV, ID_TIERS, ID_T_PAIEMENT, ID_C_PAIEMENT, REGLE_FACTURE, REM_HT_FACTURE, TX_REM_HT_FACTURE, REM_TTC_FACTURE, TX_REM_TTC_FACTURE, NB_E_FACTURE, TTC, EXPORT, QUI_CREE_FACTURE, QUAND_CREE_FACTURE, QUI_MODIF_FACTURE, QUAND_MODIF_FACTURE, "VERSION", IP_ACCES) VALUES (3, 'FV0600003', '2006-09-13 00:00:00', '2006-09-13 00:00:00', '2006-09-13 00:00:00', 'Facture N�FV0600003 -  CRU', 0, 0, 8199, 1, 0, 1000, 1186.87, 10, 2253.86, 20, 0, 1, 0, 'ADMIN', '2006-09-13 17:27:21', 'ADMIN', '2006-09-13 17:27:21', '1.0.0', '3');

COMMIT WORK;

INSERT INTO TA_INFOS_FACTURE (ID_INFOS_FACTURE, ID_FACTURE, ADRESSE1, ADRESSE2, ADRESSE3, CODEPOSTAL, VILLE, PAYS, ADRESSE1_LIV, ADRESSE2_LIV, ADRESSE3_LIV, CODEPOSTAL_LIV, VILLE_LIV, PAYS_LIV, CODE_COMPTA, COMPTE, NOM_TIERS, PRENOM_TIERS, SURNOM_TIERS, CODE_T_CIVILITE, CODE_T_ENTITE, TVA_I_COM_COMPL, CODE_C_PAIEMENT, LIB_C_PAIEMENT, REPORT_C_PAIEMENT, FIN_MOIS_C_PAIEMENT, QUI_CREE_INFOS_FACTURE, QUAND_CREE_INFOS_FACTURE, QUI_MODIF_INFOS_FACTURE, QUAND_MODIF_INFOS_FACTURE, "VERSION", IP_ACCES) VALUES (3, 3, '3 ROUTE DE CHASSELAY', '', '', '69380', 'LES CHERES', 'FRANCE', '3 ROUTE DE CHASSELAY', '', '', '69380', 'LES CHERES', 'FRANCE', 'CRUDANI', '411', 'CRU', 'DANIEL', '', 'M ME', NULL, NULL, NULL, NULL, NULL, NULL, 'ADMIN', '2006-09-13 17:27:21', 'ADMIN', '2006-09-13 17:27:21', '1.0.0', '3');

COMMIT WORK;

INSERT INTO TA_L_FACTURE (ID_L_FACTURE, ID_FACTURE, ID_T_LIGNE, ID_ARTICLE, NUM_LIGNE_L_FACTURE, LIB_L_FACTURE, QTE_L_FACTURE, U1_L_FACTURE, U2_L_FACTURE, PRIX_U_L_FACTURE, TAUX_TVA_L_FACTURE, CODE_TVA_L_FACTURE, CODE_T_TVA_L_FACTURE, MT_HT_L_FACTURE, MT_TTC_L_FACTURE, REM_TX_L_FACTURE, REM_HT_L_FACTURE, QUI_CREE_L_FACTURE, QUAND_CREE_L_FACTURE, QUI_MODIF_L_FACTURE, QUAND_MODIF_L_FACTURE, "VERSION", IP_ACCES) VALUES (3, 3, 1, 3, 0, 'DIVERS', 100, 'U', '', 100, 5.5, 'V1', 'D', 8478.67, 9000, 10, 0, 'ADMIN', '2006-09-13 17:27:21', 'ADMIN', '2006-09-13 17:27:21', '1.0.0', '3');
INSERT INTO TA_L_FACTURE (ID_L_FACTURE, ID_FACTURE, ID_T_LIGNE, ID_ARTICLE, NUM_LIGNE_L_FACTURE, LIB_L_FACTURE, QTE_L_FACTURE, U1_L_FACTURE, U2_L_FACTURE, PRIX_U_L_FACTURE, TAUX_TVA_L_FACTURE, CODE_TVA_L_FACTURE, CODE_T_TVA_L_FACTURE, MT_HT_L_FACTURE, MT_TTC_L_FACTURE, REM_TX_L_FACTURE, REM_HT_L_FACTURE, QUI_CREE_L_FACTURE, QUAND_CREE_L_FACTURE, QUI_MODIF_L_FACTURE, QUAND_MODIF_L_FACTURE, "VERSION", IP_ACCES) VALUES (4, 3, 1, 1, 1, 'FOIE GRAS CANARD ENTIER 120G', 10, '', '', 200, 5.5, 'V1', 'D', 1877.84, 1900, 5, 0, 'ADMIN', '2006-09-13 17:27:21', 'ADMIN', '2006-09-13 17:27:21', '1.0.0', '3');
INSERT INTO TA_L_FACTURE (ID_L_FACTURE, ID_FACTURE, ID_T_LIGNE, ID_ARTICLE, NUM_LIGNE_L_FACTURE, LIB_L_FACTURE, QTE_L_FACTURE, U1_L_FACTURE, U2_L_FACTURE, PRIX_U_L_FACTURE, TAUX_TVA_L_FACTURE, CODE_TVA_L_FACTURE, CODE_T_TVA_L_FACTURE, MT_HT_L_FACTURE, MT_TTC_L_FACTURE, REM_TX_L_FACTURE, REM_HT_L_FACTURE, QUI_CREE_L_FACTURE, QUAND_CREE_L_FACTURE, QUI_MODIF_L_FACTURE, QUAND_MODIF_L_FACTURE, "VERSION", IP_ACCES) VALUES (5, 3, 1, 2, 2, 'FOIE GRAS CANARD ENTIER 130G', 150, '', '', 12.33, 5.5, 'V1', 'D', 1512.17, 1621.46, 12.33, 0, 'ADMIN', '2006-09-13 17:27:21', 'ADMIN', '2006-09-13 17:27:21', '1.0.0', '3');

COMMIT WORK;