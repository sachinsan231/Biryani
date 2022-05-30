/**
 * 
 */
package com.github.biryani.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.github.biryani.web.model.BiryaniDto;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kadam.sachin
 *
 */
@Service
@Slf4j
public class BiryaniServiceImpl implements BiryaniService {

	@Override
	public BiryaniDto getBiryani(UUID id) {
		return BiryaniDto.builder().id(UUID.randomUUID()).biryaniName("Hyderabadi").biryaniStyle("Andra").build();
	}

	@Override
	public BiryaniDto saveBiryani(BiryaniDto dto) {
		return BiryaniDto.builder().id(dto.getId()).biryaniName(dto.getBiryaniName()).build();
		
	}

	@Override
	public void updateBiryani(UUID id, BiryaniDto biryaniDto) {
		// TODO Auto-generated method stub
		log.info("updating"+ biryaniDto);
	}

}
