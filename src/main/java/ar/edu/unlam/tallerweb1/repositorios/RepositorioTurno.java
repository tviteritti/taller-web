package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioTurno {
	
//	List<Veterinario> obtenerVeterinariosPorZona(String zona);
//	List<Turno> obtenerTurnos(Veterinario veterinario);
	List<Turno> obtenerTurnos(String servicio);
	List<Turno> listarTurnos();
	void cancelarTurno(Long idTurno);
	
	void generarTurnoPorIdDia(Long id_dia);
	Usuario devolverVeterinarioDeunDia(Long id_dia);
	Horarios devolverDialunes(Long id_dia);
	void generarTurno(Turno turno);


}
