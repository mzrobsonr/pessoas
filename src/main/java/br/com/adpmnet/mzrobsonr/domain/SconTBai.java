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
 * A SconTBai.
 */
@Entity
@Table(name = "scon_t_bai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTBai implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "idbai")
    private Integer idbai;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @OneToMany(mappedBy = "sconTBai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTBac> idbais = new HashSet<>();

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

    public SconTBai descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdbai() {
        return idbai;
    }

    public SconTBai idbai(Integer idbai) {
        this.idbai = idbai;
        return this;
    }

    public void setIdbai(Integer idbai) {
        this.idbai = idbai;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTBai usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTBai timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public Set<SconTBac> getIdbais() {
        return idbais;
    }

    public SconTBai idbais(Set<SconTBac> sconTBacs) {
        this.idbais = sconTBacs;
        return this;
    }

    public SconTBai addIdbai(SconTBac sconTBac) {
        this.idbais.add(sconTBac);
        sconTBac.setSconTBai(this);
        return this;
    }

    public SconTBai removeIdbai(SconTBac sconTBac) {
        this.idbais.remove(sconTBac);
        sconTBac.setSconTBai(null);
        return this;
    }

    public void setIdbais(Set<SconTBac> sconTBacs) {
        this.idbais = sconTBacs;
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
        SconTBai sconTBai = (SconTBai) o;
        if (sconTBai.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTBai.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTBai{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", idbai=" + getIdbai() +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
