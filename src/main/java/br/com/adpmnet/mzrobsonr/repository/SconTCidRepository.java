package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTCid;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTCid entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTCidRepository extends JpaRepository<SconTCid, Long> {

}
