package tn.esprit.tp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.tp2.entity.Bloc;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
@Query("select b from Bloc b where b.foyer.universite.universiteName LIKE %:keyword%")
Bloc findByKeyword(@Param("keyword") String keyword);
}
