package com.munro.service;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.munro.dto.MunroLibrary;
import com.munro.helper.MunroCSVReader;

import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MunroServiceImpl implements MunroService {

	public List<MunroLibrary> searchByHillCategory(String hillCategory, String sortBy, Boolean ascendingOrder,
			Float minHeight, Float maxHeight) {

		List<MunroLibrary> munroList = MunroCSVReader.getMunroList();

		Comparator<MunroLibrary> comparator = null;
		if (StringUtils.equalsIgnoreCase("name", sortBy)) {
			comparator = (m1, m2) -> m1.getName().compareTo(m2.getName());
		} else if (StringUtils.equalsIgnoreCase("height", sortBy)) {
			comparator = (m1, m2) -> Float.compare(m1.getHeight(), m2.getHeight());
		}

		if (ascendingOrder != null && !ascendingOrder) {
			comparator = comparator.reversed();
		}
		Predicate<MunroLibrary> filterPredicate = isPOST1997Category();
		Predicate<MunroLibrary> minHeightPredicate = null;
		Predicate<MunroLibrary> maxHeightPredicate = null;
		if (minHeight != null) {
			minHeightPredicate = minHeightFilter(minHeight);
			filterPredicate = filterPredicate.and(minHeightPredicate);
		}
		if (maxHeight != null) {
			maxHeightPredicate = maxHeightFilter(maxHeight);
			filterPredicate = filterPredicate.and(maxHeightPredicate);
		}

		if (StringUtils.isNotBlank(hillCategory)) {
			filterPredicate = filterPredicate.and(hillCategoryFilter(hillCategory));
		}

		List<MunroLibrary> list = munroList.stream().filter(filterPredicate).sorted(comparator)
				.collect(Collectors.toList());

		return list;
	}

	private Predicate<MunroLibrary> isPOST1997Category() {
		return munro -> (munro != null && munro.getPOST1997Category() != null);
	}

	private Predicate<MunroLibrary> minHeightFilter(Float minHeight) {
		return munro -> (munro.getHeight() >= minHeight);
	}

	private Predicate<MunroLibrary> maxHeightFilter(Float maxHeight) {
		return munro -> (munro.getHeight() <= maxHeight);
	}

	private Predicate<MunroLibrary> hillCategoryFilter(String hillCategory) {
		return munro -> (munro.getPOST1997Category().name().equals(hillCategory));
	}
}
