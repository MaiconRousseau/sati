/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valueObject;

/**
 *
 * @author Lucas
 */
public class DadosBancarios {
    
    private String agencia;
    private String conta;
    private String tipo;
    private int carteira;
    
    // ID deste objeto no banco de dados
    private int idDadosBancarios;
    
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

    public DadosBancarios (String agencia, String conta, String tipo, int carteira,
                            int idDadosBancarios) {
        this.agencia = agencia;
        this.conta = conta;
        this.tipo = tipo;
        this.carteira = carteira;
        this.idDadosBancarios = idDadosBancarios;
    }
    
    public DadosBancarios () {
        
    }
    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return the conta
     */
    public String getConta() {
        return conta;
    }

    /**
     * @param conta the conta to set
     */
    public void setConta(String conta) {
        this.conta = conta;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the carteira
     */
    public int getCarteira() {
        return carteira;
    }

    /**
     * @param carteira the carteira to set
     */
    public void setCarteira(int carteira) {
        this.carteira = carteira;
    }

    /**
     * @return the idDadosBancarios
     */
    public int getIdDadosBancarios() {
        return idDadosBancarios;
    }

    /**
     * @param idDadosBancarios the idDadosBancarios to set
     */
    public void setIdDadosBancarios(int idDadosBancarios) {
        this.idDadosBancarios = idDadosBancarios;
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
