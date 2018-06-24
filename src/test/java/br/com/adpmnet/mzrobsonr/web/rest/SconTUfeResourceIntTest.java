package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTUfe;
import br.com.adpmnet.mzrobsonr.repository.SconTUfeRepository;
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
 * Test class for the SconTUfeResource REST controller.
 *
 * @see SconTUfeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTUfeResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDUFE = 1;
    private static final Integer UPDATED_IDUFE = 2;

    private static final Integer DEFAULT_IDPAI = 1;
    private static final Integer UPDATED_IDPAI = 2;

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final String DEFAULT_CODIGO_IBGE = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_IBGE = "BBBBBBBBBB";

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTUfeRepository sconTUfeRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTUfeMockMvc;

    private SconTUfe sconTUfe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTUfeResource sconTUfeResource = new SconTUfeResource(sconTUfeRepository);
        this.restSconTUfeMockMvc = MockMvcBuilders.standaloneSetup(sconTUfeResource)
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
    public static SconTUfe createEntity(EntityManager em) {
        SconTUfe sconTUfe = new SconTUfe()
            .descricao(DEFAULT_DESCRICAO)
            .idufe(DEFAULT_IDUFE)
            .idpai(DEFAULT_IDPAI)
            .codigo(DEFAULT_CODIGO)
            .codigoIbge(DEFAULT_CODIGO_IBGE)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTUfe;
    }

    @Before
    public void initTest() {
        sconTUfe = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTUfe() throws Exception {
        int databaseSizeBeforeCreate = sconTUfeRepository.findAll().size();

        // Create the SconTUfe
        restSconTUfeMockMvc.perform(post("/api/scon-t-ufes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTUfe)))
            .andExpect(status().isCreated());

        // Validate the SconTUfe in the database
        List<SconTUfe> sconTUfeList = sconTUfeRepository.findAll();
        assertThat(sconTUfeList).hasSize(databaseSizeBeforeCreate + 1);
        SconTUfe testSconTUfe = sconTUfeList.get(sconTUfeList.size() - 1);
        assertThat(testSconTUfe.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTUfe.getIdufe()).isEqualTo(DEFAULT_IDUFE);
        assertThat(testSconTUfe.getIdpai()).isEqualTo(DEFAULT_IDPAI);
        assertThat(testSconTUfe.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testSconTUfe.getCodigoIbge()).isEqualTo(DEFAULT_CODIGO_IBGE);
        assertThat(testSconTUfe.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTUfe.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTUfeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTUfeRepository.findAll().size();

        // Create the SconTUfe with an existing ID
        sconTUfe.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTUfeMockMvc.perform(post("/api/scon-t-ufes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTUfe)))
            .andExpect(status().isBadRequest());

        // Validate the SconTUfe in the database
        List<SconTUfe> sconTUfeList = sconTUfeRepository.findAll();
        assertThat(sconTUfeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTUfes() throws Exception {
        // Initialize the database
        sconTUfeRepository.saveAndFlush(sconTUfe);

        // Get all the sconTUfeList
        restSconTUfeMockMvc.perform(get("/api/scon-t-ufes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTUfe.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idufe").value(hasItem(DEFAULT_IDUFE)))
            .andExpect(jsonPath("$.[*].idpai").value(hasItem(DEFAULT_IDPAI)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].codigoIbge").value(hasItem(DEFAULT_CODIGO_IBGE.toString())))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTUfe() throws Exception {
        // Initialize the database
        sconTUfeRepository.saveAndFlush(sconTUfe);

        // Get the sconTUfe
        restSconTUfeMockMvc.perform(get("/api/scon-t-ufes/{id}", sconTUfe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTUfe.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idufe").value(DEFAULT_IDUFE))
            .andExpect(jsonPath("$.idpai").value(DEFAULT_IDPAI))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.codigoIbge").value(DEFAULT_CODIGO_IBGE.toString()))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTUfe() throws Exception {
        // Get the sconTUfe
        restSconTUfeMockMvc.perform(get("/api/scon-t-ufes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTUfe() throws Exception {
        // Initialize the database
        sconTUfeRepository.saveAndFlush(sconTUfe);

        int databaseSizeBeforeUpdate = sconTUfeRepository.findAll().size();

        // Update the sconTUfe
        SconTUfe updatedSconTUfe = sconTUfeRepository.findById(sconTUfe.getId()).get();
        // Disconnect from session so that the updates on updatedSconTUfe are not directly saved in db
        em.detach(updatedSconTUfe);
        updatedSconTUfe
            .descricao(UPDATED_DESCRICAO)
            .idufe(UPDATED_IDUFE)
            .idpai(UPDATED_IDPAI)
            .codigo(UPDATED_CODIGO)
            .codigoIbge(UPDATED_CODIGO_IBGE)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTUfeMockMvc.perform(put("/api/scon-t-ufes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTUfe)))
            .andExpect(status().isOk());

        // Validate the SconTUfe in the database
        List<SconTUfe> sconTUfeList = sconTUfeRepository.findAll();
        assertThat(sconTUfeList).hasSize(databaseSizeBeforeUpdate);
        SconTUfe testSconTUfe = sconTUfeList.get(sconTUfeList.size() - 1);
        assertThat(testSconTUfe.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTUfe.getIdufe()).isEqualTo(UPDATED_IDUFE);
        assertThat(testSconTUfe.getIdpai()).isEqualTo(UPDATED_IDPAI);
        assertThat(testSconTUfe.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testSconTUfe.getCodigoIbge()).isEqualTo(UPDATED_CODIGO_IBGE);
        assertThat(testSconTUfe.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTUfe.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTUfe() throws Exception {
        int databaseSizeBeforeUpdate = sconTUfeRepository.findAll().size();

        // Create the SconTUfe

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTUfeMockMvc.perform(put("/api/scon-t-ufes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTUfe)))
            .andExpect(status().isBadRequest());

        // Validate the SconTUfe in the database
        List<SconTUfe> sconTUfeList = sconTUfeRepository.findAll();
        assertThat(sconTUfeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTUfe() throws Exception {
        // Initialize the database
        sconTUfeRepository.saveAndFlush(sconTUfe);

        int databaseSizeBeforeDelete = sconTUfeRepository.findAll().size();

        // Get the sconTUfe
        restSconTUfeMockMvc.perform(delete("/api/scon-t-ufes/{id}", sconTUfe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTUfe> sconTUfeList = sconTUfeRepository.findAll();
        assertThat(sconTUfeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTUfe.class);
        SconTUfe sconTUfe1 = new SconTUfe();
        sconTUfe1.setId(1L);
        SconTUfe sconTUfe2 = new SconTUfe();
        sconTUfe2.setId(sconTUfe1.getId());
        assertThat(sconTUfe1).isEqualTo(sconTUfe2);
        sconTUfe2.setId(2L);
        assertThat(sconTUfe1).isNotEqualTo(sconTUfe2);
        sconTUfe1.setId(null);
        assertThat(sconTUfe1).isNotEqualTo(sconTUfe2);
    }
}
