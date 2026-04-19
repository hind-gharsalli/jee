package tn.fst.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Projet;

import java.util.Date;
import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, String> {

    // Keyword: Containing + IgnoreCase on the simple field Projet.sujet
    List<Projet> findBySujetContainingIgnoreCase(String sujet);

    // Keyword: StartingWith + IgnoreCase
    List<Projet> findBySujetStartingWithIgnoreCase(String prefix);

    // Keyword: EndingWith + IgnoreCase
    List<Projet> findBySujetEndingWithIgnoreCase(String suffix);

    // Keyword: nested property on one-to-one association Projet.detail.technologie
    List<Projet> findByDetail_TechnologieIgnoreCase(String technologie);

    // Keyword: GreaterThan on the nested numeric field Projet.detail.cout
    List<Projet> findByDetail_CoutGreaterThan(Long cout);

    // Keyword: Between on date field inside ProjetDetail
    List<Projet> findByDetail_DateDebutBetween(Date start, Date end);

    // Keyword: nested collection property on many-to-many association Projet.equipes.nom
    List<Projet> findByEquipes_NomContainingIgnoreCase(String nomEquipe);

    // Keyword: filter through enum field on associated entities
    List<Projet> findByEquipes_Domaine(Domaine domaine);

    // Keyword: Distinct removes duplicates caused by the many-to-many join
    List<Projet> findDistinctByEquipes_Domaine(Domaine domaine);

    // Keyword: combined derived query across two paths
    List<Projet> findBySujetContainingIgnoreCaseAndEquipes_NomContainingIgnoreCase(String sujet, String nomEquipe);

    // Keyword: Count projection
    long countByEquipes_Domaine(Domaine domaine);

    // Keyword: Exists projection
    boolean existsBySujetIgnoreCase(String sujet);
}
