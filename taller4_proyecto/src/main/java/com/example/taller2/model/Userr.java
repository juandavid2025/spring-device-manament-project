package com.example.taller2.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

/**
 * The persistent class for the USERR database table.
 *
 */
@Entity
@NamedQuery(name = "Userr.findAll", query = "SELECT u FROM Userr u")
public class Userr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERR_USERID_GENERATOR", sequenceName = "USERR_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERR_USERID_GENERATOR")
	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "INST_INST_ID")
	private BigDecimal instInstId;

	@NotBlank
	@Column(name = "USER_NAME")
	private String userName;

	@NotBlank
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	// bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name = "PERS_PERS_ID")
	private Person person;

	public Userr() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigDecimal getInstInstId() {
		return this.instInstId;
	}

	public void setInstInstId(BigDecimal instInstId) {
		this.instInstId = instInstId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}