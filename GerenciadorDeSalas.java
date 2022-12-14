import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GerenciadorDeSalas{

    private List<Sala> salas = new ArrayList<Sala>();

    public void verificaSeSalaExiste(String nome) throws Exception{

        for (Sala sala : salas) {

            if(sala.getNome().equals(nome)){
                //Sala ja existe
                throw new Exception();
            }
        }
    }

    public void verificaSeSalaNaoExiste(String nome) throws Exception{

        for (Sala sala : salas) {

            if(sala.getNome().equals(nome)){
                //Sala ja existe
                return;
            }
        }
        throw new Exception();
    }

    public void verificaSeNaoExistemSalas() throws Exception{

        if(salas.size() == 0){

            //Nao existem salas
            throw new Exception();
        }
    }

    public Reserva getReserva(String nomeSala, LocalDateTime dataInicial, LocalDateTime dataFinal){

        Reserva reserva = null;

        for (int i = 0; i < salas.size(); i++) {
            
            if (salas.get(i).getNome().equals(nomeSala)) {
                Sala sala = salas.get(i);
                ArrayList<Reserva> reservas = sala.getReservas();

                for (int j = 0; j < reservas.size(); j++){

                    if(reservas.get(j).inicio().equals(dataInicial) && reservas.get(j).fim().equals(dataFinal)){

                        reserva = reservas.get(j);
                    }

                }   
            }
        }  
        return reserva;
    }
    
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao, String local) {

        Sala novaSala = new Sala(nome, capacidadeMaxima, descricao, local);
        adicionaSala(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) {
            
        for(int i = 0; i < salas.size(); i++){
            
            if(salas.get(i).getNome().equals(nomeDaSala)){
                
                salas.remove(i);
                return;
            }  
        }
    }

    public List<Sala> listaDeSalas(){
       
        return salas;
    }

    public void adicionaSala(Sala novaSala){
        
        salas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{

        Reserva reserva = null;
        for (int i = 0; i < salas.size(); i++) {
            
            if (salas.get(i).getNome().equals(nomeDaSala)) {
                
                ArrayList<Reserva> reservas = (ArrayList<Reserva>)salas.get(i).getReservas();
                
                for (int j = 0; j < reservas.size(); j++) {

                    Reserva currentReserva = reservas.get(i);

                    if ((dataInicial.isBefore(currentReserva.fim()) && dataInicial.isAfter(currentReserva.inicio()))
                            || (dataFinal.isBefore(currentReserva.fim()) && dataFinal.isAfter(currentReserva.inicio()))
                            || (dataInicial.isBefore(currentReserva.inicio()) && dataFinal.isAfter(currentReserva.fim()))) {
                        //Ja existe uma reserva 
                        throw new Exception();
                    }
                }
                reserva = new Reserva(salas.get(i), dataInicial, dataFinal, reservas.size());
                salas.get(i).addReserva(reserva);
                return reserva;
            }
        }
        //Sala nao foi encontrada
        throw new Exception();
    }

    public void cancelaReserva(Reserva cancelada){

        Sala sala = cancelada.sala();
        ArrayList<Reserva> reservas = sala.getReservas();

        for (int i = 0; i < reservas.size(); i++){

            if(reservas.get(i).getId() == cancelada.getId()){

                reservas.remove(i);
                System.out.println("Reserva cancelada com sucesso ");
                return;
            }

        }        
    }

    public Collection<Reserva> reservasParaSala(String nomeSala){

        Collection<Reserva> reservas = null;

        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getNome().equals(nomeSala)){
                
                reservas = salas.get(i).getReservas();
                return reservas;
            }
        }
        return reservas;
    }
    
    public void imprimeReservasDaSala(String nomeSala){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (int i = 0; i < salas.size(); i++) {

            if (salas.get(i).getNome().equals(nomeSala)){

                ArrayList<Reserva> reservasDaSala = (ArrayList<Reserva>)reservasParaSala(nomeSala);
                System.out.println("\n Lista de reservas para a sala " + nomeSala + ":");
                
                if(reservasDaSala.size() == 0){
                    System.out.println("\n Ainda nao ha reservas para esta sala");
                    return;
                }

                for(int j = 0; j < reservasDaSala.size(); j++){

                    String strDataInicial = reservasDaSala.get(i).inicio().format(formatter);
                    String strDataFinal = reservasDaSala.get(i).fim().format(formatter);
                    System.out.println("\nReserva " + (j+1) + "- Inicio: " + strDataInicial + " / Fim: " + strDataFinal);
                }
            }
        }
    }
}