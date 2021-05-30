package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioLocalidad;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service
public class ServicioLoginVeterinariaImpl implements ServicioLoginVeterinaria{
	
	private RepositorioUsuario repositorioUsuario;
	

	@Autowired
	public ServicioLoginVeterinariaImpl(RepositorioUsuario repositorioUsuario){
		this.repositorioUsuario = repositorioUsuario;	
	}
		
	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		
		if(pass.equals(repass)) {
			
			return true;
			
		}
		
		return false;
	}

	@Override
	public void guardarUsuario(Usuario user) {
		
		repositorioUsuario.registrarOMOdificarUsuario(user);
	}

	@Override
	public Boolean validarUsuario(Usuario user) {
		
		Usuario usuarioBuscado = repositorioUsuario.consultarUsuario(user);
		
		if(usuarioBuscado.equals(user)) {
			
			return true;
			
		}
		return false;
	}
	
	

}
