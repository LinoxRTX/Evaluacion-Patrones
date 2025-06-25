package almacen;

// Observer: define el contrato para los observadores que desean recibir
// notificaciones de cambios en el almacén.
// Cualquier clase que implemente Observador debe definir el método actualizar,
// que será llamado cuando el almacén notifique un evento.
public interface Observador {
    void actualizar(String mensaje);
}
