package fr.ig2i.ejb;

import jakarta.ejb.Local;

@Local
public interface ProductLocalEJB {
	
    Integer productMethod(Integer param1, Integer param2);

}
