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
import java.math.BigInteger;

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
 * Persistent class for entity stored in table "landschaftselement"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "landschaftselement", schema = "public")
// Define named queries here
@NamedQueries({
		@NamedQuery(name = "LandschaftselementEntity.countAll", query = "SELECT COUNT(x) FROM LandschaftselementEntity x") })
public class LandschaftselementEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@SequenceGenerator(name = "my_le_seq", sequenceName = "landschaftselement_gid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_le_seq")
	@Column(name = "gid", nullable = false)
	private Integer gid;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "flek", length = 16)
	private String flek;

	@Column(name = "flek_vorg", length = 16)
	private String flekVorg;

	@Column(name = "typ")
	private Short typ;

	@Column(name = "typ_txt", length = 254)
	private String typTxt;

	@Column(name = "flaeche", columnDefinition = "double precision")
	private Double flaeche;

	@Column(name = "cc_relevan")
	private Short ccRelevan;

	@Column(name = "oevf")
	private Short oevf;

	@Column(name = "stand", length = 10)
	private String stand;

	@Column(name = "se_sdo_row", columnDefinition = "BIGINT")
	private Long seSdoRow;

	@Column(name = "shape_area")
	private BigDecimal shapeArea;

	@Column(name = "shape_len")
	private BigDecimal shapeLen;

	@Column(name = "geom", columnDefinition = "MultiPolygon(" + ReferenceSystems.SRID_3044 + ")")
	private Geometry geom;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public LandschaftselementEntity() {
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
	// --- DATABASE MAPPING : flek ( varchar )
	public void setFlek(final String flek) {
		this.flek = flek;
	}

	public String getFlek() {
		return flek;
	}

	// --- DATABASE MAPPING : typ ( int2 )
	public void setTyp(final Short typ) {
		this.typ = typ;
	}

	public Short getTyp() {
		return typ;
	}

	// --- DATABASE MAPPING : typ_txt ( varchar )
	public void setTypTxt(final String typTxt) {
		this.typTxt = typTxt;
	}

	public String getTypTxt() {
		return typTxt;
	}

	// --- DATABASE MAPPING : flaeche ( numeric )
	public void setFlaeche(final Double flaeche) {
		this.flaeche = flaeche;
	}

	public Double getFlaeche() {
		return flaeche;
	}

	// --- DATABASE MAPPING : cc_relevan ( int2 )
	public void setCcRelevan(final Short ccRelevan) {
		this.ccRelevan = ccRelevan;
	}

	public Short getCcRelevan() {
		return ccRelevan;
	}

	// --- DATABASE MAPPING : stand ( varchar )
	public void setStand(final String stand) {
		this.stand = stand;
	}

	public String getStand() {
		return stand;
	}

	// --- DATABASE MAPPING : geom ( geometry )
	public void setGeom(final Geometry geom) {
		this.geom = geom;
	}

	public Geometry getGeom() {
		return geom;
	}

	public String getFlekVorg() {
		return flekVorg;
	}

	public void setFlekVorg(String flekVorg) {
		this.flekVorg = flekVorg;
	}

	public Short getOevf() {
		return oevf;
	}

	public void setOevf(Short oevf) {
		this.oevf = oevf;
	}

	public Long getSeSdoRow() {
		return seSdoRow;
	}

	public void setSeSdoRow(Long  seSdoRow) {
		this.seSdoRow = seSdoRow;
	}

	public BigDecimal getShapeArea() {
		return shapeArea;
	}

	public void setShapeArea(BigDecimal shapeArea) {
		this.shapeArea = shapeArea;
	}

	public BigDecimal getShapeLen() {
		return shapeLen;
	}

	public void setShapeLen(BigDecimal shapeLen) {
		this.shapeLen = shapeLen;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	@Override
	public String toString() {
		return "LandschaftselementEntity{" +
				"gid=" + gid +
				", flek='" + flek + '\'' +
				", flekVorg='" + flekVorg + '\'' +
				", typ=" + typ +
				", typTxt='" + typTxt + '\'' +
				", flaeche=" + flaeche +
				", ccRelevan=" + ccRelevan +
				", oevf=" + oevf +
				", stand='" + stand + '\'' +
				", seSdoRow=" + seSdoRow +
				", shapeArea=" + shapeArea +
				", shapeLen=" + shapeLen +
				", geom=" + geom +
				'}';
	}
}
