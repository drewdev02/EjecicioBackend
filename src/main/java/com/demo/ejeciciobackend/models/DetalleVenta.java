package com.demo.ejeciciobackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalleVentas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idVenta;
    private Long idProducto;

    @OneToMany(mappedBy = "detalleVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", insertable = false, updatable = false)
    private Producto producto;
}


