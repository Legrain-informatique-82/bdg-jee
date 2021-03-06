CREATE TABLE TA_T_UNITE_PERSO (
    ID							DID3 NOT NULL,
    CODE_T_UNITE_PERSO      DLGR_CODE,
	LIBELLE_T_UNITE_PERSO   DLIB255NN,
	LONGUEUR_T_UNITE_PERSO	DID9FACULT,
	HAUTEUR_T_UNITE_PERSO	DID9FACULT,
	LARGEUR_T_UNITE_PERSO 	DID9FACULT,
    NB_UNITE  					DID9FACULT,
    POIDS						DID9FACULT, -- poids du paquet vide
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT 'NOW',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT 'NOW',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ          		INTEGER
);

CREATE GENERATOR NUM_ID_T_UNITE_PERSO;
SET GENERATOR NUM_ID_T_UNITE_PERSO TO 0;

ALTER TABLE TA_T_UNITE_PERSO ADD CONSTRAINT UNQ1_TA_T_UNITE_PERSO UNIQUE (CODE_T_UNITE_PERSO);
ALTER TABLE TA_T_UNITE_PERSO ADD PRIMARY KEY (ID);

CREATE TABLE TA_UNITE_PERSO_ARTICLE (
    ID							DID3 NOT NULL,
    ID_ARTICLE      			DID3,
	ID_T_UNITE_PERSO     	DID_FACULTATIF,
 	CODE					    DLGR_CODE,
	LIBELLE						DLIB255NN,
	NB_UNITE  					DID9FACULT,
	POIDS						DID9FACULT, -- calculé
	PRIX						DID9FACULT, -- calculé
	REDUCTION				 	DID9FACULT,
	LONGUEUR					DID9FACULT, -- saisi ou récupéré à partir du type de UNITE_PERSO
	HAUTEUR						DID9FACULT, -- saisi ou récupéré à partir du type de UNITE_PERSO
	LARGEUR 					DID9FACULT, -- saisi ou récupéré à partir du type de UNITE_PERSO
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT 'NOW',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT 'NOW',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ          		INTEGER
);

CREATE GENERATOR NUM_ID_UNITE_PERSO_ARTICLE;
SET GENERATOR NUM_ID_UNITE_PERSO_ARTICLE TO 0;

ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD PRIMARY KEY (ID);
ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD CONSTRAINT FK_TA_UNITE_PERSO_ARTICLE_1 FOREIGN KEY (ID_ARTICLE) REFERENCES TA_ARTICLE(ID_ARTICLE);
ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD CONSTRAINT FK_TA_UNITE_PERSO_ARTICLE_2 FOREIGN KEY (ID_T_UNITE_PERSO) REFERENCES TA_T_UNITE_PERSO (ID);

CREATE TABLE TA_ARTICLE ( -- modif
	ID_UNITE_PERSO_ARTICLE 	DID_FACULTATIF, -- id UNITE_PERSO par défaut
	LONGUEUR					DID9FACULT,
	HAUTEUR						DID9FACULT,
	LARGEUR 					DID9FACULT,
	POIDS						DID9FACULT,
);
ALTER TABLE TA_ARTICLE ADD CONSTRAINT FK_TA_ARTICLE_COND FOREIGN KEY (ID_UNITE_PERSO_ARTICLE) REFERENCES TA_UNITE_PERSO_ARTICLE(ID);

CREATE TABLE TA_R_PRIX (
    ID							DID3 NOT NULL,
    ID_PRIX      				DID3,
	ID_TIERS     				DID_FACULTATIF,
	ID_T_TARIF	 				DID_FACULTATIF,
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT 'NOW',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT 'NOW',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ          		INTEGER
);

CREATE GENERATOR NUM_ID_R_PRIX;
SET GENERATOR NUM_ID_R_PRIX TO 0;

ALTER TABLE TA_R_PRIX ADD PRIMARY KEY (ID);

ALTER TABLE TA_R_PRIX ADD CONSTRAINT FK_TA_R_PRIX_1 FOREIGN KEY (ID_PRIX) REFERENCES TA_PRIX(ID_PRIX);
ALTER TABLE TA_R_PRIX ADD CONSTRAINT FK_TA_R_PRIX_2 FOREIGN KEY (ID_TIERS) REFERENCES TA_TIERS(ID_TIERS);

