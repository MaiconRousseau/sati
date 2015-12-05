/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import valueObject.DadosBancarios;
import valueObject.Edicao;

/**
 *
 * @author Lucas
 */
public class EdicaoControllerIT {
    
    
    
    private final EdicaoController eCon = new EdicaoController();
    
    private final DadosBancariosController dbCon = new DadosBancariosController();
    
    private final int year = 2000;
    private final int month = 01;
    private final int date = 01;

    private final Calendar calendar = Calendar.getInstance();

    private final Date inicio;
    private final Date fim;

    private Edicao edicao;
     private DadosBancarios db;
    
    public EdicaoControllerIT() {
        
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        
        this.edicao = new Edicao (inicio, fim, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
         eCon.excluirEdicao(new Edicao(), "ALL");
    }
    
    @Test
    public void testCadastrarEdicaoSemDadosBancarios() {
        edicao.setError(false);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        
        eCon.excluirEdicao(edicao, "ALL");
    }
    
    @Test
    public void testCadastrarEdicaoERecuperarDados() {
        edicao.setError(false);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        Edicao edicaoSearch = eCon.buscarEdicao(edicao, "ID").get(0);
        
        Assert.assertEquals(edicaoSearch.getIdEdicao(), edicao.getIdEdicao());
        
        eCon.excluirEdicao(edicao, "ALL");
    }
   
    
    @Test
    public void testCadastrarDadosBancariosRecuperarDados() {
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
        
        DadosBancarios dbSearch = dbCon.buscarDadosBancarios(db, "ID").get(0);
        
        Assert.assertEquals(dbSearch.getIdDadosBancarios(), db.getIdDadosBancarios());
        
    }
    

    @Test
    public void testCadastrarEdicaoComDadosBancariosRecuperarDados() {

        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
                            0);
        Assert.assertEquals(true, dbCon.cadastrarDadosBancarios(db));
        DadosBancarios dbSearch = dbCon.buscarDadosBancarios(db, "ID").get(0);
        Assert.assertEquals(dbSearch.getIdDadosBancarios(), db.getIdDadosBancarios());
        
        
        
        edicao.setDadosBancarios(db);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        Edicao edicaoSearch = eCon.buscarEdicao(edicao, "ID").get(0);
        Assert.assertEquals(edicaoSearch.getIdEdicao(), edicao.getIdEdicao());
        
        
        Assert.assertEquals(db.getIdDadosBancarios(), edicao.getDadosBancarios().getIdDadosBancarios());
        
        eCon.excluirEdicao(edicao, "ALL");
    }
    
    
}
