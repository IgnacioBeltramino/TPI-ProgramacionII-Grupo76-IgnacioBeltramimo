package clases;

import java.time.LocalDateTime;

public abstract class Base {

    private Long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
    private static long contadorId = 1;

    // construcor 
    public Base () {
        this.id = generarId();
        this.eliminado = false;
        createdAt = LocalDateTime.now();
    }

    // logica id
    public static Long generarId() {
        Long nuevoId = contadorId;
        contadorId += 1;
        return nuevoId;
    }

    // metodos 
    public void cambiarEstado() {
        if (eliminado == false) {
            this.eliminado = true;
        } else if (eliminado == true) {
            this.eliminado = false;
        }
    }

    // gettes 
    public Long getId(){
        return id;
    }

    
    public boolean isEliminado() {
        return eliminado;
    }

    public LocalDateTime getFechaCreacion() {
        return createdAt;
    }

    // setters 
    public void setId(Long nuevoId) {
        this.id = nuevoId;
    }

    public void setEliminado(Boolean estado) {
        this.eliminado = estado;
    }

    public void setFechaCreacion(LocalDateTime nuevaFechaCreacion) {
        this.createdAt = nuevaFechaCreacion;
    } 

    public abstract String toString(); // esto es para que todas las clases tengan obligatoriamente su toString 



    
}
