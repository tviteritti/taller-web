package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface RepositorioMascotas {
	
	List<Mascota> listarMascotas();
	List<Mascota> listarMascotasPorDuenio(Long id_duenio);
	List<Mascota> listarMascotasPorDuenio(Usuario usuario);
	List<TipoAnimal> listarTipoAnimal();
	TipoAnimal obtenerTipoAnimal(Long id);
	TipoAnimal obtenerTipoAnimal(String tipo);
	Mascota buscarMascotaPorDuenio(Long idDuenio);
	
	void cargarMascota(Long id_tipo, Long id_duenio, String fecha_nac, String nombre)throws ParseException;
	void cargarTipoAnimal(TipoAnimal tipo);
	void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, String tipoAnimal);
	void modificarPerfilMascota(Long idMascota, String nombre, Date fechaNacimineto, TipoAnimal tipoAnimal);
	
	
	

}