-------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------


CREATE or ALTER  PROCEDURE GESTION_CREATION_GENERATOR1
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query = '';
IF (not exists( SELECT a.RDB$GENERATOR_NAME
FROM RDB$GENERATORS a
where a.RDB$GENERATOR_NAME='NUM_ID_T_UNITE_PERSO')) THEN
  begin
    query = 'CREATE GENERATOR NUM_ID_T_UNITE_PERSO;';
    EXECUTE STATEMENT :query;
    
    query = 'SET GENERATOR NUM_ID_T_UNITE_PERSO TO 0;';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_GENERATOR1 
^
drop procedure GESTION_CREATION_GENERATOR1
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_TABLE1
as    
DECLARE VARIABLE QUERY VARCHAR(5000);
BEGIN
query = '';
IF (not exists( SELECT  a.RDB$RELATION_NAME
FROM RDB$RELATIONS a
where a.RDB$RELATION_NAME='TA_T_UNITE_PERSO')) THEN
  begin
    query = 'CREATE TABLE TA_T_UNITE_PERSO(
    ID							DID3 NOT NULL,
    CODE_T_UNITE_PERSO      DLGR_CODE,
	LIBELLE_T_UNITE_PERSO   DLIB255NN,
	LONGUEUR_T_UNITE_PERSO	DID9FACULT,
	HAUTEUR_T_UNITE_PERSO	DID9FACULT,
	LARGEUR_T_UNITE_PERSO 	DID9FACULT,
    NB_UNITE  					DID9FACULT,
    POIDS						DID9FACULT, 
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT ''NOW'',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT ''NOW'',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ          		INTEGER
    )';
    EXECUTE STATEMENT :query;
    
    query = 'ALTER TABLE TA_T_UNITE_PERSO ADD PRIMARY KEY (ID)';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_T_UNITE_PERSO ADD CONSTRAINT UNQ1_TA_T_UNITE_PERSO UNIQUE (CODE_T_UNITE_PERSO)';
    EXECUTE STATEMENT :query;
    
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_TABLE1
^
drop procedure GESTION_CREATION_TABLE1
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_GENERATOR2
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query = '';
IF (not exists( SELECT a.RDB$GENERATOR_NAME
FROM RDB$GENERATORS a
where a.RDB$GENERATOR_NAME='NUM_ID_UNITE_PERSO_ARTICLE')) THEN
  begin
    query = 'CREATE GENERATOR NUM_ID_UNITE_PERSO_ARTICLE;';
    EXECUTE STATEMENT :query;
    
    query = 'SET GENERATOR NUM_ID_UNITE_PERSO_ARTICLE TO 0;';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_GENERATOR2
^
drop procedure GESTION_CREATION_GENERATOR2
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_TABLE2
as    
DECLARE VARIABLE QUERY VARCHAR(5000);
BEGIN
query = '';
IF (not exists( SELECT  a.RDB$RELATION_NAME
FROM RDB$RELATIONS a
where a.RDB$RELATION_NAME='TA_UNITE_PERSO_ARTICLE')) THEN
  begin
    query = 'CREATE TABLE TA_UNITE_PERSO_ARTICLE (
    ID							DID3 NOT NULL,
    ID_ARTICLE      			DID3,
	ID_T_UNITE_PERSO     	DID_FACULTATIF,
 	CODE					    DLGR_CODE,
	LIBELLE						DLIB255NN,
	NB_UNITE  					DID9FACULT,
	POIDS						DID9FACULT, 
	PRIX						DID9FACULT,
	REDUCTION				 	DID9FACULT,
	LONGUEUR					DID9FACULT,
	HAUTEUR						DID9FACULT,
	LARGEUR 					DID9FACULT,
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT ''NOW'',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT ''NOW'',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ          		INTEGER
    )';
    EXECUTE STATEMENT :query;
    
    query = 'ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD PRIMARY KEY (ID)';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD CONSTRAINT FK_TA_UNITE_PERSO_ARTICLE_1 FOREIGN KEY (ID_ARTICLE) REFERENCES TA_ARTICLE(ID_ARTICLE)';
    EXECUTE STATEMENT :query;
    
    query = 'ALTER TABLE TA_UNITE_PERSO_ARTICLE ADD CONSTRAINT FK_TA_UNITE_PERSO_ARTICLE_2 FOREIGN KEY (ID_T_UNITE_PERSO) REFERENCES TA_T_UNITE_PERSO (ID)';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_TABLE2
