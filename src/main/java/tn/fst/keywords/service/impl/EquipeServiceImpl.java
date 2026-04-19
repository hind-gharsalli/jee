package tn.fst.keywords.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;
import tn.fst.keywords.repository.EquipeRepository;
import tn.fst.keywords.service.EquipeService;

import java.util.List;

@Service
@Transactional
public class EquipeServiceImpl implements EquipeService {

    private final EquipeRepository equipeRepository;

    public EquipeServiceImpl(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @Override
    public List<Equipe> findAll() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe findById(Integer id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipe not found: " + id));
    }

    @Override
    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public void deleteById(Integer id) {
        equipeRepository.deleteById(id);
    }

    @Override
    public List<Equipe> searchByNom(String nom) {
        // Keyword example: findByNomContainingIgnoreCase(...)
        return equipeRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public List<Equipe> searchByDomaine(Domaine domaine) {
        // Keyword example: findByDomaine(...)
        return equipeRepository.findByDomaine(domaine);
    }
}
