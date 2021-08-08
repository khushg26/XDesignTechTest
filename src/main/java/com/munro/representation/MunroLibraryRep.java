package com.munro.representation;

import java.util.ArrayList;
import java.util.List;

import com.munro.dto.MunroLibrary;
import com.munro.util.HillCategory;
import com.munro.util.Year;

public class MunroLibraryRep {

	private String name;
	private float height; // in meter
	private String gridRef;
	private HillCategory hillCategory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public String getGridRef() {
		return gridRef;
	}

	public void setGridRef(String gridRef) {
		this.gridRef = gridRef;
	}

	public HillCategory getHillCategory() {
		return hillCategory;
	}

	public void setHillCategory(HillCategory hillCategory) {
		this.hillCategory = hillCategory;
	}

	// ----- converters  ----------
	
	public static List<MunroLibraryRep> getReps(List<MunroLibrary> munroDtos, Integer count) {
		List<MunroLibraryRep> reps = new ArrayList<MunroLibraryRep>();
		int counter = count != null ? count : Integer.MAX_VALUE;
		if (munroDtos != null) {
			for (MunroLibrary munroDto : munroDtos) {
				if (counter > 0) {
					reps.add(getRep(munroDto));
					counter--;
				}
			}
		}
		return reps;
	}

	public static MunroLibraryRep getRep(MunroLibrary munroDto) {
		MunroLibraryRep rep = new MunroLibraryRep();
		rep.setName(munroDto.getName());
		rep.setHeight(munroDto.getHeight());
		rep.setGridRef(munroDto.getGridRef());
		rep.setHillCategory(munroDto.getYearCategory().get(Year.POST1997));
		return rep;
	}
}
