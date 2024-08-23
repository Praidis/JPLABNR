package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea correr la clase de test o interactuar con la tienda?");
        System.out.println("1. Correr clase de test");
        System.out.println("2. Interactuar con la tienda");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumimos la línea completa y vaciamos el buffer de entrada

        if (opcion == 1) {
            // Correr clase de test
            testTienda.testTienda();
        } else if (opcion == 2) {
            // Interactuar con la tienda
            interactuarConTienda(scanner);
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void interactuarConTienda(Scanner scanner) {
        Tienda tienda = new Tienda("El Yevi", 100, 10000.0);

        // Generar productos automáticamente
        Bebida bebida = new Bebida("AC001", "Cerveza", 10, 50.0, 20.0, 5.0, true);
        Envasado envase = new Envasado("AB001", "Agua", 20, 30.0, 20.0, "500g", false);
        Limpieza limpieza = new Limpieza("AZ001", "Detergente", 15, 35.0, 25.0, "Hogar");

        tienda.agregarProducto(bebida);
        tienda.agregarProducto(envase);
        tienda.agregarProducto(limpieza);

        while (true) {
            System.out.println("Tienda: " + tienda.getNombre());
            System.out.println("Número máximo de productos en stock: " + tienda.getMaxStock());
            System.out.println("Saldo en caja: " + tienda.getSaldoCaja());
            System.out.println("Productos en stock:");
            for (Producto producto : tienda.getProductos()) {
                double precioVenta = producto.calcularPrecioVenta();
                double descuento = 0;
                double impuesto = 0;

                if (producto instanceof Bebida) {
                    descuento = precioVenta * 0.10; // 10% de descuento para bebidas
                } else if (producto instanceof Envasado) {
                    descuento = precioVenta * 0.15; // 15% de descuento para envasados
                } else if (producto instanceof Limpieza) {
                    descuento = precioVenta * 0.20; // 20% de descuento para productos de limpieza
                }

                if (producto.esImportado()) {
                    impuesto = (precioVenta - descuento)* 0.12; // 12% de impuesto para productos importados
                }

                double precioFinal = precioVenta - descuento + impuesto;
                System.out.println((tienda.getProductos().indexOf(producto) + 1) + ". " + producto.getDescripcion() + " - Precio de venta: $" + String.format("%.2f", precioVenta) + " (Descuento: -$" + String.format("%.2f", descuento) + ", Impuesto: +" + String.format("%.2f", impuesto) + ") = $" + String.format("%.2f", precioFinal));
            }

            System.out.println("¿Desea comprar o vender productos?");
            System.out.println("1. Comprar");
            System.out.println("2. Vender");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumimos la línea completa y vaciamos el buffer de entrada

            if (opcion == 1) {
                comprarProductos(tienda, scanner);
            } else if (opcion == 2) {
                venderProductos(tienda, scanner);
            } else if (opcion == 3) {
                System.out.println("Gracias por interactuar con la tienda");
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
    }

    private static void comprarProductos(Tienda tienda, Scanner scanner) {
        System.out.println("¿Qué producto desea comprar?");
        for (int i = 0; i < tienda.getProductos().size(); i++) {
            Producto producto = tienda.getProductos().get(i);
            double precioVenta = producto.calcularPrecioVenta();
            double descuento=0;
            double impuesto =0;

            if (producto instanceof Bebida) {
                descuento = precioVenta * 0.10; // 10% de descuento para bebidas
                Bebida bebida = (Bebida) producto;
                if (producto.esImportado()){
                    impuesto = (precioVenta - descuento) * 0.12; // 12% de impuesto para productos importados
                }
                System.out.println((i + 1) + ". " + producto.getDescripcion() + " - Precio de venta: $" + String.format("%.2f", precioVenta) + " (Descuento: -$" + String.format("%.2f", descuento) + ", Impuesto: +" + String.format("%.2f", impuesto) + ") = $" + String.format("%.2f", precioVenta - descuento + impuesto) + " - Calorías: " + bebida.calcularCalorias());
            } else if (producto instanceof Envasado) {
                descuento = precioVenta * 0.15; // 15% de descuento para envasados
                if (producto.esImportado()){
                    impuesto = (precioVenta - descuento) * 0.12; // 12% de impuesto para productos importados
                }
                System.out.println((i + 1) + ". " + producto.getDescripcion() + " - Precio de venta: $" + String.format("%.2f", precioVenta) + " (Descuento: -$" + String.format("%.2f", descuento) + ", Impuesto: +" + String.format("%.2f", impuesto) + ") = $" + String.format("%.2f", precioVenta - descuento + impuesto));
            } else if (producto instanceof Limpieza) {
                descuento = precioVenta * 0.20; // 20% de descuento para productos de limpieza
                if (producto.esImportado()){
                    impuesto = (precioVenta - descuento) * 0.12; // 12% de impuesto para productos importados
                }
                System.out.println((i + 1) + ". " + producto.getDescripcion() + " - Precio de venta: $" + String.format("%.2f", precioVenta) + " (Descuento: -$" + String.format("%.2f", descuento) + ", Impuesto: +" + String.format("%.2f", impuesto) + ") = $" + String.format("%.2f", precioVenta - descuento + impuesto));
            }
        }

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumimos la línea completa y vaciamos el buffer de entrada

        if (opcion > 0 && opcion <= tienda.getProductos().size()) {
            Producto producto = tienda.getProductos().get(opcion - 1);
            System.out.println("¿Cuántas unidades desea comprar?");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumimos la línea completa y vaciamos el buffer de entrada

            tienda.venderProducto(producto, cantidad);
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void venderProductos(Tienda tienda, Scanner scanner) {
        System.out.println("¿Qué tipo de producto desea vender?");
        System.out.println("1. Bebida");
        System.out.println("2. Envasado");
        System.out.println("3. Limpieza");

        int opcion = scanner.nextInt();

        if (opcion == 1) {
            venderBebida(tienda, scanner);
        } else if (opcion == 2) {
            venderEnvasado(tienda, scanner);
        } else if (opcion == 3) {
            venderLimpieza(tienda, scanner);
        } else {
            System.out.println("Opción inválida");
        }
    }

    private static void venderBebida(Tienda tienda, Scanner scanner) {
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.next();
        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el Porcentaje de Ganancia (Max: 20%)");
        double porcentajeGanancia = scanner.nextInt();
        System.out.println("Ingrese la graduación alcohólica:");
        double graduacionAlcoholica = scanner.nextDouble();
        System.out.println("¿Es un producto importado? (true/false)");
        boolean importado = scanner.nextBoolean();

        // Calcula el costo total del producto
        double costoTotal = precio * cantidad;

        // Verifica si el saldo en caja es suficiente para cubrir el costo del producto
        if (tienda.getSaldoCaja() >= costoTotal) {
            String codigo = tienda.generarIdBebida(); // Llama al método específico para generar el ID de la bebida
            Bebida bebida = new Bebida(codigo, descripcion, cantidad, precio,porcentajeGanancia, graduacionAlcoholica, importado);
            tienda.agregarProducto(bebida);
            System.out.println("Se vendió " + cantidad + " unidades de " + descripcion + " con código " + codigo + " a la tienda.");
        } else {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            double saldoDisponible = tienda.getSaldoCaja() / precio;
            int cantidadMaxima = (int) Math.floor(saldoDisponible);
            System.out.println("La tienda solo puede comprar " + cantidadMaxima + " unidades con el saldo actual.");
        }
    }

    private static void venderEnvasado(Tienda tienda, Scanner scanner) {
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.next();
        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el peso del producto (en gramos):");
        String peso = scanner.next();
        System.out.println("Ingrese el porcentaje de ganancia:");
        double porcentajeGanancia = scanner.nextDouble();
        System.out.println("¿Es un producto importado? (true/false)");
        boolean importado = scanner.nextBoolean();

        // Calcula el costo total del producto
        double costoTotal = precio * cantidad;

        // Verifica si el saldo en caja es suficiente para cubrir el costo del producto
        if (tienda.getSaldoCaja() >= costoTotal) {
            String codigo = tienda.generarIdEnvasado(); // Generar un código único para el producto
            Envasado envasado = new Envasado(codigo, descripcion, cantidad, precio, porcentajeGanancia, peso, importado);
            tienda.agregarProducto(envasado);
            System.out.println("Se vendió " + cantidad + " unidades de " + descripcion + " con código " + codigo + " a la tienda.");
        } else {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            double saldoDisponible = tienda.getSaldoCaja() / precio;
            int cantidadMaxima = (int) Math.floor(saldoDisponible);
            System.out.println("La tienda solo puede comprar " + cantidadMaxima + " unidades con el saldo actual.");
        }
    }

    private static void venderLimpieza(Tienda tienda, Scanner scanner) {
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.next();
        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el tipo de limpieza (1: Hogar, 2: Industrial):");
        String tipoLimpieza = scanner.next();
        System.out.println("Ingrese el porcentaje de ganancia:");
        double porcentajeGanancia = scanner.nextDouble();
        System.out.println("¿Es un producto importado? (true/false)");
        boolean importado = scanner.nextBoolean();

        // Calcula el costo total del producto
        double costoTotal = precio * cantidad;

        // Verifica si el saldo en caja es suficiente para cubrir el costo del producto
        if (tienda.getSaldoCaja() >= costoTotal) {
            String codigo = tienda.generarIdLimpieza(); // Generar un código único para el producto
            Limpieza limpieza = new Limpieza(codigo, descripcion, cantidad, precio, porcentajeGanancia, tipoLimpieza);
            tienda.agregarProducto(limpieza);
            System.out.println("Se vendió " + cantidad + " unidades de " + descripcion + " con código " + codigo + " a la tienda.");
        } else {
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
            double saldoDisponible = tienda.getSaldoCaja() / precio;
            int cantidadMaxima = (int) Math.floor(saldoDisponible);
            System.out.println("La tienda solo puede comprar " + cantidadMaxima + " unidades con el saldo actual.");
        }
    }
}

