package com.teste.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.domain.UserDocumentation;

@Repository
public interface DocumentationRepository extends JpaRepository<UserDocumentation, Long> { }
