package fr.legrain.gwihostingfr.api.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-02-24T11:51:42.686+01:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://api.gwi-hosting.fr/api/soap/gandi", name = "GandiServicePort")
@XmlSeeAlso({ObjectFactory.class})
public interface GandiServicePort {

    /**
     * Supprime les DNS pour un sous domaine donné
     */
    @WebResult(name = "deleteDNSSubdomainResult", targetNamespace = "")
    @RequestWrapper(localName = "deleteDNSSubdomain", targetNamespace = "http://api.gwi-hosting.fr/api/soap/gandi", className = "fr.legrain.gwihostingfr.api.ws.DeleteDNSSubdomain")
    @WebMethod(action = "http://api.gwi-hosting.fr/api/soap/gandi#deleteDNSSubdomain")
    @ResponseWrapper(localName = "deleteDNSSubdomainResponse", targetNamespace = "http://api.gwi-hosting.fr/api/soap/gandi", className = "fr.legrain.gwihostingfr.api.ws.DeleteDNSSubdomainResponse")
    public boolean deleteDNSSubdomain(
        @WebParam(name = "username", targetNamespace = "")
        java.lang.String username,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "domain", targetNamespace = "")
        java.lang.String domain,
        @WebParam(name = "subdomain", targetNamespace = "")
        java.lang.String subdomain
    );

    /**
     * Ajoute une ligne de DNS pour un sous domaine donné
     */
    @WebResult(name = "registerDNSSubDomainResult", targetNamespace = "")
    @RequestWrapper(localName = "registerDNSSubDomain", targetNamespace = "http://api.gwi-hosting.fr/api/soap/gandi", className = "fr.legrain.gwihostingfr.api.ws.RegisterDNSSubDomain")
    @WebMethod(action = "http://api.gwi-hosting.fr/api/soap/gandi#registerDNSSubDomain")
    @ResponseWrapper(localName = "registerDNSSubDomainResponse", targetNamespace = "http://api.gwi-hosting.fr/api/soap/gandi", className = "fr.legrain.gwihostingfr.api.ws.RegisterDNSSubDomainResponse")
    public boolean registerDNSSubDomain(
        @WebParam(name = "username", targetNamespace = "")
        java.lang.String username,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "domain", targetNamespace = "")
        java.lang.String domain,
        @WebParam(name = "subdomain", targetNamespace = "")
        java.lang.String subdomain,
        @WebParam(name = "ip", targetNamespace = "")
        java.lang.String ip
    );
}
