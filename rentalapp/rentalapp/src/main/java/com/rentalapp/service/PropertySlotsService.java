package com.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.entity.PropertySlots;
import com.rentalapp.entity.User;
import com.rentalapp.exception.NotFoundException;
import com.rentalapp.model.RequestSlots;
import com.rentalapp.repository.IUserAccountRespository;
import com.rentalapp.repository.PropertyRepository;
import com.rentalapp.repository.PropertyReservationRepository;
import com.rentalapp.repository.PropertySlotRepository;

@Service
public class PropertySlotsService {

	@Autowired
	PropertySlotRepository propertySlotRepo;

	@Autowired
	PropertyRepository propertyRepo;
	
	@Autowired
	PropertyReservationRepository propertyReserveRepo;

	@Autowired
	IUserAccountRespository userRepo;

	public PropertySlots saveSlot(RequestSlots requestQuestions) {
//		User tenant = userRepo.findById(requestQuestions.getTenantId())
//				.orElseThrow(() -> new NotFoundException("User id not found"));
//			PropertyReservation reservation = propertyReserveRepo.findById(requestQuestions.getPropertyReservationId())
//					.orElseThrow(() -> new NotFoundException("Property id not found"));
//			PropertySlots slots = propertySlotRepo.save(slot);
				 return null;
			   
	}

	public PropertySlots updateSlot(RequestSlots requestSlot, Long slotId) {
		PropertySlots slot = propertySlotRepo.findById(slotId)
					.orElseThrow(() -> new NotFoundException("Slot id not found"));
		//slot.setStatus(requestSlot.getStatus());
		return propertySlotRepo.save(slot);
	}

	public List<PropertySlots> getAllSlots(Long userId) {
		//List<PropertySlots> slots = propertySlotRepo.findByUserId(userId);
		return null;
	}

	public void deleteBySlot(Long slotId, Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user id not found"));
		if(user.getRole().equalsIgnoreCase("tenant")) {
			propertySlotRepo.deleteById(slotId);
		}
		
	}
}
