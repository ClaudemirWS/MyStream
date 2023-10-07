
package com.api.senac.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GeneroRepository extends JpaRepository<GeneroEntity, Integer> {

    List<GeneroEntity> findByTituloContaining(String titulo);

}