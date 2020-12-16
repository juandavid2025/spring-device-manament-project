package com.example.taller2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.taller2.grouping.interfaces.InstitutionGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the INSTITUTION database table.
 *
 */
@Entity
@NamedQuery(name = "Institution.findAll", query = "SELECT i FROM Institution i")
public class Institution implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "INSTITUTION_INSTID_GENERATOR", sequenceName = "INSTITUTION_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSTITUTION_INSTID_GENERATOR")
	@Column(name = "INST_ID")
	private long instId;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADEMICSERVERURL")
	private String instAcademicserverurl;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADEXTRADATAURL")
	private String instAcadextradataurl;

	@Column(name = "INST_ACADLOGINPASSWORD")
	private String instAcadloginpassword;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADLOGINURL")
	private String instAcadloginurl;

	@Column(name = "INST_ACADLOGINUSERNAME")
	private String instAcadloginusername;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADPERSONINFODOCURL")
	private String instAcadpersoninfodocurl;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADPERSONINFOIDURL")
	private String instAcadpersoninfoidurl;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADPHYSICALSPACESURL")
	private String instAcadphysicalspacesurl;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_ACADPROGRAMMEDCOURSESURL")
	private String instAcadprogrammedcoursesurl;

	@Column(name = "INST_LDAPBASEDN")
	private String instLdapbasedn;

	@Column(name = "INST_LDAPPASSWORD")
	private String instLdappassword;

	@Column(name = "INST_LDAPURL")
	private String instLdapurl;

	@Column(name = "INST_LDAPUSERNAME")
	private String instLdapusername;

	@Column(name = "INST_LDAPUSERSEARCHBASE")
	private String instLdapusersearchbase;

	@Column(name = "INST_LDAPUSERSEARCHFILTER")
	private String instLdapusersearchfilter;

	@NotBlank(groups = { InstitutionGroup.class })
	@NotNull(groups = { InstitutionGroup.class })
	@Column(name = "INST_NAME")
	private String instName;

	// bi-directional many-to-one association to Accessdenialevent
	@OneToMany(mappedBy = "institution")
	private List<Accessdenialevent> accessdenialevents;

	// bi-directional many-to-one association to Devicestatus
	@OneToMany(mappedBy = "institution")
	private List<Devicestatus> devicestatuses;

	// bi-directional many-to-one association to Devicetype
	@JsonIgnore
	@OneToMany(mappedBy = "institution")
	private List<Devicetype> devicetypes;

	// bi-directional many-to-one association to Epidemstatustransition
	@OneToMany(mappedBy = "institution")
	private List<Epidemstatustransition> epidemstatustransitions;

	// bi-directional many-to-one association to Eventstatus
	@OneToMany(mappedBy = "institution")
	private List<Eventstatus> eventstatuses;

	// bi-directional many-to-one association to HatParameter
	@OneToMany(mappedBy = "institution")
	private List<HatParameter> hatParameters;

	// bi-directional many-to-one association to Institutioncampus
	@OneToMany(mappedBy = "institution")
	private List<Institutioncampus> institutioncampuses;

	// bi-directional many-to-one association to Measurement
	@OneToMany(mappedBy = "institution")
	private List<Measurement> measurements;

	// bi-directional many-to-one association to Person
	@OneToMany(mappedBy = "institution")
	private List<Person> persons;

	// bi-directional many-to-one association to Physicalspacetype
	@OneToMany(mappedBy = "institution")
	private List<Physicalspacetype> physicalspacetypes;

	// bi-directional many-to-one association to Posessiontype
	@OneToMany(mappedBy = "institution")
	private List<Posessiontype> posessiontypes;

	public Institution() {
	}

	public Accessdenialevent addAccessdenialevent(Accessdenialevent accessdenialevent) {
		getAccessdenialevents().add(accessdenialevent);
		accessdenialevent.setInstitution(this);

		return accessdenialevent;
	}

	public Devicestatus addDevicestatus(Devicestatus devicestatus) {
		getDevicestatuses().add(devicestatus);
		devicestatus.setInstitution(this);

		return devicestatus;
	}

	public Devicetype addDevicetype(Devicetype devicetype) {
		getDevicetypes().add(devicetype);
		devicetype.setInstitution(this);

		return devicetype;
	}

	public Epidemstatustransition addEpidemstatustransition(Epidemstatustransition epidemstatustransition) {
		getEpidemstatustransitions().add(epidemstatustransition);
		epidemstatustransition.setInstitution(this);

		return epidemstatustransition;
	}

	public Eventstatus addEventstatus(Eventstatus eventstatus) {
		getEventstatuses().add(eventstatus);
		eventstatus.setInstitution(this);

		return eventstatus;
	}

	public HatParameter addHatParameter(HatParameter hatParameter) {
		getHatParameters().add(hatParameter);
		hatParameter.setInstitution(this);

		return hatParameter;
	}

	public Institutioncampus addInstitutioncampus(Institutioncampus institutioncampus) {
		getInstitutioncampuses().add(institutioncampus);
		institutioncampus.setInstitution(this);

		return institutioncampus;
	}

	public Measurement addMeasurement(Measurement measurement) {
		getMeasurements().add(measurement);
		measurement.setInstitution(this);

		return measurement;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setInstitution(this);

		return person;
	}

	public Physicalspacetype addPhysicalspacetype(Physicalspacetype physicalspacetype) {
		getPhysicalspacetypes().add(physicalspacetype);
		physicalspacetype.setInstitution(this);

		return physicalspacetype;
	}

	public Posessiontype addPosessiontype(Posessiontype posessiontype) {
		getPosessiontypes().add(posessiontype);
		posessiontype.setInstitution(this);

		return posessiontype;
	}

	public List<Accessdenialevent> getAccessdenialevents() {
		return this.accessdenialevents;
	}

	public List<Devicestatus> getDevicestatuses() {
		return this.devicestatuses;
	}

	public List<Devicetype> getDevicetypes() {
		return this.devicetypes;
	}

	public List<Epidemstatustransition> getEpidemstatustransitions() {
		return this.epidemstatustransitions;
	}

	public List<Eventstatus> getEventstatuses() {
		return this.eventstatuses;
	}

	public List<HatParameter> getHatParameters() {
		return this.hatParameters;
	}

	public String getInstAcademicserverurl() {
		return this.instAcademicserverurl;
	}

	public String getInstAcadextradataurl() {
		return this.instAcadextradataurl;
	}

	public String getInstAcadloginpassword() {
		return this.instAcadloginpassword;
	}

	public String getInstAcadloginurl() {
		return this.instAcadloginurl;
	}

	public String getInstAcadloginusername() {
		return this.instAcadloginusername;
	}

	public String getInstAcadpersoninfodocurl() {
		return this.instAcadpersoninfodocurl;
	}

	public String getInstAcadpersoninfoidurl() {
		return this.instAcadpersoninfoidurl;
	}

	public String getInstAcadphysicalspacesurl() {
		return this.instAcadphysicalspacesurl;
	}

	public String getInstAcadprogrammedcoursesurl() {
		return this.instAcadprogrammedcoursesurl;
	}

	public long getInstId() {
		return this.instId;
	}

	public List<Institutioncampus> getInstitutioncampuses() {
		return this.institutioncampuses;
	}

	public String getInstLdapbasedn() {
		return this.instLdapbasedn;
	}

	public String getInstLdappassword() {
		return this.instLdappassword;
	}

	public String getInstLdapurl() {
		return this.instLdapurl;
	}

	public String getInstLdapusername() {
		return this.instLdapusername;
	}

	public String getInstLdapusersearchbase() {
		return this.instLdapusersearchbase;
	}

	public String getInstLdapusersearchfilter() {
		return this.instLdapusersearchfilter;
	}

	public String getInstName() {
		return this.instName;
	}

	public List<Measurement> getMeasurements() {
		return this.measurements;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public List<Physicalspacetype> getPhysicalspacetypes() {
		return this.physicalspacetypes;
	}

	public List<Posessiontype> getPosessiontypes() {
		return this.posessiontypes;
	}

	public Accessdenialevent removeAccessdenialevent(Accessdenialevent accessdenialevent) {
		getAccessdenialevents().remove(accessdenialevent);
		accessdenialevent.setInstitution(null);

		return accessdenialevent;
	}

	public Devicestatus removeDevicestatus(Devicestatus devicestatus) {
		getDevicestatuses().remove(devicestatus);
		devicestatus.setInstitution(null);

		return devicestatus;
	}

	public Devicetype removeDevicetype(Devicetype devicetype) {
		getDevicetypes().remove(devicetype);
		devicetype.setInstitution(null);

		return devicetype;
	}

	public Epidemstatustransition removeEpidemstatustransition(Epidemstatustransition epidemstatustransition) {
		getEpidemstatustransitions().remove(epidemstatustransition);
		epidemstatustransition.setInstitution(null);

		return epidemstatustransition;
	}

	public Eventstatus removeEventstatus(Eventstatus eventstatus) {
		getEventstatuses().remove(eventstatus);
		eventstatus.setInstitution(null);

		return eventstatus;
	}

	public HatParameter removeHatParameter(HatParameter hatParameter) {
		getHatParameters().remove(hatParameter);
		hatParameter.setInstitution(null);

		return hatParameter;
	}

	public Institutioncampus removeInstitutioncampus(Institutioncampus institutioncampus) {
		getInstitutioncampuses().remove(institutioncampus);
		institutioncampus.setInstitution(null);

		return institutioncampus;
	}

	public Measurement removeMeasurement(Measurement measurement) {
		getMeasurements().remove(measurement);
		measurement.setInstitution(null);

		return measurement;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setInstitution(null);

		return person;
	}

	public Physicalspacetype removePhysicalspacetype(Physicalspacetype physicalspacetype) {
		getPhysicalspacetypes().remove(physicalspacetype);
		physicalspacetype.setInstitution(null);

		return physicalspacetype;
	}

	public Posessiontype removePosessiontype(Posessiontype posessiontype) {
		getPosessiontypes().remove(posessiontype);
		posessiontype.setInstitution(null);

		return posessiontype;
	}

	public void setAccessdenialevents(List<Accessdenialevent> accessdenialevents) {
		this.accessdenialevents = accessdenialevents;
	}

	public void setDevicestatuses(List<Devicestatus> devicestatuses) {
		this.devicestatuses = devicestatuses;
	}

	public void setDevicetypes(List<Devicetype> devicetypes) {
		this.devicetypes = devicetypes;
	}

	public void setEpidemstatustransitions(List<Epidemstatustransition> epidemstatustransitions) {
		this.epidemstatustransitions = epidemstatustransitions;
	}

	public void setEventstatuses(List<Eventstatus> eventstatuses) {
		this.eventstatuses = eventstatuses;
	}

	public void setHatParameters(List<HatParameter> hatParameters) {
		this.hatParameters = hatParameters;
	}

	public void setInstAcademicserverurl(String instAcademicserverurl) {
		this.instAcademicserverurl = instAcademicserverurl;
	}

	public void setInstAcadextradataurl(String instAcadextradataurl) {
		this.instAcadextradataurl = instAcadextradataurl;
	}

	public void setInstAcadloginpassword(String instAcadloginpassword) {
		this.instAcadloginpassword = instAcadloginpassword;
	}

	public void setInstAcadloginurl(String instAcadloginurl) {
		this.instAcadloginurl = instAcadloginurl;
	}

	public void setInstAcadloginusername(String instAcadloginusername) {
		this.instAcadloginusername = instAcadloginusername;
	}

	public void setInstAcadpersoninfodocurl(String instAcadpersoninfodocurl) {
		this.instAcadpersoninfodocurl = instAcadpersoninfodocurl;
	}

	public void setInstAcadpersoninfoidurl(String instAcadpersoninfoidurl) {
		this.instAcadpersoninfoidurl = instAcadpersoninfoidurl;
	}

	public void setInstAcadphysicalspacesurl(String instAcadphysicalspacesurl) {
		this.instAcadphysicalspacesurl = instAcadphysicalspacesurl;
	}

	public void setInstAcadprogrammedcoursesurl(String instAcadprogrammedcoursesurl) {
		this.instAcadprogrammedcoursesurl = instAcadprogrammedcoursesurl;
	}

	public void setInstId(long instId) {
		this.instId = instId;
	}

	public void setInstitutioncampuses(List<Institutioncampus> institutioncampuses) {
		this.institutioncampuses = institutioncampuses;
	}

	public void setInstLdapbasedn(String instLdapbasedn) {
		this.instLdapbasedn = instLdapbasedn;
	}

	public void setInstLdappassword(String instLdappassword) {
		this.instLdappassword = instLdappassword;
	}

	public void setInstLdapurl(String instLdapurl) {
		this.instLdapurl = instLdapurl;
	}

	public void setInstLdapusername(String instLdapusername) {
		this.instLdapusername = instLdapusername;
	}

	public void setInstLdapusersearchbase(String instLdapusersearchbase) {
		this.instLdapusersearchbase = instLdapusersearchbase;
	}

	public void setInstLdapusersearchfilter(String instLdapusersearchfilter) {
		this.instLdapusersearchfilter = instLdapusersearchfilter;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void setPhysicalspacetypes(List<Physicalspacetype> physicalspacetypes) {
		this.physicalspacetypes = physicalspacetypes;
	}

	public void setPosessiontypes(List<Posessiontype> posessiontypes) {
		this.posessiontypes = posessiontypes;
	}

}