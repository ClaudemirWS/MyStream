
package com.api.senac.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FilmeRepository extends JpaRepository<FilmeEntity, Integer> {

    List<FilmeEntity> findByTituloContaining(String titulo);

}