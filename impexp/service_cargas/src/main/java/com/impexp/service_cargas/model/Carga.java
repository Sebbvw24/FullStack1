package com.impexp.service_cargas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Carga")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carga 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String material;
    private Double pesoKg;
    private String manejoCarga; 
    private String categoriaCarga; 
    private LocalDateTime fechaRegistro;
}
