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

import co.edu.usbcali.banco.domain.TipoDocumento;

class TestTipoDocumentoJPA {

	Long id_docto = 4L;
	@Test
	@DisplayName("CrearTipoDocumento")
	void atest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNull(tipoDocto, "El tipo de documento ya existe");
		
		tipoDocto = new TipoDocumento();
		tipoDocto.setTdocId(id_docto);
		tipoDocto.setNombre("Tarjeta de identidad");
		tipoDocto.setActivo("S");
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoDocto);
		entityManager.getTransaction().commit();
	}
	@Test
	@DisplayName("ModificarTipoDocumento")
	void btest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNotNull(tipoDocto, "El tipo de documento no existe");
		
		tipoDocto.setNombre("REGISTRO CIVIL");
		tipoDocto.setActivo("N");
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoDocto);
		entityManager.getTransaction().commit();
	}	
	@Test
	@DisplayName("EliminarTipoDocumento")
	void ctest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		TipoDocumento tipoDocto = entityManager.find(TipoDocumento.class, id_docto);
		assertNotNull(tipoDocto, "El tipo de documento no existe");		
		
		entityManager.getTransaction().begin();
		entityManager.remove(tipoDocto);
		entityManager.getTransaction().commit();
	}
	private final static Logger log = LoggerFactory.getLogger(TestTipoDocumentoJPA.class);
	@Test
	@DisplayName("ConsultarAllTipoDocumentos")
	void dtest() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "el entityManager es Nulo");
		
		String consulta = "select tdocto from TipoDocumento tdocto";
		List<TipoDocumento> allTypeDoctos = entityManager.createQuery(consulta).getResultList();
		
		allTypeDoctos.forEach(TipoDocumento-> {
			log.info("Id_tipoDocto:" + TipoDocumento.getTdocId() + " -> Nombre:" + TipoDocumento.getNombre() + " -> ¿Activo?" + TipoDocumento.getActivo());
		});
	}
	
}
