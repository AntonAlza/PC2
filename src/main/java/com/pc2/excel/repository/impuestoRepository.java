package com.pc2.excel.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pc2.excel.model.impuesto;

public interface impuestoRepository 
	extends Repository<impuesto,Integer>{
	
	void save(impuesto impuesto);
	List<impuesto> findAll();
	
	impuesto findById(int id);
	
	void delete(impuesto impuesto);

}
