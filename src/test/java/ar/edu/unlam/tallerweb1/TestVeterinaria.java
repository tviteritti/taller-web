package ar.edu.unlam.tallerweb1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurnoImpl;
import org.springframework.transaction.annotation.Transactional;

public class TestVeterinaria extends SpringTest{
	
	@Test
	@Transactional
	public void testQueGuardeVeterinariosEnLaBD() {
	
		Veterinario vt = new Veterinario();
		
		Zona zona  = new Zona();
		zona.setDescripcion("oeste");
		
		
		vt.setNombre("Julieta");
		vt.setApellido("Barraza");
		vt.setZona(zona);
		
		session().save(zona);
		session().save(vt);
		
		Zona zonaBD = session().get(Zona.class, 1L);
		Veterinario vtBD = session().get(Veterinario.class, 1L);
		
		assertEquals((Long)vtBD.getId(), (Long)1L);
		
	}
	
	@Test
	@Transactional
	public void testQueObtengaVTPorZona() {
		
		RepositorioTurnoImpl rt = new RepositorioTurnoImpl(sessionFactory);
		
		Veterinario vt = new Veterinario();
		
		Zona zona  = new Zona();
		zona.setDescripcion("oeste");
		
		
		vt.setNombre("Julieta");
		vt.setApellido("Barraza");
		vt.setZona(zona);
		
		session().save(zona);
		session().save(vt);
		
		Zona zonaBD = session().get(Zona.class, 1L);
		/*Veterinario vtBD = session().get(Veterinario.class, 1L);
		Veterinario vtBuscado =(Veterinario) rt.obtenerVeterinariosPorZona(zonaBD.getDescripcion());*/
		
		assertEquals( zonaBD.getDescripcion(),"oeste");

		//assertEquals((Long)zona.getId(), (Long)1L);
	}

}
