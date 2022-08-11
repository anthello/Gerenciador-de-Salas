import java.io.Console;  
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InterfaceDoUsuario {
    
    private static Console console = System.console();
    
    public static LocalDateTime askDateTimeFinal(){
        
        LocalDateTime dateTime;
        
        while(true){
           
            try {
                
                System.out.println(" Insira a Data Final e o Horario no seguinte formato dd-MM-aaaa HH:mm :");
                String DataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                dateTime = LocalDateTime.parse(DataTxt, formatter);
                break;
            } catch (Exception e) {               
                System.out.println("\n A Data Final nao esta no formato solicitado, por favor insira novamente.");
                continue;
            }
        }
        return dateTime;
    }   

    public static LocalDateTime askDateTimeInicial(){
        
        LocalDateTime dateTime;
        
        while(true){
           
            try {
                
                System.out.println(" Insira a Data Inicial e o Horario no seguinte formato dd-MM-aaaa HH:mm :");
                String DataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                dateTime = LocalDateTime.parse(DataTxt, formatter);
                break;
            } catch (Exception e) {               
                System.out.println("\n A Data Inicial nao esta no formato solicitado, por favor digite novamente.");
                continue;
            }
        }
        return dateTime;
    } 

    public static LocalDate askDateInicial(){
       
        LocalDate data;
        
        while(true){
            
            try {
               
                System.out.println("\n Insira a Data Inicial no seguinte formato dd-MM-aaaa :");
                String dataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataTxt, formatter);
                break;
            } catch (Exception e) {
                
                System.out.println("\n A Data Inicial nao esta no formato solicitado, por favor digite novamente.");
                continue;
            }
        }
        return data;
    }

    public static LocalDate askDateFinal(){
       
        LocalDate data;
        
        while(true){
            
            try {
               
                System.out.println("\n Insira a Data Final no seguinte formato dd-MM-aaaa :");
                String dataTxt = console.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataTxt, formatter);
                break;
            } catch (Exception e) {
                
                System.out.println("\n A Data Final nao esta no formato solicitado, digite novamente.");
                continue;
            }
        }
        return data;
    }

    public static Collection<String> askParticipantes(){
        System.out.println("-------------------------------------------------------------");
        
        List<String> participantes = new ArrayList<String>();
        String participante = " ";

        while(true){
            
            try {
               
                while(!participante.equals("")){
                    
                    participante = askName();

                    if(!participante.equals("")){
                        
                        participantes.add(participante);
                    }
                    else{
                        
                        break;
                    }
                }
                break;
            } catch (Exception e) {
                
                System.out.println("\n O nome digitado nao pode ser inserido, digite nomes que contenham apenas letras");
                continue;
            }
            
        }

        System.out.println("-------------------------------------------------------------\n");
        return participantes;
    }

    public static void askDisponibilidades(MarcadorDeReuniao marcadorDeReuniao){
        
        ArrayList<Participante> participantes = marcadorDeReuniao.getUltimaReuniao().getParticipantes();
        int numParticipantes = marcadorDeReuniao.getUltimaReuniao().getParticipantes().size();

        for(int i = 0; i < numParticipantes; i++){
            
            while(true){
               
                try {
                    
                    System.out.println("\nInsira a data e hora inicial e final disponivel do participante " + participantes.get(i).getNome() + ":");
                    LocalDateTime dataInicio = askDateTimeInicial();
                    LocalDateTime dataFim = askDateTimeFinal();
                    participantes.get(i).indicaDisponibilidadeParaReuniao(marcadorDeReuniao.getUltimaReuniao(), dataInicio, dataFim);
                    break;
                } catch (Exception e) {
                    
                    System.out.println("\n A data nao foi digitada da forma correta, digite novamente as datas.");
                    continue;
                }  
            }
        }
    }

    public static String askName(){
        
        String name;
        System.out.println("\n Insira o nome do participante: ");
        name = console.readLine();
        return name;
    }

    public static String askNomeSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        String nomeDaSala = "";        
        
        while(true){
            
            try{          
                
                System.out.println("Insira o nome da sala: ");
                nomeDaSala = console.readLine();
                break;
            } catch (Exception e) {
                
                System.out.println("\n Ja existe uma sala com o mesmo nome");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            } 
        }

        return nomeDaSala; 
        
    }

    public static String askLocal(){
       
        String local;
        System.out.println("\n Insira o local: ");
        local = console.readLine();
        return local;
    }
    
    public static int askCapacidade(){  
        
        int capacidadeMaxima;
        
        while(true){
            try{
                
                System.out.println("\n Insira a capacidade maxima da sala: ");
                capacidadeMaxima = Integer.parseInt(console.readLine());
                break;
            }catch (Exception e) {
               
                System.out.println("\n A capacidade maxima nao foi digitada da forma correta, digite novamente: ");
                continue;
            }  
        }
        return capacidadeMaxima;
    }

    public static String askDescricao(){
        String descricao;

        System.out.println("\n Adicione uma breve descricao da sala: ");
        descricao = console.readLine();
        
        return descricao;
    }    

    public static void mostraSalas(GerenciadorDeSalas gerenciadorDeSalas){

        System.out.println("As salas ja existentes sao:");

        List<Sala> salas = gerenciadorDeSalas.listaDeSalas();
        
        for(int i = 0; i < salas.size(); i++){

            System.out.println("\n Sala - " + salas.get(i).getNome());
            System.out.println("  Local: " + salas.get(i).getLocal());
            System.out.println("  Descricao: " + salas.get(i).getDescricao() + "\n");
        }
        if(salas.size() == 0){
            System.out.println("Ainda nao foi adicionada nenhuma sala");
        }
    }
    
    public static void criaSala(GerenciadorDeSalas gerenciadorDeSalas){
    
        int respConsole;
        String nome;
        String local;
        int capacidade;
        String descricao;

        while(true){
            try{
                System.out.println("\n Deseja adicionar uma nova sala? Digite 1 para SIM ou 2 para NAO");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2)
                    break;
                nome = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaExiste(nome);
                local = askLocal();
                capacidade = askCapacidade();
                descricao = askDescricao();
                gerenciadorDeSalas.adicionaSalaChamada(nome, capacidade, descricao, local);
                System.out.println("\n Sala adicionada com sucesso");
                break;
            }catch (Exception e) {
                System.out.println("\n Nao foi possivel adicionar a sala.");
                continue;
            }
        } 
    }

    public static void removeSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        String nomeDaSala;

        while(true){

            try{

                System.out.println("\n Insira o nome da sala para ser removida: ");
                nomeDaSala = console.readLine();
                gerenciadorDeSalas.verificaSeNaoExistemSalas();
                gerenciadorDeSalas.removeSalaChamada(nomeDaSala);
                break;
            }catch (Exception e) {
                
                System.out.println("\n A sala nao pode ser removida");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void criaReuniao(MarcadorDeReuniao marcadorDeReuniao){

        while(true){

            try{

                System.out.println("\n Insira a data inicial da reuniao:\n");
                LocalDate dataInicial = askDateInicial();
        
                System.out.println("\n Insira a data final da reuniao:\n");
                LocalDate dataFinal = askDateFinal();
        
                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);

                System.out.println("\n Insira os participantes da reuniao: " +
                "\n(Insira os nomes dos participantes um por um e tecle ENTER, quando terminar tecle ENTER duas vezes para continuar\n");
                Collection<String> participantes = askParticipantes();
        
                marcadorDeReuniao.marcarReuniaoEntre(dataInicial, dataFinal, participantes);
        
                System.out.println("\n Insira as datas e horas disponiveis para a reuniao:\n");
                askDisponibilidades(marcadorDeReuniao);

                Reuniao reuniao = marcadorDeReuniao.getUltimaReuniao();
                reuniao.mostraSobreposicao();

                System.out.println("\n Deseja marcar a reuniao? Digite 1 para SIM ou 2 para NAO e tente novamente");
                boolean desejaMarcar = Integer.parseInt(console.readLine()) == 1;
                if(desejaMarcar){

                    System.out.println("\n Escolha os horarios para a reuniao:");
                    System.out.println("\n Insira a data inicial da reuniao:\n");
                    LocalDateTime dataInicioDefinitiva = askDateTimeInicial();
                    System.out.println("\n Insira a data final da reuniao:\n");
                    LocalDateTime dataFimDefinitiva = askDateTimeFinal();
                    marcadorDeReuniao.agendaReuniao(dataInicioDefinitiva, dataFimDefinitiva);;
                    System.out.println("\n Reuniao marcada com sucesso!");
                }else{

                    System.out.println("\n Nao foi possivel marcar a reuniao");
                }
                break;
            }catch (Exception e) {
                
                System.out.println("\n A reuniao nao pode ser marcada");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
        
    }
    
    public static void criaReserva(GerenciadorDeSalas gerenciadorDeSalas){
        
        while(true){

            try{
                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n Ainda nao existe salas para serem reservadas. E preciso criar as salas primeiro");
                    break;
                }
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeSala);
                LocalDateTime dataInicial = askDateTimeInicial();
                LocalDateTime dataFinal = askDateTimeFinal();
                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);
                gerenciadorDeSalas.reservaSalaChamada(nomeSala, dataInicial, dataFinal);
                System.out.println("\n Reserva concluida");
                break;          
            }catch (Exception e) {
                
                System.out.println("\n Nao foi possivel reservar a sala");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void removerSala(GerenciadorDeSalas gerenciadorDeSalas){
        
        while(true){

            try{

                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n Ainda nao existe nenhuma sala que possa ser removida");
                    break;
                }
                System.out.println("\n Digite o nome da sala a ser removida: ");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeSala);
                gerenciadorDeSalas.removeSalaChamada(nomeSala);
                System.out.println("\n Sala removida com sucesso");
                break;
            }catch (Exception e) {
                
                System.out.println("\n Nao foi possivel remover a sala");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void imprimeReservasDeSala(GerenciadorDeSalas gerenciadorDeSalas){
               
        while(true){

            try{

                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n Ainda nao existe sala adicionada para ser impressa");
                    break;
                }
                System.out.println("\n Digite o nome da sala para ser impressa: ");
                String nomeSala = askNomeSala(gerenciadorDeSalas);
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeSala);
                gerenciadorDeSalas.imprimeReservasDaSala(nomeSala);
                break;
            }catch (Exception e) {
                
                System.out.println("\n Nao foi possivel imprimir a lista com reservas da sala");
                
                boolean resp;
                int respConsole;                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void imprimeReunioes(MarcadorDeReuniao marcadorDeReuniao){
        
        while(true){

            try{

                if(marcadorDeReuniao.listaDeReunioes().size() == 0){

                    System.out.println("\n Ainda nao existe reuniao para ser impressa");
                    break;
                }
                marcadorDeReuniao.imprimeReunioes();
                break;
            }catch (Exception e) {
                
                System.out.println("\n Nao foi possivel imprimir as reunioes");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void cancelarReserva(GerenciadorDeSalas gerenciadorDeSalas){

        String nomeDaSala;

        while(true){

            try{
                if(gerenciadorDeSalas.listaDeSalas().size() == 0){

                    System.out.println("\n Ainda nao existe nenhuma sala, portanto nao ha reserva ser removida: ");
                    break;
                }
                
                System.out.println("\n Digite o nome da sala na qual a reserva sera cancelada: ");
                nomeDaSala = console.readLine();
                gerenciadorDeSalas.verificaSeSalaNaoExiste(nomeDaSala);

                if(gerenciadorDeSalas.reservasParaSala(nomeDaSala).size() == 0){

                    System.out.println("\n Nao existem reservas para esta sala, portanto nao foi possivel cancelar a reserva ");
                    break;
                }
                LocalDateTime dataInicial = askDateTimeInicial();
                LocalDateTime dataFinal = askDateTimeFinal();

                verificaSeDataFinalMaiorQueDataInicial(dataInicial, dataFinal);
                verificaSeDataInicialEstaNoFuturo(dataInicial);
                
                Reserva cancelada = gerenciadorDeSalas.getReserva(nomeDaSala, dataInicial, dataFinal);

                gerenciadorDeSalas.cancelaReserva(cancelada);
                break;
            }catch (Exception e) {
                
                System.out.println("\n Nao foi possivel cancelar a reserva");
                
                boolean resp;
                int respConsole;                
                
                System.out.println("\n Deseja voltar para o menu? Digite 1 para SIM ou 2 para NAO e tente novamente");
                respConsole = Integer.parseInt(console.readLine());
                if(respConsole == 2) resp = true;
                else resp = false;
                
                if(resp) continue;
                else break;
            }  
        }
    }

    public static void verificaSeDataFinalMaiorQueDataInicial(LocalDate dataInicial, LocalDate dataFinal) throws Exception{
        if(dataInicial.isAfter(dataFinal)){

            //A data inicial da reuniao e depois da data final
            throw new Exception();
        }
    }

    public static void verificaSeDataFinalMaiorQueDataInicial(LocalDateTime dataInicial, LocalDateTime dataFinal) throws Exception{
        if(dataInicial.isAfter(dataFinal)){

            //A data inicial depois da data final 
            throw new Exception();
        }
    }

    public static void verificaSeDataInicialEstaNoFuturo(LocalDate dataInicial) throws Exception{
        if(LocalDate.now().isAfter(dataInicial)){

            //Data inicial da reuniao ja passou
            throw new Exception();
        }
    }

    public static void verificaSeDataInicialEstaNoFuturo(LocalDateTime dataInicial) throws Exception{
        if(LocalDateTime.now().isAfter(dataInicial)){

            //A reuniao sendo marcada para um dia que passou
            throw new Exception();
        }
    }

    public static int askOpcoes(){
        int resp;

        while(true){

            try{

                System.out.println("\n Escolha uma das opcoes:");
                System.out.println("\n(1)  Marcar Reuniao");
                System.out.println("(2)  Criar Sala");
                System.out.println("(3)  Reservar Sala");
                System.out.println("(4)  Cancelar Reserva");
                System.out.println("(5)  Remover Sala");
                System.out.println("(6)  Imprimir Reservas");
                System.out.println("(7)  Imprimir Reunioes");
                resp = (int)Integer.parseInt(console.readLine());

                if(resp > 7 || resp < 1){

                    throw new Exception("\n Digite somente numeros de 1 a 7. Por favor, selecione novamente: ");
                }
                break;
            }catch (Exception e) {

                System.out.println("\n Houve um erro na selecao de opcoes, insira a opcao novamente");
                continue;
            }
        }
        return resp;
       
    }

}
