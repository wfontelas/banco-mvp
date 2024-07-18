package banco;

import javax.swing.*;
import java.util.ArrayList;

public class Banco {
    static ArrayList<PessoaFisica> contasFisicas = new ArrayList<>();
    static ArrayList<PessoaJuridica> contasJuridicas = new ArrayList<>();
    static int indice_CLIENTE;
    static double valor_saque;
    static double valor_depositado;
    static int indice_pagamento;
    static double valor_troca;
    static int Continuar = 0;

    public static void Atendimento_Cliente(){
        Object [] pessoas = {"Cliente físico", "Cliente Jurídico"};
        Object selectedPerson = JOptionPane.showInputDialog(null, "Quem é você?", "pessoa",
                JOptionPane.INFORMATION_MESSAGE, null, pessoas, pessoas[0]);
        if (selectedPerson == pessoas[0]){
            login_ContaFisica();
            do {
                Operacoes_CF();
                Continuar = JOptionPane.showConfirmDialog(null, "Deseja realizar mais alguma operação?",
                        "Selecione uma opção: ", JOptionPane.YES_NO_OPTION);
            } while (Continuar == 0);
            JOptionPane.showMessageDialog(null, "Obrigado, tenha um bom dia " +
                    contasFisicas.get(indice_CLIENTE).getNome());
        } else {
            login_ContaJuridica();
            do{
                Operacoes_CJ();
                Continuar = JOptionPane.showConfirmDialog(null, "Deseja realizar mais alguma operação?",
                        "Selecione uma opção: ", JOptionPane.YES_NO_OPTION);
            } while (Continuar == 0);
            JOptionPane.showMessageDialog(null, "Obrigado, tenha um bom dia " +
                    contasJuridicas.get(indice_CLIENTE).getNome());
        }
    }

    public static void Operacoes_CJ(){
        Object[] operations = {"Saque", "Depósito", "Transferência", "Extrato"};
        Object selectedOperation = JOptionPane.showInputDialog(null, "Que operação deseja realizar?",
                "operação", JOptionPane.INFORMATION_MESSAGE, null, operations,operations[0]);
        double novo_saldo;
        Transacoes transacao;
        if (selectedOperation == operations[0]){
            novo_saldo = Saque(contasJuridicas.get(indice_CLIENTE).getSaldo());
            contasFisicas.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes("Saque", valor_saque, contasJuridicas.get(indice_CLIENTE).getNome(),
                    contasJuridicas.get(indice_CLIENTE).getNome(),contasJuridicas.get(indice_CLIENTE).getSaldo());
            contasJuridicas.get(indice_CLIENTE).setListaTransacoes(transacao);
        }else if (selectedOperation == operations[1]){
            novo_saldo = Deposito(contasJuridicas.get(indice_CLIENTE).getSaldo());
            contasJuridicas.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes("Deposito", valor_depositado, contasFisicas.get(indice_CLIENTE).getNome(),
                    contasJuridicas.get(indice_CLIENTE).getNome(), contasJuridicas.get(indice_CLIENTE).getSaldo());
            contasJuridicas.get(indice_CLIENTE).setListaTransacoes(transacao);
        } else if (selectedOperation == operations[2]) {
            Transferencia_ContaJuridica();
        } else {
            Extrato_Transacoes(contasJuridicas.get(indice_CLIENTE).getListaTransacoes());
        }
    }

    public static void Operacoes_CF(){
        Object[] operations = {"Saque", "Depósito", "Transferência", "Extrato"};
        Object selectedOperation = JOptionPane.showInputDialog(null, "Que operação deseja realizar?",
                "operação", JOptionPane.INFORMATION_MESSAGE, null, operations,operations[0]);
        double novo_saldo;
        Transacoes transacao;
        if (selectedOperation == operations[0]){
            novo_saldo = Saque(contasFisicas.get(indice_CLIENTE).getSaldo());
            contasFisicas.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes("Saque", valor_saque, contasFisicas.get(indice_CLIENTE).getNome(),
                    contasFisicas.get(indice_CLIENTE).getNome(),contasFisicas.get(indice_CLIENTE).getSaldo());
            contasFisicas.get(indice_CLIENTE).setListaTransacoes(transacao);
        }else if (selectedOperation == operations[1]){
            novo_saldo = Deposito(contasFisicas.get(indice_CLIENTE).getSaldo());
            contasFisicas.get(indice_CLIENTE).setSaldo(novo_saldo);
            transacao = new Transacoes("Deposito", valor_depositado, contasFisicas.get(indice_CLIENTE).getNome(),
                    contasFisicas.get(indice_CLIENTE).getNome(), contasFisicas.get(indice_CLIENTE).getSaldo());
            contasFisicas.get(indice_CLIENTE).setListaTransacoes(transacao);
        } else if (selectedOperation == operations[2]) {
            Transferencia_ContaFisica();
        } else {
            Extrato_Transacoes(contasFisicas.get(indice_CLIENTE).getListaTransacoes());
        }
    }

