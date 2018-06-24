package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTLog;
import br.com.adpmnet.mzrobsonr.repository.SconTLogRepository;
import br.com.adpmnet.mzrobsonr.web.rest.errors.BadRequestAlertException;
import br.com.adpmnet.mzrobsonr.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SconTLog.
 */
@RestController
@RequestMapping("/api")
public class SconTLogResource {

    private final Logger log = LoggerFactory.getLogger(SconTLogResource.class);

    private static final String ENTITY_NAME = "sconTLog";

    private final SconTLogRepository sconTLogRepository;

    public SconTLogResource(SconTLogRepository sconTLogRepository) {
        this.sconTLogRepository = sconTLogRepository;
    }

    /**
     * POST  /scon-t-logs : Create a new sconTLog.
     *
     * @param sconTLog the sconTLog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTLog, or with status 400 (Bad Request) if the sconTLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-logs")
    @Timed
    public ResponseEntity<SconTLog> createSconTLog(@RequestBody SconTLog sconTLog) throws URISyntaxException {
        log.debug("REST request to save SconTLog : {}", sconTLog);
        if (sconTLog.getId() != null) {
            throw new BadRequestAlertException("A new sconTLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTLog result = sconTLogRepository.save(sconTLog);
        return ResponseEntity.created(new URI("/api/scon-t-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-logs : Updates an existing sconTLog.
     *
     * @param sconTLog the sconTLog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTLog,
     * or with status 400 (Bad Request) if the sconTLog is not valid,
     * or with status 500 (Internal Server Error) if the sconTLog couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-logs")
    @Timed
    public ResponseEntity<SconTLog> updateSconTLog(@RequestBody SconTLog sconTLog) throws URISyntaxException {
        log.debug("REST request to update SconTLog : {}", sconTLog);
        if (sconTLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTLog result = sconTLogRepository.save(sconTLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-logs : get all the sconTLogs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTLogs in body
     */
    @GetMapping("/scon-t-logs")
    @Timed
    public List<SconTLog> getAllSconTLogs() {
        log.debug("REST request to get all SconTLogs");
        return sconTLogRepository.findAll();
    }

    /**
     * GET  /scon-t-logs/:id : get the "id" sconTLog.
     *
     * @param id the id of the sconTLog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTLog, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-logs/{id}")
    @Timed
    public ResponseEntity<SconTLog> getSconTLog(@PathVariable Long id) {
        log.debug("REST request to get SconTLog : {}", id);
        Optional<SconTLog> sconTLog = sconTLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTLog);
    }

    /**
     * DELETE  /scon-t-logs/:id : delete the "id" sconTLog.
     *
     * @param id the id of the sconTLog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTLog(@PathVariable Long id) {
        log.debug("REST request to delete SconTLog : {}", id);

        sconTLogRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
