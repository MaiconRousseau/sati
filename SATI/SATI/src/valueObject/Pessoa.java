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
  private int idPessoa;
  private String nome;
  private String tipo;
  private String ra;
  private String email;
  private String instituicao;
  private String cpf;
  private String rg;
  
  private boolean error = false;
        // Especificação do erro
  private String message;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setError(boolean error) {
        this.error = error;
    }
    
    

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
  
}
