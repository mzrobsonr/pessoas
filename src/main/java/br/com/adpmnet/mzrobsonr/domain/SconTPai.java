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
 * A SconTPai.
 */
@Entity
@Table(name = "scon_t_pai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTPai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idpai")
    private Integer idpai;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @OneToMany(mappedBy = "sconTPai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTUfe> idpais = new HashSet<>();

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

    public SconTPai descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdpai() {
        return idpai;
    }

    public SconTPai idpai(Integer idpai) {
        this.idpai = idpai;
        return this;
    }

    public void setIdpai(Integer idpai) {
        this.idpai = idpai;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTPai usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTPai timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Set<SconTUfe> getIdpais() {
        return idpais;
    }

    public SconTPai idpais(Set<SconTUfe> sconTUfes) {
        this.idpais = sconTUfes;
        return this;
    }

    public SconTPai addIdpai(SconTUfe sconTUfe) {
        this.idpais.add(sconTUfe);
        sconTUfe.setSconTPai(this);
        return this;
    }

    public SconTPai removeIdpai(SconTUfe sconTUfe) {
        this.idpais.remove(sconTUfe);
        sconTUfe.setSconTPai(null);
        return this;
    }

    public void setIdpais(Set<SconTUfe> sconTUfes) {
        this.idpais = sconTUfes;
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
        SconTPai sconTPai = (SconTPai) o;
        if (sconTPai.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTPai.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTPai{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", idpai=" + getIdpai() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
