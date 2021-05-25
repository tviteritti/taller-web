package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioLoginVeterinaria {
	

	Boolean validarPassRePass(String pass, String repass);
	
	void guardarUsuario(Usuario user);
	
	Boolean validarUsuario(Usuario user);

}
