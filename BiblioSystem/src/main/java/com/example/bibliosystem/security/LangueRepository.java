package com.example.bibliosystem.security;

import com.example.bibliosystem.entity.Langue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LangueRepository extends JpaRepository<Langue, String> {

}
