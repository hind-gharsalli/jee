package tn.fst.keywords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.fst.keywords.entity.ProjetDetail;

import java.util.Date;
import java.util.List;

public interface ProjetDetailRepository extends JpaRepository<ProjetDetail, Long> {

    // Keyword: simple case-insensitive filtering
    List<ProjetDetail> findByTechnologieIgnoreCase(String technologie);

    // Keyword: Between on the numeric field cout
    List<ProjetDetail> findByCoutBetween(Long min, Long max);

    // Keyword: Before on the date field
    List<ProjetDetail> findByDateDebutBefore(Date date);
}
