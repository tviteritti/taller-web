package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVeterinario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;


@Service("ServicioVeterinario")
@Transactional
public class ServicioVeterinarioImpl implements ServicioVeterinario {

	private RepositorioVeterinario servicioVeterinarioDao;

	@Autowired
	public ServicioVeterinarioImpl(RepositorioVeterinario servicioVeterinarioDao){
		this.servicioVeterinarioDao = servicioVeterinarioDao;
	}
	
	@Override
	public Veterinario consultarVeterinario(Veterinario veterinario) {
		return servicioVeterinarioDao.consultarVeterinario(veterinario);
	}

	@Override
	public List<Veterinario> getVeterinarios() {
		
		return servicioVeterinarioDao.getVeterinarios();
	}

	@Override
	public void registrarOMOdificarVeterinario(Veterinario veterinario) {
		servicioVeterinarioDao.registrarOMOdificarVeterinario(veterinario);
		
	}

	@Override
	public Veterinario getVeterinario(Long id) {
		return servicioVeterinarioDao.getVeterinario(id);
	}

	@Override
	public void eliminarVeterinario(Long id) {
		servicioVeterinarioDao.eliminarVeterinario(id);
		
	}

}
