package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface ServicioTurno {
	
	
	List<Localidad> obtenerLocalidades(String zona);
	List<Veterinario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnos(Veterinario veterinario);
	List<Turno> obtenerTurnos(String servicio);
	List<Turno> listarTurnos();
	void cancelarTurno(Long idTurno);

}
