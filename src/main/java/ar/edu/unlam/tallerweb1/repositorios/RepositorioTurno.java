package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface RepositorioTurno {
	
	List<Veterinario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnos(Veterinario veterinario);
	List<Turno> obtenerTurnos(String servicio);
	List<Turno> listarTurnos();
	Boolean cancelarTurno(Turno turno);


}
