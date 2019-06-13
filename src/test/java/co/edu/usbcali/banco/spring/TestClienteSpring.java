package co.edu.usbcali.banco.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

import co.edu.usbcali.banco.domain.Cliente;
import co.edu.usbcali.banco.domain.Cuenta;
import co.edu.usbcali.banco.domain.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestClienteSpring {
	
	@PersistenceContext
	EntityManager entityManager;

	Long clieId=1654654L;
	
	@Test
	@DisplayName("CrearCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void atest() {
		assertNotNull(entityManager);
		
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNull(cliente,"El cliente ya existe");
		
		cliente=new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hjsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("315 550 44 40");
		
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,2L);
		assertNotNull(tipoDocumento);
		
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.persist(cliente);
	}
	
	@Test
	@DisplayName("ModificarCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void btest() {
		assertNotNull(entityManager);
		
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		cliente.setActivo("N");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hjsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("315 550 44 40");
		
		entityManager.merge(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	void ctest() {
		assertNotNull(entityManager);
		
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		assertEquals(0, cliente.getCuentas().size(), "Tiene cuentas Asociadas");
		
		entityManager.remove(cliente);
	}
	
	private final static Logger log=LoggerFactory.getLogger(TestClienteSpring.class);
	
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("banco-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "El entityManager es nulo");
		
		String JPQL="SELECT cli FROM Cliente cli";
		List<Cliente> losCliente=entityManager.createQuery(JPQL).getResultList();
		
		losCliente.forEach(cliente->{
			log.info("Id:"+cliente.getClieId());
			log.info("Nombre:"+cliente.getNombre());
		});
	}
	
	
	
	
	
	
	

}
