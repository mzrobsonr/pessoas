package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTLgb;
import br.com.adpmnet.mzrobsonr.repository.SconTLgbRepository;
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
 * REST controller for managing SconTLgb.
 */
@RestController
@RequestMapping("/api")
public class SconTLgbResource {

    private final Logger log = LoggerFactory.getLogger(SconTLgbResource.class);

    private static final String ENTITY_NAME = "sconTLgb";

    private final SconTLgbRepository sconTLgbRepository;

    public SconTLgbResource(SconTLgbRepository sconTLgbRepository) {
        this.sconTLgbRepository = sconTLgbRepository;
    }

    /**
     * POST  /scon-t-lgbs : Create a new sconTLgb.
     *
     * @param sconTLgb the sconTLgb to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTLgb, or with status 400 (Bad Request) if the sconTLgb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-lgbs")
    @Timed
    public ResponseEntity<SconTLgb> createSconTLgb(@RequestBody SconTLgb sconTLgb) throws URISyntaxException {
        log.debug("REST request to save SconTLgb : {}", sconTLgb);
        if (sconTLgb.getId() != null) {
            throw new BadRequestAlertException("A new sconTLgb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTLgb result = sconTLgbRepository.save(sconTLgb);
        return ResponseEntity.created(new URI("/api/scon-t-lgbs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-lgbs : Updates an existing sconTLgb.
     *
     * @param sconTLgb the sconTLgb to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTLgb,
     * or with status 400 (Bad Request) if the sconTLgb is not valid,
     * or with status 500 (Internal Server Error) if the sconTLgb couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-lgbs")
    @Timed
    public ResponseEntity<SconTLgb> updateSconTLgb(@RequestBody SconTLgb sconTLgb) throws URISyntaxException {
        log.debug("REST request to update SconTLgb : {}", sconTLgb);
        if (sconTLgb.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTLgb result = sconTLgbRepository.save(sconTLgb);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTLgb.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-lgbs : get all the sconTLgbs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTLgbs in body
     */
    @GetMapping("/scon-t-lgbs")
    @Timed
    public List<SconTLgb> getAllSconTLgbs() {
        log.debug("REST request to get all SconTLgbs");
        return sconTLgbRepository.findAll();
    }

    /**
     * GET  /scon-t-lgbs/:id : get the "id" sconTLgb.
     *
     * @param id the id of the sconTLgb to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTLgb, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-lgbs/{id}")
    @Timed
    public ResponseEntity<SconTLgb> getSconTLgb(@PathVariable Long id) {
        log.debug("REST request to get SconTLgb : {}", id);
        Optional<SconTLgb> sconTLgb = sconTLgbRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTLgb);
    }

    /**
     * DELETE  /scon-t-lgbs/:id : delete the "id" sconTLgb.
     *
     * @param id the id of the sconTLgb to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-lgbs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTLgb(@PathVariable Long id) {
        log.debug("REST request to delete SconTLgb : {}", id);

        sconTLgbRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
