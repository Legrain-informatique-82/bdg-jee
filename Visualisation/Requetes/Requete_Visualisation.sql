
INSERT INTO TA_VISUALISATION (ID,CODE_REQUETE,REQUETE,CHAMPS,TITRE_CHAMPS,TAILLE_CHAMPS,MODULE,IMPRESSION,IDENTIFIANT,ID_EDITEUR,ID_PLUGIN,GROUPBY,PROC,PARAM) VALUES (7,'Liste des articles Facture','select V.date_facture,V.code_tiers,V.code_facture,vl.code_article,vl.u1_l_facture,(vl.qte_l_facture),(MTNETHT)as ht,(MTNETTVA)as tva,(MTNETTTC)as ttc from V_l_facture vl,calcul_total_ligne_direct(''facture'',vl.id_l_facture),v_facture V where V.ID_FACTURE=vl.ID_FACTURE and vl.code_article is not null  order by V.date_facture,V.code_tiers,V.code_facture,vl.code_article,vl.u1_l_facture','date_facture;code_tiers;code_facture;code_article;u1_l_facture;qte_l_facture;ht;tva;ttc','date facture;code tiers;code facture;code article;unit�;qt�;ht;tva;ttc','100;100;100;100;100;100;100;100;100','tiers',NULL,'CODE_TIERS','fr.legrain.tiers.editor.EditorTiers','Tiers',NULL,0,NULL);