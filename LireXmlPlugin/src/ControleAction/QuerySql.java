package ControleAction;

import Interface.ConstProjet;

public class QuerySql {
	
	public static final String CREATE_TA_FEATURE = "CREATE TABLE TA_LIST_FEATURES(" +
			"ID_FEATURE INTEGER NOT NULL,NOM_FEATURE VARCHAR(200),LABEL_FEATURE VARCHAR(200),VERSION_FEATURE VARCHAR(150)," +
			"PROVIDER_NAME VARCHAR(50),OS VARCHAR(50),WS VARCHAR(50),NL VARCHAR(50),ARCH VARCHAR(50),DESCRIPTION_URL VARCHAR(200)," +
			"COPYRIGHT_URL VARCHAR(200),LICENSE_URL VARCHAR(200),UPDATE_LABEL VARCHAR(50),UPDATE_URL VARCHAR(200)," +
			"TYPE_FEATURE VARCHAR(20))";
	
//	public static final String CREATE_TA_PLUGINS = "CREATE TABLE TA_LIST_PLUGINS(" +
//			"ID_PLUGIN INTEGER NOT NULL,NOM_PLUGIN VARCHAR(200),VERSION_PLUGIN " +
//			"VARCHAR(100))";
	public static final String CREATE_TA_PLUGINS = "CREATE TABLE TA_LIST_PLUGINS(" +
	"ID_PLUGIN INTEGER NOT NULL,NOM_PLUGIN VARCHAR(200),VERSION_PLUGIN " +
	"VARCHAR(100),OS VARCHAR(20),WS VARCHAR(20),NL VARCHAR(20),ARCH VARCHAR(20))";
	
//	public static final String CREATE_TA_DEP_FEATURE = "CREATE TABLE TA_DEP_FEATURE(" +
//			"ID INTEGER NOT NULL,ID_FEATURE_BASE INTEGER,ID_FEATURE_DEP " +
//			"INTEGER)";
	public static final String CREATE_TA_DEP_FEATURE = "CREATE TABLE TA_DEP_FEATURE(" +
	"ID INTEGER NOT NULL,NOM_FEATURE_BASE VARCHAR(200),NOM_FEATURE_DEP " +
	"VARCHAR(200))";
	
//	public static final String CREATE_TA_DEP_FEATURES_PLUGINS = "CREATE TABLE TA_DEP_FEATURES_PLUGINS" +
//			"(ID INTEGER NOT NULL,ID_FEATURE INTEGER,ID_PLUGIN INTEGER)";
	public static final String CREATE_TA_DEP_FEATURES_PLUGINS = "CREATE TABLE TA_DEP_FEATURES_PLUGINS" +
	"(ID INTEGER NOT NULL,NOM_FEATURE VARCHAR(200),NOM_PLUGIN VARCHAR(200))";
	
	
	public static String SqlCreateTables(String Name_Table){
		
		String SqlCreateTable = null;
		if(Name_Table.equalsIgnoreCase(ConstProjet.TA_LIST_FEATURES)){
			SqlCreateTable = CREATE_TA_FEATURE;
		}else if(Name_Table.equalsIgnoreCase(ConstProjet.TA_LIST_PLUGINS)){
			SqlCreateTable = CREATE_TA_PLUGINS;
		}else if(Name_Table.equalsIgnoreCase(ConstProjet.TA_DEP_FEATURE)){
			SqlCreateTable = CREATE_TA_DEP_FEATURE;
		}else if(Name_Table.equalsIgnoreCase(ConstProjet.TA_DEP_FEATURES_PLUGINS)){
			SqlCreateTable = CREATE_TA_DEP_FEATURES_PLUGINS;
		}else{
			
		}
		return SqlCreateTable;
	}
	
	public String INSERT_TA_FEATRUES = "INSERT INTO "+ConstProjet.TA_LIST_FEATURES+"(";
	public String INSERT_TA_PLUGIN = "INSERT INTO "+ConstProjet.TA_LIST_PLUGINS+"(";
	public String INSERT_TA_DEP_FEATURE = "INSERT INTO "+ConstProjet.TA_DEP_FEATURE+"(";
	public String INSERT_TA_DEP_FEATURES_PLUGINS = "INSERT INTO "+ConstProjet.TA_DEP_FEATURES_PLUGINS+"(";
	
	public static final String VALUES = ") VALUES (";
	
	
}