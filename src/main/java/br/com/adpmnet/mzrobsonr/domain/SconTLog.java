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
 * A SconTLog.
 */
@Entity
@Table(name = "scon_t_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ceplog")
    private String ceplog;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idlog")
    private Integer idlog;

    @Column(name = "idtlg")
    private Integer idtlg;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JsonIgnoreProperties("idtlgs")
    private SconTTlg sconTTlg;

    @OneToMany(mappedBy = "sconTLog")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTLgb> idlogs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCeplog() {
        return ceplog;
    }

    public SconTLog ceplog(String ceplog) {
        this.ceplog = ceplog;
        return this;
    }

    public void setCeplog(String ceplog) {
        this.ceplog = ceplog;
    }

    public String getDescricao() {
        return descricao;
    }

    public SconTLog descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdlog() {
        return idlog;
    }

    public SconTLog idlog(Integer idlog) {
        this.idlog = idlog;
        return this;
    }

    public void setIdlog(Integer idlog) {
        this.idlog = idlog;
    }

    public Integer getIdtlg() {
        return idtlg;
    }

    public SconTLog idtlg(Integer idtlg) {
        this.idtlg = idtlg;
        return this;
    }

    public void setIdtlg(Integer idtlg) {
        this.idtlg = idtlg;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTLog usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTLog timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public SconTTlg getSconTTlg() {
        return sconTTlg;
    }

    public SconTLog sconTTlg(SconTTlg sconTTlg) {
        this.sconTTlg = sconTTlg;
        return this;
    }

    public void setSconTTlg(SconTTlg sconTTlg) {
        this.sconTTlg = sconTTlg;
    }

    public Set<SconTLgb> getIdlogs() {
        return idlogs;
    }

    public SconTLog idlogs(Set<SconTLgb> sconTLgbs) {
        this.idlogs = sconTLgbs;
        return this;
    }

    public SconTLog addIdlog(SconTLgb sconTLgb) {
        this.idlogs.add(sconTLgb);
        sconTLgb.setSconTLog(this);
        return this;
    }

    public SconTLog removeIdlog(SconTLgb sconTLgb) {
        this.idlogs.remove(sconTLgb);
        sconTLgb.setSconTLog(null);
        return this;
    }

    public void setIdlogs(Set<SconTLgb> sconTLgbs) {
        this.idlogs = sconTLgbs;
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
        SconTLog sconTLog = (SconTLog) o;
        if (sconTLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTLog{" +
            "id=" + getId() +
            ", ceplog='" + getCeplog() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", idlog=" + getIdlog() +
            ", idtlg=" + getIdtlg() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
