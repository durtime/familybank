package com.bean;

public class Bill {
	private int billid;
	private double billnum;
	private String billtype;
	private String type;
	private String date;
	private String beizhu;
	
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}
	public double getBillnum() {
		return billnum;
	}
	public void setBillnum(double billnum2) {
		this.billnum = billnum2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	@Override
	public String toString() {
		return "Bill [billid=" + billid + ", billnum=" + billnum + ", type=" + type + ", date=" + date + ", beizhu="
				+ beizhu + ", billtype=" + billtype + "]";
	}
}
