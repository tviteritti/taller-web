package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVeterinario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;


@Service("ServicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario ServicioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario ServicioUsuarioDao){
		this.ServicioUsuarioDao = ServicioUsuarioDao;
	}

	@Override
	public List<Usuario> getUsuarios() {
		
		return ServicioUsuarioDao.getUsuarios();
	}

	@Override
	public void registrarOMOdificarUsuario(Usuario usuario) {
		ServicioUsuarioDao.registrarOMOdificarUsuario(usuario);
		
	}

	@Override
	public Usuario getUsuario(Long id) {
		return ServicioUsuarioDao.getUsuario(id);
	}

	@Override
	public void eliminarUsuario(Long id) {
		ServicioUsuarioDao.eliminarUsuario(id);
		
	}

	@Override
	public Boolean buscarUsuario(String usuario, String password) {
		return ServicioUsuarioDao.buscarUsuario(usuario, password);
		
	}

	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		return ServicioUsuarioDao.validarPassRePass(pass, repass);
	}

}
