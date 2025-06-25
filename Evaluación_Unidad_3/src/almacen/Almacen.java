package almacen;

import modelo.Producto;
import java.util.*;

public class Almacen {
    // Singleton
    // Esta clase garantiza que solo exista una única instancia
    // de Almacen para centralizar el acceso y la gestión del inventario.
    private static Almacen instancia;

    // Mapa que almacena los productos con su nombre como clave
    private Map<String, Producto> productos = new HashMap<>();

    // Observer
    // Lista de observadores que serán notificados cuando ocurra algún cambio
    private List<Observador> observadores = new ArrayList<>();

    // Constructor privado para evitar creación externa (Singleton)
    private Almacen() {}

    // Método estático para obtener la única instancia de Almacen
    public static Almacen getInstancia() {
        if (instancia == null)
            instancia = new Almacen();
        return instancia;
    }

    // Método para agregar un producto al almacén
    public void agregarProducto(Producto p) {
        productos.put(p.getNombre(), p);
        // Notifica a todos los observadores que se agregó un producto
        notificar("Producto agregado: " + p.getNombre());
    }

    // Método para eliminar un producto por nombre
    public void eliminarProducto(String nombre) {
        productos.remove(nombre);
        // Notifica a los observadores que se eliminó un producto
        notificar("Producto eliminado: " + nombre);
    }

    // Método para actualizar un producto existente
    public void actualizarProducto(String nombreAntiguo, String nuevoNombre, String nuevaCategoria, int nuevoPrecio) {
        Producto p = productos.get(nombreAntiguo);
        if (p != null) {
            productos.remove(nombreAntiguo);
            Producto actualizado = new Producto(nuevoNombre, nuevaCategoria, nuevoPrecio);
            productos.put(nuevoNombre, actualizado);
            // Notifica que se actualizó un producto
            notificar("Producto actualizado: " + nuevoNombre);
        } else {
            System.out.println("Producto no encontrado para actualizar.");
        }
    }

    // Método para obtener un producto por nombre
    public Producto obtenerProducto(String nombre) {
        return productos.get(nombre);
    }

    // Método para mostrar todos los productos en consola
    public void mostrarProductos() {
        System.out.println("\n=== Productos en el Almacén ===");
        for (Producto p : productos.values()) {
            p.mostrar();
        }
    }

    // Método para agregar un observador que será notificado de cambios
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    // Método privado para notificar a todos los observadores con un mensaje
    private void notificar(String mensaje) {
        for (Observador o : observadores) {
            o.actualizar(mensaje);
        }
    }
}