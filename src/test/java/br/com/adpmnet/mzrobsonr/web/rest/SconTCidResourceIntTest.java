package br.com.adpmnet.mzrobsonr.web.rest;

import br.com.adpmnet.mzrobsonr.PessoasApp;

import br.com.adpmnet.mzrobsonr.domain.SconTCid;
import br.com.adpmnet.mzrobsonr.repository.SconTCidRepository;
import br.com.adpmnet.mzrobsonr.service.SconTCidService;
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
 * Test class for the SconTCidResource REST controller.
 *
 * @see SconTCidResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoasApp.class)
public class SconTCidResourceIntTest {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDUFE = 1;
    private static final Integer UPDATED_IDUFE = 2;

    private static final Integer DEFAULT_IDCID = 1;
    private static final Integer UPDATED_IDCID = 2;

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final Integer DEFAULT_IDDIC = 1;
    private static final Integer UPDATED_IDDIC = 2;

    private static final Integer DEFAULT_IDDIC_0 = 1;
    private static final Integer UPDATED_IDDIC_0 = 2;

    private static final String DEFAULT_CEPCID = "AAAAAAAAAA";
    private static final String UPDATED_CEPCID = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_IBGE = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_IBGE = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_IBGE_0 = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_IBGE_0 = "BBBBBBBBBB";

    private static final Integer DEFAULT_TIPO = 1;
    private static final Integer UPDATED_TIPO = 2;

    private static final String DEFAULT_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_USUARIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TIMESTAMP = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIMESTAMP = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private SconTCidRepository sconTCidRepository;

    

    @Autowired
    private SconTCidService sconTCidService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSconTCidMockMvc;

    private SconTCid sconTCid;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SconTCidResource sconTCidResource = new SconTCidResource(sconTCidService);
        this.restSconTCidMockMvc = MockMvcBuilders.standaloneSetup(sconTCidResource)
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
    public static SconTCid createEntity(EntityManager em) {
        SconTCid sconTCid = new SconTCid()
            .descricao(DEFAULT_DESCRICAO)
            .idufe(DEFAULT_IDUFE)
            .idcid(DEFAULT_IDCID)
            .codigo(DEFAULT_CODIGO)
            .iddic(DEFAULT_IDDIC)
            .iddic0(DEFAULT_IDDIC_0)
            .cepcid(DEFAULT_CEPCID)
            .codigoIbge(DEFAULT_CODIGO_IBGE)
            .codigoIbge0(DEFAULT_CODIGO_IBGE_0)
            .tipo(DEFAULT_TIPO)
            .usuario(DEFAULT_USUARIO)
            .timestamp(DEFAULT_TIMESTAMP);
        return sconTCid;
    }

    @Before
    public void initTest() {
        sconTCid = createEntity(em);
    }