    public static void Extrato_Transacoes(ArrayList<Transacoes> extrato){
        System.out.println(extrato);
    }

    public static void Transferencia_ContaFisica(){
        indice_pagamento = - 1;
        Object[] transferencias = {
                "Transferência para Cliente Físico", "Transferência para Cliente Jurídico"
        };
        Object selectedTransfers = JOptionPane.showInputDialog(null,
                "Que tipo de transfêrencia deseja realizar?", "transferência", JOptionPane.INFORMATION_MESSAGE,null,
                transferencias, transferencias[0]);
        if (selectedTransfers == transferencias[0]){
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinatário? ");
            String clientePagoCPF = JOptionPane.showInputDialog("Qual o CPF do destinatário?");
            for (int i= 0; i < contasFisicas.size(); i++){
                if ((contasFisicas.get(i).getNome().equals(clientePagoNome)) &&
                        (contasFisicas.get(i).getCPF().equals(clientePagoCPF))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = i;

                    TransferenciaCF_CF(contasFisicas.get(indice_CLIENTE), contasFisicas.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        } else {
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinátario? ");
            String clientePagoCNPJ = JOptionPane.showInputDialog("Qual o CNPJ do destinátario?");
            for (int i = 0; i < 10; i++){
                if ((contasJuridicas.get(i).getNome().equals(clientePagoNome)) &&
                        (contasJuridicas.get(i).getCNPJ().equals(clientePagoCNPJ))){
                JOptionPane.showMessageDialog(null, "Conta encontrada");
                indice_pagamento = i;

                TransferenciaCF_CJ(contasFisicas.get(indice_CLIENTE), contasJuridicas.get(indice_pagamento));
                break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        }
    }

    public static void TransferenciaCF_CF(PessoaFisica cliente_perde,PessoaFisica cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do pagamento?"));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaTransacoes(new Transacoes("Transferência", valor_troca, cliente_perde.getNome(),
                cliente_ganha.getNome(), cliente_perde.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void TransferenciaCF_CJ(PessoaFisica cliente_perde, PessoaJuridica cliente_ganha ){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do pagamento?"));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaTransacoes(new Transacoes("Transferência", valor_troca, cliente_perde.getNome(),
                cliente_ganha.getNome(), cliente_ganha.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void Transferencia_ContaJuridica(){
        indice_pagamento = - 1;
        Object[] transferencias = {
                "Transferência para Cliente Físico", "Transferência para Cliente Jurídico"
        };
        Object selectedTransfers = JOptionPane.showInputDialog(null,
                "Que tipo de transfêrencia deseja realizar?", "transferência", JOptionPane.INFORMATION_MESSAGE,null,
                transferencias, transferencias[0]);
        if (selectedTransfers == transferencias[0]){
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinatário? ");
            String clientePagoCPF = JOptionPane.showInputDialog("Qual o CPF do destinatário?");
            for (int i= 0; i < contasFisicas.size(); i++){
                if ((contasFisicas.get(i).getNome().equals(clientePagoNome)) &&
                        (contasFisicas.get(i).getCPF().equals(clientePagoCPF))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada!");
                    indice_pagamento = i;

                    TransferenciaCJ_CF(contasJuridicas.get(indice_CLIENTE), contasFisicas.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        } else {
            String clientePagoNome = JOptionPane.showInputDialog("Qual o nome do destinátario? ");
            String clientePagoCNPJ = JOptionPane.showInputDialog("Qual o CNPJ do destinátario?");
            for (int i = 0; i < 10; i++){
                if ((contasJuridicas.get(i).getNome().equals(clientePagoNome)) &&
                        (contasJuridicas.get(i).getCNPJ().equals(clientePagoCNPJ))){
                    JOptionPane.showMessageDialog(null, "Conta encontrada");
                    indice_pagamento = i;

                    TransferenciaCJ_CJ(contasJuridicas.get(indice_CLIENTE), contasJuridicas.get(indice_pagamento));
                    break;
                }
            }
            if (indice_pagamento == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
            }
        }
    }

    public static void TransferenciaCJ_CF(PessoaJuridica cliente_perde, PessoaFisica cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do pagamento?"));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaTransacoes(new Transacoes("Transferência", valor_troca, cliente_perde.getNome(),
                cliente_ganha.getNome(), cliente_perde.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static void TransferenciaCJ_CJ(PessoaJuridica cliente_perde, PessoaJuridica cliente_ganha){
        valor_troca = Double.parseDouble(JOptionPane.showInputDialog("qual será o valor do pagamento?"));
        cliente_perde.setSaldo(cliente_perde.getSaldo() - valor_troca);
        cliente_ganha.setSaldo(cliente_ganha.getSaldo() + valor_troca);
        cliente_perde.setListaTransacoes(new Transacoes("Transferência", valor_troca, cliente_perde.getNome(),
                cliente_ganha.getNome(), cliente_perde.getSaldo()));
        JOptionPane.showMessageDialog(null, "Transferência realizada.");
    }

    public static double Deposito(double valor_saldo){
        valor_depositado = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do depósito?"));
        valor_saldo += valor_depositado;
        JOptionPane.showMessageDialog(null, "Depósito realizado.");
        return valor_saldo;
    }

    public static double Saque(double valor_saldo){
        valor_saque = Double.parseDouble(JOptionPane.showInputDialog("Qual será o valor do saque?"));
        if (valor_saldo > valor_saque){
            valor_saldo -= valor_saque;
            JOptionPane.showMessageDialog(null, "Saque realizado.");
            return valor_saldo;
        } else {
            JOptionPane.showMessageDialog(null, "O saque não pode ser realizado. Saldo insuficiente.");
            int saque_inesperado = JOptionPane.showConfirmDialog(null, "Deseja trocar o valor do saque?",
                    "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
            if (saque_inesperado == -1){
                valor_saldo = Saque(valor_saldo);
                return valor_saldo;
            }else {
                return valor_saldo;
            }
        }
    }

    public static void login_ContaFisica(){
        indice_CLIENTE = -1;
        int login = JOptionPane.showConfirmDialog(null, "Já possui conta?",
                "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
        if (login == 0){
            String clienteNovoVelhoNome = JOptionPane.showInputDialog("Qual o nome? ");
            String clienteNovoVelhoSenha = JOptionPane.showInputDialog("Qual a senha? ");
            for (int k = 0; k < contasFisicas.size(); k++){
                if ((contasFisicas.get(k).getNome().equals(clienteNovoVelhoNome)) &&
                        (contasFisicas.get(k).getSenha().equals(clienteNovoVelhoSenha))){
                    JOptionPane.showMessageDialog(null, "Conta confirmada!");
                    indice_CLIENTE = k;
                    break;
                }
            }
            if (indice_CLIENTE == -1){
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
                JOptionPane.showMessageDialog(null, "Seja bemvindo " + newContaFisica() + " !");
                indice_CLIENTE = contasFisicas.size() - 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seja bem vindo " + newContaFisica() + " !");
            indice_CLIENTE = contasFisicas.size() - 1;
        }
    }

    public static void login_ContaJuridica(){
        indice_CLIENTE = -1;
        int login = JOptionPane.showConfirmDialog(null, "Já possui conta?",
                "Selecione uma opção:", JOptionPane.YES_NO_OPTION);
        if (login == 0){
            String clienteNovoVelhoNome = JOptionPane.showInputDialog("Qual é o seu nome? ");
            String clienteNovoVelhoSenha = JOptionPane.showInputDialog("Qual é a sua senha? ");
            for (int i = 0; i < contasJuridicas.size(); i++){
                if ((contasJuridicas.get(i).getNome().equals(clienteNovoVelhoNome)) &&
                        (contasJuridicas.get(i).getSenha().equals(clienteNovoVelhoSenha))){
                    JOptionPane.showMessageDialog(null, "Olá " + contasJuridicas.get(i).getNome() + " !");
                    indice_CLIENTE = i;
                    break;
                }
            }
            if (indice_CLIENTE == -1) {
                JOptionPane.showMessageDialog(null, "Essa conta não existe!");
                JOptionPane.showMessageDialog(null, "Seja bem vindo " + newContaJuridica() + " !");
                indice_CLIENTE = contasJuridicas.size() - 1;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seja bem vindo " + newContaJuridica() + " !");
            indice_CLIENTE = contasJuridicas.size() - 1;
        }
    }

    public static String newContaFisica(){
        String pessoaFisicaNome;
        String pessoaFisicaCPF;
        String pessoaFisicaSenha;

        pessoaFisicaNome = JOptionPane.showInputDialog("Qual é o nome?");
        pessoaFisicaCPF = JOptionPane.showInputDialog(("Qual é o CPF?"));
        pessoaFisicaSenha = JOptionPane.showInputDialog("Qual é a senha?");

        contasFisicas.add(new PessoaFisica(pessoaFisicaCPF,pessoaFisicaSenha, pessoaFisicaNome));

        return pessoaFisicaNome;
    }

    public static String newContaJuridica(){
        String pessoaJuridicaNome;
        String pessoaJuridicaCNPJ;
        String pessoaJuridicaSenha;

        pessoaJuridicaNome = JOptionPane.showInputDialog("Qual é o nome?");
        pessoaJuridicaCNPJ = JOptionPane.showInputDialog(("Qual é o CNPJ?"));
        pessoaJuridicaSenha = JOptionPane.showInputDialog("Qual é a senha?");

        contasJuridicas.add(new PessoaJuridica(pessoaJuridicaCNPJ,pessoaJuridicaSenha, pessoaJuridicaNome));

        return pessoaJuridicaNome;
    }
}
