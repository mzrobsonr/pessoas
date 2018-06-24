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
 * A SconTBac.
 */
@Entity
@Table(name = "scon_t_bac")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTBac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "idbac")
    private Integer idbac;

    @Column(name = "idbai")
    private Integer idbai;

    @Column(name = "idcid")
    private Integer idcid;

    @Column(name = "cepcid")
    private String cepcid;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "_timestamp")
    private LocalDate timestamp;

    @ManyToOne
    @JsonIgnoreProperties("idufes")
    private SconTCid sconTCid;

    @ManyToOne
    @JsonIgnoreProperties("idbais")
    private SconTBai sconTBai;

    @OneToMany(mappedBy = "sconTBac")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SconTLgb> idbacs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdbac() {
        return idbac;
    }

    public SconTBac idbac(Integer idbac) {
        this.idbac = idbac;
        return this;
    }

    public void setIdbac(Integer idbac) {
        this.idbac = idbac;
    }

    public Integer getIdbai() {
        return idbai;
    }

    public SconTBac idbai(Integer idbai) {
        this.idbai = idbai;
        return this;
    }

    public void setIdbai(Integer idbai) {
        this.idbai = idbai;
    }

    public Integer getIdcid() {
        return idcid;
    }

    public SconTBac idcid(Integer idcid) {
        this.idcid = idcid;
        return this;
    }

    public void setIdcid(Integer idcid) {
        this.idcid = idcid;
    }

    public String getCepcid() {
        return cepcid;
    }

    public SconTBac cepcid(String cepcid) {
        this.cepcid = cepcid;
        return this;
    }

    public void setCepcid(String cepcid) {
        this.cepcid = cepcid;
    }

    public String getUsuario() {
        return usuario;
    }

    public SconTBac usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public SconTBac timestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public SconTCid getSconTCid() {
        return sconTCid;
    }

    public SconTBac sconTCid(SconTCid sconTCid) {
        this.sconTCid = sconTCid;
        return this;
    }

    public void setSconTCid(SconTCid sconTCid) {
        this.sconTCid = sconTCid;
    }

    public SconTBai getSconTBai() {
        return sconTBai;
    }

    public SconTBac sconTBai(SconTBai sconTBai) {
        this.sconTBai = sconTBai;
        return this;
    }

    public void setSconTBai(SconTBai sconTBai) {
        this.sconTBai = sconTBai;
    }

    public Set<SconTLgb> getIdbacs() {
        return idbacs;
    }

    public SconTBac idbacs(Set<SconTLgb> sconTLgbs) {
        this.idbacs = sconTLgbs;
        return this;
    }

    public SconTBac addIdbac(SconTLgb sconTLgb) {
        this.idbacs.add(sconTLgb);
        sconTLgb.setSconTBac(this);
        return this;
    }

    public SconTBac removeIdbac(SconTLgb sconTLgb) {
        this.idbacs.remove(sconTLgb);
        sconTLgb.setSconTBac(null);
        return this;
    }

    public void setIdbacs(Set<SconTLgb> sconTLgbs) {
        this.idbacs = sconTLgbs;
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
        SconTBac sconTBac = (SconTBac) o;
        if (sconTBac.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTBac.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTBac{" +
            "id=" + getId() +
            ", idbac=" + getIdbac() +
            ", idbai=" + getIdbai() +
            ", idcid=" + getIdcid() +
            ", cepcid='" + getCepcid() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }
}
