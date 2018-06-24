package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTLogRepository extends JpaRepository<SconTLog, Long> {

}
