package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioMascotas {
	
	List<Mascota> listarMascotas();
	List<TipoAnimal> listarTipoAnimal();
	Mascota obtenerMascota(Long id);
	Mascota buscarMascotaPorDuenio(Long idDuenio);
	TipoAnimal obtenerTipoAnimal(Long id);
	List<Mascota> listarMascotasPorDuenio(Usuario id_duenio);
	
	void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre)throws ParseException;
	void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, TipoAnimal tipoAnimal);
	

}
