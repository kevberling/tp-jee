package fr.ig2i.record;

import jakarta.persistence.Embeddable;

// Utilisé comme un objet de valeur immuable (Value Object)
@Embeddable
public record Address(String street, String city, String zipCode) {
}
