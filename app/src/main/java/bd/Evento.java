package bd;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;

    public Evento(LocalDate fecha, LocalTime hora, String descripcion) {
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    // Métodos de acceso (getters y setters)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString para imprimir la información del evento
    @Override
    public String toString() {
        return "Evento{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
