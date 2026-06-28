package com.usuario.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usuario.usuarios.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.usuario.id_usuario = :usuarioId ORDER BY p.fecha_transaccion DESC")
    List<Pago> buscarHistorialPorUsuario(@Param("usuarioId") Long usuarioId);
}
