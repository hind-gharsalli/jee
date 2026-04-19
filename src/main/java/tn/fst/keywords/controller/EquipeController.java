package tn.fst.keywords.controller;

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
import tn.fst.keywords.service.EquipeService;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping
    // Controller endpoint for the Equipe resource.
    public List<Equipe> findAll() {
        return equipeService.findAll();
    }

    @GetMapping("/{id}")
    public Equipe findById(@PathVariable Integer id) {
        return equipeService.findById(id);
    }

    @PostMapping
    public Equipe save(@RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @PutMapping("/{id}")
    public Equipe update(@PathVariable Integer id, @RequestBody Equipe equipe) {
        equipe.setId(id);
        return equipeService.save(equipe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        equipeService.deleteById(id);
    }

    @GetMapping("/search/nom")
    // Keyword example: findByNomContainingIgnoreCase(...)
    public List<Equipe> searchByNom(@RequestParam String value) {
        return equipeService.searchByNom(value);
    }

    @GetMapping("/search/domaine")
    // Keyword example: findByDomaine(...)
    public List<Equipe> searchByDomaine(@RequestParam Domaine domaine) {
        return equipeService.searchByDomaine(domaine);
    }
}
