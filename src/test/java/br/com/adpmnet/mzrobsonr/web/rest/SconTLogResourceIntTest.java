package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTLog;
import br.com.adpmnet.mzrobsonr.repository.SconTLogRepository;
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
 * Test class for the SconTLogResource REST controller.
 *
 * @see SconTLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTLogResourceIntTest {

    private static final String DEFAULT_CEPLOG = "AAAAAAAAAA";
    private static final String UPDATED_CEPLOG = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDLOG = 1;
    private static final Integer UPDATED_IDLOG = 2;

    private static final Integer DEFAULT_IDTLG = 1;
    private static final Integer UPDATED_IDTLG = 2;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTLogRepository sconTLogRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTLogMockMvc;

    private SconTLog sconTLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTLogResource sconTLogResource = new SconTLogResource(sconTLogRepository);
        this.restSconTLogMockMvc = MockMvcBuilders.standaloneSetup(sconTLogResource)
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
    public static SconTLog createEntity(EntityManager em) {
        SconTLog sconTLog = new SconTLog()
            .ceplog(DEFAULT_CEPLOG)
            .descricao(DEFAULT_DESCRICAO)
            .idlog(DEFAULT_IDLOG)
            .idtlg(DEFAULT_IDTLG)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTLog;
    }

    @Before
    public void initTest() {
        sconTLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTLog() throws Exception {
        int databaseSizeBeforeCreate = sconTLogRepository.findAll().size();

        // Create the SconTLog
        restSconTLogMockMvc.perform(post("/api/scon-t-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLog)))
            .andExpect(status().isCreated());

        // Validate the SconTLog in the database
        List<SconTLog> sconTLogList = sconTLogRepository.findAll();
        assertThat(sconTLogList).hasSize(databaseSizeBeforeCreate + 1);
        SconTLog testSconTLog = sconTLogList.get(sconTLogList.size() - 1);
        assertThat(testSconTLog.getCeplog()).isEqualTo(DEFAULT_CEPLOG);
        assertThat(testSconTLog.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTLog.getIdlog()).isEqualTo(DEFAULT_IDLOG);
        assertThat(testSconTLog.getIdtlg()).isEqualTo(DEFAULT_IDTLG);
        assertThat(testSconTLog.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTLog.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTLogRepository.findAll().size();

        // Create the SconTLog with an existing ID
        sconTLog.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTLogMockMvc.perform(post("/api/scon-t-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLog)))
            .andExpect(status().isBadRequest());

        // Validate the SconTLog in the database
        List<SconTLog> sconTLogList = sconTLogRepository.findAll();
        assertThat(sconTLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTLogs() throws Exception {
        // Initialize the database
        sconTLogRepository.saveAndFlush(sconTLog);

        // Get all the sconTLogList
        restSconTLogMockMvc.perform(get("/api/scon-t-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].ceplog").value(hasItem(DEFAULT_CEPLOG.toString())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idlog").value(hasItem(DEFAULT_IDLOG)))
            .andExpect(jsonPath("$.[*].idtlg").value(hasItem(DEFAULT_IDTLG)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTLog() throws Exception {
        // Initialize the database
        sconTLogRepository.saveAndFlush(sconTLog);

        // Get the sconTLog
        restSconTLogMockMvc.perform(get("/api/scon-t-logs/{id}", sconTLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTLog.getId().intValue()))
            .andExpect(jsonPath("$.ceplog").value(DEFAULT_CEPLOG.toString()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idlog").value(DEFAULT_IDLOG))
            .andExpect(jsonPath("$.idtlg").value(DEFAULT_IDTLG))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTLog() throws Exception {
        // Get the sconTLog
        restSconTLogMockMvc.perform(get("/api/scon-t-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTLog() throws Exception {
        // Initialize the database
        sconTLogRepository.saveAndFlush(sconTLog);

        int databaseSizeBeforeUpdate = sconTLogRepository.findAll().size();

        // Update the sconTLog
        SconTLog updatedSconTLog = sconTLogRepository.findById(sconTLog.getId()).get();
        // Disconnect from session so that the updates on updatedSconTLog are not directly saved in db
        em.detach(updatedSconTLog);
        updatedSconTLog
            .ceplog(UPDATED_CEPLOG)
            .descricao(UPDATED_DESCRICAO)
            .idlog(UPDATED_IDLOG)
            .idtlg(UPDATED_IDTLG)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTLogMockMvc.perform(put("/api/scon-t-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTLog)))
            .andExpect(status().isOk());

        // Validate the SconTLog in the database
        List<SconTLog> sconTLogList = sconTLogRepository.findAll();
        assertThat(sconTLogList).hasSize(databaseSizeBeforeUpdate);
        SconTLog testSconTLog = sconTLogList.get(sconTLogList.size() - 1);
        assertThat(testSconTLog.getCeplog()).isEqualTo(UPDATED_CEPLOG);
        assertThat(testSconTLog.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTLog.getIdlog()).isEqualTo(UPDATED_IDLOG);
        assertThat(testSconTLog.getIdtlg()).isEqualTo(UPDATED_IDTLG);
        assertThat(testSconTLog.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTLog.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTLog() throws Exception {
        int databaseSizeBeforeUpdate = sconTLogRepository.findAll().size();

        // Create the SconTLog

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTLogMockMvc.perform(put("/api/scon-t-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTLog)))
            .andExpect(status().isBadRequest());

        // Validate the SconTLog in the database
        List<SconTLog> sconTLogList = sconTLogRepository.findAll();
        assertThat(sconTLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTLog() throws Exception {
        // Initialize the database
        sconTLogRepository.saveAndFlush(sconTLog);

        int databaseSizeBeforeDelete = sconTLogRepository.findAll().size();

        // Get the sconTLog
        restSconTLogMockMvc.perform(delete("/api/scon-t-logs/{id}", sconTLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTLog> sconTLogList = sconTLogRepository.findAll();
        assertThat(sconTLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTLog.class);
        SconTLog sconTLog1 = new SconTLog();
        sconTLog1.setId(1L);
        SconTLog sconTLog2 = new SconTLog();
        sconTLog2.setId(sconTLog1.getId());
        assertThat(sconTLog1).isEqualTo(sconTLog2);
        sconTLog2.setId(2L);
        assertThat(sconTLog1).isNotEqualTo(sconTLog2);
        sconTLog1.setId(null);
        assertThat(sconTLog1).isNotEqualTo(sconTLog2);
    }
}
