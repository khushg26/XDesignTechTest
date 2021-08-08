package com.munro.helper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.munro.dto.MunroLibrary;
import com.munro.util.HillCategory;
import com.munro.util.Year;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

@Component
public class MunroCSVReader {

	public static List<MunroLibrary> munroLibraryList;

	@PostConstruct
	public void initializeCSV() {
		try (InputStreamReader fr = new InputStreamReader(
				getClass().getClassLoader().getResourceAsStream("munroData.csv"))) {
			CSVReader reader = new CSVReaderBuilder(fr).withSkipLines(1).// Skipping first line as it is a header
					build();

			munroLibraryList = reader.readAll().stream().map(data -> {
				if (StringUtils.isNotBlank(data[0])) {
					MunroLibrary csvObject = new MunroLibrary();
					csvObject.setRunningNo(Integer.parseInt(data[0]));
					csvObject.setDoBIHNumber(Integer.parseInt(data[1]));
					csvObject.setStreetMap(data[2]);
					csvObject.setGeograph(data[3]);
					csvObject.setHillBagging(data[4]);
					csvObject.setName(data[5]);
					csvObject.setSmcSection(data[6]);
					csvObject.setRbhSection(data[7]);
					csvObject.setSection(Float.parseFloat(data[8]));
					csvObject.setHeight(Float.parseFloat(data[9]));
					csvObject.setMap1_50(data[11]);
					csvObject.setMap1_25(data[12]);
					csvObject.setGridRef(data[13]);
					csvObject.setGridRefXY(data[14]);
					csvObject.setxCoord(Integer.parseInt(data[15]));
					csvObject.setyCoord(Integer.parseInt(data[16]));

					csvObject.addYearCategory(Year.YEAR1891, HillCategory.getValueOfCategory(data[17]));
					csvObject.addYearCategory(Year.YEAR1921, HillCategory.getValueOfCategory(data[18]));
					csvObject.addYearCategory(Year.YEAR1933, HillCategory.getValueOfCategory(data[19]));
					csvObject.addYearCategory(Year.YEAR1953, HillCategory.getValueOfCategory(data[20]));
					csvObject.addYearCategory(Year.YEAR1969, HillCategory.getValueOfCategory(data[21]));
					csvObject.addYearCategory(Year.YEAR1974, HillCategory.getValueOfCategory(data[22]));
					csvObject.addYearCategory(Year.YEAR1981, HillCategory.getValueOfCategory(data[23]));
					csvObject.addYearCategory(Year.YEAR1984, HillCategory.getValueOfCategory(data[24]));
					csvObject.addYearCategory(Year.YEAR1990, HillCategory.getValueOfCategory(data[25]));
					csvObject.addYearCategory(Year.YEAR1997, HillCategory.getValueOfCategory(data[26]));
					csvObject.addYearCategory(Year.POST1997, HillCategory.getValueOfCategory(data[27]));
					csvObject.setComments(data[28]);
					return csvObject;
				}
				return null;
			}).collect(Collectors.toList());
			munroLibraryList.forEach(System.out::println);
		} catch (IOException | CsvException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public static List<MunroLibrary> getMunroList() {
		return munroLibraryList;
	}
}
