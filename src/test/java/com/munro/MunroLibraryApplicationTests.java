package com.munro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.munro.dto.MunroLibrary;
import com.munro.exception.InvalidHillCategoryException;
import com.munro.exception.InvalidMinMaxHeight;
import com.munro.exception.InvalidSortByField;
import com.munro.helper.MunroCSVReader;
import com.munro.service.MunroService;
import com.munro.service.MunroServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class MunroLibraryApplicationTests {

	MunroService munroService = new MunroServiceImpl();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetByTopCategory() {
		MunroCSVReader csvReader = new MunroCSVReader();
		csvReader.initializeCSV();
		List<MunroLibrary> munroList = munroService.searchByHillCategory("TOP", "height", true, 915.3F, 1103F);
		assertNotNull(munroList);
		assertEquals(199, munroList.size());
	}

	@Test
	public void testGetByNoCategory() {
		MunroCSVReader csvReader = new MunroCSVReader();
		csvReader.initializeCSV();
		List<MunroLibrary> munroList = munroService.searchByHillCategory(null, "height", false, 915.3F, 1103F);
		assertNotNull(munroList);
		assertEquals(433, munroList.size());
	}

	@Test
	public void testInvalidCategory() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getHill?hillCategory=abc"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
		assertNotNull(result);
		assertEquals(InvalidHillCategoryException.class, result.getResolvedException().getClass());

	}

	@Test
	public void testInvalidSortByField() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getHill?sortBy=abc"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
		assertNotNull(result);
		assertEquals(InvalidSortByField.class, result.getResolvedException().getClass());

	}

	@Test
	public void testInvalidMinMaxHeight() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/getHill?minHeight=10&maxHeight=2"))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value())).andReturn();
		assertNotNull(result);
		assertEquals(InvalidMinMaxHeight.class, result.getResolvedException().getClass());

	}
}
