package com.autor.autores.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.autor.autores.DTO.AutorDTO;
import com.autor.autores.DTO.MangaExternoDTO;
import com.autor.autores.DTO.UsuarioExternoDTO;
import com.autor.autores.Model.Autor;

import reactor.core.publisher.Mono;

@Service
@Component
public class AutorValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<MangaExternoDTO> obtenerMangasDelAutor(Integer autorId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://mangas/api/v1/mangas/autor/" + autorId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToFlux(MangaExternoDTO.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public UsuarioExternoDTO obtenerUsuarioExterno(Integer usuarioId) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://usuarios/api/v1/usuarios/" + usuarioId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(UsuarioExternoDTO.class)
                    .block();
        } catch (Exception e) {
            UsuarioExternoDTO fallback = new UsuarioExternoDTO();
            fallback.setId_usuario(0);
            fallback.setNombre("Usuario no encontrado");
            return fallback;
        }
    }

    public AutorDTO convertirADTO(Autor autor) {
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setIdAutor(autor.getIdAutor());
        autorDTO.setNombre(autor.getNombre());
        return autorDTO;
    }

}
