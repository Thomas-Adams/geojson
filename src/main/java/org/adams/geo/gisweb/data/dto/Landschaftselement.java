/*
 * Java bean class for entity table landschaftselement
 * Created on 23 M�r 2018 ( Date ISO 2018-03-23 - Time 23:44:21 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.adams.geo.gisweb.data.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Entity bean for table "landschaftselement"
 *
 * @author Telosys Tools Generator
 *
 */
public class Landschaftselement implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer gid; // Primary Key

	private String flek;
	private Short typ;
	private String typTxt;
	private Double flaeche;
	private Short ccRelevan;
	private String stand;
	private Short oevf;
	private BigInteger seSdoRow;
	private BigDecimal shapeArea;
	private BigDecimal shapeLen;
	private Geometry geom;

	/**
	 * Default constructor
	 */
	public Landschaftselement() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR THE PRIMARY KEY
	// ----------------------------------------------------------------------
	/**
	 * Set the "gid" field value This field is mapped on the database column "gid" (
	 * type "serial", NotNull : true )
	 *
	 * @param gid
	 */
	public void setGid(final Integer gid) {
		this.gid = gid;
	}

	/**
	 * Get the "gid" field value This field is mapped on the database column "gid" (
	 * type "serial", NotNull : true )
	 *
	 * @return the field value
	 */
	public Integer getGid() {
		return gid;
	}

	// ----------------------------------------------------------------------
	// GETTER(S) & SETTER(S) FOR DATA FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : flek ( varchar )
	/**
	 * Set the "flek" field value This field is mapped on the database column "flek"
	 * ( type "varchar", NotNull : false )
	 *
	 * @param flek
	 */
	public void setFlek(final String flek) {
		this.flek = flek;
	}

	/**
	 * Get the "flek" field value This field is mapped on the database column "flek"
	 * ( type "varchar", NotNull : false )
	 *
	 * @return the field value
	 */
	public String getFlek() {
		return flek;
	}

	// --- DATABASE MAPPING : typ ( int2 )
	/**
	 * Set the "typ" field value This field is mapped on the database column "typ" (
	 * type "int2", NotNull : false )
	 *
	 * @param typ
	 */
	public void setTyp(final Short typ) {
		this.typ = typ;
	}

	/**
	 * Get the "typ" field value This field is mapped on the database column "typ" (
	 * type "int2", NotNull : false )
	 *
	 * @return the field value
	 */
	public Short getTyp() {
		return typ;
	}

	// --- DATABASE MAPPING : typ_txt ( varchar )
	/**
	 * Set the "typTxt" field value This field is mapped on the database column
	 * "typ_txt" ( type "varchar", NotNull : false )
	 *
	 * @param typTxt
	 */
	public void setTypTxt(final String typTxt) {
		this.typTxt = typTxt;
	}

	/**
	 * Get the "typTxt" field value This field is mapped on the database column
	 * "typ_txt" ( type "varchar", NotNull : false )
	 *
	 * @return the field value
	 */
	public String getTypTxt() {
		return typTxt;
	}

	// --- DATABASE MAPPING : flaeche ( numeric )
	/**
	 * Set the "flaeche" field value This field is mapped on the database column
	 * "flaeche" ( type "numeric", NotNull : false )
	 *
	 * @param flaeche
	 */
	public void setFlaeche(final Double flaeche) {
		this.flaeche = flaeche;
	}

	/**
	 * Get the "flaeche" field value This field is mapped on the database column
	 * "flaeche" ( type "numeric", NotNull : false )
	 *
	 * @return the field value
	 */
	public Double getFlaeche() {
		return flaeche;
	}

	// --- DATABASE MAPPING : cc_relevan ( int2 )
	/**
	 * Set the "ccRelevan" field value This field is mapped on the database column
	 * "cc_relevan" ( type "int2", NotNull : false )
	 *
	 * @param ccRelevan
	 */
	public void setCcRelevan(final Short ccRelevan) {
		this.ccRelevan = ccRelevan;
	}

	/**
	 * Get the "ccRelevan" field value This field is mapped on the database column
	 * "cc_relevan" ( type "int2", NotNull : false )
	 *
	 * @return the field value
	 */
	public Short getCcRelevan() {
		return ccRelevan;
	}

	// --- DATABASE MAPPING : stand ( varchar )
	/**
	 * Set the "stand" field value This field is mapped on the database column
	 * "stand" ( type "varchar", NotNull : false )
	 *
	 * @param stand
	 */
	public void setStand(final String stand) {
		this.stand = stand;
	}

	/**
	 * Get the "stand" field value This field is mapped on the database column
	 * "stand" ( type "varchar", NotNull : false )
	 *
	 * @return the field value
	 */
	public String getStand() {
		return stand;
	}

	// --- DATABASE MAPPING : geom ( geometry )
	/**
	 * Set the "geom" field value This field is mapped on the database column "geom"
	 * ( type "geometry", NotNull : false )
	 *
	 * @param geom
	 */
	public void setGeom(final Geometry geom) {
		this.geom = geom;
	}

	/**
	 * Get the "geom" field value This field is mapped on the database column "geom"
	 * ( type "geometry", NotNull : false )
	 *
	 * @return the field value
	 */
	public Geometry getGeom() {
		return geom;
	}


	public Short getOevf() {
		return oevf;
	}

	public void setOevf(Short oevf) {
		this.oevf = oevf;
	}

	public BigInteger getSeSdoRow() {
		return seSdoRow;
	}

	public void setSeSdoRow(BigInteger seSdoRow) {
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
	// toString METHOD
	// ----------------------------------------------------------------------


	@Override
	public String toString() {
		return "Landschaftselement{" +
				"gid=" + gid +
				", flek='" + flek + '\'' +
				", typ=" + typ +
				", typTxt='" + typTxt + '\'' +
				", flaeche=" + flaeche +
				", ccRelevan=" + ccRelevan +
				", stand='" + stand + '\'' +
				", oevf=" + oevf +
				", seSdoRow=" + seSdoRow +
				", shapeArea=" + shapeArea +
				", shapeLen=" + shapeLen +
				", geom=" + geom +
				'}';
	}
}
