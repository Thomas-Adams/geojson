/*
 * Created on 23 M�r 2018 ( Time 23:05:07 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite)

package org.adams.geo.gisweb.data.entities;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.adams.geo.gisweb.config.ReferenceSystems;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Persistent class for entity stored in table "teilschlag"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "teilschlag", schema = "public")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "TeilschlagEntity.countAll", query = "SELECT COUNT(x) FROM TeilschlagEntity x") })
public class TeilschlagEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@SequenceGenerator(name = "my_ts_seq", sequenceName = "teilschlag_gid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_ts_seq")
	@Column(name = "gid", nullable = false)
	private Integer gid;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "flik", length = 254)
	private String flik;

	@Column(name = "schlagnr")
	private BigDecimal schlagnr;

	@Column(name = "tsbez", length = 2)
	private String tsbez;

	@Column(name = "kulturcode", length = 100)
	private String kulturcode;

	@Column(name = "antjahr")
	private Long antjahr;

	@Column(name = "gem_fl")
	private Double gemFl;

	@Column(name = "geom", columnDefinition = "MultiPolygon(" + ReferenceSystems.SRID_3044 + ")")
	private Geometry geom;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public TeilschlagEntity() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setGid(final Integer gid) {
		this.gid = gid;
	}

	public Integer getGid() {
		return gid;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : flik ( varchar )
	public void setFlik(final String flik) {
		this.flik = flik;
	}

	public String getFlik() {
		return flik;
	}

	// --- DATABASE MAPPING : schlagnr ( numeric )
	public void setSchlagnr(final BigDecimal schlagnr) {
		this.schlagnr = schlagnr;
	}

	public BigDecimal getSchlagnr() {
		return schlagnr;
	}

	// --- DATABASE MAPPING : tsbez ( varchar )
	public void setTsbez(final String tsbez) {
		this.tsbez = tsbez;
	}

	public String getTsbez() {
		return tsbez;
	}

	// --- DATABASE MAPPING : kulturcode ( varchar )
	public void setKulturcode(final String kulturcode) {
		this.kulturcode = kulturcode;
	}

	public String getKulturcode() {
		return kulturcode;
	}

	// --- DATABASE MAPPING : antjahr ( numeric )
	public void setAntjahr(final Long antjahr) {
		this.antjahr = antjahr;
	}

	public Long getAntjahr() {
		return antjahr;
	}

	// --- DATABASE MAPPING : gem_fl ( float8 )
	public void setGemFl(final Double gemFl) {
		this.gemFl = gemFl;
	}

	public Double getGemFl() {
		return gemFl;
	}

	// --- DATABASE MAPPING : geom ( geometry )
	public void setGeom(final Geometry geom) {
		this.geom = geom;
	}

	public Geometry getGeom() {
		return geom;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(gid);
		sb.append("]:");
		sb.append(flik);
		sb.append("|");
		sb.append(schlagnr);
		sb.append("|");
		sb.append(tsbez);
		sb.append("|");
		sb.append(kulturcode);
		sb.append("|");
		sb.append(antjahr);
		sb.append("|");
		sb.append(gemFl);
		sb.append("|");
		sb.append(geom);
		return sb.toString();
	}

}
