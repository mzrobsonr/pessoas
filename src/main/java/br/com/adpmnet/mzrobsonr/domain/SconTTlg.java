package br.com.adpmnet.mzrobsonr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SconTTlg.
 */
@Entity
@Table(name = "scon_t_tlg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTTlg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idtlg")
    private Integer idtlg;

    @Column(name = "iddic")
    private Integer iddic;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @OneToMany(mappedBy = "sconTTlg")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTLog> idtlgs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public SconTTlg codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public SconTTlg descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdtlg() {
        return idtlg;
    }

    public SconTTlg idtlg(Integer idtlg) {
        this.idtlg = idtlg;
        return this;
    }

    public void setIdtlg(Integer idtlg) {
        this.idtlg = idtlg;
    }

    public Integer getIddic() {
        return iddic;
    }

    public SconTTlg iddic(Integer iddic) {
        this.iddic = iddic;
        return this;
    }

    public void setIddic(Integer iddic) {
        this.iddic = iddic;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTTlg usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTTlg timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Set<SconTLog> getIdtlgs() {
        return idtlgs;
    }

    public SconTTlg idtlgs(Set<SconTLog> sconTLogs) {
        this.idtlgs = sconTLogs;
        return this;
    }

    public SconTTlg addIdtlg(SconTLog sconTLog) {
        this.idtlgs.add(sconTLog);
        sconTLog.setSconTTlg(this);
        return this;
    }

    public SconTTlg removeIdtlg(SconTLog sconTLog) {
        this.idtlgs.remove(sconTLog);
        sconTLog.setSconTTlg(null);
        return this;
    }

    public void setIdtlgs(Set<SconTLog> sconTLogs) {
        this.idtlgs = sconTLogs;
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
        SconTTlg sconTTlg = (SconTTlg) o;
        if (sconTTlg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTTlg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTTlg{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", idtlg=" + getIdtlg() +
            ", iddic=" + getIddic() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
