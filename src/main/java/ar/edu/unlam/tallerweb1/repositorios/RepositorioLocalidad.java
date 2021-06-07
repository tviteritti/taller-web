package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface RepositorioLocalidad {
	
	List<Localidad> obtenerLocalidadesPorZona(String zona);

}