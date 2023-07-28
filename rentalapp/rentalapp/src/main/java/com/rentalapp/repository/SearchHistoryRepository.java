package com.rentalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

}
