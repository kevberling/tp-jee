/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ig2i.ejb;

import fr.ig2i.entities.HistoriqueEntity;
import fr.ig2i.entities.OperationEnum;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 *
 * @author kberling
 */
@Stateless
public class ProductEJB implements ProductLocalEJB, ProductRemoteEJB{
	
	@EJB
	private HistoriqueEJB historiqueEJB;

    public Integer productMethod(Integer param1, Integer param2) {
    	for(int i= 0; i<1000; i++) {
    		String test = "";
    	}
    	int resultat = Math.multiplyExact(param1, param2);
    	HistoriqueEntity historiqueEntity = new HistoriqueEntity();
    	historiqueEntity.setParam1(String.valueOf(param1));
    	historiqueEntity.setParam2(String.valueOf(param2));
    	historiqueEntity.setOperationEnum(OperationEnum.MULTIPLICATION);
    	historiqueEntity.setResultat(resultat);
    	historiqueEJB.save(historiqueEntity);
		return resultat;
    }

}
