package banco;

public class PessoaFisica extends Modelo_Conta{

    private String CPF;

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public PessoaFisica(String CPF,String senha, String nome) {
        super(senha, nome);
        this.CPF = CPF;
    }
}
