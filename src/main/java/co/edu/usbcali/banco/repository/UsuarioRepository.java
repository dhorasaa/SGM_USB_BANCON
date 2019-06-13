package co.edu.usbcali.banco.repository;

import java.util.List;

import co.edu.usbcali.banco.domain.Usuario;

public interface UsuarioRepository {
	public void save(Usuario usuario)throws Exception;
	public void update(Usuario usuario)throws Exception;
	public void delete(Usuario usuario)throws Exception;
	public Usuario findById(String id);
	public List<Usuario> findAll();
}
