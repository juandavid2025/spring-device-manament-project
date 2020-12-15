package com.example.taller2.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.taller2.grouping.interfaces.DevicetypeGroup;

/**
 * The persistent class for the DEVICETYPE database table.
 *
 */
@Entity
@NamedQuery(name = "Devicetype.findAll", query = "SELECT d FROM Devicetype d")
public class Devicetype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEVICETYPE_DEVTYPEID_GENERATOR", sequenceName = "DEVICETYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICETYPE_DEVTYPEID_GENERATOR")
	@Column(name = "DEVTYPE_ID")
	private long devtypeId;

	@NotNull(groups = { DevicetypeGroup.class })
	@NotBlank(groups = { DevicetypeGroup.class })
	@Column(name = "DEVTYPE_NAME")
	private String devtypeName;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "devicetype")
	private List<Device> devices;

	// bi-directional many-to-one association to Institution
	@NotNull(groups = { DevicetypeGroup.class })
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID")
	private Institution institution;

	public Devicetype() {
	}

	public long getDevtypeId() {
		return this.devtypeId;
	}

	public void setDevtypeId(long devtypeId) {
		this.devtypeId = devtypeId;
	}

	public String getDevtypeName() {
		return this.devtypeName;
	}

	public void setDevtypeName(String devtypeName) {
		this.devtypeName = devtypeName;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setDevicetype(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDevicetype(null);

		return device;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}