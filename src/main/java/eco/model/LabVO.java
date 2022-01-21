package eco.model;

import java.io.Serializable;

public class LabVO implements Serializable {
	int xnum;
	double t_i;
	double x0, x1, x2;
	int n;
	double h;
	double a00;
	double a10;
	double a20;
	double a21;
	double a22;
	double bb;
	double r0;
	double r1;
	double r2;
	double cc;
	double dd;
	String title;
	String description;

	public int getXnum() {
		return xnum;
	}

	public void setXnum(int xnum) {
		this.xnum = xnum;
	}

	public double getT_i() {
		return t_i;
	}

	public void setT_i(double t_i) {
		this.t_i = t_i;
	}

	public double getX0() {
		return x0;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public double getA00() {
		return a00;
	}

	public void setA00(double a00) {
		this.a00 = a00;
	}

	public double getA10() {
		return a10;
	}

	public void setA10(double a10) {
		this.a10 = a10;
	}

	public double getA20() {
		return a20;
	}

	public void setA20(double a20) {
		this.a20 = a20;
	}

	public double getA21() {
		return a21;
	}

	public void setA21(double a21) {
		this.a21 = a21;
	}

	public double getA22() {
		return a22;
	}

	public void setA22(double a22) {
		this.a22 = a22;
	}

	public double getBb() {
		return bb;
	}

	public void setBb(double bb) {
		this.bb = bb;
	}

	public double getR0() {
		return r0;
	}

	public void setR0(double r0) {
		this.r0 = r0;
	}

	public double getR1() {
		return r1;
	}

	public void setR1(double r1) {
		this.r1 = r1;
	}

	public double getR2() {
		return r2;
	}

	public void setR2(double r2) {
		this.r2 = r2;
	}

	public double getCc() {
		return cc;
	}

	public void setCc(double cc) {
		this.cc = cc;
	}

	public double getDd() {
		return dd;
	}

	public void setDd(double dd) {
		this.dd = dd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
