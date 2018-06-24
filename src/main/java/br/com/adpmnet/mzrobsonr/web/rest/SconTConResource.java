package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTCon;
import br.com.adpmnet.mzrobsonr.repository.SconTConRepository;
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
 * REST controller for managing SconTCon.
 */
@RestController
@RequestMapping("/api")
public class SconTConResource {

    private final Logger log = LoggerFactory.getLogger(SconTConResource.class);

    private static final String ENTITY_NAME = "sconTCon";

    private final SconTConRepository sconTConRepository;

    public SconTConResource(SconTConRepository sconTConRepository) {
        this.sconTConRepository = sconTConRepository;
    }

    /**
     * POST  /scon-t-cons : Create a new sconTCon.
     *
     * @param sconTCon the sconTCon to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTCon, or with status 400 (Bad Request) if the sconTCon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-cons")
    @Timed
    public ResponseEntity<SconTCon> createSconTCon(@RequestBody SconTCon sconTCon) throws URISyntaxException {
        log.debug("REST request to save SconTCon : {}", sconTCon);
        if (sconTCon.getId() != null) {
            throw new BadRequestAlertException("A new sconTCon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTCon result = sconTConRepository.save(sconTCon);
        return ResponseEntity.created(new URI("/api/scon-t-cons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-cons : Updates an existing sconTCon.
     *
     * @param sconTCon the sconTCon to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTCon,
     * or with status 400 (Bad Request) if the sconTCon is not valid,
     * or with status 500 (Internal Server Error) if the sconTCon couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-cons")
    @Timed
    public ResponseEntity<SconTCon> updateSconTCon(@RequestBody SconTCon sconTCon) throws URISyntaxException {
        log.debug("REST request to update SconTCon : {}", sconTCon);
        if (sconTCon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTCon result = sconTConRepository.save(sconTCon);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTCon.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-cons : get all the sconTCons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTCons in body
     */
    @GetMapping("/scon-t-cons")
    @Timed
    public List<SconTCon> getAllSconTCons() {
        log.debug("REST request to get all SconTCons");
        return sconTConRepository.findAll();
    }

    /**
     * GET  /scon-t-cons/:id : get the "id" sconTCon.
     *
     * @param id the id of the sconTCon to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTCon, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-cons/{id}")
    @Timed
    public ResponseEntity<SconTCon> getSconTCon(@PathVariable Long id) {
        log.debug("REST request to get SconTCon : {}", id);
        Optional<SconTCon> sconTCon = sconTConRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTCon);
    }

    /**
     * DELETE  /scon-t-cons/:id : delete the "id" sconTCon.
     *
     * @param id the id of the sconTCon to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-cons/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTCon(@PathVariable Long id) {
        log.debug("REST request to delete SconTCon : {}", id);

        sconTConRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
