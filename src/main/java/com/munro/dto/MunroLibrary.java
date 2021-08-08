package com.munro.dto;

import java.util.HashMap;
import java.util.Map;

import com.munro.util.HillCategory;
import com.munro.util.Year;

public class MunroLibrary {

	private int runningNo;
	private int doBIHNumber;
	private String streetMap;
	private String geograph;
	private String hillBagging;
	private String name;
	private String smcSection;
	private String rbhSection;
	private float section;
	private float height; // in meter
	private String Map1_50;
	private String Map1_25;
	private String gridRef;
	private String gridRefXY;
	private int xCoord;
	private int yCoord;
	private Map<Year, HillCategory> yearCategory;

	private String comments;

	public int getRunningNo() {
		return runningNo;
	}

	public void setRunningNo(int runningNo) {
		this.runningNo = runningNo;
	}

	public int getDoBIHNumber() {
		return doBIHNumber;
	}

	public void setDoBIHNumber(int doBIHNumber) {
		this.doBIHNumber = doBIHNumber;
	}

	public String getStreetMap() {
		return streetMap;
	}

	public void setStreetMap(String streetMap) {
		this.streetMap = streetMap;
	}

	public String getGeograph() {
		return geograph;
	}

	public void setGeograph(String geograph) {
		this.geograph = geograph;
	}

	public String getHillBagging() {
		return hillBagging;
	}

	public void setHillBagging(String hillBagging) {
		this.hillBagging = hillBagging;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmcSection() {
		return smcSection;
	}

	public void setSmcSection(String smcSection) {
		this.smcSection = smcSection;
	}

	public String getRbhSection() {
		return rbhSection;
	}

	public void setRbhSection(String rbhSection) {
		this.rbhSection = rbhSection;
	}

	public float getSection() {
		return section;
	}

	public void setSection(float section) {
		this.section = section;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getMap1_50() {
		return Map1_50;
	}

	public void setMap1_50(String map1_50) {
		Map1_50 = map1_50;
	}

	public String getMap1_25() {
		return Map1_25;
	}

	public void setMap1_25(String map1_25) {
		Map1_25 = map1_25;
	}

	public String getGridRef() {
		return gridRef;
	}

	public void setGridRef(String gridRef) {
		this.gridRef = gridRef;
	}

	public String getGridRefXY() {
		return gridRefXY;
	}

	public void setGridRefXY(String gridRefXY) {
		this.gridRefXY = gridRefXY;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public Map<Year, HillCategory> getYearCategory() {
		return yearCategory;
	}

	public void setYearCategory(Map<Year, HillCategory> yearCategory) {
		this.yearCategory = yearCategory;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	// ---- Helper methods ----

	// Put new value in category map
	public void addYearCategory(Year year, HillCategory category) {
		if (yearCategory == null)
			yearCategory = new HashMap<Year, HillCategory>();

		yearCategory.put(year, category);
	}

	public HillCategory getPOST1997Category() {
		return this.getYearCategory().get(Year.POST1997);
	}

}
