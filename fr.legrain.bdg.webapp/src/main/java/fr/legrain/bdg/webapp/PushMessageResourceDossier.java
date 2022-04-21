package fr.legrain.bdg.webapp;

import javax.websocket.OnMessage;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

//import org.primefaces.push.annotation.OnMessage;
//import org.primefaces.push.annotation.PathParam;
//import org.primefaces.push.annotation.PushEndpoint;
//import org.primefaces.push.impl.JSONEncoder;

@ServerEndpoint(value="/notifications/{dossier}"/*, encoders = {JSONEncoder.class}*/)
public class PushMessageResourceDossier {
	
//    @OnMessage(encoders = {JSONEncoder.class})
//    public String onMessage(String message) {
//    	System.out.println("PushMessageResource : "+message);
//        return message;
//    }
	
    
    
    @OnMessage/*(encoders = {JSONEncoder.class})*/
    public String onMessage(@PathParam("dossier") String dossier, String message) {
        System.out.println("onMessage - dossier");
        return message;
    }
}