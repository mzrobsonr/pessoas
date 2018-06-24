package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTPai;
import br.com.adpmnet.mzrobsonr.repository.SconTPaiRepository;
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
 * Test class for the SconTPaiResource REST controller.
 *
 * @see SconTPaiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTPaiResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDPAI = 1;
    private static final Integer UPDATED_IDPAI = 2;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTPaiRepository sconTPaiRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTPaiMockMvc;

    private SconTPai sconTPai;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTPaiResource sconTPaiResource = new SconTPaiResource(sconTPaiRepository);
        this.restSconTPaiMockMvc = MockMvcBuilders.standaloneSetup(sconTPaiResource)
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
    public static SconTPai createEntity(EntityManager em) {
        SconTPai sconTPai = new SconTPai()
            .descricao(DEFAULT_DESCRICAO)
            .idpai(DEFAULT_IDPAI)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTPai;
    }

    @Before
    public void initTest() {
        sconTPai = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTPai() throws Exception {
        int databaseSizeBeforeCreate = sconTPaiRepository.findAll().size();

        // Create the SconTPai
        restSconTPaiMockMvc.perform(post("/api/scon-t-pais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTPai)))
            .andExpect(status().isCreated());

        // Validate the SconTPai in the database
        List<SconTPai> sconTPaiList = sconTPaiRepository.findAll();
        assertThat(sconTPaiList).hasSize(databaseSizeBeforeCreate + 1);
        SconTPai testSconTPai = sconTPaiList.get(sconTPaiList.size() - 1);
        assertThat(testSconTPai.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTPai.getIdpai()).isEqualTo(DEFAULT_IDPAI);
        assertThat(testSconTPai.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTPai.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTPaiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTPaiRepository.findAll().size();

        // Create the SconTPai with an existing ID
        sconTPai.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTPaiMockMvc.perform(post("/api/scon-t-pais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTPai)))
            .andExpect(status().isBadRequest());

        // Validate the SconTPai in the database
        List<SconTPai> sconTPaiList = sconTPaiRepository.findAll();
        assertThat(sconTPaiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTPais() throws Exception {
        // Initialize the database
        sconTPaiRepository.saveAndFlush(sconTPai);

        // Get all the sconTPaiList
        restSconTPaiMockMvc.perform(get("/api/scon-t-pais?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTPai.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idpai").value(hasItem(DEFAULT_IDPAI)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTPai() throws Exception {
        // Initialize the database
        sconTPaiRepository.saveAndFlush(sconTPai);

        // Get the sconTPai
        restSconTPaiMockMvc.perform(get("/api/scon-t-pais/{id}", sconTPai.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTPai.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idpai").value(DEFAULT_IDPAI))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTPai() throws Exception {
        // Get the sconTPai
        restSconTPaiMockMvc.perform(get("/api/scon-t-pais/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTPai() throws Exception {
        // Initialize the database
        sconTPaiRepository.saveAndFlush(sconTPai);

        int databaseSizeBeforeUpdate = sconTPaiRepository.findAll().size();

        // Update the sconTPai
        SconTPai updatedSconTPai = sconTPaiRepository.findById(sconTPai.getId()).get();
        // Disconnect from session so that the updates on updatedSconTPai are not directly saved in db
        em.detach(updatedSconTPai);
        updatedSconTPai
            .descricao(UPDATED_DESCRICAO)
            .idpai(UPDATED_IDPAI)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTPaiMockMvc.perform(put("/api/scon-t-pais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTPai)))
            .andExpect(status().isOk());

        // Validate the SconTPai in the database
        List<SconTPai> sconTPaiList = sconTPaiRepository.findAll();
        assertThat(sconTPaiList).hasSize(databaseSizeBeforeUpdate);
        SconTPai testSconTPai = sconTPaiList.get(sconTPaiList.size() - 1);
        assertThat(testSconTPai.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTPai.getIdpai()).isEqualTo(UPDATED_IDPAI);
        assertThat(testSconTPai.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTPai.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTPai() throws Exception {
        int databaseSizeBeforeUpdate = sconTPaiRepository.findAll().size();

        // Create the SconTPai

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTPaiMockMvc.perform(put("/api/scon-t-pais")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTPai)))
            .andExpect(status().isBadRequest());

        // Validate the SconTPai in the database
        List<SconTPai> sconTPaiList = sconTPaiRepository.findAll();
        assertThat(sconTPaiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTPai() throws Exception {
        // Initialize the database
        sconTPaiRepository.saveAndFlush(sconTPai);

        int databaseSizeBeforeDelete = sconTPaiRepository.findAll().size();

        // Get the sconTPai
        restSconTPaiMockMvc.perform(delete("/api/scon-t-pais/{id}", sconTPai.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTPai> sconTPaiList = sconTPaiRepository.findAll();
        assertThat(sconTPaiList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTPai.class);
        SconTPai sconTPai1 = new SconTPai();
        sconTPai1.setId(1L);
        SconTPai sconTPai2 = new SconTPai();
        sconTPai2.setId(sconTPai1.getId());
        assertThat(sconTPai1).isEqualTo(sconTPai2);
        sconTPai2.setId(2L);
        assertThat(sconTPai1).isNotEqualTo(sconTPai2);
        sconTPai1.setId(null);
        assertThat(sconTPai1).isNotEqualTo(sconTPai2);
    }
}
