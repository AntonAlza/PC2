package com.pc2.excel.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pc2.excel.model.impuesto;
import com.pc2.excel.repository.impuestoRepository;




@Controller
public class impuestoController {
	
	@Autowired
	private impuestoRepository impuestoRepository;
		
	
	
	public impuesto calculoImpuesto(impuesto impuesto) {
		
		 double hast5UIT=0;
		 double hast20UIT=0;
		 double hast35UIT=0;
		 double hast45UIT=0;
		 double masde45UIT=0;
		 double monto7=0;
		 double renta=0;
		 
		 monto7 =impuesto.getUit()*7;
		 
		 if(impuesto.getTipTrabajador().equals("Dependiente")) {
			 renta=((impuesto.getProybruta()*14)-monto7);
			 impuesto.setTotal_ingreso(impuesto.getProybruta()*14);
		 }else {
			 renta =((impuesto.getProybruta()*12)-monto7);
			 impuesto.setTotal_ingreso(impuesto.getProybruta()*12);
		 }
		

		 if(renta>0) {
			 
			 		 if(renta<impuesto.getUit()*5) {
			 			 hast5UIT=renta;
			 		 }else{
			 			hast5UIT=impuesto.getUit()*5;
			 		 }
			 		 
			 		
			 		if(impuesto.getUit()*5>renta && renta<=impuesto.getUit()*20){
						 
			 			hast20UIT=renta-hast5UIT;
			 			
				 	}else if(renta>impuesto.getUit()*20) {
				 		
				 		hast20UIT=impuesto.getUit()*20-hast5UIT;
				 		
				 	}
			 		
			 		if( renta>impuesto.getUit()*20 && renta<=impuesto.getUit()*35){
						 
			 			hast35UIT=renta-(hast20UIT+hast5UIT);
			 			
			 		}else if(renta>impuesto.getUit()*35) {
					 	
			 			hast35UIT=impuesto.getUit()*35-(hast20UIT+hast5UIT);
					 
			 		}
					 
					 if( renta>impuesto.getUit()*35 && renta<=impuesto.getUit()*45){
						
						 hast45UIT=renta-(hast20UIT+hast5UIT+hast35UIT);
					 
					 }else if(renta>impuesto.getUit()*45) {
						 
						 hast45UIT=impuesto.getUit()*35-(hast20UIT+hast5UIT+hast35UIT);
					 }
					 
					 if(renta>impuesto.getUit()*45){
						
						 masde45UIT=renta-impuesto.getUit()*35;
					 }
			}
					 
		     impuesto.setRetencAnual((hast5UIT*0.08)+(hast20UIT*0.14)+(hast35UIT*0.17)+(hast45UIT*0.20)+(masde45UIT*0.30));
		     impuesto.setTotal_neto(impuesto.getTotal_ingreso()-impuesto.getRetencAnual());
		     
				 	
			
		 		
		return impuesto;
	}
	
	
	@GetMapping("/impuesto/calcular")
	public String initCreationForm(Model model) {
		
		model.addAttribute("impuesto",new impuesto());
		return"impuestoFrm";
	}
	
	@PostMapping("/impuesto/calcular")
	private String calculoImpuesto(@Valid impuesto impuesto, BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()) {
			return "impuestoFrm";
		}
		impuesto=calculoImpuesto(impuesto);
		return"calculoImpuesto";
	}
	
	@PostMapping("/impuesto/nuevo")
	private String guardarImpuesto(@ModelAttribute impuesto impuesto) {
		impuestoRepository.save(impuesto);
		
		return"redirect:/impuesto/lista";
	}
	@GetMapping("/impuesto/lista")
	private String listado(Map<String,Object> model) {
		
		List<impuesto> impuesto= impuestoRepository.findAll();
		model.put("impuesto",impuesto);
		return "listado";
	}
	
	
	
	

}
