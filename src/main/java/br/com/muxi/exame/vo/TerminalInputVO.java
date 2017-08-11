package br.com.muxi.exame.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TerminalInputVO {
	
	@JsonProperty("logic")
	private Object logic;

	@JsonProperty("serial")
	private Object serial;

	@JsonProperty("model")
	private Object model;

	@JsonProperty("sam")
	private Object sam;

	@JsonProperty("ptid")
	private Object ptid;

	@JsonProperty("plat")
	private Object plat;

	@JsonProperty("version")
	private Object version;

	@JsonProperty("mxr")
	private Object mxr;

	@JsonProperty("mxf")
	private Object mxf;

	@JsonProperty("VERFM")
	private Object verfm;
	
	private TerminalInputVO() {
		super();
	}
	
	public static TerminalInputVO build(String[] args) throws Exception {
		if (args.length != 10) {
			throw new Exception();
		}
		TerminalInputVO vo = new TerminalInputVO();
		vo.setLogic(args[0]);
		vo.setSerial(args[1]);
		vo.setModel(args[2]);
		vo.setSam(args[3]);
		vo.setPtid(args[4]);
		vo.setPlat(args[5]);
		vo.setVersion(args[6]);
		vo.setMxr(args[7]);
		vo.setMxf(args[8]);
		vo.setVerfm(args[9]);
		return vo;
	}

	public Object getLogic() {
		return logic;
	}

	public void setLogic(Object logic) {
		this.logic = logic;
	}
	
	private void setLogic(String logic) {
		if (isNumeric(logic)) {
			this.logic = Integer.parseInt(logic);
    	} else {
    		this.logic = logic;
    	}
	}

	public Object getSerial() {
		return serial;
	}

	public void setSerial(Object serial) {
		this.serial = serial;
	}
	
	private void setSerial(String serial) {
		this.serial = serial;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
	
	private void setModel(String model) {
		this.model = model;
	}

	public Object getSam() {
		return sam;
	}

	public void setSam(Object sam) {
		this.sam = sam;
	}
	
	private void setSam(String sam) {
		if (isNumeric(sam)) {
			this.sam = Integer.parseInt(sam);
    	} else {
    		this.sam = sam;
    	}
	}

	public Object getPtid() {
		return ptid;
	}

	public void setPtid(Object ptid) {
		this.ptid = ptid;
	}
	
	private void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public Object getPlat() {
		return plat;
	}

	public void setPlat(Object plat) {
		this.plat = plat;
	}
	
	private void setPlat(String plat) {
		if (isNumeric(plat)) {
			this.plat = Integer.parseInt(plat);
    	} else {
    		this.plat = plat;
    	}
	}

	public Object getVersion() {
		return version;
	}

	public void setVersion(Object version) {
		this.version = version;
	}
	
	private void setVersion(String version) {
		this.version = version;
	}

	public Object getMxr() {
		return mxr;
	}

	public void setMxr(Object mxr) {
		this.mxr = mxr;
	}
	
	private void setMxr(String mxr) {
		if (isNumeric(mxr)) {
			this.mxr = Integer.parseInt(mxr);
    	} else {
    		this.mxr = mxr;
    	}
	}

	public Object getMxf() {
		return mxf;
	}

	public void setMxf(Object mxf) {
		this.mxf = mxf;
	}
	
	private void setMxf(String mxf) {
		if (isNumeric(mxf)) {
			this.mxf = Integer.parseInt(mxf);
    	} else {
    		this.mxf = mxf;
    	}
	}

	public Object getVerfm() {
		return verfm;
	}

	public void setVerfm(Object verfm) {
		this.verfm = verfm;
	}
	
	private void setVerfm(String verfm) {
		this.verfm = verfm;
	}
	
	private boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 

}
