package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTLgb;
import br.com.adpmnet.mzrobsonr.repository.SconTLgbRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static br.com.adpmnet.mzrobsonr.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SconTLgbResource REST controller.
 *
 * @see SconTLgbResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTLgbResourceIntTest {

    private static final Integer DEFAULT_IDLGB = 1;
    private static final Integer UPDATED_IDLGB = 2;

    private static final Integer DEFAULT_IDLOG = 1;
    private static final Integer UPDATED_IDLOG = 2;

    private static final Integer DEFAULT_IDBAC = 1;
    private static final Integer UPDATED_IDBAC = 2;

    private static final String DEFAULT_CEPLGB = "AAAAAAAAAA";
    private static final String UPDATED_CEPLGB = "BBBBBBBBBB";

    private static final Long DEFAULT_LARGURALOG = 1L;
    private static final Long UPDATED_LARGURALOG = 2L;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTLgbRepository sconTLgbRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTLgbMockMvc;

    private SconTLgb sconTLgb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTLgbResource sconTLgbResource = new SconTLgbResource(sconTLgbRepository);
        this.restSconTLgbMockMvc = MockMvcBuilders.standaloneSetup(sconTLgbResource)
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
    public static SconTLgb createEntity(EntityManager em) {
        SconTLgb sconTLgb = new SconTLgb()
            .idlgb(DEFAULT_IDLGB)
            .idlog(DEFAULT_IDLOG)
            .idbac(DEFAULT_IDBAC)
            .ceplgb(DEFAULT_CEPLGB)
            .larguralog(DEFAULT_LARGURALOG)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTLgb;
    }

    @Before
    public void initTest() {
        sconTLgb = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTLgb() throws Exception {
        int databaseSizeBeforeCreate = sconTLgbRepository.findAll().size();

        // Create the SconTLgb
        restSconTLgbMockMvc.perform(post("/api/scon-t-lgbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLgb)))
            .andExpect(status().isCreated());

        // Validate the SconTLgb in the database
        List<SconTLgb> sconTLgbList = sconTLgbRepository.findAll();
        assertThat(sconTLgbList).hasSize(databaseSizeBeforeCreate + 1);
        SconTLgb testSconTLgb = sconTLgbList.get(sconTLgbList.size() - 1);
        assertThat(testSconTLgb.getIdlgb()).isEqualTo(DEFAULT_IDLGB);
        assertThat(testSconTLgb.getIdlog()).isEqualTo(DEFAULT_IDLOG);
        assertThat(testSconTLgb.getIdbac()).isEqualTo(DEFAULT_IDBAC);
        assertThat(testSconTLgb.getCeplgb()).isEqualTo(DEFAULT_CEPLGB);
        assertThat(testSconTLgb.getLarguralog()).isEqualTo(DEFAULT_LARGURALOG);
        assertThat(testSconTLgb.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTLgb.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTLgbWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTLgbRepository.findAll().size();

        // Create the SconTLgb with an existing ID
        sconTLgb.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTLgbMockMvc.perform(post("/api/scon-t-lgbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLgb)))
            .andExpect(status().isBadRequest());

        // Validate the SconTLgb in the database
        List<SconTLgb> sconTLgbList = sconTLgbRepository.findAll();
        assertThat(sconTLgbList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTLgbs() throws Exception {
        // Initialize the database
        sconTLgbRepository.saveAndFlush(sconTLgb);

        // Get all the sconTLgbList
        restSconTLgbMockMvc.perform(get("/api/scon-t-lgbs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTLgb.getId().intValue())))
            .andExpect(jsonPath("$.[*].idlgb").value(hasItem(DEFAULT_IDLGB)))
            .andExpect(jsonPath("$.[*].idlog").value(hasItem(DEFAULT_IDLOG)))
            .andExpect(jsonPath("$.[*].idbac").value(hasItem(DEFAULT_IDBAC)))
            .andExpect(jsonPath("$.[*].ceplgb").value(hasItem(DEFAULT_CEPLGB.toString())))
            .andExpect(jsonPath("$.[*].larguralog").value(hasItem(DEFAULT_LARGURALOG.intValue())))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTLgb() throws Exception {
        // Initialize the database
        sconTLgbRepository.saveAndFlush(sconTLgb);

        // Get the sconTLgb
        restSconTLgbMockMvc.perform(get("/api/scon-t-lgbs/{id}", sconTLgb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTLgb.getId().intValue()))
            .andExpect(jsonPath("$.idlgb").value(DEFAULT_IDLGB))
            .andExpect(jsonPath("$.idlog").value(DEFAULT_IDLOG))
            .andExpect(jsonPath("$.idbac").value(DEFAULT_IDBAC))
            .andExpect(jsonPath("$.ceplgb").value(DEFAULT_CEPLGB.toString()))
            .andExpect(jsonPath("$.larguralog").value(DEFAULT_LARGURALOG.intValue()))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTLgb() throws Exception {
        // Get the sconTLgb
        restSconTLgbMockMvc.perform(get("/api/scon-t-lgbs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTLgb() throws Exception {
        // Initialize the database
        sconTLgbRepository.saveAndFlush(sconTLgb);

        int databaseSizeBeforeUpdate = sconTLgbRepository.findAll().size();

        // Update the sconTLgb
        SconTLgb updatedSconTLgb = sconTLgbRepository.findById(sconTLgb.getId()).get();
        // Disconnect from session so that the updates on updatedSconTLgb are not directly saved in db
        em.detach(updatedSconTLgb);
        updatedSconTLgb
            .idlgb(UPDATED_IDLGB)
            .idlog(UPDATED_IDLOG)
            .idbac(UPDATED_IDBAC)
            .ceplgb(UPDATED_CEPLGB)
            .larguralog(UPDATED_LARGURALOG)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTLgbMockMvc.perform(put("/api/scon-t-lgbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTLgb)))
            .andExpect(status().isOk());

        // Validate the SconTLgb in the database
        List<SconTLgb> sconTLgbList = sconTLgbRepository.findAll();
        assertThat(sconTLgbList).hasSize(databaseSizeBeforeUpdate);
        SconTLgb testSconTLgb = sconTLgbList.get(sconTLgbList.size() - 1);
        assertThat(testSconTLgb.getIdlgb()).isEqualTo(UPDATED_IDLGB);
        assertThat(testSconTLgb.getIdlog()).isEqualTo(UPDATED_IDLOG);
        assertThat(testSconTLgb.getIdbac()).isEqualTo(UPDATED_IDBAC);
        assertThat(testSconTLgb.getCeplgb()).isEqualTo(UPDATED_CEPLGB);
        assertThat(testSconTLgb.getLarguralog()).isEqualTo(UPDATED_LARGURALOG);
        assertThat(testSconTLgb.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTLgb.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTLgb() throws Exception {
        int databaseSizeBeforeUpdate = sconTLgbRepository.findAll().size();

        // Create the SconTLgb

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTLgbMockMvc.perform(put("/api/scon-t-lgbs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLgb)))
            .andExpect(status().isBadRequest());

        // Validate the SconTLgb in the database
        List<SconTLgb> sconTLgbList = sconTLgbRepository.findAll();
        assertThat(sconTLgbList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTLgb() throws Exception {
        // Initialize the database
        sconTLgbRepository.saveAndFlush(sconTLgb);

        int databaseSizeBeforeDelete = sconTLgbRepository.findAll().size();

        // Get the sconTLgb
        restSconTLgbMockMvc.perform(delete("/api/scon-t-lgbs/{id}", sconTLgb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTLgb> sconTLgbList = sconTLgbRepository.findAll();
        assertThat(sconTLgbList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTLgb.class);
        SconTLgb sconTLgb1 = new SconTLgb();
        sconTLgb1.setId(1L);
        SconTLgb sconTLgb2 = new SconTLgb();
        sconTLgb2.setId(sconTLgb1.getId());
        assertThat(sconTLgb1).isEqualTo(sconTLgb2);
        sconTLgb2.setId(2L);
        assertThat(sconTLgb1).isNotEqualTo(sconTLgb2);
        sconTLgb1.setId(null);
        assertThat(sconTLgb1).isNotEqualTo(sconTLgb2);
    }
}
