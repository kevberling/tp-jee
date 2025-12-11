/**
 * 
 */
package fr.ig2i.clientEJB;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import fr.ig2i.ejb.ProductEJB;
import fr.ig2i.ejb.ProductRemoteEJB;

/**
 * Exercice 6 - Multiplication via les EJB
 *
 * @author kberling
 */
public class Multiplication {

	/**
     * Main de la classe {@link Multiplication}
	 * @param args
	 * @throws NamingException exception
	 */
	public static void main(String[] args) throws NamingException {
		testRemoteStatelessEJB();
	}
	
	private static void testRemoteStatelessEJB() throws NamingException {

        final ProductRemoteEJB ejb = lookupRemoteEJB();
        for(int i =0; i< 10; i++) {
        	Integer resultat = ejb.productMethod(3, 4);
        	System.out.println("Résultat de la multiplication "+ i +" : " + resultat);
        }
    }

    /**
     * Instanciation de mon EJB Client par JNDI
     * @return {@link fr.ig2i.ejb.ProductRemoteEJB}
     * @throws NamingException exception
     */
    private static ProductRemoteEJB lookupRemoteEJB() throws NamingException {
        final Context context = createInitialContext();

        final String appName = "jee-with-ejb-ear-1.0.0-SNAPSHOT";
        final String moduleName = "jee-with-ejb-jar-1.0.0-SNAPSHOT";
        final String beanName = ProductEJB.class.getSimpleName();
        final String viewClassName = ProductRemoteEJB.class.getName();
        System.out.println("Looking EJB via JNDI ");
        System.out.println("ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + viewClassName);

        return (ProductRemoteEJB) context.lookup("ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + viewClassName);
    }

    /**
     * Définition de mon context JNDI : service, annuaire
     * @return Context
     * @throws NamingException exception
     */
    private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        return new InitialContext(jndiProperties);
    }

}
