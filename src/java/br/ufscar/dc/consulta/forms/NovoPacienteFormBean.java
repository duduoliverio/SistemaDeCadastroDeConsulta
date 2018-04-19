/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.consulta.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duduoliverio
 */
public class NovoPacienteFormBean {

    private String cpf, nome, senha, telefone, sexo, dataDeNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public List<String> validar() {
        List<String> mensagens = new ArrayList<>();
        if (cpf.contains(".")) {
            mensagens.add("CPF não pode conter pontos!");
        }
        if (cpf.contains("-")) {
            mensagens.add("CPF não pode conter traço!");
        }
        if (cpf.trim().length() == 0) {
            mensagens.add("CPF não pode ser vazio");
        }
        if (nome.trim().length() == 0) {
            mensagens.add("Nome não pode ser vazio!");
        }
        if (telefone.trim().length() == 0) {
            mensagens.add("Telefone não pode ser vazia!");
        }
        if (sexo.trim().length() == 0) {
            mensagens.add("Sexo não pode ser vazio!");
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.parse(dataDeNascimento);
        } catch (ParseException pe) {
            mensagens.add("Data de nascimento deve estar no formato dd/mm/aaaa!");
        }
        return (mensagens.isEmpty() ? null : mensagens);
    }

}
