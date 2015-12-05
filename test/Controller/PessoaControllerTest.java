/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import valueObject.DadosBancarios;
import valueObject.Edicao;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaControllerTest {
    
    private PessoaController pCon = new PessoaController();
    private Pessoa pessoa;
    
    @Test
    public void testCadastrarPessoaDadosNulos() {
        this.pessoa = new Pessoa (-1, null, 
                null, null, null, null,//"Instituição", 
                        null, null);
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, null, 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                null, "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
       
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", null, "",//"Instituição", 
                        "37789417800", "RG");
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", null, "email@valido.com", null,//"Instituição", 
                        "37789417800", "RG");
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        null, "RG");
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", null);
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaDadosVazios() {
        this.pessoa = new Pessoa (-1, "", 
                "Professor", "1371800", "email@valido.com", "Q",
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "", "1371800", "email@valido.com", "Q",
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "", "email@valido.com", "",
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "", "Q",
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "Q",
                        "", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "Q",
                        "37789417800", "");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaComRAOuInstituicao(){
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "", "email@valido.com", "Instituição",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaInstituicaoEUTFPR() {
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        Assert.assertEquals("UTFPR", pessoa.getInstituicao());
        
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "", "email@valido.com", "Instituição",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        Assert.assertEquals("UTFPR", pessoa.getInstituicao());
        
    }
    
    @Test
    public void testCadastrarPessoaRAValido() {
    
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1234567", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaRAInvalido() {
    
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1", "email@valido.com", "",//"Instituição", 
                        "ABC1!@24234", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1", "email@valido.com", "",//"Instituição", 
                        "12345678", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaTipoValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Aluno", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Servidor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa = new Pessoa (-1, "Nome", 
                "Outros", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaTipoInvalido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "OutraCoisa", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    
    @Test
    public void testCadastrarPessoaEmailValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "exemplo@email.sati", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaEmailInvalido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "exemploNãoValido", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaCPFValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "14661485785", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaCPFInvalido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "12345678901", "RG");
        
        Assert.assertEquals(false, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testCadastrarPessoaValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Professor", "1371800", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testAlterarPessoaValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Aluno", "1234567", "email@valido.com", "",//"Instituição", 
                        "37789417800", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa.setNome("Nomizinho");
        Assert.assertEquals(true, pCon.alterarPessoa(pessoa));
    }

    @Test
    public void testValidar() {
    }
    
}
