package fr.legrain.prelevement.exportation.divers.pt.sibace.sepa;

import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.CustomerDirectDebitInitiationV02;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.Document;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.GenericPersonIdentification1;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.GroupHeader39;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.ObjectFactory;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.Party6Choice;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.PartyIdentification32;
import fr.legrain.prelevement.exportation.divers.iso.std.iso._20022.tech.xsd.pain_008_001_02.PersonIdentification5;

//import fr.legrain.prelevement.exportation.divers.pt.sibace.sepa.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



/**
 * Helper class to build and save a SEPA Direct Debit document, using the pain.008.001.02 xml file format, with conditionals specified for operations in Portugal.
 * 
 * Iso classes were created using the xjc compiler and the pain.008.001.02.xsd schema available at the ISO 20022 web site (www.iso20022.org)
 *  
 * @author "Joao Osorio <joao.osorio@sibace.pt>"
 *
 */

public class DirectDebit {
	
	private Document document;
	
	/**
	 * Initialize a SEPA Direct Debit Document
	 * Sets GroupHeader with supplied arguments.
	 */
	public DirectDebit(String msgId, String companyName, String companyId){
		// Initialize document
		document = new Document();
		
		// Initialize message root
		document.setCstmrDrctDbtInitn(new CustomerDirectDebitInitiationV02());
		
		// Create group header
		GroupHeader39 groupHeader = new GroupHeader39();
		
		// Set message id
		groupHeader.setMsgId(msgId);
		
		// Set date
//		SepaUtils.ISODateTime(new Date());
		groupHeader.setCreDtTm(SepaUtils.ISODateTime(new Date())); //<--
		
	    // Set number of transactions
		groupHeader.setNbOfTxs("0");
		
		// Set control Sum
		groupHeader.setCtrlSum(BigDecimal.ZERO);
		
		// Set id based party identification
		GenericPersonIdentification1 other = new GenericPersonIdentification1();
//		other.setId(companyId);
		PersonIdentification5 prvtId = new PersonIdentification5();
//		prvtId.getOthr().add(other);
		Party6Choice partyId = new Party6Choice();
//		partyId.setPrvtId(prvtId);
		PartyIdentification32 party = new PartyIdentification32();
//		party.setId(partyId);
		
		// Set company name		
		party.setNm(companyName);
		
		// Set party identification
		groupHeader.setInitgPty(party);
		
		// Add group header to document
		document.getCstmrDrctDbtInitn().setGrpHdr(groupHeader);		
	}
	
	public void addPaymentGroup(DirectDebitPaymentGroup paymentGroup){
	
		// Add group do document
		document.getCstmrDrctDbtInitn().getPmtInf().add(paymentGroup.getInformation());
		
		// Update number of transactions in document's group header
		int gpCount = Integer.parseInt(document.getCstmrDrctDbtInitn().getGrpHdr().getNbOfTxs());
		int pmtCount = Integer.parseInt(paymentGroup.getInformation().getNbOfTxs());
		document.getCstmrDrctDbtInitn().getGrpHdr().setNbOfTxs(Integer.toString(gpCount+pmtCount));
		
		// Update control sum in document's group header
		BigDecimal newSum = document.getCstmrDrctDbtInitn().getGrpHdr().getCtrlSum()
									.add(paymentGroup.getInformation().getCtrlSum());		
		document.getCstmrDrctDbtInitn().getGrpHdr().setCtrlSum(newSum);
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void write(String fileName) throws JAXBException, IOException{
		File ff=new File(fileName); // définir l'arborescence
		File Rep=ff.getParentFile();
		if(Rep.exists()==false)
			Rep.mkdir();
		
		FileWriter file = new FileWriter(fileName);
//		Schema sepaPain = new Schema("pain.008.001.02.xsd");
		JAXBContext jc = JAXBContext.newInstance(Document.class);
		Marshaller marshaller = jc.createMarshaller();
//		marshaller.setSchema();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "pain.008.001.02.xsd"); //<--
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(new ObjectFactory().createDocument(document), new BufferedWriter(file));
        file.close();
        
	}
 
}
