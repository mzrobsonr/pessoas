package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTBac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTBac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTBacRepository extends JpaRepository<SconTBac, Long> {

}
