
package com.api.senac.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SerieRepository extends JpaRepository<SerieEntity, Integer> {

    List<SerieEntity> findByTituloContaining(String titulo);

}