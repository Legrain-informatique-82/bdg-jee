<%@page import="java.net.URLEncoder"%>

<html>
	<head>
		<link rel="shortcut icon" type="image/gif" href="legrain.gif" />
	</head>
	<body>
		<!-- 
			<a href="http://213.186.38.48:8080/birt/frameset?__report=/report/analyse.rptdesign&data=/var/tomcat/apache-tomcat-5.5.23/webapps/birt/report/format_donnees_edition.xml&__format=pdf">test edition normal</a>
		-->

		<%! 
			String url = null; 
			String urlPdf = null;
			final String debutUrl = "http://213.186.38.48:8080/birt/report";
		%>

		<hr/>
		<ul>
			<li>
				<a href="<%= debutUrl %>/analyseEco.xml">Fichier venant de la comptabilit�</a>
			</li>
			<li>
				<a href="<%= debutUrl %>/format_donnees_edition.xml">Fichier repr�sentant l'analyse �conomique</a>
			</li>
			<li>
				<a href="<%= debutUrl %>/analyse.rptdesign">Mod�le de l'�dition</a>
			</li>
		</ul>

		<%
			String rptDesignPath = URLEncoder.encode("/report/analyse.rptdesign", "UTF-8");
			String xmlDataSourcePath = URLEncoder.encode("/var/tomcat/apache-tomcat-5.5.23/webapps/birt/report/format_donnees_edition.xml", "UTF-8");
			url = "http://213.186.38.48:8080/birt/frameset?__report="+rptDesignPath+"&data="+xmlDataSourcePath;
			urlPdf = url+"&__format=pdf";
		%>
		<hr/>
		<ul>
			<li>
				<a href="<%= url %>">Exemple d'�dition en HTML pagin� (web viewer)</a>
			</li>
			<li>
				<a href="<%= urlPdf %>">Exemple d'�dition en PDF</a>
			</li>
		</ul>
		<hr/>

	</body>
</html>
