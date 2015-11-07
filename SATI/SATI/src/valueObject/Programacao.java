/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import java.sql.Date;

/**
 *
 * @author Lucas
 */
public class Programacao {
    
    private int idProgramacao;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private double custo;
    private double custoResponsavel;
    private String local;
    private String titulo;
    private String descricao;
    private int capacidade;
    private int cargaHoraria;
    private double valorInscricao;
    private Edicao edicao;
    private Pessoa pessoa;
  
    // Quando uma operação envolvendo este objeto der erro em tempo de execução, 
    // este atributo será acionado
        private boolean error = false;
        // Especificação do erro
        private String message;


    public Programacao(int idProgramacao, Date dataHoraInicio, Date dataHoraFim, double custo, double custoResponsavel, String local, String titulo, String descricao, int capacidade, int cargaHoraria, double valorInscricao, Edicao edicao, Pessoa pessoa) {
        this.idProgramacao = idProgramacao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.custo = custo;
        this.custoResponsavel = custoResponsavel;
        this.local = local;
        this.titulo = titulo;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.cargaHoraria = cargaHoraria;
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

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
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

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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
}
