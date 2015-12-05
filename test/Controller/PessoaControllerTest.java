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
    public void testCadastrarPessoaValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178.00", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
    }
    
    @Test
    public void testAlterarPessoaValido() {
        this.pessoa = new Pessoa (-1, "Nome", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178.00", "RG");
        
        Assert.assertEquals(true, pCon.cadastrarPessoa(pessoa));
        
        this.pessoa.setNome("Nomizinho");
        Assert.assertEquals(true, pCon.alterarPessoa(pessoa));
    }

    @Test
    public void testValidar() {
    }
    
}
