package org.example;

public abstract class Producto {
    private String id;
    private String descripcion;
    private int cantidad;
    private double precio;
    private double porcentajeGanancia;
    private boolean disponible;

    public Producto(String id, String descripcion, int cantidad, double precio, double porcentajeGanancia) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.porcentajeGanancia = porcentajeGanancia;
        this.disponible = true;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método para calcular el precio de venta
    public double calcularPrecioVenta() {
        return precio + (precio * porcentajeGanancia / 100);
    }

    // Método para aplicar un descuento
    public double aplicarDescuento(double porcentajeDescuento) {
        return calcularPrecioVenta() - (calcularPrecioVenta() * porcentajeDescuento / 100);
    }

    // Método para determinar si es importado (se puede sobreescribir)
    public boolean esImportado() {
        return false;
    }
}