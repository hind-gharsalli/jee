package tn.fst.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;

import java.util.List;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    // Keyword: Containing + IgnoreCase on Equipe.nom
    List<Equipe> findByNomContainingIgnoreCase(String nom);

    // Keyword: StartingWith + IgnoreCase
    List<Equipe> findByNomStartingWithIgnoreCase(String prefix);

    // Keyword: equality on enum field
    List<Equipe> findByDomaine(Domaine domaine);

    // Keyword: Count projection
    long countByDomaine(Domaine domaine);

    // Keyword: Exists projection
    boolean existsByNomIgnoreCase(String nom);
}
