CREATE TABLE TA_GEN_CODE (
	ID_GEN_CODE           DID3 NOT NULL,
	ENTITE DLIB255NN,
	FIXE DLIB100,
	EXO DLIB100,
	COMPTEUR DLIB100,
	QUI_CREE     DLIB50,
	QUAND_CREE   TIMESTAMP DEFAULT 'NOW',
	QUI_MODIF    DLIB50,
	QUAND_MODIF  TIMESTAMP DEFAULT 'NOW',
	"VERSION"    NUM_VERSION,
	IP_ACCES     DLIB50NN DEFAULT 0,
	VERSION_OBJ  INTEGER
);

set term ^;
CREATE OR ALTER TRIGGER TBI_TA_GEN_CODE FOR TA_GEN_CODE
ACTIVE BEFORE INSERT POSITION 0
as
begin
   If (New.ID_GEN_CODE is null) Then
      New.ID_GEN_CODE = GEN_ID(NUM_ID_GEN_CODE,1);
   New.QUI_CREE = USER;
   new.QUAND_CREE = 'NOW';
   new.QUI_MODIF = USER;
   new.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

CREATE OR ALTER TRIGGER TBU_TA_GEN_CODE FOR TA_GEN_CODE
ACTIVE BEFORE UPDATE POSITION 1
as
begin
   new.QUI_MODIF = USER;
   new.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

INSERT INTO TA_GEN_CODE (ID_GEN_CODE, ENTITE, FIXE, EXO, COMPTEUR, QUI_CREE, QUAND_CREE, QUI_MODIF, QUAND_MODIF, "VERSION", IP_ACCES, VERSION_OBJ) VALUES ('1', 'TaTiers', NULL, NULL, 'TaTiers a, a.codeTiers,5', 'ADMIN', '21.07.2014, 14:20:08.442', 'ADMIN', '21.07.2014, 14:20:32.529', '2.0.11', '588', '0');
INSERT INTO TA_GEN_CODE (ID_GEN_CODE, ENTITE, FIXE, EXO, COMPTEUR, QUI_CREE, QUAND_CREE, QUI_MODIF, QUAND_MODIF, "VERSION", IP_ACCES, VERSION_OBJ) VALUES ('2', 'TaDevis', 'DE', 'courant', 'TaDevis a, a.codeDocument,5', 'ADMIN', '22.08.2014, 15:19:46.603', 'ADMIN', '22.08.2014, 15:19:46.603', '2.0.13', '856', NULL);
INSERT INTO TA_GEN_CODE (ID_GEN_CODE, ENTITE, FIXE, EXO, COMPTEUR, QUI_CREE, QUAND_CREE, QUI_MODIF, QUAND_MODIF, "VERSION", IP_ACCES, VERSION_OBJ) VALUES ('3', 'TaArticle', 'ART_', 'courant', 'TaArticle a, a.codeArticle,5', 'ADMIN', '22.08.2014, 15:19:46.603', 'ADMIN', '22.08.2014, 15:19:46.603', '2.0.13', '856', NULL);

INSERT INTO TA_GEN_CODE (ID_GEN_CODE, ENTITE, FIXE, EXO, COMPTEUR, VERSION_OBJ) VALUES ('4', 'TaLot', 'L', 'courant', 'TaLot a, a.numlot,5', 0);

