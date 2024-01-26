package bd;

public class BDUser {
    private String nombre;
    private String password;
    private BDHorario horario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BDHorario getHorario() {
        return horario;
    }

    public void setHorario(BDHorario horario) {
        this.horario = horario;
    }
}
