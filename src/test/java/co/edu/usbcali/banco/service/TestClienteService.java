package co.edu.usbcali.banco.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.domain.Cliente;
import co.edu.usbcali.banco.domain.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TestClienteService {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private TipoDocumentoService tipoDocumentoService;

	Long clieId=1654654L;
	
	@Test
	@DisplayName("CrearCliente")
	void atest()throws Exception {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoService);
		
		Cliente cliente=clienteService.findById(clieId);
		assertNull(cliente,"El cliente ya existe");
		
		cliente=new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hjsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("315 550 44 40");
		
		TipoDocumento tipoDocumento=tipoDocumentoService.findById(2L);
		assertNotNull(tipoDocumento);
		
		cliente.setTipoDocumento(tipoDocumento);
		
		clienteService.save(cliente);
	}
	
	@Test
	@DisplayName("ModificarCliente")
	void btest()throws Exception {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoService);
		
		Cliente cliente=clienteService.findById(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		cliente.setActivo("N");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hjsimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("315 550 44 40");
		
		clienteService.update(cliente);
	}
	
	@Test
	@DisplayName("BorrarCliente")
	void ctest()throws Exception {
		assertNotNull(clienteService);
		
		Cliente cliente=clienteService.findById(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		clienteService.delete(cliente);		
	}
	
	private final static Logger log=LoggerFactory.getLogger(TestClienteService.class);
	
	@Test
	@DisplayName("ConsultarTodos")
	void dTest() {		
		assertNotNull(clienteService);
		List<Cliente> losCliente=clienteService.findAll();		
		losCliente.forEach(cliente->{
			log.info("Id:"+cliente.getClieId());
			log.info("Nombre:"+cliente.getNombre());
		});
	}
	
	
	
	
	
	
	

}
