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
import javax.validation.constraints.NotNull;

import com.example.taller2.grouping.interfaces.DevicestatusGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the DEVICESTATUS database table.
 *
 */
@Entity
@NamedQuery(name = "Devicestatus.findAll", query = "SELECT d FROM Devicestatus d")
public class Devicestatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "DEVICESTATUS_DEVSTATID_GENERATOR", sequenceName = "DEVICESTATUS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEVICESTATUS_DEVSTATID_GENERATOR")
	@Column(name = "DEVSTAT_ID")
	private long devstatId;

	// @NotBlank(groups = { DevicestatusGroup.class })
	@Column(name = "DEVSTAT_NAME")
	private String devstatName;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "devicestatus")
	private List<Device> devices;

	// bi-directional many-to-one association to Institution
	@JsonIgnore
	@NotNull(groups = { DevicestatusGroup.class })
	@ManyToOne
	@JoinColumn(name = "INST_INST_ID")
	private Institution institution;

	// bi-directional many-to-one association to Permissionn
	// @NotNull(groups = { DevicestatusGroup.class })
	@ManyToOne
	@JoinColumn(name = "PERM_PERM_ID")
	private Permissionn permissionn;

	public Devicestatus() {
	}

	public long getDevstatId() {
		return this.devstatId;
	}

	public void setDevstatId(long devstatId) {
		this.devstatId = devstatId;
	}

	public String getDevstatName() {
		return this.devstatName;
	}

	public void setDevstatName(String devstatName) {
		this.devstatName = devstatName;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setDevicestatus(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDevicestatus(null);

		return device;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Permissionn getPermissionn() {
		return this.permissionn;
	}

	public void setPermissionn(Permissionn permissionn) {
		this.permissionn = permissionn;
	}

}