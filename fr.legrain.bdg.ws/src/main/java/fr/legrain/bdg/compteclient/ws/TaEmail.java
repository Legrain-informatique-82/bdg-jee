
package fr.legrain.bdg.compteclient.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * <p>Classe Java pour taEmail complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="taEmail"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="adresseEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="commAdministratifEmail" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="commCommercialEmail" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="idEmail" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="idImport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ipAcces" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="origineImport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quandCree" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="quandModif" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="quiCree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quiModif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="taTEmail" type="{http://service.bdg.legrain.fr/}taTEmail" minOccurs="0"/&gt;
 *         &lt;element name="taTiers" type="{http://service.bdg.legrain.fr/}taTiers" minOccurs="0"/&gt;
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="versionObj" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taEmail", propOrder = {
    "adresseEmail",
    "commAdministratifEmail",
    "commCommercialEmail",
    "idEmail",
    "idImport",
    "ipAcces",
    "origineImport",
    "quandCree",
    "quandModif",
    "quiCree",
    "quiModif",
    "taTEmail",
    "taTiers",
    "version",
    "versionObj"
})
public class TaEmail {

    protected String adresseEmail;
    protected Integer commAdministratifEmail;
    protected Integer commCommercialEmail;
    protected int idEmail;
    protected String idImport;
    protected String ipAcces;
    protected String origineImport;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar quandCree;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar quandModif;
    protected String quiCree;
    protected String quiModif;
    protected TaTEmail taTEmail;
    @XmlInverseReference(mappedBy = "taEmails")
    @XmlElement
    protected TaTiers taTiers;
    protected String version;
    protected Integer versionObj;

    /**
     * Obtient la valeur de la propriété adresseEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresseEmail() {
        return adresseEmail;
    }

    /**
     * Définit la valeur de la propriété adresseEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresseEmail(String value) {
        this.adresseEmail = value;
    }

    /**
     * Obtient la valeur de la propriété commAdministratifEmail.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCommAdministratifEmail() {
        return commAdministratifEmail;
    }

    /**
     * Définit la valeur de la propriété commAdministratifEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCommAdministratifEmail(Integer value) {
        this.commAdministratifEmail = value;
    }

    /**
     * Obtient la valeur de la propriété commCommercialEmail.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCommCommercialEmail() {
        return commCommercialEmail;
    }

    /**
     * Définit la valeur de la propriété commCommercialEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCommCommercialEmail(Integer value) {
        this.commCommercialEmail = value;
    }

    /**
     * Obtient la valeur de la propriété idEmail.
     * 
     */
    public int getIdEmail() {
        return idEmail;
    }

    /**
     * Définit la valeur de la propriété idEmail.
     * 
     */
    public void setIdEmail(int value) {
        this.idEmail = value;
    }

    /**
     * Obtient la valeur de la propriété idImport.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdImport() {
        return idImport;
    }

    /**
     * Définit la valeur de la propriété idImport.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdImport(String value) {
        this.idImport = value;
    }

    /**
     * Obtient la valeur de la propriété ipAcces.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAcces() {
        return ipAcces;
    }

    /**
     * Définit la valeur de la propriété ipAcces.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAcces(String value) {
        this.ipAcces = value;
    }

    /**
     * Obtient la valeur de la propriété origineImport.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigineImport() {
        return origineImport;
    }

    /**
     * Définit la valeur de la propriété origineImport.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigineImport(String value) {
        this.origineImport = value;
    }

    /**
     * Obtient la valeur de la propriété quandCree.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getQuandCree() {
        return quandCree;
    }

    /**
     * Définit la valeur de la propriété quandCree.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setQuandCree(XMLGregorianCalendar value) {
        this.quandCree = value;
    }

    /**
     * Obtient la valeur de la propriété quandModif.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getQuandModif() {
        return quandModif;
    }

    /**
     * Définit la valeur de la propriété quandModif.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setQuandModif(XMLGregorianCalendar value) {
        this.quandModif = value;
    }

    /**
     * Obtient la valeur de la propriété quiCree.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuiCree() {
        return quiCree;
    }

    /**
     * Définit la valeur de la propriété quiCree.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuiCree(String value) {
        this.quiCree = value;
    }

    /**
     * Obtient la valeur de la propriété quiModif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuiModif() {
        return quiModif;
    }

    /**
     * Définit la valeur de la propriété quiModif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuiModif(String value) {
        this.quiModif = value;
    }

    /**
     * Obtient la valeur de la propriété taTEmail.
     * 
     * @return
     *     possible object is
     *     {@link TaTEmail }
     *     
     */
    public TaTEmail getTaTEmail() {
        return taTEmail;
    }

    /**
     * Définit la valeur de la propriété taTEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link TaTEmail }
     *     
     */
    public void setTaTEmail(TaTEmail value) {
        this.taTEmail = value;
    }

    /**
     * Obtient la valeur de la propriété taTiers.
     * 
     * @return
     *     possible object is
     *     {@link TaTiers }
     *     
     */
    public TaTiers getTaTiers() {
        return taTiers;
    }

    /**
     * Définit la valeur de la propriété taTiers.
     * 
     * @param value
     *     allowed object is
     *     {@link TaTiers }
     *     
     */
    public void setTaTiers(TaTiers value) {
        this.taTiers = value;
    }

    /**
     * Obtient la valeur de la propriété version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Définit la valeur de la propriété version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Obtient la valeur de la propriété versionObj.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVersionObj() {
        return versionObj;
    }

    /**
     * Définit la valeur de la propriété versionObj.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVersionObj(Integer value) {
        this.versionObj = value;
    }

}
