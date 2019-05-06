//package local.jordi.kwetter.boundary.rest.security;
//
//import javax.ws.rs.NameBinding;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@NameBinding
//@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.METHOD})
//public @interface RequiresJWT
//{
//}
package local.jordi.kwetter.boundary.rest.security;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@javax.ws.rs.NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface RequiresJWT
{
}
