import static banco.Banco.Atendimento_Cliente;

public class Main {

    static int atendimentos = 0;
    static String atendimento = "on";

    public static void main(String[] args) {
        do {
            Atendimento_Cliente();
            atendimentos++;
            if (atendimentos == 10){
                atendimento = "off";
            }
        }while(atendimento.equals("on"));
    }
}