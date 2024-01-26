package bd;

import java.util.ArrayList;

public class BDHorario {
    private ArrayList<Integer> date;
    private ArrayList<String> evento;

    public void añadirEvento(int año,int mes, int dia, String hora, String evento){
        
        this.evento.add(evento);
    }

}
