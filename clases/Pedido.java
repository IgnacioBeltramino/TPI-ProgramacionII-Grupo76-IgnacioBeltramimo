package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import interfaces.Calculable;
import enums.Estado;
import enums.FormaPago;

public class Pedido extends Base implements Calculable{
    
    private LocalDate fecha;
    private Estado estado;
    private double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private ArrayList<DetallePedido> detalles = new ArrayList<>();  // creo una lista que contenga todos los detalle pedido generados 

    public Pedido (LocalDate fecha, Estado estado, FormaPago formaPago, Usuario usuario) {
        super();
        this.fecha = fecha;
        this.estado = estado;
        this.total = 0.0;
        this.formaPago = formaPago;
        this.usuario = usuario;
    }

    // metodo de la inferfaz
    public void calcularTotal () {
        this.total = 0;
        for (DetallePedido p : detalles) {
            this.total += p.getSubtotal();
        }
    }


    // metodos pedido
    public void addDetallePedido (int cantidad, Producto producto){

        DetallePedido nuevoDetalle = new DetallePedido(cantidad, producto);
        this.detalles.add(nuevoDetalle);
        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto (Producto producto) {

        for (DetallePedido p : detalles) {
            if (p.getProducto().equals(producto)) {
                return p;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {

        DetallePedido eliminar = findDetallePedidoByProducto(producto); // creo un detalle pedido, lo asocio con el otro metodo a un detalle producto existente y luego lo elimino
        if (eliminar != null) {
            detalles.remove(eliminar);
            calcularTotal();
        }
    }




    // getters
    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    // setters
    public void setFecha(LocalDate nuevaFecha) {
        this.fecha = nuevaFecha;
    }

    public void setEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setTotal(double nuevoTotal) {
        this.total = nuevoTotal;
    }

    public void setFormaPago(FormaPago nuevaFormaPago) {
        this.formaPago = nuevaFormaPago;
    }

    public void setUsuario(Usuario nuevoUsuario) {
        this.usuario = nuevoUsuario;
    }

    public void setDetalles(ArrayList<DetallePedido> nuevosDetalles) {
        this.detalles = nuevosDetalles;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + getId() + ", fecha=" + fecha + ", estado=" + estado + ", total=" + total + ", formaPago=" + formaPago + ", usuario=" + usuario.getNombre() + "]";
    }

}
