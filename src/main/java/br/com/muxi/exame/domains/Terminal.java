package br.com.muxi.exame.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "terminal")
public class Terminal {

	@Id
	@Column(name = "logic")
	@JsonProperty("logic")
	@NotNull
	private Integer logic;

	@Column(name = "serial")
	@JsonProperty("serial")
	@NotNull
	private String serial;

	@Column(name = "model")
	@JsonProperty("model")
	@NotNull
	private String model;

	@Column(name = "sam")
	@JsonProperty("sam")
	@DecimalMin("0")
	private Integer sam;

	@Column(name = "ptid")
	@JsonProperty("ptid")
	private String ptid;

	@Column(name = "plat")
	@JsonProperty("plat")
	private Integer plat;

	@Column(name = "version")
	@JsonProperty("version")
	@NotNull
	private String version;

	@Column(name = "mxr")
	@JsonProperty("mxr")
	private Integer mxr;

	@Column(name = "mxf")
	@JsonProperty("mxf")
	private Integer mxf;

	@Column(name = "verfm")
	@JsonProperty("VERFM")
	private String verfm;
	
	public Terminal() {
		super();
	}

	public Terminal(Integer logic, String serial, String model, Integer sam,
			String ptid, Integer plat, String version, Integer mxr,
			Integer mxf, String verfm) {
		super();
		this.logic = logic;
		this.serial = serial;
		this.model = model;
		this.sam = sam;
		this.ptid = ptid;
		this.plat = plat;
		this.version = version;
		this.mxr = mxr;
		this.mxf = mxf;
		this.verfm = verfm;
	}

	public Integer getLogic() {
		return logic;
	}

	public void setLogic(Integer logic) {
		this.logic = logic;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getSam() {
		return sam;
	}

	public void setSam(Integer sam) {
		this.sam = sam;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public Integer getPlat() {
		return plat;
	}

	public void setPlat(Integer plat) {
		this.plat = plat;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getMxr() {
		return mxr;
	}

	public void setMxr(Integer mxr) {
		this.mxr = mxr;
	}

	public Integer getMxf() {
		return mxf;
	}

	public void setMxf(Integer mxf) {
		this.mxf = mxf;
	}

	public String getVerfm() {
		return verfm;
	}

	public void setVerfm(String verfm) {
		this.verfm = verfm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((verfm == null) ? 0 : verfm.hashCode());
		result = prime * result + ((logic == null) ? 0 : logic.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((mxf == null) ? 0 : mxf.hashCode());
		result = prime * result + ((mxr == null) ? 0 : mxr.hashCode());
		result = prime * result + ((plat == null) ? 0 : plat.hashCode());
		result = prime * result + ((ptid == null) ? 0 : ptid.hashCode());
		result = prime * result + ((sam == null) ? 0 : sam.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Terminal other = (Terminal) obj;
		if (verfm == null) {
			if (other.verfm != null)
				return false;
		} else if (!verfm.equals(other.verfm))
			return false;
		if (logic == null) {
			if (other.logic != null)
				return false;
		} else if (!logic.equals(other.logic))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (mxf == null) {
			if (other.mxf != null)
				return false;
		} else if (!mxf.equals(other.mxf))
			return false;
		if (mxr == null) {
			if (other.mxr != null)
				return false;
		} else if (!mxr.equals(other.mxr))
			return false;
		if (plat == null) {
			if (other.plat != null)
				return false;
		} else if (!plat.equals(other.plat))
			return false;
		if (ptid == null) {
			if (other.ptid != null)
				return false;
		} else if (!ptid.equals(other.ptid))
			return false;
		if (sam == null) {
			if (other.sam != null)
				return false;
		} else if (!sam.equals(other.sam))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

}
