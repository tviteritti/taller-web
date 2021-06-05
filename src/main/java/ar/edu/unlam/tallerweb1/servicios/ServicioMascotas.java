package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface ServicioMascotas {
	
	List<Mascota> listarMascotas();
	List<TipoAnimal> listarTipoAnimal();
	
	void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre)throws ParseException;
	

}
