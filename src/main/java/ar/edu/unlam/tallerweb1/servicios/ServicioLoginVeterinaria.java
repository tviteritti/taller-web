package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioLoginVeterinaria {
	
	Boolean buscarUsuario(String usuario, String password);
	
	Boolean validarPassRePass(String pass, String repass);

}
