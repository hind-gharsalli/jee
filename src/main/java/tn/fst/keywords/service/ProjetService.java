package tn.fst.keywords.service;

import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;
import tn.fst.keywords.entity.Projet;
import tn.fst.keywords.entity.ProjetDetail;

import java.util.Date;
import java.util.List;

public interface ProjetService {

    List<Projet> findAll();

    Projet findById(String id);

    Projet save(Projet projet);

    void deleteById(String id);

    List<Projet> searchBySujet(String sujet);

    List<Projet> searchByTechnologie(String technologie);

    List<Projet> searchByEquipe(String nomEquipe);

    List<Projet> searchByDomaine(Domaine domaine);

    List<Projet> searchByTechnologieAndEquipe(String sujet, String nomEquipe);

    List<Projet> searchByDateRange(Date start, Date end);

    Projet attachDetail(String projetId, ProjetDetail detail);

    Projet attachEquipe(String projetId, Equipe equipe);
}
