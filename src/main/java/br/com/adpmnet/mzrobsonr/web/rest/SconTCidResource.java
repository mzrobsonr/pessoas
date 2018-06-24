package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTCid;
import br.com.adpmnet.mzrobsonr.service.SconTCidService;
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
 * REST controller for managing SconTCid.
 */
@RestController
@RequestMapping("/api")
public class SconTCidResource {

    private final Logger log = LoggerFactory.getLogger(SconTCidResource.class);

    private static final String ENTITY_NAME = "sconTCid";

    private final SconTCidService sconTCidService;

    public SconTCidResource(SconTCidService sconTCidService) {
        this.sconTCidService = sconTCidService;
    }

    /**
     * POST  /scon-t-cids : Create a new sconTCid.
     *
     * @param sconTCid the sconTCid to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTCid, or with status 400 (Bad Request) if the sconTCid has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-cids")
    @Timed
    public ResponseEntity<SconTCid> createSconTCid(@RequestBody SconTCid sconTCid) throws URISyntaxException {
        log.debug("REST request to save SconTCid : {}", sconTCid);
        if (sconTCid.getId() != null) {
            throw new BadRequestAlertException("A new sconTCid cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTCid result = sconTCidService.save(sconTCid);
        return ResponseEntity.created(new URI("/api/scon-t-cids/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-cids : Updates an existing sconTCid.
     *
     * @param sconTCid the sconTCid to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTCid,
     * or with status 400 (Bad Request) if the sconTCid is not valid,
     * or with status 500 (Internal Server Error) if the sconTCid couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-cids")
    @Timed
    public ResponseEntity<SconTCid> updateSconTCid(@RequestBody SconTCid sconTCid) throws URISyntaxException {
        log.debug("REST request to update SconTCid : {}", sconTCid);
        if (sconTCid.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTCid result = sconTCidService.save(sconTCid);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTCid.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-cids : get all the sconTCids.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTCids in body
     */
    @GetMapping("/scon-t-cids")
    @Timed
    public List<SconTCid> getAllSconTCids() {
        log.debug("REST request to get all SconTCids");
        return sconTCidService.findAll();
    }

    /**
     * GET  /scon-t-cids/:id : get the "id" sconTCid.
     *
     * @param id the id of the sconTCid to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTCid, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-cids/{id}")
    @Timed
    public ResponseEntity<SconTCid> getSconTCid(@PathVariable Long id) {
        log.debug("REST request to get SconTCid : {}", id);
        Optional<SconTCid> sconTCid = sconTCidService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sconTCid);
    }

    /**
     * DELETE  /scon-t-cids/:id : delete the "id" sconTCid.
     *
     * @param id the id of the sconTCid to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-cids/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTCid(@PathVariable Long id) {
        log.debug("REST request to delete SconTCid : {}", id);
        sconTCidService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
