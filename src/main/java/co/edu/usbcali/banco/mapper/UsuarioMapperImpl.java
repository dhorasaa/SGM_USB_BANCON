package co.edu.usbcali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.domain.Usuario;
import co.edu.usbcali.banco.dto.UsuarioDTO;

@Component
@Scope("singleton")
public class UsuarioMapperImpl implements UsuarioMapper {

	
	@Transactional(readOnly = true)
	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) throws Exception {
		 try {
	            UsuarioDTO usuarioDTO = new UsuarioDTO();	           

	            usuarioDTO.setUsuUsuario(usuarioDTO.getUsuUsuario());
	            usuarioDTO.setClave((usuarioDTO.getClave() != null)
		                ? usuarioDTO.getClave() : null);
	            usuarioDTO.setActivo((usuarioDTO.getActivo() != null)
	                ? usuarioDTO.getActivo() : null);
	            usuarioDTO.setNombre((usuarioDTO.getNombre() != null)
	                ? usuarioDTO.getNombre() : null);
	            usuarioDTO.setTipoUsuario((usuario.getTipoUsuario() != null)
		                ? usuarioDTO.getTipoUsuario() : null);  

	            return usuarioDTO;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Transactional(readOnly = true)
	public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) throws Exception {
		 try {
	            Usuario usuario = new Usuario();	           

	            usuario.setUsuUsuario(usuario.getUsuUsuario());
	            usuario.setClave((usuario.getClave() != null)
		                ? usuario.getClave() : null);
	            usuario.setActivo((usuario.getActivo() != null)
	                ? usuario.getActivo() : null);
	            usuario.setNombre((usuario.getNombre() != null)
	                ? usuario.getNombre() : null);
	            usuario.setTipoUsuario((usuario.getTipoUsuario() != null)
		                ? usuario.getTipoUsuario() : null);   

	            return usuario;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Transactional(readOnly = true)
	public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios) throws Exception {
		  try {
	            List<UsuarioDTO> usuarioDTOs = new ArrayList<UsuarioDTO>();

	            for (Usuario usuario : usuarios) {
	                UsuarioDTO usuarioDTO = usuarioToUsuarioDTO(usuario);

	                usuarioDTOs.add(usuarioDTO);
	            }

	            return usuarioDTOs;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Transactional(readOnly = true)
	public List<Usuario> listUsuarioDTOToListUsuario(List<UsuarioDTO> usuarioDTOs) throws Exception {
	      try {
	            List<Usuario> listUsuario = new ArrayList<Usuario>();

	            for (UsuarioDTO usuarioDTO : usuarioDTOs) {
	                Usuario usuario = usuarioDTOToUsuario(usuarioDTO);

	                listUsuario.add(usuario);
	            }

	            return listUsuario;
	        } catch (Exception e) {
	            throw e;
	        }
	}

}
