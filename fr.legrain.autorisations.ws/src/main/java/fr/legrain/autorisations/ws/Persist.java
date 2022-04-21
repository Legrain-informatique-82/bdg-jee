
package fr.legrain.autorisations.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour persist complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="persist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://service.autorisations.autorisations.legrain.fr/}taAutorisations" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "persist", propOrder = {
    "arg0"
})
public class Persist {

    protected TaAutorisations arg0;

    /**
     * Obtient la valeur de la propriété arg0.
     * 
     * @return
     *     possible object is
     *     {@link TaAutorisations }
     *     
     */
    public TaAutorisations getArg0() {
        return arg0;
    }

    /**
     * Définit la valeur de la propriété arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link TaAutorisations }
     *     
     */
    public void setArg0(TaAutorisations value) {
        this.arg0 = value;
    }

}