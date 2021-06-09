package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioTurno {
	
	List<Usuario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnosPorVeterinario(Usuario veterinario);
	List<Turno> obtenerTurnosPorEspecialidad(String servicio);
	List<Turno> obtenerTurnosPorEspecialidadZonaYVeterinario(String servicio, String zona, Usuario veterinario);
	
	List<Turno> listarTurnos();
	List<Turno> buscarTurnoPorDuenio(Long id);
	List<Turno> buscarTurnoPorVeterinario(Long id);
	void asignarTurno(Long idTurno, Mascota mascota , Usuario duenio);
	void cargarTurno(Turno turno);
	void cancelarTurno(Long idTurno);
	
	
	List<Turno> listarTurnosSinTomar();
	Usuario devolverVeterinarioDeunDia(Long id_dia);
	Especialidad devolverEspecialidadDeunDia(Long id_dia);
	void generarTurno(Turno turno);
	void tomarTurno(Long id, Usuario duenio);
	
	Horarios devolverDialunes(Long id_dia);
	Horarios devolverDiaMartes(Long id_dia);
	Horarios devolverDiaMiercoles(Long id_dia);
	Horarios devolverDiaJueves(Long id_dia);
	Horarios devolverDiaViernes(Long id_dia);
	Horarios devolverDiaSabado(Long id_dia);
	Horarios devolverDiaDomingo(Long id_dia);
	


}
