package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTTlg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTTlg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTTlgRepository extends JpaRepository<SconTTlg, Long> {

}
