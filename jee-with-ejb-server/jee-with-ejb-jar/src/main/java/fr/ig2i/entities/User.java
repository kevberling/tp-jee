package fr.ig2i.entities;

import fr.ig2i.record.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;

    @Column
    private String username;

    // Le record est intégré directement dans la table User
    @Embedded
    private Address address;

    // Getters/Setters standards...
}
