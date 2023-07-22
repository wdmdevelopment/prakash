package com.rentalapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.constant.Slot;
import com.rentalapp.entity.PaymentDetail;
import com.rentalapp.entity.Property;
import com.rentalapp.entity.PropertyReservation;
import com.rentalapp.entity.PropertySlots;
import com.rentalapp.entity.User;
import com.rentalapp.exception.NotFoundException;
import com.rentalapp.exception.RentalAppException;
import com.rentalapp.model.RequestReservation;
import com.rentalapp.repository.IUserAccountRespository;
import com.rentalapp.repository.PropertyRepository;
import com.rentalapp.repository.PropertyReservationRepository;
import com.rentalapp.repository.PropertySlotRepository;

@Service
public class ReservationService {

	@Autowired
	PropertyReservationRepository reservationRepo;

	@Autowired
	PropertyRepository propertyRepo;

	@Autowired
	IUserAccountRespository userRepo;

	@Autowired
	PropertySlotRepository propertySlotRepo;

	public PropertyReservation reserveProperty(RequestReservation requestReservation) {

		User user = userRepo.findById(requestReservation.getTenantId())
				.orElseThrow(() -> new NotFoundException("User id not found"));

		if (!user.getRole().equalsIgnoreCase("user")) {
			throw new RentalAppException("you are not applicable to booking any property");
		}

		Property property = propertyRepo.findById(requestReservation.getPropertyId())
				.orElseThrow(() -> new NotFoundException("Property id not found"));

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startDate = LocalDate.parse(requestReservation.getCheckInDate(), formatter);
		LocalDate endDate = LocalDate.parse(requestReservation.getCheckOutDate(), formatter);
		PropertyReservation propertyReservation = reservationRepo
				.findAllByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqualAndPropertyId(startDate, endDate,
						requestReservation.getPropertyId());

		if (propertyReservation == null) {
			propertyReservation = new PropertyReservation();
			long totalDays = ChronoUnit.DAYS.between(startDate.minusDays(1), endDate);

			double paidAmount = totalDays * property.getCostPerDay();

			propertyReservation.setCheckInDate(startDate);
			propertyReservation.setCheckOutDate(endDate);
			propertyReservation.setPaidAmount(paidAmount);
			propertyReservation.setNumberOfPeoplesStay(requestReservation.getNumberOfPersonStay());
			List<PropertySlots> propertySlotList = propertySlotRepo.findByAvailableDatesBetweenAndPropertyId(startDate,
					endDate, property.getId());
			for (PropertySlots propertySlots : propertySlotList) {
				propertySlots.setPropertyStatus(Slot.BOOKED.name());
			}
			propertyReservation.setProperty(property);
			propertyReservation.setStatus(Slot.BOOKED.name());
			propertyReservation.setUser(user);
			propertyReservation.setReservedOn(LocalDate.now());
			
			PaymentDetail paymetDetail = new PaymentDetail();
			paymetDetail.setAmount(paidAmount);
			paymetDetail.setPaymentType(requestReservation.getPaymentType());
			paymetDetail.setTransactionId(requestReservation.getTransactionId());
			paymetDetail.setTransactionTime(LocalDateTime.now());
			
			propertyReservation.setPaymentDetail(paymetDetail);
			
			return reservationRepo.save(propertyReservation);
		} else {
			throw new RentalAppException("This date already booked Please add some other days");
		}

	}
}
