package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;


@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {
	
	private RepositorioLocalidad repositorio;

	@Autowired
	public ServicioTurnoImpl(RepositorioLocalidad repositorioLocalidad){
		this.repositorio = repositorioLocalidad;
	}


	@Override
	public List<Localidad> obtenerLocalidades(String zona) {
		
		return repositorio.obtenerLocalidades(zona);
	}

}
