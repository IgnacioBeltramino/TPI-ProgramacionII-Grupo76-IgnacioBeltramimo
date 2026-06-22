package clases;

import enums.Rol;
import java.util.ArrayList;
import java.util.List;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;
    private List<Pedido> pedidos = new ArrayList<Pedido>();


    public Usuario(String nombre, String apellido, String mail, String celular, String contrasenia, Rol rol) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }


    // getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getMail() {
        return mail;
    }

    public String getCelular() {
        return celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    // setters
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setApellido(String nuevoApellido) {
        this.apellido = nuevoApellido;
    }

    public void setMail(String nuevoMail) {
        this.mail = nuevoMail;
    }

    public void setCelular(String nuevoCelular) {
        this.celular = nuevoCelular;
    }

    public void setContrasenia(String nuevaContrasenia) {
        this.contrasenia = nuevaContrasenia;
    }

    public void setRol(Rol nuevoRol) {
        this.rol = nuevoRol;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    // metodos de la clase 
    @Override
    public String toString() {
        return "Usuario [id=" + getId() + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", rol=" + rol + "]";
    }

    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

}