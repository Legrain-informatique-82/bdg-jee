
package fr.legrain.moncompte.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour taCgPartenaire complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="taCgPartenaire"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actif" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="blobFichier" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="cgPartenaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dateCgPartenaire" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idCgPartenaire" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ipAcces" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quandCree" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="quandModif" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="quiCree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="quiModif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "taCgPartenaire", propOrder = {
    "actif",
    "blobFichier",
    "cgPartenaire",
    "dateCgPartenaire",
    "idCgPartenaire",
    "ipAcces",
    "quandCree",
    "quandModif",
    "quiCree",
    "quiModif",
    "url",
    "versionObj"
})
public class TaCgPartenaire implements Serializable {

    protected Boolean actif;
    protected byte[] blobFichier;
    protected String cgPartenaire;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCgPartenaire;
    protected Integer idCgPartenaire;
    protected String ipAcces;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar quandCree;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar quandModif;
    protected String quiCree;
    protected String quiModif;
    protected String url;
    protected Integer versionObj;

    /**
     * Obtient la valeur de la propriété actif.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActif() {
        return actif;
    }

    /**
     * Définit la valeur de la propriété actif.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActif(Boolean value) {
        this.actif = value;
    }

    /**
     * Obtient la valeur de la propriété blobFichier.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBlobFichier() {
        return blobFichier;
    }

    /**
     * Définit la valeur de la propriété blobFichier.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBlobFichier(byte[] value) {
        this.blobFichier = value;
    }

    /**
     * Obtient la valeur de la propriété cgPartenaire.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCgPartenaire() {
        return cgPartenaire;
    }

    /**
     * Définit la valeur de la propriété cgPartenaire.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCgPartenaire(String value) {
        this.cgPartenaire = value;
    }

    /**
     * Obtient la valeur de la propriété dateCgPartenaire.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCgPartenaire() {
        return dateCgPartenaire;
    }

    /**
     * Définit la valeur de la propriété dateCgPartenaire.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCgPartenaire(XMLGregorianCalendar value) {
        this.dateCgPartenaire = value;
    }

    /**
     * Obtient la valeur de la propriété idCgPartenaire.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdCgPartenaire() {
        return idCgPartenaire;
    }

    /**
     * Définit la valeur de la propriété idCgPartenaire.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdCgPartenaire(Integer value) {
        this.idCgPartenaire = value;
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
     * Obtient la valeur de la propriété url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Définit la valeur de la propriété url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
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
