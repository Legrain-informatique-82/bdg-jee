CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE1
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_VISUALISATION' and  a.RDB$FIELD_NAME ='PROC')) THEN
  begin
    query = 'ALTER TABLE TA_VISUALISATION  ADD PROC DBOOL';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE1
^
drop procedure GESTION_ALTER_TABLE1
^

CREATE or ALTER  PROCEDURE GESTION_ALTER_TABLE2
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_VISUALISATION' and  a.RDB$FIELD_NAME ='PARAM')) THEN
  begin
    query = 'ALTER TABLE TA_VISUALISATION  ADD PARAM DLIB255';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TABLE2
^
drop procedure GESTION_ALTER_TABLE2
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_DOMAINE1
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query = '';
IF (not exists( select RDB$FIELD_NAME from RDB$FIELDS where RDB$FIELD_NAME = 'DLIB_MOUV')) THEN
query = 'CREATE DOMAIN DLIB_MOUV AS
VARCHAR(1)
NOT NULL
CHECK ((VALUE = ''E'') or  (VALUE = ''S''))';

IF (:query<>'')then EXECUTE STATEMENT :query;

END
^

EXECUTE PROCEDURE GESTION_CREATION_DOMAINE1
^
drop procedure GESTION_CREATION_DOMAINE1
^

CREATE or ALTER  PROCEDURE GESTION_CREATION_GENERATOR1
as    
DECLARE VARIABLE QUERY VARCHAR(255);
BEGIN
query = '';
IF (not exists( SELECT a.RDB$GENERATOR_NAME
FROM RDB$GENERATORS a
where a.RDB$GENERATOR_NAME='NUM_ID_STOCK')) THEN
  begin
    query = 'CREATE GENERATOR NUM_ID_STOCK;';
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
where a.RDB$RELATION_NAME='TA_STOCK')) THEN
  begin
    query = 'CREATE TABLE TA_STOCK (
    ID_STOCK           DID3 NOT NULL ,
    ID_ARTICLE_STOCK   DID3 ,
    MOUV_STOCK         DLIB_MOUV DEFAULT ''E'' ,
    DATE_STOCK         TIMESTAMP,
    LIBELLE_STOCK      DLIB255 ,
    QTE1_STOCK         NUMERIC(15,2),
    UN1_STOCK           DLGR_CODEL ,
    QTE2_STOCK         NUMERIC(15,2),
    UN2_STOCK           DLGR_CODEL ,
    QUI_CREE_STOCK     DLIB50 ,
    QUAND_CREE_STOCK   TIMESTAMP DEFAULT ''NOW'',
    QUI_MODIF_STOCK    DLIB50 ,
    QUAND_MODIF_STOCK  TIMESTAMP DEFAULT ''NOW'',
    "VERSION"          NUM_VERSION,
    IP_ACCES           DLIB50NN DEFAULT 0 
)';
    EXECUTE STATEMENT :query;
    
query = 'ALTER TABLE TA_STOCK ADD PRIMARY KEY (ID_STOCK)';
    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_TABLE1 
^
drop procedure GESTION_CREATION_TABLE1
^




/* Trigger: TBIID_ARTICLE_STOCK */
CREATE or alter TRIGGER TBIID_ARTICLE_STOCK FOR TA_STOCK
ACTIVE BEFORE INSERT POSITION 1
as
DECLARE variable numrows integer;
begin
  /* XAKID_ARTICLE */
   if (NEW.ID_ARTICLE_STOCK is not null) then
       begin
       select count(*) from TA_ARTICLE
       where ID_ARTICLE = NEW.ID_ARTICLE_STOCK
       into :numrows;
       if (numrows = 0) then
          exception E_XAKID_ARTICLE;
       end   
end
^



/* Trigger: TBI_STOCK */
CREATE or alter TRIGGER TBI_STOCK FOR TA_STOCK
ACTIVE BEFORE INSERT POSITION 1
as
begin
   If (New.ID_STOCK  is null) Then
      New.ID_STOCK  = GEN_ID(NUM_ID_STOCK,1);
   New.QUI_CREE_STOCK  = USER;
   New.QUAND_CREE_STOCK = 'NOW';
   New.QUI_MODIF_STOCK = USER;
   New.QUAND_MODIF_STOCK = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^


/* Trigger: TBUID_ARTICLE_STOCK */
CREATE or alter TRIGGER TBUID_ARTICLE_STOCK FOR TA_STOCK
ACTIVE BEFORE UPDATE POSITION 1
as
DECLARE variable numrows integer;
begin
  /* XAKID_ARTICLE */
   if ((OLD.ID_ARTICLE_STOCK <> NEW.ID_ARTICLE_STOCK)) then
   begin
     select count(*) from TA_ARTICLE
     where ID_ARTICLE = NEW.ID_ARTICLE_STOCK
     into :numrows;
     if (numrows = 0) then
        exception E_XAKID_ARTICLE;
   end
end
^

/* Trigger: TBU_STOCK */
CREATE or alter TRIGGER TBU_STOCK FOR TA_STOCK
ACTIVE BEFORE UPDATE POSITION 1
as
begin
   new.QUI_MODIF_STOCK = USER;
   new.QUAND_MODIF_STOCK = 'NOW';
   new.IP_ACCES = current_connection;
   select num_version from ta_version into new."VERSION";
end
^




CREATE or ALTER  PROCEDURE GESTION_CREATION_TABLE2
as    
DECLARE VARIABLE QUERY VARCHAR(5000);
BEGIN
query = '';
IF (not exists( SELECT  a.RDB$RELATION_NAME
FROM RDB$RELATIONS a
where a.RDB$RELATION_NAME='TA_REPORT_STOCK')) THEN
  begin
    query = 'CREATE TABLE TA_REPORT_STOCK (
    ID_REPORT_STOCK           DID3 NOT NULL /* DID3 = INTEGER NOT NULL */,
    ID_ARTICLE_REPORT_STOCK   DID3 /* DID3 = INTEGER NOT NULL */,
    DATE_DEB_REPORT_STOCK     TIMESTAMP,
    DATE_FIN_REPORT_STOCK     TIMESTAMP,
    QTE_REPORT_STOCK          NUMERIC(15,2),
    UNITE_REPORT_STOCK        DLGR_CODEL /* DLGR_CODEL = VARCHAR(20) */,
    QUI_CREE_REPORT_STOCK     DLIB50 /* DLIB50 = VARCHAR(50) */,
    QUAND_CREE_REPORT_STOCK   TIMESTAMP DEFAULT ''NOW'',
    QUI_MODIF_REPORT_STOCK    DLIB50 /* DLIB50 = VARCHAR(50) */,
    QUAND_MODIF_REPORT_STOCK  TIMESTAMP DEFAULT ''NOW'',
    "VERSION"                 NUM_VERSION /* NUM_VERSION = VARCHAR(20) */,
    IP_ACCES                  DLIB50NN DEFAULT 0 /* DLIB50NN = VARCHAR(50) NOT NULL */
)';
  EXECUTE STATEMENT :query;
query = 'ALTER TABLE TA_REPORT_STOCK ADD PRIMARY KEY (ID_REPORT_STOCK)';
  EXECUTE STATEMENT :query;
query = 'CREATE GENERATOR NUM_ID_REPORT_STOCK';

    EXECUTE STATEMENT :query;
  end
END
^
EXECUTE PROCEDURE GESTION_CREATION_TABLE2 
^
drop procedure GESTION_CREATION_TABLE2
^


