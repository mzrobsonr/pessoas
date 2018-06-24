package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTBai;
import br.com.adpmnet.mzrobsonr.repository.SconTBaiRepository;
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
 * Test class for the SconTBaiResource REST controller.
 *
 * @see SconTBaiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTBaiResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDBAI = 1;
    private static final Integer UPDATED_IDBAI = 2;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTBaiRepository sconTBaiRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTBaiMockMvc;

    private SconTBai sconTBai;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTBaiResource sconTBaiResource = new SconTBaiResource(sconTBaiRepository);
        this.restSconTBaiMockMvc = MockMvcBuilders.standaloneSetup(sconTBaiResource)
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
    public static SconTBai createEntity(EntityManager em) {
        SconTBai sconTBai = new SconTBai()
            .descricao(DEFAULT_DESCRICAO)
            .idbai(DEFAULT_IDBAI)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTBai;
    }

    @Before
    public void initTest() {
        sconTBai = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTBai() throws Exception {
        int databaseSizeBeforeCreate = sconTBaiRepository.findAll().size();

        // Create the SconTBai
        restSconTBaiMockMvc.perform(post("/api/scon-t-bais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBai)))
            .andExpect(status().isCreated());

        // Validate the SconTBai in the database
        List<SconTBai> sconTBaiList = sconTBaiRepository.findAll();
        assertThat(sconTBaiList).hasSize(databaseSizeBeforeCreate + 1);
        SconTBai testSconTBai = sconTBaiList.get(sconTBaiList.size() - 1);
        assertThat(testSconTBai.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTBai.getIdbai()).isEqualTo(DEFAULT_IDBAI);
        assertThat(testSconTBai.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTBai.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTBaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTBaiRepository.findAll().size();

        // Create the SconTBai with an existing ID
        sconTBai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTBaiMockMvc.perform(post("/api/scon-t-bais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBai)))
            .andExpect(status().isBadRequest());

        // Validate the SconTBai in the database
        List<SconTBai> sconTBaiList = sconTBaiRepository.findAll();
        assertThat(sconTBaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTBais() throws Exception {
        // Initialize the database
        sconTBaiRepository.saveAndFlush(sconTBai);

        // Get all the sconTBaiList
        restSconTBaiMockMvc.perform(get("/api/scon-t-bais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTBai.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idbai").value(hasItem(DEFAULT_IDBAI)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTBai() throws Exception {
        // Initialize the database
        sconTBaiRepository.saveAndFlush(sconTBai);

        // Get the sconTBai
        restSconTBaiMockMvc.perform(get("/api/scon-t-bais/{id}", sconTBai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTBai.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idbai").value(DEFAULT_IDBAI))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTBai() throws Exception {
        // Get the sconTBai
        restSconTBaiMockMvc.perform(get("/api/scon-t-bais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTBai() throws Exception {
        // Initialize the database
        sconTBaiRepository.saveAndFlush(sconTBai);

        int databaseSizeBeforeUpdate = sconTBaiRepository.findAll().size();

        // Update the sconTBai
        SconTBai updatedSconTBai = sconTBaiRepository.findById(sconTBai.getId()).get();
        // Disconnect from session so that the updates on updatedSconTBai are not directly saved in db
        em.detach(updatedSconTBai);
        updatedSconTBai
            .descricao(UPDATED_DESCRICAO)
            .idbai(UPDATED_IDBAI)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTBaiMockMvc.perform(put("/api/scon-t-bais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTBai)))
            .andExpect(status().isOk());

        // Validate the SconTBai in the database
        List<SconTBai> sconTBaiList = sconTBaiRepository.findAll();
        assertThat(sconTBaiList).hasSize(databaseSizeBeforeUpdate);
        SconTBai testSconTBai = sconTBaiList.get(sconTBaiList.size() - 1);
        assertThat(testSconTBai.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTBai.getIdbai()).isEqualTo(UPDATED_IDBAI);
        assertThat(testSconTBai.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTBai.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTBai() throws Exception {
        int databaseSizeBeforeUpdate = sconTBaiRepository.findAll().size();

        // Create the SconTBai

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTBaiMockMvc.perform(put("/api/scon-t-bais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTBai)))
            .andExpect(status().isBadRequest());

        // Validate the SconTBai in the database
        List<SconTBai> sconTBaiList = sconTBaiRepository.findAll();
        assertThat(sconTBaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTBai() throws Exception {
        // Initialize the database
        sconTBaiRepository.saveAndFlush(sconTBai);

        int databaseSizeBeforeDelete = sconTBaiRepository.findAll().size();

        // Get the sconTBai
        restSconTBaiMockMvc.perform(delete("/api/scon-t-bais/{id}", sconTBai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTBai> sconTBaiList = sconTBaiRepository.findAll();
        assertThat(sconTBaiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTBai.class);
        SconTBai sconTBai1 = new SconTBai();
        sconTBai1.setId(1L);
        SconTBai sconTBai2 = new SconTBai();
        sconTBai2.setId(sconTBai1.getId());
        assertThat(sconTBai1).isEqualTo(sconTBai2);
        sconTBai2.setId(2L);
        assertThat(sconTBai1).isNotEqualTo(sconTBai2);
        sconTBai1.setId(null);
        assertThat(sconTBai1).isNotEqualTo(sconTBai2);
    }
}
