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
public class Pessoa {
    
  protected int idPessoa;
  protected String nome;
  protected String tipo;
  protected String ra;
  protected String email;
  protected String instituicao;
  protected String cpf;
  protected String rg;
  
    // Quando uma operação envolvendo este objeto der erro em tempo de execução, 
    // este atributo será acionado
    protected boolean error = false;
    // Especificação do erro
    protected String message;

    public Pessoa(int idPessoa, String nome, String tipo, String ra, String email, String instituicao, String cpf, String rg) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.tipo = tipo;
        this.ra = ra;
        this.email = email;
        this.instituicao = instituicao;
        this.cpf = cpf;
        this.rg = rg;
    }

    public Pessoa() {
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
