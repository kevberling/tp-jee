package fr.ig2i.calculator;

import fr.ig2i.ejb.ProductEJB;
import fr.ig2i.ejb.ProductRemoteEJB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class OutOfMemTest {

    ProductRemoteEJB multiplicateur;
	
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
    
    private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        return new InitialContext(jndiProperties);
    }

    @BeforeEach
    void setUp() throws Exception{
        multiplicateur = lookupRemoteEJB();  
    }

    @Disabled
    @Test
    void testOutOfMem() throws Exception{
        
        int nbThread = 20;
        Executor exec = Executors.newFixedThreadPool(nbThread);
        for(int i=0; i< nbThread; i++){
            exec.execute(() -> {
                int leftLimit = 10;
                int rightLimit = 100;
                while(true){
                    int left = leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
                    int right = leftLimit + (int) (Math.random() * (rightLimit - leftLimit));
                    multiplicateur.productMethod(left,right);
                }
            });
        }
        ((ExecutorService) exec).awaitTermination(20L, TimeUnit.MINUTES);
        
    }
}
