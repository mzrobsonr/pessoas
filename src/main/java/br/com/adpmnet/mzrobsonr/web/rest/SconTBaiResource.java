package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTBai;
import br.com.adpmnet.mzrobsonr.repository.SconTBaiRepository;
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
 * REST controller for managing SconTBai.
 */
@RestController
@RequestMapping("/api")
public class SconTBaiResource {

    private final Logger log = LoggerFactory.getLogger(SconTBaiResource.class);

    private static final String ENTITY_NAME = "sconTBai";

    private final SconTBaiRepository sconTBaiRepository;

    public SconTBaiResource(SconTBaiRepository sconTBaiRepository) {
        this.sconTBaiRepository = sconTBaiRepository;
    }

    /**
     * POST  /scon-t-bais : Create a new sconTBai.
     *
     * @param sconTBai the sconTBai to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTBai, or with status 400 (Bad Request) if the sconTBai has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-bais")
    @Timed
    public ResponseEntity<SconTBai> createSconTBai(@RequestBody SconTBai sconTBai) throws URISyntaxException {
        log.debug("REST request to save SconTBai : {}", sconTBai);
        if (sconTBai.getId() != null) {
            throw new BadRequestAlertException("A new sconTBai cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTBai result = sconTBaiRepository.save(sconTBai);
        return ResponseEntity.created(new URI("/api/scon-t-bais/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-bais : Updates an existing sconTBai.
     *
     * @param sconTBai the sconTBai to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTBai,
     * or with status 400 (Bad Request) if the sconTBai is not valid,
     * or with status 500 (Internal Server Error) if the sconTBai couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-bais")
    @Timed
    public ResponseEntity<SconTBai> updateSconTBai(@RequestBody SconTBai sconTBai) throws URISyntaxException {
        log.debug("REST request to update SconTBai : {}", sconTBai);
        if (sconTBai.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTBai result = sconTBaiRepository.save(sconTBai);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTBai.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-bais : get all the sconTBais.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sconTBais in body
     */
    @GetMapping("/scon-t-bais")
    @Timed
    public List<SconTBai> getAllSconTBais() {
        log.debug("REST request to get all SconTBais");
        return sconTBaiRepository.findAll();
    }

    /**
     * GET  /scon-t-bais/:id : get the "id" sconTBai.
     *
     * @param id the id of the sconTBai to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTBai, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-bais/{id}")
    @Timed
    public ResponseEntity<SconTBai> getSconTBai(@PathVariable Long id) {
        log.debug("REST request to get SconTBai : {}", id);
        Optional<SconTBai> sconTBai = sconTBaiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTBai);
    }

    /**
     * DELETE  /scon-t-bais/:id : delete the "id" sconTBai.
     *
     * @param id the id of the sconTBai to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-bais/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTBai(@PathVariable Long id) {
        log.debug("REST request to delete SconTBai : {}", id);

        sconTBaiRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
