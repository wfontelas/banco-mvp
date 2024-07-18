package banco;

import java.util.ArrayList;

public class Modelo_Conta {
    private String senha;
    private String nome;
    private double saldo = 0;
    private ArrayList<Transacoes> listaTransacoes = new ArrayList<>();

    public Modelo_Conta(String senha, String nome) {
        this.senha = senha;
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Transacoes> getListaTransacoes() {
        return listaTransacoes;
    }

    public void setListaTransacoes(Transacoes transacao) {
        this.listaTransacoes.add(transacao);
    }

}
