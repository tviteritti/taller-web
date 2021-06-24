package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioMascotas {
	
	List<Mascota> listarMascotas();
	List<Mascota> listarMascotasPorDuenio(Usuario id_duenio);
	List<TipoAnimal> listarTipoAnimal();
	TipoAnimal obtenerTipoAnimal(Long id);
	Mascota buscarMascotaPorDuenio(Long idDuenio);
	
	void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre)throws ParseException;
	void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, TipoAnimal tipoAnimal);
	
	
	

}
