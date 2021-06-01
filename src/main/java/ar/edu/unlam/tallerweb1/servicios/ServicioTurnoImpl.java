package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;


@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {
	
	private RepositorioLocalidad repositorioLocalidad;
	private RepositorioTurno repositorioTurno;


	@Autowired
	public ServicioTurnoImpl(RepositorioLocalidad repositorioLocalidad, RepositorioTurno repositorioTurno){
		this.repositorioLocalidad = repositorioLocalidad;
		this.repositorioTurno = repositorioTurno;
	}


	@Override
	public List<Localidad> obtenerLocalidades(String zona) {
		
		return repositorioLocalidad.obtenerLocalidades(zona);
	}


	@Override
	public List<Usuario> obtenerVeterinariosPorZona(String zona) {
		
		return repositorioTurno.obtenerVeterinariosPorZona(zona);
	}


	@Override
	public void cancelarTurno(Long idTurno) {
		
		repositorioTurno.cancelarTurno(idTurno);
	}


	@Override
	public List<Turno> obtenerTurnosPorVeterinario(Usuario veterinario) {
		
		return repositorioTurno.obtenerTurnosPorVeterinario(veterinario);
	}


	@Override
	public List<Turno> obtenerTurnosPorEspecialidad(String servicio) {
		
		return repositorioTurno.obtenerTurnosPorEspecialidad(servicio);
	}


	@Override
	public List<Turno> listarTurnos() {
		
		return repositorioTurno.listarTurnos();
	}


	@Override
	public void asignarTurno(Long idTurno, Mascota mascota) {
		
		repositorioTurno.asignarTurno(idTurno, mascota);
		
	}
	
	

}
