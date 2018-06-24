package br.com.adpmnet.mzrobsonr.repository;

import br.com.adpmnet.mzrobsonr.domain.SconTBai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SconTBai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SconTBaiRepository extends JpaRepository<SconTBai, Long> {

}
