package com.multiplication.rest.multiplication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.multiplication.rest.multiplication.domain.Multiplication;
 

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {


}
