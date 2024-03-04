package com.example.spring2trim.repository;

import com.example.spring2trim.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.estado = :estado AND c.total > :cantidad")
    List<Cliente> ActivoVentas(String estado, Double cantidad);

    @Query("SELECT SUM(c.total) FROM Cliente c")
    Double TotalVentas();

    @Query("SELECT AVG(c.total) FROM Cliente c WHERE c.estado = :estado")
    Double PromedioEstado(String estado);

    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.estado = :estado AND c.total > :cantidad")
    Long InactivosVentas(String estado, Double cantidad);

}
