package com.rentalapp.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateRangeUtils {
 
	public static List<LocalDate> getLocalDatesInRange(LocalDate startDate, LocalDate endDate) {

		List<LocalDate> localDateList = new ArrayList<>();
		LocalDate current = startDate;

		while (!current.isAfter(endDate)) {
			localDateList.add(current);
			current = current.plusDays(1);
		}
		for (LocalDate localDate : localDateList) {
			System.out.println(localDate);
		}
		return localDateList;

	}

	public static List<LocalDate> getDateList(List<String> weekdays) {
		int startYear = LocalDate.now().getYear();
		LocalDate startDate = LocalDate.of(startYear, 1, 1);
		LocalDate endDate = LocalDate.of(startYear, 12, 31);
		   List<LocalDate> matchingDate = new ArrayList<>();
	        LocalDate currentDate = startDate;
	        while (!currentDate.isAfter(endDate)) {
	            if (weekdays.contains(currentDate.getDayOfWeek().name().toUpperCase(Locale.ENGLISH))) {
	            	matchingDate.add(currentDate);
	            }
	            currentDate = currentDate.plusDays(1);
	        }
	        return matchingDate;
		 
	}
	
	public static List<LocalDate> getDateListDateFormat(LocalDate startDate, LocalDate endDate){
		return Stream.iterate(startDate, date -> date.plusDays(1))
        .limit(startDate.until(endDate.plusDays(1)).getDays())
        .collect(Collectors.toList());
	}

}