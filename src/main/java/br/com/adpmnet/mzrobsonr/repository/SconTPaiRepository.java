package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTPai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTPai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTPaiRepository extends JpaRepository<SconTPai, Long> {

}
