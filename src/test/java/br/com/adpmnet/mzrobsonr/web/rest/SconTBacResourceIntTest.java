package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTBac;
import br.com.adpmnet.mzrobsonr.repository.SconTBacRepository;
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
 * Test class for the SconTBacResource REST controller.
 *
 * @see SconTBacResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTBacResourceIntTest {

    private static final Integer DEFAULT_IDBAC = 1;
    private static final Integer UPDATED_IDBAC = 2;

    private static final Integer DEFAULT_IDBAI = 1;
    private static final Integer UPDATED_IDBAI = 2;

    private static final Integer DEFAULT_IDCID = 1;
    private static final Integer UPDATED_IDCID = 2;

    private static final String DEFAULT_CEPCID = "AAAAAAAAAA";
    private static final String UPDATED_CEPCID = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTBacRepository sconTBacRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTBacMockMvc;

    private SconTBac sconTBac;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTBacResource sconTBacResource = new SconTBacResource(sconTBacRepository);
        this.restSconTBacMockMvc = MockMvcBuilders.standaloneSetup(sconTBacResource)
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
    public static SconTBac createEntity(EntityManager em) {
        SconTBac sconTBac = new SconTBac()
            .idbac(DEFAULT_IDBAC)
            .idbai(DEFAULT_IDBAI)
            .idcid(DEFAULT_IDCID)
            .cepcid(DEFAULT_CEPCID)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTBac;
    }

    @Before
    public void initTest() {
        sconTBac = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTBac() throws Exception {
        int databaseSizeBeforeCreate = sconTBacRepository.findAll().size();

        // Create the SconTBac
        restSconTBacMockMvc.perform(post("/api/scon-t-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBac)))
            .andExpect(status().isCreated());

        // Validate the SconTBac in the database
        List<SconTBac> sconTBacList = sconTBacRepository.findAll();
        assertThat(sconTBacList).hasSize(databaseSizeBeforeCreate + 1);
        SconTBac testSconTBac = sconTBacList.get(sconTBacList.size() - 1);
        assertThat(testSconTBac.getIdbac()).isEqualTo(DEFAULT_IDBAC);
        assertThat(testSconTBac.getIdbai()).isEqualTo(DEFAULT_IDBAI);
        assertThat(testSconTBac.getIdcid()).isEqualTo(DEFAULT_IDCID);
        assertThat(testSconTBac.getCepcid()).isEqualTo(DEFAULT_CEPCID);
        assertThat(testSconTBac.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTBac.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTBacWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTBacRepository.findAll().size();

        // Create the SconTBac with an existing ID
        sconTBac.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTBacMockMvc.perform(post("/api/scon-t-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBac)))
            .andExpect(status().isBadRequest());

        // Validate the SconTBac in the database
        List<SconTBac> sconTBacList = sconTBacRepository.findAll();
        assertThat(sconTBacList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTBacs() throws Exception {
        // Initialize the database
        sconTBacRepository.saveAndFlush(sconTBac);

        // Get all the sconTBacList
        restSconTBacMockMvc.perform(get("/api/scon-t-bacs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTBac.getId().intValue())))
            .andExpect(jsonPath("$.[*].idbac").value(hasItem(DEFAULT_IDBAC)))
            .andExpect(jsonPath("$.[*].idbai").value(hasItem(DEFAULT_IDBAI)))
            .andExpect(jsonPath("$.[*].idcid").value(hasItem(DEFAULT_IDCID)))
            .andExpect(jsonPath("$.[*].cepcid").value(hasItem(DEFAULT_CEPCID.toString())))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTBac() throws Exception {
        // Initialize the database
        sconTBacRepository.saveAndFlush(sconTBac);

        // Get the sconTBac
        restSconTBacMockMvc.perform(get("/api/scon-t-bacs/{id}", sconTBac.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTBac.getId().intValue()))
            .andExpect(jsonPath("$.idbac").value(DEFAULT_IDBAC))
            .andExpect(jsonPath("$.idbai").value(DEFAULT_IDBAI))
            .andExpect(jsonPath("$.idcid").value(DEFAULT_IDCID))
            .andExpect(jsonPath("$.cepcid").value(DEFAULT_CEPCID.toString()))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTBac() throws Exception {
        // Get the sconTBac
        restSconTBacMockMvc.perform(get("/api/scon-t-bacs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTBac() throws Exception {
        // Initialize the database
        sconTBacRepository.saveAndFlush(sconTBac);

        int databaseSizeBeforeUpdate = sconTBacRepository.findAll().size();

        // Update the sconTBac
        SconTBac updatedSconTBac = sconTBacRepository.findById(sconTBac.getId()).get();
        // Disconnect from session so that the updates on updatedSconTBac are not directly saved in db
        em.detach(updatedSconTBac);
        updatedSconTBac
            .idbac(UPDATED_IDBAC)
            .idbai(UPDATED_IDBAI)
            .idcid(UPDATED_IDCID)
            .cepcid(UPDATED_CEPCID)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTBacMockMvc.perform(put("/api/scon-t-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTBac)))
            .andExpect(status().isOk());

        // Validate the SconTBac in the database
        List<SconTBac> sconTBacList = sconTBacRepository.findAll();
        assertThat(sconTBacList).hasSize(databaseSizeBeforeUpdate);
        SconTBac testSconTBac = sconTBacList.get(sconTBacList.size() - 1);
        assertThat(testSconTBac.getIdbac()).isEqualTo(UPDATED_IDBAC);
        assertThat(testSconTBac.getIdbai()).isEqualTo(UPDATED_IDBAI);
        assertThat(testSconTBac.getIdcid()).isEqualTo(UPDATED_IDCID);
        assertThat(testSconTBac.getCepcid()).isEqualTo(UPDATED_CEPCID);
        assertThat(testSconTBac.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTBac.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTBac() throws Exception {
        int databaseSizeBeforeUpdate = sconTBacRepository.findAll().size();

        // Create the SconTBac

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTBacMockMvc.perform(put("/api/scon-t-bacs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBac)))
            .andExpect(status().isBadRequest());

        // Validate the SconTBac in the database
        List<SconTBac> sconTBacList = sconTBacRepository.findAll();
        assertThat(sconTBacList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTBac() throws Exception {
        // Initialize the database
        sconTBacRepository.saveAndFlush(sconTBac);

        int databaseSizeBeforeDelete = sconTBacRepository.findAll().size();

        // Get the sconTBac
        restSconTBacMockMvc.perform(delete("/api/scon-t-bacs/{id}", sconTBac.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTBac> sconTBacList = sconTBacRepository.findAll();
        assertThat(sconTBacList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTBac.class);
        SconTBac sconTBac1 = new SconTBac();
        sconTBac1.setId(1L);
        SconTBac sconTBac2 = new SconTBac();
        sconTBac2.setId(sconTBac1.getId());
        assertThat(sconTBac1).isEqualTo(sconTBac2);
        sconTBac2.setId(2L);
        assertThat(sconTBac1).isNotEqualTo(sconTBac2);
        sconTBac1.setId(null);
        assertThat(sconTBac1).isNotEqualTo(sconTBac2);
    }
}
