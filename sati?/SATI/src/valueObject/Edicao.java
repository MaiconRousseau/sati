/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Edicao {
    
    private Date dataInicio;
    private Date dataFim;
    private Date dataVencimentoInscricao;
    private boolean agendaDefinida;
    private String titulo;
    private String tema;
    
    private DadosBancarios dadosBancarios;
    
    // ID deste objeto no banco de dados
    private int idEdicao;
    
    /*
     * Ajuda MVC
     * Os atributos abaixo contribuir para o controle das operações 
     * envolvendo os objetos desta classe.
     */
    
    // Quando uma operação envolvendo este objeto der erro em tempo de execução, 
    // este atributo será acionado
    private boolean error = false;
    // Especificação do erro
    private String message;
    
    public Edicao (Date dataInicio, Date dataFim, Date dataVencimentoInscricao, 
                    boolean agendaDefinida, String titulo, String tema,
                    DadosBancarios dadosBancarios, int idEdicao) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataVencimentoInscricao = dataVencimentoInscricao;
        this.agendaDefinida= agendaDefinida;
        this.titulo = titulo;
        this.tema = tema;
        this.dadosBancarios = dadosBancarios;
        this.idEdicao = idEdicao;
    }
    
    public Edicao () {
        
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the dataVencimentoInscricao
     */
    public Date getDataVencimentoInscricao() {
        return dataVencimentoInscricao;
    }

    /**
     * @param dataVencimentoInscricao the dataVencimentoInscricao to set
     */
    public void setDataVencimentoInscricao(Date dataVencimentoInscricao) {
        this.dataVencimentoInscricao = dataVencimentoInscricao;
    }

    /**
     * @return the agendaDefinida
     */
    public boolean isAgendaDefinida() {
        return agendaDefinida;
    }

    /**
     * @param agendaDefinida the agendaDefinida to set
     */
    public void setAgendaDefinida(boolean agendaDefinida) {
        this.agendaDefinida = agendaDefinida;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the dadosBancarios
     */
    public DadosBancarios getDadosBancarios() {
        return dadosBancarios;
    }

    /**
     * @param dadosBancarios the dadosBancarios to set
     */
    public void setDadosBancarios(DadosBancarios dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    /**
     * @return the idEdicao
     */
    public int getIdEdicao() {
        return idEdicao;
    }

    /**
     * @param idEdicao the idEdicao to set
     */
    public void setIdEdicao(int idEdicao) {
        this.idEdicao = idEdicao;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
