package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wdm.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
