package banco;

public class PessoaJuridica extends Modelo_Conta{

    private String CNPJ;

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public PessoaJuridica(String CNPJ,String senha, String nome) {
        super(senha, nome);
        this.CNPJ = CNPJ;
    }
}
