package br.com.adpmnet.mzrobsonr.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.adpmnet.mzrobsonr.domain.SconTTlg;
import br.com.adpmnet.mzrobsonr.repository.SconTTlgRepository;
import br.com.adpmnet.mzrobsonr.web.rest.errors.BadRequestAlertException;
import br.com.adpmnet.mzrobsonr.web.rest.util.HeaderUtil;
import br.com.adpmnet.mzrobsonr.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SconTTlg.
 */
@RestController
@RequestMapping("/api")
public class SconTTlgResource {

    private final Logger log = LoggerFactory.getLogger(SconTTlgResource.class);

    private static final String ENTITY_NAME = "sconTTlg";

    private final SconTTlgRepository sconTTlgRepository;

    public SconTTlgResource(SconTTlgRepository sconTTlgRepository) {
        this.sconTTlgRepository = sconTTlgRepository;
    }

    /**
     * POST  /scon-t-tlgs : Create a new sconTTlg.
     *
     * @param sconTTlg the sconTTlg to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sconTTlg, or with status 400 (Bad Request) if the sconTTlg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scon-t-tlgs")
    @Timed
    public ResponseEntity<SconTTlg> createSconTTlg(@RequestBody SconTTlg sconTTlg) throws URISyntaxException {
        log.debug("REST request to save SconTTlg : {}", sconTTlg);
        if (sconTTlg.getId() != null) {
            throw new BadRequestAlertException("A new sconTTlg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SconTTlg result = sconTTlgRepository.save(sconTTlg);
        return ResponseEntity.created(new URI("/api/scon-t-tlgs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scon-t-tlgs : Updates an existing sconTTlg.
     *
     * @param sconTTlg the sconTTlg to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sconTTlg,
     * or with status 400 (Bad Request) if the sconTTlg is not valid,
     * or with status 500 (Internal Server Error) if the sconTTlg couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scon-t-tlgs")
    @Timed
    public ResponseEntity<SconTTlg> updateSconTTlg(@RequestBody SconTTlg sconTTlg) throws URISyntaxException {
        log.debug("REST request to update SconTTlg : {}", sconTTlg);
        if (sconTTlg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SconTTlg result = sconTTlgRepository.save(sconTTlg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sconTTlg.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scon-t-tlgs : get all the sconTTlgs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sconTTlgs in body
     */
    @GetMapping("/scon-t-tlgs")
    @Timed
    public ResponseEntity<List<SconTTlg>> getAllSconTTlgs(Pageable pageable) {
        log.debug("REST request to get a page of SconTTlgs");
        Page<SconTTlg> page = sconTTlgRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/scon-t-tlgs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /scon-t-tlgs/:id : get the "id" sconTTlg.
     *
     * @param id the id of the sconTTlg to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sconTTlg, or with status 404 (Not Found)
     */
    @GetMapping("/scon-t-tlgs/{id}")
    @Timed
    public ResponseEntity<SconTTlg> getSconTTlg(@PathVariable Long id) {
        log.debug("REST request to get SconTTlg : {}", id);
        Optional<SconTTlg> sconTTlg = sconTTlgRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sconTTlg);
    }

    /**
     * DELETE  /scon-t-tlgs/:id : delete the "id" sconTTlg.
     *
     * @param id the id of the sconTTlg to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scon-t-tlgs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSconTTlg(@PathVariable Long id) {
        log.debug("REST request to delete SconTTlg : {}", id);

        sconTTlgRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
