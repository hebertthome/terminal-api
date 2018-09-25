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

    public Terminal(Integer logic, String serial, String model, String version) {
        super();
        this.logic = logic;
        this.serial = serial;
        this.model = model;
        this.version = version;
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
        result = prime * result + ((logic == null) ? 0 : logic.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
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
        if (logic == null) {
            if (other.logic != null)
                return false;
        } else if (!logic.equals(other.logic))
            return false;
        return true;
    }

}
