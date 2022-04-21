
package fr.legrain.bdg.ws;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.11
 * 2016-02-04T16:16:21.567+01:00
 * Generated source version: 2.7.11
 */

@WebFault(name = "IOException", targetNamespace = "http://service.general.legrain.fr/")
public class IOException_Exception extends Exception {
    
    private fr.legrain.bdg.ws.IOException ioException;

    public IOException_Exception() {
        super();
    }
    
    public IOException_Exception(String message) {
        super(message);
    }
    
    public IOException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public IOException_Exception(String message, fr.legrain.bdg.ws.IOException ioException) {
        super(message);
        this.ioException = ioException;
    }

    public IOException_Exception(String message, fr.legrain.bdg.ws.IOException ioException, Throwable cause) {
        super(message, cause);
        this.ioException = ioException;
    }

    public fr.legrain.bdg.ws.IOException getFaultInfo() {
        return this.ioException;
    }
}
