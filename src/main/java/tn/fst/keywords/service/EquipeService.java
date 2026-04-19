package tn.fst.keywords.service;

import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;

import java.util.List;

public interface EquipeService {

    List<Equipe> findAll();

    Equipe findById(Integer id);

    Equipe save(Equipe equipe);

    void deleteById(Integer id);

    List<Equipe> searchByNom(String nom);

    List<Equipe> searchByDomaine(Domaine domaine);
}