    @Test
    @Transactional
    public void createSconTCid() throws Exception {
        int databaseSizeBeforeCreate = sconTCidRepository.findAll().size();

        // Create the SconTCid
        restSconTCidMockMvc.perform(post("/api/scon-t-cids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCid)))
            .andExpect(status().isCreated());

        // Validate the SconTCid in the database
        List<SconTCid> sconTCidList = sconTCidRepository.findAll();
        assertThat(sconTCidList).hasSize(databaseSizeBeforeCreate + 1);
        SconTCid testSconTCid = sconTCidList.get(sconTCidList.size() - 1);
        assertThat(testSconTCid.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testSconTCid.getIdufe()).isEqualTo(DEFAULT_IDUFE);
        assertThat(testSconTCid.getIdcid()).isEqualTo(DEFAULT_IDCID);
        assertThat(testSconTCid.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testSconTCid.getIddic()).isEqualTo(DEFAULT_IDDIC);
        assertThat(testSconTCid.getIddic0()).isEqualTo(DEFAULT_IDDIC_0);
        assertThat(testSconTCid.getCepcid()).isEqualTo(DEFAULT_CEPCID);
        assertThat(testSconTCid.getCodigoIbge()).isEqualTo(DEFAULT_CODIGO_IBGE);
        assertThat(testSconTCid.getCodigoIbge0()).isEqualTo(DEFAULT_CODIGO_IBGE_0);
        assertThat(testSconTCid.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testSconTCid.getUsuario()).isEqualTo(DEFAULT_USUARIO);
        assertThat(testSconTCid.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createSconTCidWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sconTCidRepository.findAll().size();

        // Create the SconTCid with an existing ID
        sconTCid.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSconTCidMockMvc.perform(post("/api/scon-t-cids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCid)))
            .andExpect(status().isBadRequest());

        // Validate the SconTCid in the database
        List<SconTCid> sconTCidList = sconTCidRepository.findAll();
        assertThat(sconTCidList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSconTCids() throws Exception {
        // Initialize the database
        sconTCidRepository.saveAndFlush(sconTCid);

        // Get all the sconTCidList
        restSconTCidMockMvc.perform(get("/api/scon-t-cids?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sconTCid.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO.toString())))
            .andExpect(jsonPath("$.[*].idufe").value(hasItem(DEFAULT_IDUFE)))
            .andExpect(jsonPath("$.[*].idcid").value(hasItem(DEFAULT_IDCID)))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].iddic").value(hasItem(DEFAULT_IDDIC)))
            .andExpect(jsonPath("$.[*].iddic0").value(hasItem(DEFAULT_IDDIC_0)))
            .andExpect(jsonPath("$.[*].cepcid").value(hasItem(DEFAULT_CEPCID.toString())))
            .andExpect(jsonPath("$.[*].codigoIbge").value(hasItem(DEFAULT_CODIGO_IBGE.toString())))
            .andExpect(jsonPath("$.[*].codigoIbge0").value(hasItem(DEFAULT_CODIGO_IBGE_0.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].usuario").value(hasItem(DEFAULT_USUARIO.toString())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())));
    }
    

    @Test
    @Transactional
    public void getSconTCid() throws Exception {
        // Initialize the database
        sconTCidRepository.saveAndFlush(sconTCid);

        // Get the sconTCid
        restSconTCidMockMvc.perform(get("/api/scon-t-cids/{id}", sconTCid.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sconTCid.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO.toString()))
            .andExpect(jsonPath("$.idufe").value(DEFAULT_IDUFE))
            .andExpect(jsonPath("$.idcid").value(DEFAULT_IDCID))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.iddic").value(DEFAULT_IDDIC))
            .andExpect(jsonPath("$.iddic0").value(DEFAULT_IDDIC_0))
            .andExpect(jsonPath("$.cepcid").value(DEFAULT_CEPCID.toString()))
            .andExpect(jsonPath("$.codigoIbge").value(DEFAULT_CODIGO_IBGE.toString()))
            .andExpect(jsonPath("$.codigoIbge0").value(DEFAULT_CODIGO_IBGE_0.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.usuario").value(DEFAULT_USUARIO.toString()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingSconTCid() throws Exception {
        // Get the sconTCid
        restSconTCidMockMvc.perform(get("/api/scon-t-cids/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSconTCid() throws Exception {
        // Initialize the database
        sconTCidService.save(sconTCid);

        int databaseSizeBeforeUpdate = sconTCidRepository.findAll().size();

        // Update the sconTCid
        SconTCid updatedSconTCid = sconTCidRepository.findById(sconTCid.getId()).get();
        // Disconnect from session so that the updates on updatedSconTCid are not directly saved in db
        em.detach(updatedSconTCid);
        updatedSconTCid
            .descricao(UPDATED_DESCRICAO)
            .idufe(UPDATED_IDUFE)
            .idcid(UPDATED_IDCID)
            .codigo(UPDATED_CODIGO)
            .iddic(UPDATED_IDDIC)
            .iddic0(UPDATED_IDDIC_0)
            .cepcid(UPDATED_CEPCID)
            .codigoIbge(UPDATED_CODIGO_IBGE)
            .codigoIbge0(UPDATED_CODIGO_IBGE_0)
            .tipo(UPDATED_TIPO)
            .usuario(UPDATED_USUARIO)
            .timestamp(UPDATED_TIMESTAMP);

        restSconTCidMockMvc.perform(put("/api/scon-t-cids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSconTCid)))
            .andExpect(status().isOk());

        // Validate the SconTCid in the database
        List<SconTCid> sconTCidList = sconTCidRepository.findAll();
        assertThat(sconTCidList).hasSize(databaseSizeBeforeUpdate);
        SconTCid testSconTCid = sconTCidList.get(sconTCidList.size() - 1);
        assertThat(testSconTCid.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testSconTCid.getIdufe()).isEqualTo(UPDATED_IDUFE);
        assertThat(testSconTCid.getIdcid()).isEqualTo(UPDATED_IDCID);
        assertThat(testSconTCid.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testSconTCid.getIddic()).isEqualTo(UPDATED_IDDIC);
        assertThat(testSconTCid.getIddic0()).isEqualTo(UPDATED_IDDIC_0);
        assertThat(testSconTCid.getCepcid()).isEqualTo(UPDATED_CEPCID);
        assertThat(testSconTCid.getCodigoIbge()).isEqualTo(UPDATED_CODIGO_IBGE);
        assertThat(testSconTCid.getCodigoIbge0()).isEqualTo(UPDATED_CODIGO_IBGE_0);
        assertThat(testSconTCid.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testSconTCid.getUsuario()).isEqualTo(UPDATED_USUARIO);
        assertThat(testSconTCid.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingSconTCid() throws Exception {
        int databaseSizeBeforeUpdate = sconTCidRepository.findAll().size();

        // Create the SconTCid

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSconTCidMockMvc.perform(put("/api/scon-t-cids")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sconTCid)))
            .andExpect(status().isBadRequest());

        // Validate the SconTCid in the database
        List<SconTCid> sconTCidList = sconTCidRepository.findAll();
        assertThat(sconTCidList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSconTCid() throws Exception {
        // Initialize the database
        sconTCidService.save(sconTCid);

        int databaseSizeBeforeDelete = sconTCidRepository.findAll().size();

        // Get the sconTCid
        restSconTCidMockMvc.perform(delete("/api/scon-t-cids/{id}", sconTCid.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SconTCid> sconTCidList = sconTCidRepository.findAll();
        assertThat(sconTCidList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SconTCid.class);
        SconTCid sconTCid1 = new SconTCid();
        sconTCid1.setId(1L);
        SconTCid sconTCid2 = new SconTCid();
        sconTCid2.setId(sconTCid1.getId());
        assertThat(sconTCid1).isEqualTo(sconTCid2);
        sconTCid2.setId(2L);
        assertThat(sconTCid1).isNotEqualTo(sconTCid2);
        sconTCid1.setId(null);
        assertThat(sconTCid1).isNotEqualTo(sconTCid2);
    }
}
