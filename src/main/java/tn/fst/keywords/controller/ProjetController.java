package tn.fst.keywords.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;
import tn.fst.keywords.entity.Projet;
import tn.fst.keywords.entity.ProjetDetail;
import tn.fst.keywords.service.ProjetService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {

    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    // Controller endpoint: delegates to the service layer, which calls Spring Data JPA methods.
    public List<Projet> findAll() {
        return projetService.findAll();
    }

    @GetMapping("/{id}")
    public Projet findById(@PathVariable String id) {
        return projetService.findById(id);
    }

    @PostMapping
    public Projet save(@RequestBody Projet projet) {
        return projetService.save(projet);
    }

    @PutMapping("/{id}")
    // REST update endpoint for the parent entity Projet.
    public Projet update(@PathVariable String id, @RequestBody Projet projet) {
        projet.setId(id);
        return projetService.save(projet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        projetService.deleteById(id);
    }

    @GetMapping("/search/sujet")
    // Keyword example: findBySujetContainingIgnoreCase(...)
    public List<Projet> searchBySujet(@RequestParam String value) {
        return projetService.searchBySujet(value);
    }

    @GetMapping("/search/technologie")
    // Keyword example: findByDetail_TechnologieIgnoreCase(...)
    public List<Projet> searchByTechnologie(@RequestParam String value) {
        return projetService.searchByTechnologie(value);
    }

    @GetMapping("/search/equipe")
    // Keyword example: findByEquipes_NomContainingIgnoreCase(...)
    public List<Projet> searchByEquipe(@RequestParam String value) {
        return projetService.searchByEquipe(value);
    }

    @GetMapping("/search/domaine")
    // Keyword example: findByEquipes_Domaine(...)
    public List<Projet> searchByDomaine(@RequestParam Domaine domaine) {
        return projetService.searchByDomaine(domaine);
    }

    @GetMapping("/search/multi")
    // Keyword example: combined derived query on sujet + equipe.
    public List<Projet> searchBySujetAndEquipe(@RequestParam String sujet, @RequestParam String equipe) {
        return projetService.searchByTechnologieAndEquipe(sujet, equipe);
    }

    @GetMapping("/search/date")
    // Keyword example: findByDetail_DateDebutBetween(...)
    public List<Projet> searchByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return projetService.searchByDateRange(start, end);
    }

    @PutMapping("/{projetId}/detail")
    // One-to-one association update: Projet is the parent, ProjetDetail is the child.
    public Projet attachDetail(@PathVariable String projetId, @RequestBody ProjetDetail detail) {
        return projetService.attachDetail(projetId, detail);
    }

    @PutMapping("/{projetId}/equipes")
    // Many-to-many association update endpoint between Projet and Equipe.
    public Projet attachEquipe(@PathVariable String projetId, @RequestBody Equipe equipe) {
        return projetService.attachEquipe(projetId, equipe);
    }
}
