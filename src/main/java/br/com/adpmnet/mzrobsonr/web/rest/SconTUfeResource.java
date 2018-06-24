package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTUfe;
import br.com.adpmnet.mzrobsonr.repository.SconTUfeRepository;
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
 * REST controller for managing SconTUfe.
 */
@RestController
@RequestMapping("/api")
public class SconTUfeResource {

    private final Logger log = LoggerFactory.getLogger(SconTUfeResource.class);

    private static final String ENTITY_NAME = "sconTUfe";

    private final SconTUfeRepository sconTUfeRepository;

    public SconTUfeResource(SconTUfeRepository sconTUfeRepository) {
        this.sconTUfeRepository = sconTUfeRepository;
    }

    /**
     * POST  /scon-t-ufes : Create a new sconTUfe.
     *
     * @param sconTUfe the sconTUfe to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTUfe, or with status 400 (Bad Request) if the sconTUfe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-ufes")
    @Timed
    public ResponseEntity<SconTUfe> createSconTUfe(@RequestBody SconTUfe sconTUfe) throws URISyntaxException {
        log.debug("REST request to save SconTUfe : {}", sconTUfe);
        if (sconTUfe.getId() != null) {
            throw new BadRequestAlertException("A new sconTUfe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTUfe result = sconTUfeRepository.save(sconTUfe);
        return ResponseEntity.created(new URI("/api/scon-t-ufes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-ufes : Updates an existing sconTUfe.
     *
     * @param sconTUfe the sconTUfe to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTUfe,
     * or with status 400 (Bad Request) if the sconTUfe is not valid,
     * or with status 500 (Internal Server Error) if the sconTUfe couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-ufes")
    @Timed
    public ResponseEntity<SconTUfe> updateSconTUfe(@RequestBody SconTUfe sconTUfe) throws URISyntaxException {
        log.debug("REST request to update SconTUfe : {}", sconTUfe);
        if (sconTUfe.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTUfe result = sconTUfeRepository.save(sconTUfe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTUfe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-ufes : get all the sconTUfes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTUfes in body
     */
    @GetMapping("/scon-t-ufes")
    @Timed
    public List<SconTUfe> getAllSconTUfes() {
        log.debug("REST request to get all SconTUfes");
        return sconTUfeRepository.findAll();
    }

    /**
     * GET  /scon-t-ufes/:id : get the "id" sconTUfe.
     *
     * @param id the id of the sconTUfe to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTUfe, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-ufes/{id}")
    @Timed
    public ResponseEntity<SconTUfe> getSconTUfe(@PathVariable Long id) {
        log.debug("REST request to get SconTUfe : {}", id);
        Optional<SconTUfe> sconTUfe = sconTUfeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTUfe);
    }

    /**
     * DELETE  /scon-t-ufes/:id : delete the "id" sconTUfe.
     *
     * @param id the id of the sconTUfe to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-ufes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTUfe(@PathVariable Long id) {
        log.debug("REST request to delete SconTUfe : {}", id);

        sconTUfeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
