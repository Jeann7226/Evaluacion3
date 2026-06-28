package com.usuario.usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.usuarios.dto.UsuarioDTO;
import com.usuario.usuarios.model.Usuario;
import com.usuario.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<UsuarioDTO> obtenerTodos(){
        return usuarioRepository.findAll().stream().map(this::convertirUsuarioDTO).toList();
    }

    public UsuarioDTO buscaPorId(Long id_usuario){
        Usuario usuario = usuarioRepository.findById(id_usuario)
        .orElseThrow(() -> new RuntimeException("El usuario no existe en los registros"));
        return convertirUsuarioDTO(usuario);
    }

    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id_usuario, Usuario usuarioActualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id_usuario)
        .orElseThrow(() -> new RuntimeException("el usuario no existe en los registros"));
        
        if(usuarioActualizado.getNombre() != null){
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
        }
        if(usuarioActualizado.getCorreo() != null){
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        }
        return usuarioRepository.save(usuarioExistente);
    }

    public String eliminar(Long id_usuario){
        try{
            Usuario usuario = usuarioRepository.findById(id_usuario)
            .orElseThrow(() -> new RuntimeException("el usuario con el id " + id_usuario +"' no existe"));
            usuarioRepository.delete(usuario);
            return "el usuario ha sido eliminado.";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    
    private UsuarioDTO convertirUsuarioDTO(Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId_usuario(usuario.getId_usuario());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        return dto;
    }
    
}