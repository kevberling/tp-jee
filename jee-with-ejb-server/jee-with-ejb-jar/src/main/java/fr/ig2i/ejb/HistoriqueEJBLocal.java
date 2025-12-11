package fr.ig2i.ejb;

import fr.ig2i.entities.HistoriqueEntity;
import jakarta.ejb.Local;

@Local
public interface HistoriqueEJBLocal {
	
	void save(HistoriqueEntity historiqueEntity);
	
	HistoriqueEntity findHistoriqueById(int historiqueId);

}
