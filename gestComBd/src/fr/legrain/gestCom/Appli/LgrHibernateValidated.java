//package fr.legrain.gestCom.Appli;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
////import org.hibernate.validator.ValidatorClass;
//import javax.validation.Constraint;
//import javax.validation.Payload;
//
////@ValidatorClass(LgrHibernateValidator.class)
//@Constraint(validatedBy = LgrHibernateValidator.class)
//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//public @interface LgrHibernateValidated {
//	Class<?> clazz() default Object.class; //pour rechercher les controles a faire en fonction de la classe/table 
//	String champ();
//	String table();
//	String message() default "{validator.capitalized}";
////	#in ValidatorMessages.properties
////	validator.capitalized = Capitalization is not {type}
//	Class<?>[] groups() default {};
//	Class<? extends Payload>[] payload() default {};
//}
//
//
//
