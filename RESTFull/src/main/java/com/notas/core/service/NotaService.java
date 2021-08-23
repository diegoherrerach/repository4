package com.notas.core.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.notas.core.converter.Convertidor;
import com.notas.core.entity.Nota;
import com.notas.core.model.Mnota;
import com.notas.core.repository.NotaRepositorio;

@Service("servicio")
public class NotaService {
	@Autowired
	@Qualifier("repositorio")
	private NotaRepositorio repositorio; 
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	private static final Log logger = LogFactory.getLog(NotaService.class);
	
	public boolean crear(Nota nota) {
		logger.info("CREANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("NOTA CREADA");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	public boolean actualizar(Nota nota) {
		logger.info("ACTUALIZANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("NOTA ACTUALIZADA");
			return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	public boolean borrar(String nombre, long id) {
		logger.warn("ELIMINANDO NOTA");
		try {
		Nota nota =	repositorio.findByNombreAndId(nombre, id);
		repositorio.delete(nota);
		logger.info("NOTA ELIMINADA");
		return true;
		}catch(Exception e) {
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	
	public List<Mnota> obtener(){
		logger.info("OBTENIENDO TODA LA LISTA");
		return convertidor.convertirLista(repositorio.findAll());
	}
	public Mnota obtenerPorNombreTitulo(String nombre, String titulo) {
		return new Mnota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	public List<Mnota> obtenerTitulo(String titulo){
		return convertidor.convertirLista(repositorio.findByTitulo(titulo));
	}
	public Mnota obtenerNombre(String nombre) {
		return new Mnota(repositorio.findByNombre(nombre));
	}
	public Mnota obtenerNombreId(String nombre, long id) {
		return new Mnota(repositorio.findByNombreAndId(nombre, id));
	}
	public List<Mnota> obtenerPorPaginacion(Pageable pageable){
		return convertidor.convertirLista(repositorio.findAll(pageable).getContent());
	}
	
}
