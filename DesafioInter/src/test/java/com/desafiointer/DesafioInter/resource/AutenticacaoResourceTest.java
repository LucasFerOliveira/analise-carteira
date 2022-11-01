package com.desafiointer.DesafioInter.resource;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaDevolver400CasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\":\"desafio@inter.com.invalido\",\"senha\":\"123456\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}
	
	
	@Test
	public void deveriaDevolverTokenBearCasoDadosDeAutenticacaoEstejamCorretos() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\":\"desafio@inter.com\",\"senha\":\"1234\"}";
		
		MvcResult result = mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200)).andReturn();
		
		
		String content = result.getResponse().getContentAsString();
		
		JSONObject jsons = new JSONObject(content);  
		
		String token = jsons.getString("tipo") + " " + jsons.getString("token");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			 fail("token não gerado corretamente");
		}
		
	}
}
