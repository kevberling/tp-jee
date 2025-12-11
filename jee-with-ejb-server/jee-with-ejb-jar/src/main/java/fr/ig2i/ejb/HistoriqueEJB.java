package fr.ig2i.ejb;

import fr.ig2i.entities.HistoriqueEntity;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Session Bean implementation class HistoriqueEJB
 */
@Stateless
@LocalBean
public class HistoriqueEJB implements HistoriqueEJBLocal {
	
	@PersistenceContext(unitName = "jpajeetp")
	private EntityManager entityManager;
	

    /**
     * Default constructor. 
     */
    public HistoriqueEJB() {
        // Auto-generated constructor stub
    }

	@Override
	public void save(HistoriqueEntity historiqueEntity) {
		this.entityManager.persist(historiqueEntity);
	}


	@Override
	public HistoriqueEntity findHistoriqueById(int historiqueId) {
		return this.entityManager.find(HistoriqueEntity.class, historiqueId);
	}

}
