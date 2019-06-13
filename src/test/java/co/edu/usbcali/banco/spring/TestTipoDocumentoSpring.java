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

import co.edu.usbcali.banco.domain.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)

class TestTipoDocumentoSpring {

	Long id_docto = 4L;
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@DisplayName("CrearTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	
	void atest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNull(tipoDocto, "El tipo de documento ya existe");
		
		tipoDocto = new TipoDocumento();
		tipoDocto.setTdocId(id_docto);
		tipoDocto.setNombre("Tarjeta de identidad");
		tipoDocto.setActivo("S");		
		
		entityManager.persist(tipoDocto);	
	}
	
	@Test
	@DisplayName("ModificarTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	
	void btest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNotNull(tipoDocto, "El tipo de documento no existe");
		
		tipoDocto.setNombre("REGISTRO CIVIL");
		tipoDocto.setActivo("N");		
		
		entityManager.merge(tipoDocto);
	}	
	@Test
	@DisplayName("EliminarTipoDocumento")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	
	void ctest() {
		
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNotNull(tipoDocto, "El tipo de documento no existe");		
		
		entityManager.remove(tipoDocto);
	}
	
	private final static Logger log = LoggerFactory.getLogger(TestTipoUsuarioSprig.class);
	@Test
	@DisplayName("ConsultarAllTipoDocumentos")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	
	void dtest() {
		
		assertNull(entityManager, "el entityManager es Nulo");
		
		String consulta = "select tdocto from TipoDocumento tdocto";
		List<TipoDocumento> allTypeDoctos = entityManager.createQuery(consulta).getResultList();
		
		allTypeDoctos.forEach(TipoDocumento-> {
			log.info("Id_tipoDocto:" + TipoDocumento.getTdocId() + " -> Nombre:" + TipoDocumento.getNombre() + " -> ¿Activo?" + TipoDocumento.getActivo());
		});
	}
	
}