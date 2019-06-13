package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.domain.TipoDocumento;
import co.edu.usbcali.banco.domain.TipoUsuario;
import co.edu.usbcali.banco.domain.Usuario;

class TestUsuarioJPA {
	
	String usuarioId = "L70"; //llave primaria;	
	@Test
	@DisplayName("CrearUsuario")
	void atest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNull(usuario, "El usuario ya existe");
		
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("L70");		
	    usuario.setIdentificacion(new BigDecimal(1002));
		
		usuario.setNombre("Joe Hurtado Plata");			
				
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 3L);
		assertNotNull(tipoUsuario, "El tipo de usuario NO existe");
		
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuUsuario(usuarioId);
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
	}

	@Test
	@DisplayName("ModificarUsuario")
	void btest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNotNull(usuario, "El usuario No existe");
		
		usuario.setActivo("N");
		usuario.setClave("L70");		
	    usuario.setIdentificacion(new BigDecimal(1001));		
		usuario.setNombre("Noe Hurtado Plata");			
				
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 3L);
		assertNotNull(tipoUsuario, "El tipo de usuario NO existe");		
			
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		
	}
	@Test
	@DisplayName("BorrarUsuario")
	void ctest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNotNull(usuario, "El usuario No existe");						

		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		
	}
	private final static Logger log=LoggerFactory.getLogger(TestUsuarioJPA.class);
	@Test
	@DisplayName("ConsultarAllUsuarios")
	void dtest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		String JPQL="select c from Usuario c";
		List<Usuario> allUsers = entityManager.createQuery(JPQL).getResultList();
		
		/*for (Usuario usuario : allUsers) {
			log.info("Id_usuario: "+ usuario.getUsuUsuario());
	    	log.info("Nombre: "+ usuario.getNombre());
		}*/
		
	    allUsers.forEach(Usuario-> {
	    	log.info("Id_usuario:"+ Usuario.getUsuUsuario() + " -> Nombre:" + Usuario.getNombre() + " -> Clave:" + Usuario.getClave() + " -> ¿Activo? " + Usuario.getActivo());
	    	    });
				
	}
}
