package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHistoriaClinica;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioMascotas;

@Service
@Transactional
public class ServicioHistoriaClinicaImpl implements ServicioHistoriaClinica{
	
	private RepositorioHistoriaClinica repositorioHistoriaClinica;

	@Autowired
	public ServicioHistoriaClinicaImpl(RepositorioHistoriaClinica repositorioHistoriaClinica){
		this.repositorioHistoriaClinica = repositorioHistoriaClinica;
		
	}

	@Override
	public List<HistoriaClinica> listarHistoriaClinicaPorMascotaYVeterinario(Mascota mascota, Usuario veterinario) {
		
		return repositorioHistoriaClinica.listarHistoriaClinicaPorMascotaYVeterinario(mascota, veterinario);
	}

	@Override
	public List<HistoriaClinica> buscarHCPorMascota(Long idMascota) {
		
		List<HistoriaClinica> historiaClinica = repositorioHistoriaClinica.obtenerHistoriaClinica();
		List<HistoriaClinica>hcEncontrada =  new ArrayList<>();
		
		for(HistoriaClinica hc: historiaClinica) {
			if(hc.getMascota().getId().equals(idMascota)) {
				
				hcEncontrada.add(hc);
				
			}
		}
		return hcEncontrada;
	}

	@Override
	public List<HistoriaClinica> buscarHCPorMascotaYDuenio(Mascota mascota, Usuario duenio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cargarHC(Usuario veterinario, Mascota mascota, String fecha, String diagnostico, String tratamiento) {
		repositorioHistoriaClinica.cargarHC(veterinario, mascota, fecha, diagnostico, tratamiento);
		
	}
	
	
	
	

}
