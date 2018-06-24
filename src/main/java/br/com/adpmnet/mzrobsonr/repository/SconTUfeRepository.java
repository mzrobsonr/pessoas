package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTUfe;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTUfe entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTUfeRepository extends JpaRepository<SconTUfe, Long> {

}
