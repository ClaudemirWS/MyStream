
package com.api.senac.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StreamingRepository extends JpaRepository<StreamingEntity, Integer> {

    List<StreamingEntity> findByTituloContaining(String titulo);

}