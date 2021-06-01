package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface ServicioTurno {
	
	
	List<Localidad> obtenerLocalidades(String zona);
	List<Usuario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnosPorVeterinario(Usuario veterinario);
	List<Turno> obtenerTurnosPorEspecialidad(String servicio);
	List<Turno> listarTurnos();
	void cancelarTurno(Long idTurno);
	void asignarTurno(Long idTurno, Mascota mascota);

}
