package mutant.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import mutant.service.HumanService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MutantController.class, secure = false)
public class MutantControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HumanService humanService;
	
	/**
	 * This test ensures that a mutant DNA is recognized when a post request
	 * is performed.
	 * 
	 * A valid DNA is informed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testServiceWithMutantDna() throws Exception {
		Mockito.when(humanService.isMutant(Mockito.anyObject())).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content("{\"Dna\":[\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	/**
	 * This test ensures that a human DNA is recognized when a post request
	 * is performed.
	 * 
	 * A valid DNA is informed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testServiceWithHumanDna() throws Exception {
		Mockito.when(humanService.isMutant(Mockito.anyObject())).thenReturn(false);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content("{\"Dna\":[\"AC\", \"TG\"]}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
	}
	
	/**
	 * This test ensures that a human DNA is recognized when a post request
	 * is performed.
	 * 
	 * A valid DNA is informed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testServiceWithInvalidDna() throws Exception {
		Mockito.when(humanService.isMutant(Mockito.anyObject())).thenReturn(false);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content("{\"Dna\":[\"A\", \"TG\"]}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
	}

}
