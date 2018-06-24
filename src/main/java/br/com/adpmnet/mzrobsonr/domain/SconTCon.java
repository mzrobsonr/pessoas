package br.com.adpmnet.mzrobsonr.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Entity
@Table(name = "scon_t_con")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SconTCon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "idlgbc")
    private Integer idlgbc;

    @Column(name = "idlgbr")
    private Integer idlgbr;

    @OneToOne
    @JoinColumn(unique = true)
    private SconTLgb idlgbc;

    @OneToOne
    @JoinColumn(unique = true)
    private SconTLgb idlgbr;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public SconTCon nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public SconTCon email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdlgbc() {
        return idlgbc;
    }

    public SconTCon idlgbc(Integer idlgbc) {
        this.idlgbc = idlgbc;
        return this;
    }

    public void setIdlgbc(Integer idlgbc) {
        this.idlgbc = idlgbc;
    }

    public Integer getIdlgbr() {
        return idlgbr;
    }

    public SconTCon idlgbr(Integer idlgbr) {
        this.idlgbr = idlgbr;
        return this;
    }

    public void setIdlgbr(Integer idlgbr) {
        this.idlgbr = idlgbr;
    }

    public SconTLgb getIdlgbc() {
        return idlgbc;
    }

    public SconTCon idlgbc(SconTLgb sconTLgb) {
        this.idlgbc = sconTLgb;
        return this;
    }

    public void setIdlgbc(SconTLgb sconTLgb) {
        this.idlgbc = sconTLgb;
    }

    public SconTLgb getIdlgbr() {
        return idlgbr;
    }

    public SconTCon idlgbr(SconTLgb sconTLgb) {
        this.idlgbr = sconTLgb;
        return this;
    }

    public void setIdlgbr(SconTLgb sconTLgb) {
        this.idlgbr = sconTLgb;
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
        SconTCon sconTCon = (SconTCon) o;
        if (sconTCon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sconTCon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SconTCon{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            ", idlgbc=" + getIdlgbc() +
            ", idlgbr=" + getIdlgbr() +
            "}";
    }
}
