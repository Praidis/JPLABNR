package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class testTienda {
    public static void testTienda() {
        Tienda tienda = new Tienda("Mi Tienda", 100, 1000.0);

        // Crear productos
        Producto p1 = new Bebida("AC001", "Coca-Cola", 10, 2.5, 20, 0.5, false);
        Producto p2 = new Envasado("AB001", "Arroz", 20, 1.5, 10, "Bolsa", true);
        Producto p3 = new Limpieza("AZ001", "Detergente", 15, 3.0, 15, "ROPA");
        Producto p4;

        try {
            p4 = new Bebida("AX001", "Agua Mineral", 10, 1.0, 10, 0.0, false);
            tienda.agregarProducto(p4);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
        }

        // Agregar productos a la tienda
        tienda.agregarProducto(p1);
        tienda.agregarProducto(p2);
        tienda.agregarProducto(p3);

        // Imprimir el stock inicial
        tienda.imprimirStock();
        tienda.imprimirSaldoCaja();

        // Realizar una venta
        tienda.venderProducto(p1, 5);
        tienda.venderProducto(p2, 10);

        // Imprimir el stock y saldo después de la venta
        tienda.imprimirStock();
        tienda.imprimirSaldoCaja();

        // Aplicar un descuento y obtener lista de comestibles
        double porcentajeDescuento = 10.0;
        List<String> productosConDescuento = tienda.obtenerComestiblesConMenorDescuento(10.0).stream()
                .map(p -> p.getDescripcion().toUpperCase())
                .collect(Collectors.toList());

        System.out.println("Productos comestibles con descuento menor al " + porcentajeDescuento + "%:");
        for (String producto : productosConDescuento) System.out.println(producto);
    }
}

