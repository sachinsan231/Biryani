package com.github.biryani.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.biryani.services.BiryaniService;
import com.github.biryani.web.model.BiryaniDto;

@WebMvcTest(BiryaniController.class)
public class BiryaniControllerTest {

	@MockBean
	BiryaniService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	BiryaniDto validBiryani;
	
	@BeforeEach
	public void setup() {
		validBiryani = BiryaniDto.builder().id(UUID.randomUUID())
				.biryaniName("Hyderabdai").biryaniStyle("Andra").build();
	}
	
	
	@Test
	public void getBiryani() throws Exception {
		given(service.getBiryani(any(UUID.class))).willReturn(validBiryani);
		mockMvc.perform(get("/api/v1/biryani/"+validBiryani.getId().toString()).accept(org.springframework.http.MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	public void handlePost() throws Exception {
		BiryaniDto biryaniDto = validBiryani;
		biryaniDto.setId(null);
		BiryaniDto savedBiryaniDto = BiryaniDto.builder().id(UUID.randomUUID()).biryaniName("new").build();
		
		String request = objectMapper.writeValueAsString(biryaniDto);
		
		given(service.saveBiryani(any())).willReturn(savedBiryaniDto);
		
		mockMvc.perform(post("/api/v1/biryani/").contentType(MediaType.APPLICATION_JSON).content(request))
		.andExpect(status().isCreated());
		
		
	}
	
	
	@Test
    public void handleUpdate() throws Exception {
        //given
		BiryaniDto biryaniDto = validBiryani;
        String biryaniDtoJson = objectMapper.writeValueAsString(biryaniDto);

        //when
        mockMvc.perform(put("/api/v1/biryani/" + validBiryani.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(biryaniDtoJson))
                .andExpect(status().isNoContent());

        then(service).should().updateBiryani(any(), any());

    }
	
}
