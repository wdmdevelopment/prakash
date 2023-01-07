package com.wdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wdm.entity.UserAccount;

public interface UserAccountRespository extends JpaRepository<UserAccount, Long>{

}
