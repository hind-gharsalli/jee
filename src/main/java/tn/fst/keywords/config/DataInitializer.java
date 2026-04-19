package tn.fst.keywords.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.fst.keywords.entity.Domaine;
import tn.fst.keywords.entity.Equipe;
import tn.fst.keywords.entity.Projet;
import tn.fst.keywords.entity.ProjetDetail;
import tn.fst.keywords.repository.EquipeRepository;
import tn.fst.keywords.repository.ProjetRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(EquipeRepository equipeRepository, ProjetRepository projetRepository) {
        return args -> {
            if (projetRepository.count() > 0 || equipeRepository.count() > 0) {
                return;
            }

            Equipe equipe1 = new Equipe();
            equipe1.setId(1);
            equipe1.setNom("Alpha");
            equipe1.setDomaine(Domaine.ERPBI);

            Equipe equipe2 = new Equipe();
            equipe2.setId(2);
            equipe2.setNom("Beta");
            equipe2.setDomaine(Domaine.SIM);

            Equipe equipe3 = new Equipe();
            equipe3.setId(3);
            equipe3.setNom("Gamma");
            equipe3.setDomaine(Domaine.TWIN);

            equipeRepository.saveAll(List.of(equipe1, equipe2, equipe3));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Projet projet1 = new Projet();
                projet1.setId("P1");
                projet1.setSujet("Plateforme BI pour la fac");
                projet1.setDetail(new ProjetDetail(null, "Dashboard et reporting", "Spring Boot", 15000L, sdf.parse("2026-01-10"), null));
                projet1.addEquipe(equipe1);
                projet1.addEquipe(equipe2);

                Projet projet2 = new Projet();
                projet2.setId("P2");
                projet2.setSujet("Simulation IoT");
                projet2.setDetail(new ProjetDetail(null, "Simulation des capteurs", "Java", 8000L, sdf.parse("2026-02-05"), null));
                projet2.addEquipe(equipe2);
                projet2.addEquipe(equipe3);

                projetRepository.saveAll(List.of(projet1, projet2));
            } catch (ParseException e) {
                throw new IllegalStateException("Unable to seed demo data", e);
            }
        };
    }
}
