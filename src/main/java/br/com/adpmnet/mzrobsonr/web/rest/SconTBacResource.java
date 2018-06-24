package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTBac;
import br.com.adpmnet.mzrobsonr.repository.SconTBacRepository;
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
 * REST controller for managing SconTBac.
 */
@RestController
@RequestMapping("/api")
public class SconTBacResource {

    private final Logger log = LoggerFactory.getLogger(SconTBacResource.class);

    private static final String ENTITY_NAME = "sconTBac";

    private final SconTBacRepository sconTBacRepository;

    public SconTBacResource(SconTBacRepository sconTBacRepository) {
        this.sconTBacRepository = sconTBacRepository;
    }

    /**
     * POST  /scon-t-bacs : Create a new sconTBac.
     *
     * @param sconTBac the sconTBac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTBac, or with status 400 (Bad Request) if the sconTBac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-bacs")
    @Timed
    public ResponseEntity<SconTBac> createSconTBac(@RequestBody SconTBac sconTBac) throws URISyntaxException {
        log.debug("REST request to save SconTBac : {}", sconTBac);
        if (sconTBac.getId() != null) {
            throw new BadRequestAlertException("A new sconTBac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTBac result = sconTBacRepository.save(sconTBac);
        return ResponseEntity.created(new URI("/api/scon-t-bacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-bacs : Updates an existing sconTBac.
     *
     * @param sconTBac the sconTBac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTBac,
     * or with status 400 (Bad Request) if the sconTBac is not valid,
     * or with status 500 (Internal Server Error) if the sconTBac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-bacs")
    @Timed
    public ResponseEntity<SconTBac> updateSconTBac(@RequestBody SconTBac sconTBac) throws URISyntaxException {
        log.debug("REST request to update SconTBac : {}", sconTBac);
        if (sconTBac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTBac result = sconTBacRepository.save(sconTBac);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTBac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-bacs : get all the sconTBacs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTBacs in body
     */
    @GetMapping("/scon-t-bacs")
    @Timed
    public List<SconTBac> getAllSconTBacs() {
        log.debug("REST request to get all SconTBacs");
        return sconTBacRepository.findAll();
    }

    /**
     * GET  /scon-t-bacs/:id : get the "id" sconTBac.
     *
     * @param id the id of the sconTBac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTBac, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-bacs/{id}")
    @Timed
    public ResponseEntity<SconTBac> getSconTBac(@PathVariable Long id) {
        log.debug("REST request to get SconTBac : {}", id);
        Optional<SconTBac> sconTBac = sconTBacRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTBac);
    }

    /**
     * DELETE  /scon-t-bacs/:id : delete the "id" sconTBac.
     *
     * @param id the id of the sconTBac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-bacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTBac(@PathVariable Long id) {
        log.debug("REST request to delete SconTBac : {}", id);

        sconTBacRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
