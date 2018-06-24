package br.com.adpmnet.mzrobsonr.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A SconTLgb.
 */
@Entity
@Table(name = "scon_t_lgb")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTLgb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "idlgb")
    private Integer idlgb;

    @Column(name = "idlog")
    private Integer idlog;

    @Column(name = "idbac")
    private Integer idbac;

    @Column(name = "ceplgb")
    private String ceplgb;

    @Column(name = "larguralog")
    private Long larguralog;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JsonIgnoreProperties("idbacs")
    private SconTBac sconTBac;

    @ManyToOne
    @JsonIgnoreProperties("idlogs")
    private SconTLog sconTLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdlgb() {
        return idlgb;
    }

    public SconTLgb idlgb(Integer idlgb) {
        this.idlgb = idlgb;
        return this;
    }

    public void setIdlgb(Integer idlgb) {
        this.idlgb = idlgb;
    }

    public Integer getIdlog() {
        return idlog;
    }

    public SconTLgb idlog(Integer idlog) {
        this.idlog = idlog;
        return this;
    }

    public void setIdlog(Integer idlog) {
        this.idlog = idlog;
    }

    public Integer getIdbac() {
        return idbac;
    }

    public SconTLgb idbac(Integer idbac) {
        this.idbac = idbac;
        return this;
    }

    public void setIdbac(Integer idbac) {
        this.idbac = idbac;
    }

    public String getCeplgb() {
        return ceplgb;
    }

    public SconTLgb ceplgb(String ceplgb) {
        this.ceplgb = ceplgb;
        return this;
    }

    public void setCeplgb(String ceplgb) {
        this.ceplgb = ceplgb;
    }

    public Long getLarguralog() {
        return larguralog;
    }

    public SconTLgb larguralog(Long larguralog) {
        this.larguralog = larguralog;
        return this;
    }

    public void setLarguralog(Long larguralog) {
        this.larguralog = larguralog;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTLgb usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTLgb timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public SconTBac getSconTBac() {
        return sconTBac;
    }

    public SconTLgb sconTBac(SconTBac sconTBac) {
        this.sconTBac = sconTBac;
        return this;
    }

    public void setSconTBac(SconTBac sconTBac) {
        this.sconTBac = sconTBac;
    }

    public SconTLog getSconTLog() {
        return sconTLog;
    }

    public SconTLgb sconTLog(SconTLog sconTLog) {
        this.sconTLog = sconTLog;
        return this;
    }

    public void setSconTLog(SconTLog sconTLog) {
        this.sconTLog = sconTLog;
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
        SconTLgb sconTLgb = (SconTLgb) o;
        if (sconTLgb.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTLgb.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTLgb{" +
            "id=" + getId() +
            ", idlgb=" + getIdlgb() +
            ", idlog=" + getIdlog() +
            ", idbac=" + getIdbac() +
            ", ceplgb='" + getCeplgb() + "'" +
            ", larguralog=" + getLarguralog() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
