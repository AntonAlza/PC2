package com.pc2.excel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class impuesto {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	@Size(min=2, max=30, message="Ingrese nuevamente su nombre")
	private String nombre;
	@NotNull
	@Size(min=7,message="Ingrese los 7 Dígitos de su DNI")
	private String DNI;
	@NotNull
	private String TipTrabajador;
	@NotNull
	@Min(1)
	private Double proybruta;
	@NotNull
	@Min(850)
	private Double uit;
	private Double total_ingreso;
	private Double retencAnual;
	private Double total_neto;
	
	public Double getProybruta() {
		return proybruta;
	}
	public void setProybruta(Double proybruta) {
		this.proybruta = proybruta;
	}
	
	public Double getRetencAnual() {
		return retencAnual;
	}
	public void setRetencAnual(Double retencAnual) {
		this.retencAnual = retencAnual;
	}
	public Double getUit() {
		return uit;
	}
	public void setUit(Double uit) {
		this.uit = uit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getTipTrabajador() {
		return TipTrabajador;
	}
	public void setTipTrabajador(String tipTrabajador) {
		TipTrabajador = tipTrabajador;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getTotal_ingreso() {
		return total_ingreso;
	}
	public void setTotal_ingreso(Double total_ingreso) {
		this.total_ingreso = total_ingreso;
	}
	public Double getTotal_neto() {
		return total_neto;
	}
	public void setTotal_neto(Double total_neto) {
		this.total_neto = total_neto;
	}
	
	
	
}
