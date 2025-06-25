import java.util.Scanner;
import modelo.*;
import almacen.*;

public class App {
    public static void main(String[] args) {
        // Obtiene la única instancia del Almacén (Singleton)
        Almacen almacen = Almacen.getInstancia();

        // Se registra un observador para mostrar notificaciones (Observer)
        almacen.agregarObservador(msg -> System.out.println("[NOTIFICACIÓN] " + msg));

        Scanner sc = new Scanner(System.in);

        String opcion;
        do {
            limpiarPantalla();
            System.out.println("===== CRUD Almacén con Prototype =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Ver producto");
            System.out.println("5. Ver todos los productos");
            System.out.println("6. Agregar producto externo (adaptado)");
            System.out.println("7. Clonar producto (Prototype)");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> {
                    // Crear nuevo producto (creacional sin patrón explícito)
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();
                    System.out.print("Precio: ");
                    int precio = Integer.parseInt(sc.nextLine());
                    Producto p = new Producto(nombre, categoria, precio);
                    almacen.agregarProducto(p);
                }
                case "2" -> {
                    System.out.print("Nombre a eliminar: ");
                    String nombre = sc.nextLine();
                    almacen.eliminarProducto(nombre);
                }
                case "3" -> {
                    System.out.print("Nombre a actualizar: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva categoría: ");
                    String categoria = sc.nextLine();
                    System.out.print("Nuevo precio: ");
                    int precio = Integer.parseInt(sc.nextLine());
                    almacen.actualizarProducto(nombre, nuevoNombre, categoria, precio);
                }
                case "4" -> {
                    System.out.print("Nombre a buscar: ");
                    String nombre = sc.nextLine();
                    Producto p = almacen.obtenerProducto(nombre);
                    if (p != null) {
                        p.mostrar();
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                }
                case "5" -> almacen.mostrarProductos();

                case "6" -> {
                    // Se crea un ProductoExterno incompatible,
                    // que se adapta con ProductoAdaptado para integrarse al almacén. (Adapter)
                    System.out.print("Código externo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Costo: ");
                    int costo = Integer.parseInt(sc.nextLine());
                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();
                    ProductoAdaptado adaptado = new ProductoAdaptado(new ProductoExterno(codigo, costo), categoria);
                    almacen.agregarProducto(adaptado);
                }

                case "7" -> {
                    // Clonar un producto existente para crear uno nuevo sin reconstruirlo manualmente (Prototype)
                    System.out.print("Nombre del producto a clonar: ");
                    String original = sc.nextLine();
                    Producto originalProducto = almacen.obtenerProducto(original);
                    if (originalProducto != null) {
                        Producto clon = originalProducto.clonar();
                        System.out.print("Nuevo nombre para el clon: ");
                        clon.setNombre(sc.nextLine());
                        almacen.agregarProducto(clon);
                        System.out.println("Producto clonado correctamente.");
                    } else {
                        System.out.println("Producto original no encontrado.");
                    }
                }

                case "0", "" -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

            if (!opcion.equals("0") && !opcion.equals("")) {
                System.out.println("\nPresiona ENTER para continuar...");
                sc.nextLine();
            }

        } while (!opcion.equals("0") && !opcion.equals(""));

        sc.close();
    }

    private static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
