package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTCon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTCon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTConRepository extends JpaRepository<SconTCon, Long> {

}
