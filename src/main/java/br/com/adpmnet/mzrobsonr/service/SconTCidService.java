package br.com.adpmnet.mzrobsonr.service;

import br.com.adpmnet.mzrobsonr.domain.SconTCid;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SconTCid.
 */
public interface SconTCidService {

    /**
     * Save a sconTCid.
     *
     * @param sconTCid the entity to save
     * @return the persisted entity
     */
    SconTCid save(SconTCid sconTCid);

    /**
     * Get all the sconTCids.
     *
     * @return the list of entities
     */
    List<SconTCid> findAll();


    /**
     * Get the "id" sconTCid.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SconTCid> findOne(Long id);

    /**
     * Delete the "id" sconTCid.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
