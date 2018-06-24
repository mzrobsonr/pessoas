package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTPai;
import br.com.adpmnet.mzrobsonr.repository.SconTPaiRepository;
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
 * REST controller for managing SconTPai.
 */
@RestController
@RequestMapping("/api")
public class SconTPaiResource {

    private final Logger log = LoggerFactory.getLogger(SconTPaiResource.class);

    private static final String ENTITY_NAME = "sconTPai";

    private final SconTPaiRepository sconTPaiRepository;

    public SconTPaiResource(SconTPaiRepository sconTPaiRepository) {
        this.sconTPaiRepository = sconTPaiRepository;
    }

    /**
     * POST  /scon-t-pais : Create a new sconTPai.
     *
     * @param sconTPai the sconTPai to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTPai, or with status 400 (Bad Request) if the sconTPai has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-pais")
    @Timed
    public ResponseEntity<SconTPai> createSconTPai(@RequestBody SconTPai sconTPai) throws URISyntaxException {
        log.debug("REST request to save SconTPai : {}", sconTPai);
        if (sconTPai.getId() != null) {
            throw new BadRequestAlertException("A new sconTPai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTPai result = sconTPaiRepository.save(sconTPai);
        return ResponseEntity.created(new URI("/api/scon-t-pais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-pais : Updates an existing sconTPai.
     *
     * @param sconTPai the sconTPai to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTPai,
     * or with status 400 (Bad Request) if the sconTPai is not valid,
     * or with status 500 (Internal Server Error) if the sconTPai couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-pais")
    @Timed
    public ResponseEntity<SconTPai> updateSconTPai(@RequestBody SconTPai sconTPai) throws URISyntaxException {
        log.debug("REST request to update SconTPai : {}", sconTPai);
        if (sconTPai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTPai result = sconTPaiRepository.save(sconTPai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTPai.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-pais : get all the sconTPais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTPais in body
     */
    @GetMapping("/scon-t-pais")
    @Timed
    public List<SconTPai> getAllSconTPais() {
        log.debug("REST request to get all SconTPais");
        return sconTPaiRepository.findAll();
    }

    /**
     * GET  /scon-t-pais/:id : get the "id" sconTPai.
     *
     * @param id the id of the sconTPai to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTPai, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-pais/{id}")
    @Timed
    public ResponseEntity<SconTPai> getSconTPai(@PathVariable Long id) {
        log.debug("REST request to get SconTPai : {}", id);
        Optional<SconTPai> sconTPai = sconTPaiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTPai);
    }

    /**
     * DELETE  /scon-t-pais/:id : delete the "id" sconTPai.
     *
     * @param id the id of the sconTPai to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-pais/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTPai(@PathVariable Long id) {
        log.debug("REST request to delete SconTPai : {}", id);

        sconTPaiRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