^
drop procedure GESTION_CREATION_TABLE2
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_GENERATOR3
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query = '';
IF (not exists( SELECT a.RDB$GENERATOR_NAME
FROM RDB$GENERATORS a
where a.RDB$GENERATOR_NAME='NUM_ID_R_PRIX')) THEN
  begin
    query = 'CREATE GENERATOR NUM_ID_R_PRIX;';
    EXECUTE STATEMENT :query;
    
    query = 'SET GENERATOR NUM_ID_R_PRIX TO 0;';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_GENERATOR3
^
drop procedure GESTION_CREATION_GENERATOR3
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_TABLE3
as    
DECLARE VARIABLE QUERY VARCHAR(5000);
BEGIN
query = '';
IF (not exists( SELECT  a.RDB$RELATION_NAME
FROM RDB$RELATIONS a
where a.RDB$RELATION_NAME='TA_R_PRIX')) THEN
  begin
    query = 'CREATE TABLE TA_R_PRIX(
    ID							DID3 NOT NULL,
    ID_PRIX      				DID3,
	ID_TIERS     				DID_FACULTATIF,
	ID_T_TARIF	 				DID_FACULTATIF,
    QUI_CREE     				DLIB50,
    QUAND_CREE  				TIMESTAMP DEFAULT ''NOW'',
    QUI_MODIF    				DLIB50,
    QUAND_MODIF  				TIMESTAMP DEFAULT ''NOW'',
    "VERSION"            		NUM_VERSION,
    IP_ACCES             		DLIB50NN DEFAULT 0,
    VERSION_OBJ					INTEGER
    )';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_R_PRIX ADD PRIMARY KEY (ID)';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_R_PRIX ADD CONSTRAINT FK_TA_R_PRIX_1 FOREIGN KEY (ID_PRIX) REFERENCES TA_PRIX(ID_PRIX)';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_R_PRIX ADD CONSTRAINT FK_TA_R_PRIX_2 FOREIGN KEY (ID_TIERS) REFERENCES TA_TIERS(ID_TIERS)';
    EXECUTE STATEMENT :query;
    
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_TABLE3
^
drop procedure GESTION_CREATION_TABLE3
^

CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE1
as    
DECLARE VARIABLE QUERY VARCHAR(500);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_ARTICLE' and  a.RDB$FIELD_NAME ='ID_UNITE_PERSO_ARTICLE')) THEN
  begin
    query = 'ALTER TABLE TA_ARTICLE ADD ID_UNITE_PERSO_ARTICLE DID_FACULTATIF';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_ARTICLE ADD LONGUEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_ARTICLE ADD HAUTEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_ARTICLE ADD LARGEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_ARTICLE ADD POIDS DID9FACULT';
    EXECUTE STATEMENT :query;
    
    query='ALTER TABLE TA_ARTICLE ADD CONSTRAINT FK_TA_ARTICLE_COND FOREIGN KEY (ID_UNITE_PERSO_ARTICLE) REFERENCES TA_UNITE_PERSO_ARTICLE(ID)';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE1
^
drop procedure GESTION_ALTER_TABLE1
^

create or alter TRIGGER TBI_UNITE_PERSO_ARTICLE FOR TA_UNITE_PERSO_ARTICLE ACTIVE
BEFORE INSERT POSITION 1
as
begin
   If (New.ID  is null) Then
      New.ID  = GEN_ID(NUM_ID_UNITE_PERSO_ARTICLE,1);
   New.QUI_CREE  = USER;
   New.QUAND_CREE = 'NOW';
   New.QUI_MODIF = USER;
   New.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

create or alter TRIGGER TBU_UNITE_PERSO_ARTICLE FOR TA_UNITE_PERSO_ARTICLE ACTIVE
BEFORE UPDATE POSITION 1
as
begin
   new.QUI_MODIF = USER;
   new.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

