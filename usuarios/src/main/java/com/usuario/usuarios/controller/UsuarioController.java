package com.usuario.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.usuarios.dto.UsuarioDTO;
import com.usuario.usuarios.model.Usuario;
import com.usuario.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServices;
    

    @Operation(summary = "Buscar usuario por ID", description = "Busca un usuario específico por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuario(){
        List<UsuarioDTO> usuarios = usuarioServices.obtenerTodos();
        if(usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    @Operation(summary = "Buscar usuario por ID", description = "Busca un usuario específico por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @GetMapping("/{id_usuario}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Long id_usuario){
        try{
            UsuarioDTO usuario = usuarioServices.buscaPorId(id_usuario);
            return new ResponseEntity<>(usuario,HttpStatus.OK);
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Registrar Usuario", description = "Crea un nuevo usuario en la base de datos.")
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@Valid @RequestBody Usuario usuarioNuevo){
        Usuario usuario = usuarioServices.guardar(usuarioNuevo);
        if(usuario != null){
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Editar Usuario", description = "Actualiza un usuario existente por su ID.")
    @PutMapping("/{id_usuario}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id_usuario, @RequestBody Usuario usuario) {
        Usuario usuarioEditado = usuarioServices.actualizar(id_usuario, usuario);
        if (usuarioEditado != null) {
            return new ResponseEntity<>(usuarioEditado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Eliminar Usuario", description = "Elimina un usuario existente por su ID.")
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id_usuario) {
        String resultado = usuarioServices.eliminar(id_usuario);
        if (resultado.equals("El usuario ha sido eliminado.")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
    }
}