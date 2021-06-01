package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;



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
	public List<Usuario> getVeterinarios() {
		return ServicioUsuarioDao.getVeterinarios();
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
		List<Usuario> usuarios= ServicioUsuarioDao.getUsuarios();
	
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Usuario devolverUsuario(String usuario, String password) {
		
		List<Usuario> usuarios= ServicioUsuarioDao.getUsuarios();
		
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return usuario2;
			}
		}
		return null;
	}

	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		if(pass.equals(repass)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Especialidad> getEspecialidades() {
		return ServicioUsuarioDao.getEspecialidades();
	}

	@Override
	public void ingresarEspecialidad(Long id, Long id_especialidad) {
		ServicioUsuarioDao.ingresarEspecialidad(id, id_especialidad);
		
	}



}
