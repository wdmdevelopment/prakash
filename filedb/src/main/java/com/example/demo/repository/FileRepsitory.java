package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FileModel;

public interface FileRepsitory extends JpaRepository<FileModel, String>{

}
