// Java bean class
package com.nt.beans;

public class Employee {
	private int empno;
	private String ename;
	private String job;
	private float sal;
    private  float grossSal;
    private float netSal;
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public float getSal() {
		return sal;
	}

	public void setSal(float sal) {
		this.sal = sal;
	}

	public float getGrossSal() {
		return grossSal;
	}

	public void setGrossSal(float grossSal) {
		this.grossSal = grossSal;
	}

	public float getNetSal() {
		return netSal;
	}

	public void setNetSal(float netSal) {
		this.netSal = netSal;
	}
}
