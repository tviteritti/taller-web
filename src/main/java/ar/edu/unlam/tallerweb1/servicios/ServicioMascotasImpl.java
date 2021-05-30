package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;

@Service
public class ServicioMascotasImpl implements ServicioMascotas{
	
	private RepositorioMascotas repositorioMascotas;

	@Autowired
	public ServicioMascotasImpl(RepositorioMascotas repositorioMascotas){
		this.repositorioMascotas = repositorioMascotas;
		
	}

	@Override
	public List<Mascota> listarMascotas() {
		
		return repositorioMascotas.listarMascotas();
	}

	@Override
	public List<TipoAnimal> listarTipoAnimal() {
		
		return repositorioMascotas.listarTipoAnimal();
	}
	
	
	

}
