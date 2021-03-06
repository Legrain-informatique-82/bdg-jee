CREATE or ALTER  PROCEDURE GESTION_ALTER_TA_TIERS
as    
DECLARE VARIABLE QUERY VARCHAR(500);
BEGIN
query ='';
IF (not exists( SELECT a.RDB$FIELD_NAME, a.RDB$RELATION_NAME FROM RDB$RELATION_FIELDS a
where a.RDB$RELATION_NAME = 'TA_TIERS' and  a.RDB$FIELD_NAME ='ID_FAMILLE_TIERS')) THEN
  begin
    query = 'ALTER TABLE TA_TIERS ADD ID_FAMILLE_TIERS DID_FACULTATIF';
    EXECUTE STATEMENT :query;
    
    query='ALTER TABLE TA_TIERS ADD CONSTRAINT FK_TA_TIERS_famille FOREIGN KEY (ID_FAMILLE_TIERS) REFERENCES TA_FAMILLE_TIERS (ID_FAMILLE)';
    EXECUTE STATEMENT :query;
    
    query='alter table TA_TIERS alter VERSION_OBJ position 36';
    EXECUTE STATEMENT :query;
    
  end
END
^
EXECUTE PROCEDURE GESTION_ALTER_TA_TIERS
^
drop procedure GESTION_ALTER_TA_TIERS
^