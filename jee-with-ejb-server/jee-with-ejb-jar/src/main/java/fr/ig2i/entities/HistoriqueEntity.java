package fr.ig2i.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "historique", schema = "public")
@Getter
@Setter
public class HistoriqueEntity implements Serializable {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "valeur1")
	private String param1;
	
	@Column(name = "valeur2")
	private String param2;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private OperationEnum operationEnum;
	
	@Column
	private int resultat;

}
