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
 * Persistent class for entity stored in table "teillandschaftselement"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "teillandschaftselement", schema = "public")
// Define named queries here
@NamedQueries({
		@NamedQuery(name = "TeillandschaftselementEntity.countAll", query = "SELECT COUNT(x) FROM TeillandschaftselementEntity x") })
public class TeillandschaftselementEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@SequenceGenerator(name = "my_tl_seq", sequenceName = "teillandschaftselement_gid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_tl_seq")
	@Column(name = "gid", nullable = false)
	private Integer gid;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "flik", length = 254)
	private String flik;

	@Column(name = "flek", length = 254)
	private String flek;

	@Column(name = "nrle")
	private BigDecimal nrle;

	@Column(name = "schlagnr")
	private BigDecimal schlagnr;

	@Column(name = "geom", columnDefinition = "MultiPolygon(" + ReferenceSystems.SRID_3044 + ")")
	private Geometry geom;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public TeillandschaftselementEntity() {
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

	// --- DATABASE MAPPING : flek ( varchar )
	public void setFlek(final String flek) {
		this.flek = flek;
	}

	public String getFlek() {
		return flek;
	}

	// --- DATABASE MAPPING : nrle ( numeric )
	public void setNrle(final BigDecimal nrle) {
		this.nrle = nrle;
	}

	public BigDecimal getNrle() {
		return nrle;
	}

	// --- DATABASE MAPPING : schlagnr ( numeric )
	public void setSchlagnr(final BigDecimal schlagnr) {
		this.schlagnr = schlagnr;
	}

	public BigDecimal getSchlagnr() {
		return schlagnr;
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
		sb.append(flek);
		sb.append("|");
		sb.append(nrle);
		sb.append("|");
		sb.append(schlagnr);
		sb.append("|");
		sb.append(geom);
		return sb.toString();
	}

}
