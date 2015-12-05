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
    public void cadastrarCorreto() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
    }
    
    
    @Test
    public void cadastrarComDadosNulos() {
        db = new DadosBancarios (null, "Conta", "Tipo", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        db = new DadosBancarios ("Agencia", null, "Tipo", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("Agencia", "Conta", null, 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios (null, null, null, 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
    }
    
     @Test
    public void cadastrarComDadosVazios() {
        db = new DadosBancarios ("", "Conta", "Tipo", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("Agencia", "", "Tipo", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("Agencia", "Conta", "", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("", "", "", 1,
                            0);
       
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
    }
    
    
    @Test
    public void cadastrarCarteiraCorreta() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1000,
                            0);
        
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
    }
    
    @Test
    public void cadastrarCarteiraIncorreta() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 0,
                            0);
        
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
        
        
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", -10,
                            0);
        
        Assert.assertEquals(false, dbCon.cadastrarDadosBancarios(db));
    }
  
}
