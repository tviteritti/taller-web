package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;


@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {
	
	private RepositorioLocalidad repositorio;
	private RepositorioTurno repositorioTurno;


	@Autowired
	public ServicioTurnoImpl(RepositorioLocalidad repositorioLocalidad, RepositorioTurno repositorioTurno){
		this.repositorio = repositorioLocalidad;
		this.repositorioTurno = repositorioTurno;
	}


	@Override
	public List<Localidad> obtenerLocalidades(String zona) {
		
		return repositorio.obtenerLocalidades(zona);
	}


	@Override
	public List<Veterinario> obtenerVeterinariosPorZona(String zona) {
		
		return repositorioTurno.obtenerVeterinariosPorZona(zona);
	}


	@Override
	public Boolean cancelarTurno(Turno turno) {
		
		return repositorioTurno.cancelarTurno(turno);
	}


	@Override
	public List<Turno> obtenerTurnos(Veterinario veterinario) {
		
		return repositorioTurno.obtenerTurnos(veterinario);
	}


	@Override
	public List<Turno> obtenerTurnos(String servicio) {
		
		return repositorioTurno.obtenerTurnos(servicio);
	}


	@Override
	public List<Turno> listarTurnos() {
		
		return repositorioTurno.listarTurnos();
	}

}
