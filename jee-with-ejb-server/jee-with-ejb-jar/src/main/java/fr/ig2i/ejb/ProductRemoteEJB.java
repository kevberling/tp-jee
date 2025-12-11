package fr.ig2i.ejb;

import jakarta.ejb.Remote;

@Remote
public interface ProductRemoteEJB {
	
    Integer productMethod(Integer param1, Integer param2);

}
