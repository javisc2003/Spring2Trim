package com.example.spring2trim.controller;


import com.example.spring2trim.model.Cliente;
import com.example.spring2trim.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/nuevocliente")
    public ResponseEntity<?> nuevoCliente(@RequestBody Cliente nuevoCliente) {
        clienteRepository.save(nuevoCliente);
        return ResponseEntity.ok("Cliente creado exitosamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerIdCliente(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos-con-ventas-mayor-a/{cantidad}")
    public ResponseEntity<List<Cliente>> activosVentasMayorA(@PathVariable Double cantidad) {
        List<Cliente> clientes = clienteRepository.ActivoVentas("activo", cantidad);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/resumen-estadistico")
    public ResponseEntity<?> estadisticas() {
        Double totalVentas = clienteRepository.TotalVentas();
        Double promedioVentas = clienteRepository.PromedioEstado("activo");
        Long clientesInactivosConVentas = clienteRepository.InactivosVentas("inactivo", 0.0);

        return ResponseEntity.ok().body(
                Map.of("total", totalVentas, "promedio", promedioVentas, "inactivos", clientesInactivosConVentas)
        );
    }
}

