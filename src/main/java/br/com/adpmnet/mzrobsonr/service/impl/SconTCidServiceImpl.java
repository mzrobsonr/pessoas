package br.com.adpmnet.mzrobsonr.service.impl;

import br.com.adpmnet.mzrobsonr.service.SconTCidService;
import br.com.adpmnet.mzrobsonr.domain.SconTCid;
import br.com.adpmnet.mzrobsonr.repository.SconTCidRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing SconTCid.
 */
@Service
@Transactional
public class SconTCidServiceImpl implements SconTCidService {

    private final Logger log = LoggerFactory.getLogger(SconTCidServiceImpl.class);

    private final SconTCidRepository sconTCidRepository;

    public SconTCidServiceImpl(SconTCidRepository sconTCidRepository) {
        this.sconTCidRepository = sconTCidRepository;
    }

    /**
     * Save a sconTCid.
     *
     * @param sconTCid the entity to save
     * @return the persisted entity
     */
    @Override
    public SconTCid save(SconTCid sconTCid) {
        log.debug("Request to save SconTCid : {}", sconTCid);        return sconTCidRepository.save(sconTCid);
    }

    /**
     * Get all the sconTCids.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SconTCid> findAll() {
        log.debug("Request to get all SconTCids");
        return sconTCidRepository.findAll();
    }


    /**
     * Get one sconTCid by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SconTCid> findOne(Long id) {
        log.debug("Request to get SconTCid : {}", id);
        return sconTCidRepository.findById(id);
    }

    /**
     * Delete the sconTCid by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SconTCid : {}", id);
        sconTCidRepository.deleteById(id);
    }
}
