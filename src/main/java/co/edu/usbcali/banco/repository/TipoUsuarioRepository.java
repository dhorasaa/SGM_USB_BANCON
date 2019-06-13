package co.edu.usbcali.banco.repository;

import java.util.List;

import co.edu.usbcali.banco.domain.TipoUsuario;

public interface TipoUsuarioRepository {
public void save(TipoUsuario tipoUsuario)throws Exception;
public void update(TipoUsuario tipoUsuario)throws Exception;
public void delete(TipoUsuario tipoUsuario)throws Exception;
public TipoUsuario findById(Long id);
public List<TipoUsuario> findAll();
}
