package br.com.adpmnet.mzrobsonr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SconTUfe.
 */
@Entity
@Table(name = "scon_t_ufe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTUfe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idufe")
    private Integer idufe;

    @Column(name = "idpai")
    private Integer idpai;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "codigo_ibge")
    private String codigoIbge;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JsonIgnoreProperties("idpais")
    private SconTPai sconTPai;

    /**
     * A relationship
     */
    @ApiModelProperty(value = "A relationship")
    @OneToMany(mappedBy = "sconTUfe")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTCid> idufes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public SconTUfe descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdufe() {
        return idufe;
    }

    public SconTUfe idufe(Integer idufe) {
        this.idufe = idufe;
        return this;
    }

    public void setIdufe(Integer idufe) {
        this.idufe = idufe;
    }

    public Integer getIdpai() {
        return idpai;
    }

    public SconTUfe idpai(Integer idpai) {
        this.idpai = idpai;
        return this;
    }

    public void setIdpai(Integer idpai) {
        this.idpai = idpai;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public SconTUfe codigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public SconTUfe codigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTUfe usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTUfe timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public SconTPai getSconTPai() {
        return sconTPai;
    }

    public SconTUfe sconTPai(SconTPai sconTPai) {
        this.sconTPai = sconTPai;
        return this;
    }

    public void setSconTPai(SconTPai sconTPai) {
        this.sconTPai = sconTPai;
    }

    public Set<SconTCid> getIdufes() {
        return idufes;
    }

    public SconTUfe idufes(Set<SconTCid> sconTCids) {
        this.idufes = sconTCids;
        return this;
    }

    public SconTUfe addIdufe(SconTCid sconTCid) {
        this.idufes.add(sconTCid);
        sconTCid.setSconTUfe(this);
        return this;
    }

    public SconTUfe removeIdufe(SconTCid sconTCid) {
        this.idufes.remove(sconTCid);
        sconTCid.setSconTUfe(null);
        return this;
    }

    public void setIdufes(Set<SconTCid> sconTCids) {
        this.idufes = sconTCids;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SconTUfe sconTUfe = (SconTUfe) o;
        if (sconTUfe.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTUfe.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTUfe{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", idufe=" + getIdufe() +
            ", idpai=" + getIdpai() +
            ", codigo=" + getCodigo() +
            ", codigoIbge='" + getCodigoIbge() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
