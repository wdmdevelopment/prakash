package com.rentalapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.entity.Property;
import com.rentalapp.entity.PropertyMessages;
import com.rentalapp.entity.User;
import com.rentalapp.exception.NotFoundException;
import com.rentalapp.model.RequestGetAllMessage;
import com.rentalapp.model.RequestPropertyMessage;
import com.rentalapp.repository.IUserAccountRespository;
import com.rentalapp.repository.MessageRepository;
import com.rentalapp.repository.PropertyRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository meessageRepo;

	@Autowired
	PropertyRepository propertyRepo;

	@Autowired
	IUserAccountRespository userRepo;

	public PropertyMessages sendMessage(RequestPropertyMessage requestMessage) {

		Property property = propertyRepo.findById(requestMessage.getPropertyId())
				.orElseThrow(() -> new NotFoundException("Property Id not found"));
		User user = userRepo.findById(requestMessage.getFromId())
				.orElseThrow(() -> new NotFoundException("User Id not found"));
		 
		PropertyMessages messages = new PropertyMessages();
		if (user.getRole().equalsIgnoreCase("tenant")) {
			 
			messages.setProperty(property);
			messages.setMessage(requestMessage.getMessage());
			messages.setFrom(user);
			messages.setTo(property.getUser());

		} else if (user.getRole().equalsIgnoreCase("host")) {
			 
			messages.setProperty(property);
			messages.setMessage(requestMessage.getMessage());
			messages.setFrom(property.getUser());
			messages.setTo(user);
		}
		messages.setTime(LocalDateTime.now());
		return meessageRepo.save(messages);
	}

	public List<PropertyMessages> getAllMessagesUser(RequestGetAllMessage requestMessage) {
		List<PropertyMessages> messageList = meessageRepo.findByFromIdAndToIdAndPropertyId(requestMessage.getFromId(),
				requestMessage.getToId(), requestMessage.getPropertyId());
		return messageList;
	}

	public void deleteMessageTenantUser(Long tanatId) {
		meessageRepo.deleteByFrom(tanatId);

	}

}
