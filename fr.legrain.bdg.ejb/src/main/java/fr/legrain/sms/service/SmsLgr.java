package fr.legrain.sms.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
public @interface SmsLgr {
	
	public static final String TYPE_SMS_MAILJET = "TYPE_SMS_MAILJET";
	public static final String TYPE_SMS_MAILJET_PROGRAMME = "TYPE_SMS_MAILJET_PROGRAMME";
	
	public static final String TYPE_SMS_OVH = "TYPE_SMS_OVH";
	public static final String TYPE_SMS_OVH_PROGRAMME = "TYPE_SMS_OVH_PROGRAMME";

	
	String value();
}

