package fr.legrain.bdg.compteclient.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2018-06-14T14:51:43.229+02:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://service.bdg.legrain.fr/", name = "CompteClientFinalService")
@XmlSeeAlso({ObjectFactory.class})
public interface CompteClientFinalService {

    @WebMethod
    @RequestWrapper(localName = "infosClientChezFournisseur", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.InfosClientChezFournisseur")
    @ResponseWrapper(localName = "infosClientChezFournisseurResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.InfosClientChezFournisseurResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public fr.legrain.bdg.compteclient.ws.TaTiers infosClientChezFournisseur(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "facturesClientChezFournisseur", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FacturesClientChezFournisseur")
    @ResponseWrapper(localName = "facturesClientChezFournisseurResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FacturesClientChezFournisseurResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public java.util.List<fr.legrain.bdg.compteclient.ws.TaFacture> facturesClientChezFournisseur(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "listeFournisseur", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.ListeFournisseur")
    @ResponseWrapper(localName = "listeFournisseurResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.ListeFournisseurResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public java.util.List<fr.legrain.bdg.compteclient.ws.TiersDossier> listeFournisseur();

    @WebMethod
    @RequestWrapper(localName = "init", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.Init")
    @ResponseWrapper(localName = "initResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.InitResponse")
    public void init();

    @WebMethod
    @RequestWrapper(localName = "devisClientChezFournisseur", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.DevisClientChezFournisseur")
    @ResponseWrapper(localName = "devisClientChezFournisseurResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.DevisClientChezFournisseurResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public java.util.List<fr.legrain.bdg.compteclient.ws.TaDevis> devisClientChezFournisseur(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "pdfClient", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PdfClient")
    @ResponseWrapper(localName = "pdfClientResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PdfClientResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public byte[] pdfClient(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "clientExisteChezFournisseur", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.ClientExisteChezFournisseur")
    @ResponseWrapper(localName = "clientExisteChezFournisseurResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.ClientExisteChezFournisseurResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public boolean clientExisteChezFournisseur(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg2
    );

    @WebMethod
    @RequestWrapper(localName = "payerFactureCB", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PayerFactureCB")
    @ResponseWrapper(localName = "payerFactureCBResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PayerFactureCBResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public fr.legrain.bdg.compteclient.ws.RetourPaiementCarteBancaire payerFactureCB(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "http://service.bdg.legrain.fr/")
        fr.legrain.bdg.compteclient.ws.PaiementCarteBancaire arg2,
        @WebParam(name = "arg3", targetNamespace = "http://service.bdg.legrain.fr/")
        fr.legrain.bdg.compteclient.ws.TaFacture arg3
    ) throws EJBException_Exception;

    @WebMethod
    @RequestWrapper(localName = "fournisseurPermetPaiementParCB", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FournisseurPermetPaiementParCB")
    @ResponseWrapper(localName = "fournisseurPermetPaiementParCBResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FournisseurPermetPaiementParCBResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public boolean fournisseurPermetPaiementParCB(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "facturesClientChezFournisseurDate", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FacturesClientChezFournisseurDate")
    @ResponseWrapper(localName = "facturesClientChezFournisseurDateResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.FacturesClientChezFournisseurDateResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public java.util.List<fr.legrain.bdg.compteclient.ws.TaFacture> facturesClientChezFournisseurDate(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "http://service.bdg.legrain.fr/")
        javax.xml.datatype.XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", targetNamespace = "http://service.bdg.legrain.fr/")
        javax.xml.datatype.XMLGregorianCalendar arg3
    );

    @WebMethod
    @RequestWrapper(localName = "pdfFacture", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PdfFacture")
    @ResponseWrapper(localName = "pdfFactureResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.PdfFactureResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public byte[] pdfFacture(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1
    );

    @WebMethod
    @RequestWrapper(localName = "devisClientChezFournisseurDate", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.DevisClientChezFournisseurDate")
    @ResponseWrapper(localName = "devisClientChezFournisseurDateResponse", targetNamespace = "http://service.bdg.legrain.fr/", className = "fr.legrain.bdg.compteclient.ws.DevisClientChezFournisseurDateResponse")
    @WebResult(name = "return", targetNamespace = "http://service.bdg.legrain.fr/")
    public java.util.List<fr.legrain.bdg.compteclient.ws.TaDevis> devisClientChezFournisseurDate(
        @WebParam(name = "arg0", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "http://service.bdg.legrain.fr/")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "http://service.bdg.legrain.fr/")
        javax.xml.datatype.XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", targetNamespace = "http://service.bdg.legrain.fr/")
        javax.xml.datatype.XMLGregorianCalendar arg3
    );
}