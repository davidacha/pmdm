package bd;

public class BDUser {
    private String nombre;
    private String password;
    private BDAgenda bdAgenda;

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

    public BDAgenda getBdAgenda() {
        return bdAgenda;
    }

    public void setBdAgenda(BDAgenda bdAgenda) {
        this.bdAgenda = bdAgenda;
    }
}
