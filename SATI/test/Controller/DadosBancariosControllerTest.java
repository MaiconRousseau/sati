/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import valueObject.DadosBancarios;

/**
 *
 * @author Lucas
 */
public class DadosBancariosControllerTest {
    
    private DadosBancariosController dbCon = new DadosBancariosController();
    
    
    private String agencia;
    private String conta;
    private String tipo;
    private int carteira;
    
    private DadosBancarios db;
    
    public DadosBancariosControllerTest() {
    }

    @Test
    public void verificarAtributosCorreto() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        
        Assert.assertEquals(true, dbCon.verificarAtributos(db));
    }
    
    @Test
    public void verificarAtributosNenhumCorreto() {
        db = new DadosBancarios ("", "", "", 0,
                            0);
        
        Assert.assertEquals(false, dbCon.verificarAtributos(db));
    }
    
    @Test
    public void verificarAtributosSemAgencia() {
        db = new DadosBancarios ("", "Conta", "Tipo", 1,
                            0);
        
        Assert.assertEquals(false, dbCon.verificarAtributos(db));
    }

    @Test
    public void cadastrarCorreto() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
    }
    
    @Test
    public void cadastrarIncorreto() {
        db = new DadosBancarios ("Agencia", "Conta", "", 1,
                            0);
        
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
    }
    
}