create or alter TRIGGER TBI_T_UNITE_PERSO FOR TA_T_UNITE_PERSO ACTIVE
BEFORE INSERT POSITION 1
as
begin
   If (New.ID  is null) Then
      New.ID  = GEN_ID(NUM_ID_T_UNITE_PERSO,1);
   New.QUI_CREE  = USER;
   New.QUAND_CREE = 'NOW';
   New.QUI_MODIF = USER;
   New.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

create or alter TRIGGER TBU_T_UNITE_PERSO FOR TA_T_UNITE_PERSO ACTIVE
BEFORE UPDATE POSITION 1
as
begin
   new.QUI_MODIF = USER;
   new.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

create or alter TRIGGER TBI_R_PRIX FOR TA_R_PRIX ACTIVE
BEFORE INSERT POSITION 1
as
begin
   If (New.ID  is null) Then
      New.ID  = GEN_ID(NUM_ID_R_PRIX,1);
   New.QUI_CREE  = USER;
   New.QUAND_CREE = 'NOW';
   New.QUI_MODIF = USER;
   New.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

create or alter TRIGGER TBU_R_PRIX FOR TA_R_PRIX ACTIVE
BEFORE UPDATE POSITION 1
as
begin
   new.QUI_MODIF = USER;
   new.QUAND_MODIF = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^

/***************************************************************************************************************************/
CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE2
as    
DECLARE VARIABLE QUERY VARCHAR(500);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_T_TARIF' and  a.RDB$FIELD_NAME ='SENS')) THEN
  begin
    query = 'ALTER TABLE TA_T_TARIF ADD SENS DBOOL DEFAULT 0';
    EXECUTE STATEMENT :query;
  end

IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_T_TARIF' and  a.RDB$FIELD_NAME ='POURCENTAGE')) THEN
  begin
    query = 'ALTER TABLE TA_T_TARIF ADD POURCENTAGE DBOOL DEFAULT 0';
    EXECUTE STATEMENT :query;
  end

IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_T_TARIF' and  a.RDB$FIELD_NAME ='VALEUR')) THEN
  begin
    query = 'ALTER TABLE TA_T_TARIF ADD VALEUR DID9FACULT';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE2
^
drop procedure GESTION_ALTER_TABLE2
^
/*
CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE3
as    
DECLARE VARIABLE QUERY VARCHAR(500);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_TIERS' and  a.RDB$FIELD_NAME ='ID_T_TARIF')) THEN
  begin
    query = 'ALTER TABLE TA_TIERS ADD ID_T_TARIF DID_FACULTATIF';
    EXECUTE STATEMENT :query;
    
    query='ALTER TABLE TA_TIERS ADD CONSTRAINT FK_TA_T_TARIF FOREIGN KEY (ID_T_TARIF) REFERENCES TA_T_TARIF (ID_T_TARIF)';
    EXECUTE STATEMENT :query;
  end

END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE3
^
drop procedure GESTION_ALTER_TABLE3
^
*/

/*****************************************************************************************************************************/
CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE4
as    
DECLARE VARIABLE QUERY VARCHAR(500);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_UNITE' and  a.RDB$FIELD_NAME ='LONGUEUR')) THEN
  begin
    query = 'ALTER TABLE TA_UNITE ADD LONGUEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_UNITE ADD HAUTEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_UNITE ADD LARGEUR DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_UNITE ADD POIDS DID9FACULT';
    EXECUTE STATEMENT :query;

    query = 'ALTER TABLE TA_UNITE ADD NB_UNITE DID9FACULT';
    EXECUTE STATEMENT :query;

    --query = 'ALTER TABLE TA_UNITE ADD ID_ARTICLE DID_FACULTATIF';
    --EXECUTE STATEMENT :query;
    
    --query='ALTER TABLE TA_UNITE ADD CONSTRAINT FK_TA_UNITE_2 FOREIGN KEY (ID_ARTICLE) REFERENCES TA_ARTICLE(ID_ARTICLE)';
    --EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE4
^
drop procedure GESTION_ALTER_TABLE4
^

/*****************************************************************************************************************************/
alter table TA_UNITE add constraint UNQ_TA_UNITE_1 unique (CODE_UNITE, ID_ARTICLE)

ALTER TABLE TA_UNITE DROP CONSTRAINT UNQ1_TA_UNITE

