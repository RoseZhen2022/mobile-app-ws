package com.appdevelop.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdevelop.app.ws.io.entity.AddressEntity;
import com.appdevelop.app.ws.io.entity.UserEntity;
import com.appdevelop.app.ws.io.repository.AddressRepository;
import com.appdevelop.app.ws.io.repository.UserRepository;
import com.appdevelop.app.ws.service.AddressesService;
import com.appdevelop.app.ws.shared.dto.AddressDTO;

@Service
public class AddressesServiceImpl implements AddressesService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public List<AddressDTO> getAddresses(String userId) {
		
		List<AddressDTO> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();
		
		UserEntity userEntity = userRepository.findByUserId(userId);		
		if(userEntity==null) return returnValue;
		
		Iterable<AddressEntity> address = addressRepository.findAllByUserDetails(userEntity);
		for(AddressEntity addressEntity:address) {
			returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
		}
			
		
		return returnValue;
	}

	@Override
	public AddressDTO getAddress(String addressId) {
		AddressDTO returnValue = null;
				
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		
		if(addressEntity != null) {
			returnValue = new ModelMapper().map(addressEntity, AddressDTO.class);
		}
				
		return returnValue;
		
	}

}
