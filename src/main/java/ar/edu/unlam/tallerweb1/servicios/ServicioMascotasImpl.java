package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;

@Service("ServicioMascotas")
@Transactional
public class ServicioMascotasImpl implements ServicioMascotas{
	
	private RepositorioMascotas servicioMascotasDao;

	@Autowired
	public ServicioMascotasImpl(RepositorioMascotas servicioMascotasDao){
		this.servicioMascotasDao = servicioMascotasDao;
		
	}

	@Override
	public List<Mascota> listarMascotas() {
		
		return servicioMascotasDao.listarMascotas();
	}

	@Override
	public List<TipoAnimal> listarTipoAnimal() {
		
		return servicioMascotasDao.listarTipoAnimal();
	}

	@Override
	public void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre)
			throws ParseException {
		servicioMascotasDao.cargarMascota(id_tipo, id_duenio, fecha_nac, nombre);
	}
	
	
	

}
