package co.edu.usbcali.banco.spring;

import static org.junit.jupiter.api.Assertions.*;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestTipoUsuarioSprig {

	Long tiUsuId = 4L;
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@DisplayName("CrearTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void atest() {
		
		assertNotNull(entityManager,"el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiUsuId);
		assertNull(tipoUsuario, "El Tipo de usuario ya existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setTiusId(tiUsuId);
		tipoUsuario.setNombre("Vendedor");
		tipoUsuario.setActivo("S");
		
		entityManager.persist(tipoUsuario);		
	}
	
	@Test
	@DisplayName("ModificarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void btest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiUsuId);
		assertNotNull(tipoUsuario, "El tipo de usuario no existe");
		
		tipoUsuario.setNombre("VENDEDOR STUFF");
		tipoUsuario.setActivo("N");
		
		entityManager.merge(tipoUsuario);	
	}
	
	@Test
	@DisplayName("BorrarTipoUsuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void ctest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, tiUsuId);
		assertNotNull(tipoUsuario, "El tipo de usuario no existe");		
		
		entityManager.remove(tipoUsuario);
	}
	
	private final static Logger log = LoggerFactory.getLogger(TestTipoUsuarioSprig.class);
	@Test
	@DisplayName("ConsultarAllTipoUsuarios")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dtest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		String consulta = "select c from TipoUsuario c";
		List<TipoUsuario> allTypeUsers = entityManager.createQuery(consulta).getResultList();
		
		allTypeUsers.forEach(TipoUsuario-> {
			log.info("Id_TipoUsuario:" + TipoUsuario.getTiusId() + " -> Nombre:" + TipoUsuario.getNombre() + " -> ¿Activo? " + TipoUsuario.getActivo());
		});
	}
}
