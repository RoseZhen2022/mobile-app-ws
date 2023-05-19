package com.appdevelop.app.ws.service;

import java.util.List;

import com.appdevelop.app.ws.shared.dto.AddressDTO;

public interface AddressesService {
	List<AddressDTO> getAddresses(String userId);
	AddressDTO getAddress(String addressId);

}
