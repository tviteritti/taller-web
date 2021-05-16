package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
public class ServicioLoginVeterinariaImpl implements ServicioLoginVeterinaria{

	@Override
	public Boolean buscarUsuario(String usuario, String password) {
		
		
		if(usuario.equals("julieta") && password.equals("1234")) {
			
			return true;
			
		}
		
		return false;
	}

	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		
		if(pass.equals(repass)) {
			
			return true;
			
		}
		
		return false;
	}
	
	

}
