package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTCon;
import br.com.adpmnet.mzrobsonr.repository.SconTConRepository;
import br.com.adpmnet.mzrobsonr.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static br.com.adpmnet.mzrobsonr.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SconTConResource REST controller.
 *
 * @see SconTConResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTConResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDLGBC = 1;
    private static final Integer UPDATED_IDLGBC = 2;

    private static final Integer DEFAULT_IDLGBR = 1;
    private static final Integer UPDATED_IDLGBR = 2;

    @Autowired
    private SconTConRepository sconTConRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTConMockMvc;

    private SconTCon sconTCon;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTConResource sconTConResource = new SconTConResource(sconTConRepository);
        this.restSconTConMockMvc = MockMvcBuilders.standaloneSetup(sconTConResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SconTCon createEntity(EntityManager em) {
        SconTCon sconTCon = new SconTCon()
            .nome(DEFAULT_NOME)
            .email(DEFAULT_EMAIL)
            .idlgbc(DEFAULT_IDLGBC)
            .idlgbr(DEFAULT_IDLGBR);
        return sconTCon;
    }

    @Before
    public void initTest() {
        sconTCon = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTCon() throws Exception {
        int databaseSizeBeforeCreate = sconTConRepository.findAll().size();

        // Create the SconTCon
        restSconTConMockMvc.perform(post("/api/scon-t-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCon)))
            .andExpect(status().isCreated());

        // Validate the SconTCon in the database
        List<SconTCon> sconTConList = sconTConRepository.findAll();
        assertThat(sconTConList).hasSize(databaseSizeBeforeCreate + 1);
        SconTCon testSconTCon = sconTConList.get(sconTConList.size() - 1);
        assertThat(testSconTCon.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testSconTCon.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSconTCon.getIdlgbc()).isEqualTo(DEFAULT_IDLGBC);
        assertThat(testSconTCon.getIdlgbr()).isEqualTo(DEFAULT_IDLGBR);
    }

    @Test
    @Transactional
    public void createSconTConWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTConRepository.findAll().size();

        // Create the SconTCon with an existing ID
        sconTCon.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTConMockMvc.perform(post("/api/scon-t-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCon)))
            .andExpect(status().isBadRequest());

        // Validate the SconTCon in the database
        List<SconTCon> sconTConList = sconTConRepository.findAll();
        assertThat(sconTConList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTCons() throws Exception {
        // Initialize the database
        sconTConRepository.saveAndFlush(sconTCon);

        // Get all the sconTConList
        restSconTConMockMvc.perform(get("/api/scon-t-cons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTCon.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].idlgbc").value(hasItem(DEFAULT_IDLGBC)))
            .andExpect(jsonPath("$.[*].idlgbr").value(hasItem(DEFAULT_IDLGBR)));
    }
    

    @Test
    @Transactional
    public void getSconTCon() throws Exception {
        // Initialize the database
        sconTConRepository.saveAndFlush(sconTCon);

        // Get the sconTCon
        restSconTConMockMvc.perform(get("/api/scon-t-cons/{id}", sconTCon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTCon.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.idlgbc").value(DEFAULT_IDLGBC))
            .andExpect(jsonPath("$.idlgbr").value(DEFAULT_IDLGBR));
    }
    @Test
    @Transactional
    public void getNonExistingSconTCon() throws Exception {
        // Get the sconTCon
        restSconTConMockMvc.perform(get("/api/scon-t-cons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTCon() throws Exception {
        // Initialize the database
        sconTConRepository.saveAndFlush(sconTCon);

        int databaseSizeBeforeUpdate = sconTConRepository.findAll().size();

        // Update the sconTCon
        SconTCon updatedSconTCon = sconTConRepository.findById(sconTCon.getId()).get();
        // Disconnect from session so that the updates on updatedSconTCon are not directly saved in db
        em.detach(updatedSconTCon);
        updatedSconTCon
            .nome(UPDATED_NOME)
            .email(UPDATED_EMAIL)
            .idlgbc(UPDATED_IDLGBC)
            .idlgbr(UPDATED_IDLGBR);

        restSconTConMockMvc.perform(put("/api/scon-t-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTCon)))
            .andExpect(status().isOk());

        // Validate the SconTCon in the database
        List<SconTCon> sconTConList = sconTConRepository.findAll();
        assertThat(sconTConList).hasSize(databaseSizeBeforeUpdate);
        SconTCon testSconTCon = sconTConList.get(sconTConList.size() - 1);
        assertThat(testSconTCon.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testSconTCon.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSconTCon.getIdlgbc()).isEqualTo(UPDATED_IDLGBC);
        assertThat(testSconTCon.getIdlgbr()).isEqualTo(UPDATED_IDLGBR);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTCon() throws Exception {
        int databaseSizeBeforeUpdate = sconTConRepository.findAll().size();

        // Create the SconTCon

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTConMockMvc.perform(put("/api/scon-t-cons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCon)))
            .andExpect(status().isBadRequest());

        // Validate the SconTCon in the database
        List<SconTCon> sconTConList = sconTConRepository.findAll();
        assertThat(sconTConList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTCon() throws Exception {
        // Initialize the database
        sconTConRepository.saveAndFlush(sconTCon);

        int databaseSizeBeforeDelete = sconTConRepository.findAll().size();

        // Get the sconTCon
        restSconTConMockMvc.perform(delete("/api/scon-t-cons/{id}", sconTCon.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTCon> sconTConList = sconTConRepository.findAll();
        assertThat(sconTConList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTCon.class);
        SconTCon sconTCon1 = new SconTCon();
        sconTCon1.setId(1L);
        SconTCon sconTCon2 = new SconTCon();
        sconTCon2.setId(sconTCon1.getId());
        assertThat(sconTCon1).isEqualTo(sconTCon2);
        sconTCon2.setId(2L);
        assertThat(sconTCon1).isNotEqualTo(sconTCon2);
        sconTCon1.setId(null);
        assertThat(sconTCon1).isNotEqualTo(sconTCon2);
    }
}
