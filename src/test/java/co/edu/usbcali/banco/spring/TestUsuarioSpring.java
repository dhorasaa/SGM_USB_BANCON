package co.edu.usbcali.banco.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.domain.TipoUsuario;
import co.edu.usbcali.banco.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

class TestUsuarioSpring {
	
	String usuarioId = "L70"; //llave primaria;	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@DisplayName("CrearUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void atest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNull(usuario, "El usuario ya existe");
		
		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setClave("L70");		
	    usuario.setIdentificacion(new BigDecimal(1002));
		
		usuario.setNombre("Joe Hurtado Plata");			
				
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 3L);
		assertNotNull(tipoUsuario, "El tipo de usuario no existe");
		
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setUsuUsuario(usuarioId);
		
		entityManager.persist(usuario);
	}

	@Test
	@DisplayName("ModificarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void btest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNotNull(usuario, "El usuario no existe");
		
		usuario.setActivo("N");
		usuario.setClave("L70");		
	    usuario.setIdentificacion(new BigDecimal(1001));		
		usuario.setNombre("Noe Hurtado Plata");			
				
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 3L);
		assertNotNull(tipoUsuario, "El tipo de usuario no existe");		
			
		entityManager.merge(usuario);	
	}
	@Test
	@DisplayName("BorrarUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void ctest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		Usuario usuario = entityManager.find(Usuario.class, usuarioId);
		assertNotNull(usuario, "El usuario no existe");			

		entityManager.remove(usuario);				
	}
	
	private final static Logger log=LoggerFactory.getLogger(TestUsuarioSpring.class);
	@Test
	@DisplayName("ConsultarAllUsuarios")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dtest() {
	
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		String JPQL="select c from Usuario c";
		List<Usuario> allUsers = entityManager.createQuery(JPQL).getResultList();		
	
	    allUsers.forEach(Usuario-> {
	    	log.info("Id_usuario:"+ Usuario.getUsuUsuario() + " -> Nombre:" + Usuario.getNombre() + " -> Clave:" + Usuario.getClave() + " -> ¿Activo? " + Usuario.getActivo());
	    	    });
				
	}
}
