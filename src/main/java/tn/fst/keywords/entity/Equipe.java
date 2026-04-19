package tn.fst.keywords.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {

    @Id
    private Integer id;

    private String nom;

    @Enumerated(EnumType.STRING)
    private Domaine domaine;

    @ManyToMany(mappedBy = "equipes")
    @JsonBackReference("projet-equipe")
    private Set<Projet> projets = new HashSet<>();

    public void addProjet(Projet projet) {
        projets.add(projet);
        projet.getEquipes().add(this);
    }

    public void removeProjet(Projet projet) {
        projets.remove(projet);
        projet.getEquipes().remove(this);
    }
}
