package tn.esprit.tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tp2.entity.Universite;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
}
