package tn.fst.keywords.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;
import tn.fst.keywords.entity.Projet;
import tn.fst.keywords.entity.ProjetDetail;
import tn.fst.keywords.repository.EquipeRepository;
import tn.fst.keywords.repository.ProjetRepository;
import tn.fst.keywords.service.ProjetService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjetServiceImpl implements ProjetService {

    private final ProjetRepository projetRepository;
    private final EquipeRepository equipeRepository;

    public ProjetServiceImpl(ProjetRepository projetRepository, EquipeRepository equipeRepository) {
        this.projetRepository = projetRepository;
        this.equipeRepository = equipeRepository;
    }

    @Override
    public List<Projet> findAll() {
        return projetRepository.findAll();
    }

    @Override
    public Projet findById(String id) {
        return projetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Projet not found: " + id));
    }

    @Override
    public Projet save(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public void deleteById(String id) {
        projetRepository.deleteById(id);
    }

    @Override
    public List<Projet> searchBySujet(String sujet) {
        // Spring Data JPA keyword: Containing + IgnoreCase
        return projetRepository.findBySujetContainingIgnoreCase(sujet);
    }

    @Override
    public List<Projet> searchByTechnologie(String technologie) {
        // Nested property keyword: detail.technologie
        return projetRepository.findByDetail_TechnologieIgnoreCase(technologie);
    }

    @Override
    public List<Projet> searchByEquipe(String nomEquipe) {
        // Nested collection keyword: equipes.nom
        return projetRepository.findByEquipes_NomContainingIgnoreCase(nomEquipe);
    }

    @Override
    public List<Projet> searchByDomaine(Domaine domaine) {
        // Enum keyword filter on associated Equipe entities
        return projetRepository.findByEquipes_Domaine(domaine);
    }

    @Override
    public List<Projet> searchByTechnologieAndEquipe(String sujet, String nomEquipe) {
        // Combined derived query: sujet + nested equipe name
        return projetRepository.findBySujetContainingIgnoreCaseAndEquipes_NomContainingIgnoreCase(sujet, nomEquipe);
    }

    @Override
    public List<Projet> searchByDateRange(Date start, Date end) {
        // Range keyword: Between
        return projetRepository.findByDetail_DateDebutBetween(start, end);
    }

    @Override
    public Projet attachDetail(String projetId, ProjetDetail detail) {
        // One-to-one parent side: setting the child also updates the back-reference.
        Projet projet = findById(projetId);
        projet.setDetail(detail);
        return projetRepository.save(projet);
    }

    @Override
    public Projet attachEquipe(String projetId, Equipe equipe) {
        // Many-to-many association managed from the owning side (Projet).
        Projet projet = findById(projetId);
        Equipe managedEquipe = equipeRepository.findById(equipe.getId())
                .orElseThrow(() -> new IllegalArgumentException("Equipe not found: " + equipe.getId()));
        projet.addEquipe(managedEquipe);
        return projetRepository.save(projet);
    }
}
