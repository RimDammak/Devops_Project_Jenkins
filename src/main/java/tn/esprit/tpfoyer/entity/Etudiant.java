package tn.esprit.tpfoyer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomEtudiant;
    String prenomEtudiant;
    long cinEtudiant;
    Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants")
    Set<Reservation> reservations;

    // Constructor with parameters
    public Etudiant(long idEtudiant, String nomEtudiant, String prenomEtudiant, long cinEtudiant, Date dateNaissance) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.cinEtudiant = cinEtudiant;
        this.dateNaissance = dateNaissance;
    }

    // Corrected getPrenom method
    public String getPrenom() {
        return prenomEtudiant; // Return the actual field
    }

    // Corrected getNom method
    public String getNom() {
        return nomEtudiant; // Return the actual field
    }
}
