package fr.legrain.document.events;


import java.util.EventListener;


public interface SWTModificationDocumentListener extends EventListener {
	
	public void modificationDocument(SWTModificationDocumentEvent evt) throws Exception;
	
}