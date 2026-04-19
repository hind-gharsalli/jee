package tn.fst.keywords.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
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
public class Projet {

    @Id
    private String id;

    private String sujet;

    @OneToOne(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("projet-detail")
    private ProjetDetail detail;

    @ManyToMany
    @JoinTable(
            name = "projet_equipe",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id")
    )
    @JsonManagedReference("projet-equipe")
    private Set<Equipe> equipes = new HashSet<>();

    public void setDetail(ProjetDetail detail) {
        this.detail = detail;
        if (detail != null) {
            detail.setProjet(this);
        }
    }

    public void addEquipe(Equipe equipe) {
        equipes.add(equipe);
        equipe.getProjets().add(this);
    }

    public void removeEquipe(Equipe equipe) {
        equipes.remove(equipe);
        equipe.getProjets().remove(this);
    }
}
