package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface RepositorioTurno {
	
	List<Usuario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnos(Usuario veterinario);
	List<Turno> obtenerTurnos(String servicio);
	List<Turno> listarTurnos();
	void asignarTurno(Long idTurno, Mascota mascota);
	void cancelarTurno(Long idTurno);
	


}
