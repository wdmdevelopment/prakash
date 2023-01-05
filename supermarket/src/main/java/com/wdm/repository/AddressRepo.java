package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
