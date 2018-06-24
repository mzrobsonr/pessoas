package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTLgb;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTLgb entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTLgbRepository extends JpaRepository<SconTLgb, Long> {

}
