package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.domain.TipoUsuario;

class TestTipoUsuarioJPA {
    
	Long tiUsuId = 4L;
	@Test
	@DisplayName("CrearTipoUsuario")
	void atest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory,"El entityManagerFactory es nulo");
		
		EntityManager enttEntityManager = entityManagerFactory.createEntityManager();
		assertNotNull(enttEntityManager, "el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = enttEntityManager.find(TipoUsuario.class, tiUsuId);
		assertNull(tipoUsuario, "El Tipo de usuario ya existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setTiusId(tiUsuId);
		tipoUsuario.setNombre("Vendedor");
		tipoUsuario.setActivo("S");
		
		enttEntityManager.getTransaction().begin();
		enttEntityManager.persist(tipoUsuario);
		enttEntityManager.getTransaction().commit();
	}
	@Test
	@DisplayName("ModificarTipoUsuario")
	void btest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory,"El entityManagerFactory es nulo");
		
		EntityManager enttEntityManager = entityManagerFactory.createEntityManager();
		assertNotNull(enttEntityManager, "el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = enttEntityManager.find(TipoUsuario.class, tiUsuId);
		assertNotNull(tipoUsuario, "El Tipo de usuario no existe");
		
		tipoUsuario.setNombre("VENDEDOR STUFF");
		tipoUsuario.setActivo("N");
		
		enttEntityManager.getTransaction().begin();
		enttEntityManager.merge(tipoUsuario);
		enttEntityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoUsuario")
	void ctest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory,"El entityManagerFactory es nulo");
		
		EntityManager enttEntityManager = entityManagerFactory.createEntityManager();
		assertNotNull(enttEntityManager, "el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = enttEntityManager.find(TipoUsuario.class, tiUsuId);
		assertNotNull(tipoUsuario, "El Tipo de usuario no existe");		
		
		enttEntityManager.getTransaction().begin();
		enttEntityManager.remove(tipoUsuario);
		enttEntityManager.getTransaction().commit();
	}
	private final static Logger log = LoggerFactory.getLogger(TestTipoUsuarioJPA.class);
	@Test
	@DisplayName("ConsultarAllTipoUsuarios")
	void dtest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory,"El entityManagerFactory es nulo");
		
		EntityManager enttEntityManager = entityManagerFactory.createEntityManager();
		assertNotNull(enttEntityManager, "el entityManager es Nulo");
		
		String consulta = "select c from TipoUsuario c";
		List<TipoUsuario> allTypeUsers = enttEntityManager.createQuery(consulta).getResultList();
		
		allTypeUsers.forEach(TipoUsuario-> {
			log.info("Id_TipoUsuario:" + TipoUsuario.getTiusId() + " -> Nombre:" + TipoUsuario.getNombre() + " -> ¿Activo? " + TipoUsuario.getActivo());
		});
	}
}
