package com.github.biryani.services;

import java.util.UUID;

import com.github.biryani.web.model.BiryaniDto;

public interface BiryaniService {

	public BiryaniDto getBiryani(UUID id) ;

	public BiryaniDto saveBiryani(BiryaniDto dto);

	public void updateBiryani(UUID id, BiryaniDto biryaniDto);

}
