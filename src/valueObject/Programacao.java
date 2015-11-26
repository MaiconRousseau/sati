/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;


import java.sql.Timestamp;

public class Programacao {
    
    private int idProgramacao;
    private Timestamp dataInicio;
    private double custo;
    private double custoResponsavel;
    private String local;
    private String titulo;
    private String descricao;
    private int capacidade;
    private Timestamp dataFim;
    private double valorInscricao;
    private Edicao edicao;
    private Pessoa pessoa;
  
    private boolean error = false;
    private String message;


    public Programacao(int idProgramacao, Timestamp dataInicio, double custo, double custoResponsavel, String local, String titulo, String descricao, int capacidade, Timestamp dataFim, double valorInscricao, Edicao edicao, Pessoa pessoa) {
        this.idProgramacao = idProgramacao;
        this.dataInicio = dataInicio;
        this.custo = custo;
        this.custoResponsavel = custoResponsavel;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.dataFim = dataFim;
        this.valorInscricao = valorInscricao;
        this.edicao = edicao;
        this.pessoa = pessoa;
        
    }  
  
    public int getIdProgramacao() {
        return idProgramacao;
    }

    public void setIdProgramacao(int idProgramacao) {
        this.idProgramacao = idProgramacao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getCustoResponsavel() {
        return custoResponsavel;
    }

    public void setCustoResponsavel(double custoResponsavel) {
        this.custoResponsavel = custoResponsavel;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Timestamp getDataFim() {
        return dataFim;
    }

    public void setDataFim(Timestamp dataFim) {
        this.dataFim = dataFim;
    }

    public double getValorInscricao() {
        return valorInscricao;
    }

    public void setValorInscricao(double valorInscricao) {
        this.valorInscricao = valorInscricao;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Timestamp dataInicio) {
        this.dataInicio = dataInicio;
    }
}
