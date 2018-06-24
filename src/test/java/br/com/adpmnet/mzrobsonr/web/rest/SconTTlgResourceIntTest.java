package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTTlg;
import br.com.adpmnet.mzrobsonr.repository.SconTTlgRepository;
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
 * Test class for the SconTTlgResource REST controller.
 *
 * @see SconTTlgResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTTlgResourceIntTest {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDTLG = 1;
    private static final Integer UPDATED_IDTLG = 2;

    private static final Integer DEFAULT_IDDIC = 1;
    private static final Integer UPDATED_IDDIC = 2;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTTlgRepository sconTTlgRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTTlgMockMvc;

    private SconTTlg sconTTlg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTTlgResource sconTTlgResource = new SconTTlgResource(sconTTlgRepository);
        this.restSconTTlgMockMvc = MockMvcBuilders.standaloneSetup(sconTTlgResource)
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
    public static SconTTlg createEntity(EntityManager em) {
        SconTTlg sconTTlg = new SconTTlg()
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO)
            .idtlg(DEFAULT_IDTLG)
            .iddic(DEFAULT_IDDIC)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTTlg;
    }

    @Before
    public void initTest() {
        sconTTlg = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTTlg() throws Exception {
        int databaseSizeBeforeCreate = sconTTlgRepository.findAll().size();

        // Create the SconTTlg
        restSconTTlgMockMvc.perform(post("/api/scon-t-tlgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTTlg)))
            .andExpect(status().isCreated());

        // Validate the SconTTlg in the database
        List<SconTTlg> sconTTlgList = sconTTlgRepository.findAll();
        assertThat(sconTTlgList).hasSize(databaseSizeBeforeCreate + 1);
        SconTTlg testSconTTlg = sconTTlgList.get(sconTTlgList.size() - 1);
        assertThat(testSconTTlg.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testSconTTlg.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTTlg.getIdtlg()).isEqualTo(DEFAULT_IDTLG);
        assertThat(testSconTTlg.getIddic()).isEqualTo(DEFAULT_IDDIC);
        assertThat(testSconTTlg.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTTlg.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTTlgWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTTlgRepository.findAll().size();

        // Create the SconTTlg with an existing ID
        sconTTlg.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTTlgMockMvc.perform(post("/api/scon-t-tlgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTTlg)))
            .andExpect(status().isBadRequest());

        // Validate the SconTTlg in the database
        List<SconTTlg> sconTTlgList = sconTTlgRepository.findAll();
        assertThat(sconTTlgList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTTlgs() throws Exception {
        // Initialize the database
        sconTTlgRepository.saveAndFlush(sconTTlg);

        // Get all the sconTTlgList
        restSconTTlgMockMvc.perform(get("/api/scon-t-tlgs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTTlg.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idtlg").value(hasItem(DEFAULT_IDTLG)))
            .andExpect(jsonPath("$.[*].iddic").value(hasItem(DEFAULT_IDDIC)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTTlg() throws Exception {
        // Initialize the database
        sconTTlgRepository.saveAndFlush(sconTTlg);

        // Get the sconTTlg
        restSconTTlgMockMvc.perform(get("/api/scon-t-tlgs/{id}", sconTTlg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTTlg.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idtlg").value(DEFAULT_IDTLG))
            .andExpect(jsonPath("$.iddic").value(DEFAULT_IDDIC))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTTlg() throws Exception {
        // Get the sconTTlg
        restSconTTlgMockMvc.perform(get("/api/scon-t-tlgs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTTlg() throws Exception {
        // Initialize the database
        sconTTlgRepository.saveAndFlush(sconTTlg);

        int databaseSizeBeforeUpdate = sconTTlgRepository.findAll().size();

        // Update the sconTTlg
        SconTTlg updatedSconTTlg = sconTTlgRepository.findById(sconTTlg.getId()).get();
        // Disconnect from session so that the updates on updatedSconTTlg are not directly saved in db
        em.detach(updatedSconTTlg);
        updatedSconTTlg
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO)
            .idtlg(UPDATED_IDTLG)
            .iddic(UPDATED_IDDIC)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTTlgMockMvc.perform(put("/api/scon-t-tlgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTTlg)))
            .andExpect(status().isOk());

        // Validate the SconTTlg in the database
        List<SconTTlg> sconTTlgList = sconTTlgRepository.findAll();
        assertThat(sconTTlgList).hasSize(databaseSizeBeforeUpdate);
        SconTTlg testSconTTlg = sconTTlgList.get(sconTTlgList.size() - 1);
        assertThat(testSconTTlg.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testSconTTlg.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTTlg.getIdtlg()).isEqualTo(UPDATED_IDTLG);
        assertThat(testSconTTlg.getIddic()).isEqualTo(UPDATED_IDDIC);
        assertThat(testSconTTlg.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTTlg.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTTlg() throws Exception {
        int databaseSizeBeforeUpdate = sconTTlgRepository.findAll().size();

        // Create the SconTTlg

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTTlgMockMvc.perform(put("/api/scon-t-tlgs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTTlg)))
            .andExpect(status().isBadRequest());

        // Validate the SconTTlg in the database
        List<SconTTlg> sconTTlgList = sconTTlgRepository.findAll();
        assertThat(sconTTlgList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTTlg() throws Exception {
        // Initialize the database
        sconTTlgRepository.saveAndFlush(sconTTlg);

        int databaseSizeBeforeDelete = sconTTlgRepository.findAll().size();

        // Get the sconTTlg
        restSconTTlgMockMvc.perform(delete("/api/scon-t-tlgs/{id}", sconTTlg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTTlg> sconTTlgList = sconTTlgRepository.findAll();
        assertThat(sconTTlgList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTTlg.class);
        SconTTlg sconTTlg1 = new SconTTlg();
        sconTTlg1.setId(1L);
        SconTTlg sconTTlg2 = new SconTTlg();
        sconTTlg2.setId(sconTTlg1.getId());
        assertThat(sconTTlg1).isEqualTo(sconTTlg2);
        sconTTlg2.setId(2L);
        assertThat(sconTTlg1).isNotEqualTo(sconTTlg2);
        sconTTlg1.setId(null);
        assertThat(sconTTlg1).isNotEqualTo(sconTTlg2);
    }
}
