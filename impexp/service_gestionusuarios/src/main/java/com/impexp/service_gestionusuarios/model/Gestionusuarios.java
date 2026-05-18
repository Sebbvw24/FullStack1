package com.impexp.service_gestionusuarios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gestionusuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gestionusuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreEmpresa;
    private String rut;
    private String numeroContacto;
    private String gmail;
    private String horarioAtencion;
}