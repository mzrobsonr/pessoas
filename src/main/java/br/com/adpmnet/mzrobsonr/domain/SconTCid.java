package br.com.adpmnet.mzrobsonr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SconTCid.
 */
@Entity
@Table(name = "scon_t_cid")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTCid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idufe")
    private Integer idufe;

    @Column(name = "idcid")
    private Integer idcid;

    @Column(name = "codigo")
    private Integer codigo;

    @Column(name = "iddic")
    private Integer iddic;

    @Column(name = "iddic_0")
    private Integer iddic0;

    @Column(name = "cepcid")
    private String cepcid;

    @Column(name = "codigo_ibge")
    private String codigoIbge;

    @Column(name = "codigo_ibge_0")
    private String codigoIbge0;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JsonIgnoreProperties("idufes")
    private SconTUfe sconTUfe;

    @OneToMany(mappedBy = "sconTCid")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTBac> idufes = new HashSet<>();

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

    public SconTCid descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdufe() {
        return idufe;
    }

    public SconTCid idufe(Integer idufe) {
        this.idufe = idufe;
        return this;
    }

    public void setIdufe(Integer idufe) {
        this.idufe = idufe;
    }

    public Integer getIdcid() {
        return idcid;
    }

    public SconTCid idcid(Integer idcid) {
        this.idcid = idcid;
        return this;
    }

    public void setIdcid(Integer idcid) {
        this.idcid = idcid;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public SconTCid codigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getIddic() {
        return iddic;
    }

    public SconTCid iddic(Integer iddic) {
        this.iddic = iddic;
        return this;
    }

    public void setIddic(Integer iddic) {
        this.iddic = iddic;
    }

    public Integer getIddic0() {
        return iddic0;
    }

    public SconTCid iddic0(Integer iddic0) {
        this.iddic0 = iddic0;
        return this;
    }

    public void setIddic0(Integer iddic0) {
        this.iddic0 = iddic0;
    }

    public String getCepcid() {
        return cepcid;
    }

    public SconTCid cepcid(String cepcid) {
        this.cepcid = cepcid;
        return this;
    }

    public void setCepcid(String cepcid) {
        this.cepcid = cepcid;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public SconTCid codigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getCodigoIbge0() {
        return codigoIbge0;
    }

    public SconTCid codigoIbge0(String codigoIbge0) {
        this.codigoIbge0 = codigoIbge0;
        return this;
    }

    public void setCodigoIbge0(String codigoIbge0) {
        this.codigoIbge0 = codigoIbge0;
    }

    public Integer getTipo() {
        return tipo;
    }

    public SconTCid tipo(Integer tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTCid usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTCid timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public SconTUfe getSconTUfe() {
        return sconTUfe;
    }

    public SconTCid sconTUfe(SconTUfe sconTUfe) {
        this.sconTUfe = sconTUfe;
        return this;
    }

    public void setSconTUfe(SconTUfe sconTUfe) {
        this.sconTUfe = sconTUfe;
    }

    public Set<SconTBac> getIdufes() {
        return idufes;
    }

    public SconTCid idufes(Set<SconTBac> sconTBacs) {
        this.idufes = sconTBacs;
        return this;
    }

    public SconTCid addIdufe(SconTBac sconTBac) {
        this.idufes.add(sconTBac);
        sconTBac.setSconTCid(this);
        return this;
    }

    public SconTCid removeIdufe(SconTBac sconTBac) {
        this.idufes.remove(sconTBac);
        sconTBac.setSconTCid(null);
        return this;
    }

    public void setIdufes(Set<SconTBac> sconTBacs) {
        this.idufes = sconTBacs;
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
        SconTCid sconTCid = (SconTCid) o;
        if (sconTCid.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTCid.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTCid{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", idufe=" + getIdufe() +
            ", idcid=" + getIdcid() +
            ", codigo=" + getCodigo() +
            ", iddic=" + getIddic() +
            ", iddic0=" + getIddic0() +
            ", cepcid='" + getCepcid() + "'" +
            ", codigoIbge='" + getCodigoIbge() + "'" +
            ", codigoIbge0='" + getCodigoIbge0() + "'" +
            ", tipo=" + getTipo() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
