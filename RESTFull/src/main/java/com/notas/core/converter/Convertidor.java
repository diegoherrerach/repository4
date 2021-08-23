package com.notas.core.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.notas.core.entity.Nota;
import com.notas.core.model.Mnota;

@Component("convertidor")
public class Convertidor {
	public List<Mnota> convertirLista(List<Nota> notas){
		List<Mnota> mnotas= new ArrayList<>();
		for(Nota nota : notas) {
			mnotas.add(new Mnota(nota));
		}
		return mnotas;
	}
}
