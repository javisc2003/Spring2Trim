package com.example.spring2trim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    String nombre;

    @Column(name = "total")
    Double total;

    @Column
    String estado;

    public Cliente(){

    }

    public Cliente(String nombre, Double total, String estado) {
        this.nombre = nombre;
        this.total = total;
        this.estado = estado;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", total='" + total + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
