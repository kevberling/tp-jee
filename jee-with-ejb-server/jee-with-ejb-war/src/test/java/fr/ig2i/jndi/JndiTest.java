/**
 * 
 */
package fr.ig2i.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author kberling
 *
 */
public class JndiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Hashtable<String, Object> env = new Hashtable<>();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	    env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
	    InitialContext ctx;
	    String hello = null;
		try {
			ctx = new InitialContext(env);
			hello = (String) ctx.lookup("homepagelabel");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(hello);
	    
	}

}
