package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface ServicioTurno {
	
	
	List<Localidad> obtenerLocalidades(String zona);

}
