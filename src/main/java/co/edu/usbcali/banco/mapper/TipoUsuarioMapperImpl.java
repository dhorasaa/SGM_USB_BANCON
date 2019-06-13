package co.edu.usbcali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.domain.TipoUsuario;
import co.edu.usbcali.banco.dto.TipoUsuarioDTO;
;

@Component
@Scope("singleton")
public class TipoUsuarioMapperImpl implements TipoUsuarioMapper {
	
	public TipoUsuarioDTO tipoUsuarioToTipoUsuarioDTO(TipoUsuario tipoUsuario) throws Exception {
	    try {
        	
        	TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();

            tipoUsuarioDTO.setTiusid(tipoUsuario.getTiusId());
            tipoUsuarioDTO.setActivo((tipoUsuario.getActivo() != null)
                ? tipoUsuario.getActivo() : null);
            tipoUsuarioDTO.setNombre((tipoUsuario.getNombre() != null)
                ? tipoUsuario.getNombre() : null);
            
            return tipoUsuarioDTO;
        } catch (Exception e) {
            throw e;
        }
	}

	@Transactional(readOnly = true)
	public TipoUsuario tipoUsuarioDTOToTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) throws Exception {
		 try {
	            TipoUsuario tipoUsuario = new TipoUsuario();	           

	            tipoUsuario.setTiusId(tipoUsuario.getTiusId());
	            tipoUsuario.setActivo((tipoUsuario.getActivo() != null)
	                ? tipoUsuario.getActivo() : null);
	            tipoUsuario.setNombre((tipoUsuario.getNombre() != null)
	                ? tipoUsuario.getNombre() : null);    

	            return tipoUsuario;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Transactional(readOnly = true)
	public List<TipoUsuarioDTO> listTipoUsuarioToListTipoUsuarioDTO(List<TipoUsuario> tipoUsuarios) throws Exception {
		 try {
	            List<TipoUsuarioDTO> TipoUsuarioDTOs = new ArrayList<TipoUsuarioDTO>();

	            for (TipoUsuario tipoUsuario : tipoUsuarios) {
	                TipoUsuarioDTO tipoUsuarioDTO = tipoUsuarioToTipoUsuarioDTO(tipoUsuario);

	                TipoUsuarioDTOs.add(tipoUsuarioDTO);
	            }

	            return TipoUsuarioDTOs;
	        } catch (Exception e) {
	            throw e;
	        }
	}

	@Transactional(readOnly = true)
	public List<TipoUsuario> listTipoUsuarioDTOToListTipoUsuario(List<TipoUsuarioDTO> tipoUsuarioDTOs)
			throws Exception {
		   try {
	            List<TipoUsuario> listTipoUsuario = new ArrayList<TipoUsuario>();

	            for (TipoUsuarioDTO tipoUsuarioDTO : tipoUsuarioDTOs) {
	                TipoUsuario tipoUsuario = tipoUsuarioDTOToTipoUsuario(tipoUsuarioDTO);

	                listTipoUsuario.add(tipoUsuario);
	            }

	            return listTipoUsuario;
	        } catch (Exception e) {
	            throw e;
	        }
	    }
	}


