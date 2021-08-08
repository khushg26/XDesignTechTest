package com.munro.service;

import java.util.List;

import com.munro.dto.MunroLibrary;

public interface MunroService {

	public List<MunroLibrary> searchByHillCategory(String hillCategory, String sortBy, Boolean ascendingOrder,
			Float minHeight, Float maxHeight);
}
