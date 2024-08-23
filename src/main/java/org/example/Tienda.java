package org.example;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;

public class Tienda {
    private String nombre;
    private int maxStock;
    private double saldoCaja;
    private List<Producto> productos;
    private int ultimoIdBebida;
    private int ultimoIdEnvasado;
    private int ultimoIdLimpieza;

    public Tienda(String nombre, int maxStock, double saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.productos = new ArrayList<>();
        this.ultimoIdBebida = 0;
        this.ultimoIdEnvasado = 0;
        this.ultimoIdLimpieza = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public String generarIdBebida() {
        return "AC" + String.format("%03d", ultimoIdBebida + 1);
    }

    public String generarIdEnvasado() {
        return "AB" + String.format("%03d", ultimoIdEnvasado + 1);
    }

    public String generarIdLimpieza() {
        return "AZ" + String.format("%03d", ultimoIdLimpieza + 1);
    }

    public void agregarProducto(Producto producto) {
        int totalUnidades = productos.stream().mapToInt(Producto::getCantidad).sum();
        if (totalUnidades + producto.getCantidad() <= maxStock) {
            if (saldoCaja >= producto.getPrecio() * producto.getCantidad()) {
                if (producto instanceof Bebida) {
                    ((Bebida) producto).setId(generarIdBebida());
                    ultimoIdBebida++;
                } else if (producto instanceof Envasado) {
                    ((Envasado) producto).setId(generarIdEnvasado());
                    ultimoIdEnvasado++;
                } else if (producto instanceof Limpieza) {
                    ((Limpieza) producto).setId(generarIdLimpieza());
                    ultimoIdLimpieza++;
                }
                productos.add(producto);
                saldoCaja -= producto.getPrecio() * producto.getCantidad();
                System.out.println("Producto agregado con éxito");
            } else {
                double saldoDisponible = saldoCaja / producto.getPrecio();
                int cantidadMaxima = (int) Math.floor(saldoDisponible);
                System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
                System.out.println("La tienda solo puede comprar " + cantidadMaxima + " unidades con el saldo actual.");
            }
        } else {
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
        }
    }

    public void venderProducto(Producto producto, int cantidad) {
        if (!producto.isDisponible()) {
            System.out.println("El producto " + producto.getDescripcion() + " no se encuentra disponible");
            return;
        }

        if (producto instanceof Bebida) {
            if (cantidad > 12) {
                System.out.println("No se pueden vender más de 12 bebidas en una sola compra");
                return;
            }
        } else if (producto instanceof Envasado) {
            if (cantidad > 12) {
                System.out.println("No se pueden vender más de 12 envasados en una sola compra");
                return;
            }
        } else if (producto instanceof Limpieza) {
            if (cantidad > 12) {
                System.out.println("No se pueden vender más de 12 limpiezas en una sola compra");
                return;
            }
        }

        int unidadesDisponibles = producto.getCantidad();
        int unidadesAVender = Math.min(cantidad, unidadesDisponibles);

        double precioTotal = unidadesAVender * producto.calcularPrecioVenta();
        saldoCaja += precioTotal;
        producto.setCantidad(unidadesDisponibles - unidadesAVender);
        System.out.println("Venta realizada con éxito. Se vendieron " + unidadesAVender + " unidades de " + producto.getDescripcion());

        if (producto.getCantidad() == 0) {
            producto.setDisponible(false);
            System.out.println("El producto " + producto.getDescripcion() + " se ha agotado y no está disponible para la venta.");
        }
    }

    public void imprimirStock() {
        System.out.println("Stock de la tienda:");
        for (Producto producto : productos) {
            System.out.println(producto.getDescripcion() + ": " + producto.getCantidad() + " unidades");

        }
    }
    public void imprimirDetalleVenta(Producto producto, int cantidad) {
        double precioVenta = producto.calcularPrecioVenta();

        System.out.println("Detalle de venta:");
        System.out.println("Identificador: " + producto.getId());
        System.out.println("Descripción: " + producto.getDescripcion());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio de venta por unidad: $" + precioVenta);
        System.out.println("Total: $" + (precioVenta * cantidad));
    }

    public void imprimirSaldoCaja() {
        System.out.println("Saldo de la caja: $" + saldoCaja);
    }

    public List<Producto> obtenerComestiblesConMenorDescuento(double porcentajeDescuento) {
        return productos.stream()
                .filter(p -> p instanceof Bebida || p instanceof Envasado)
                .filter(p -> !p.esImportado())
                .filter(p -> p.aplicarDescuento(porcentajeDescuento) < p.calcularPrecioVenta())
                .sorted((p1, p2) -> Double.compare(p1.aplicarDescuento(porcentajeDescuento), p2.aplicarDescuento(porcentajeDescuento)))
                .collect(Collectors.toList());
    }

}
