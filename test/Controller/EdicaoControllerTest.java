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
import valueObject.*;

/**
 *
 * @author Lucas
 */
public class EdicaoControllerTest {
    
    private final EdicaoController eCon = new EdicaoController();
    
    private final int year = 2000;
    private final int month = 01;
    private final int date = 01;

    private final Calendar calendar = Calendar.getInstance();

    private final Date inicio;
    private final Date fim;

    private Edicao edicao;
    
    public EdicaoControllerTest() {
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
    public void testEdicaoDeveConterTituloETema() {
        this.edicao = new Edicao (inicio, fim,  
                false, "","",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim,  
                false, "Titulo","",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim,  
                false, "","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        eCon.excluirEdicao(new Edicao(), "ALL");
    }
    
    @Test
    public void testDataInicioAntesDataFim(){
        
        Date dataInicio, dataFim;
        
        calendar.set(2011, 11, 11);
        dataInicio = calendar.getTime();
        calendar.set(2011, 11, 10);
        dataFim = calendar.getTime();

        
        this.edicao = new Edicao (dataInicio, dataFim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        calendar.set(2015, 11, 14);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 15);
        dataFim = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        eCon.excluirEdicao(new Edicao(), "ALL");
    } 
    
    @Test
    public void testCadastrarEdicaoMesmoAno() {
        Date dataInicio, dataFim;
        
        calendar.set(2015, 11, 11);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 25);
        dataFim = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        calendar.set(2015, 5, 14);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 25);
        dataFim = calendar.getTime();
        
        Edicao edicaoMesmoAno = new Edicao (dataInicio, dataFim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicaoMesmoAno));
        eCon.excluirEdicao(edicao, "ALL");
    }
    
   
    
    @Test
    public void testCadastrarEdicaoDadosCorretos1() {
        
        Date dataInicio, dataFim;
        
        calendar.set(2011, 11, 9);
        dataInicio = calendar.getTime();
        calendar.set(2011, 11, 10);
        dataFim = calendar.getTime();

        
        this.edicao = new Edicao (dataInicio, dataFim,  
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        edicao.setError(false);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        eCon.excluirEdicao(edicao, "ALL");
    }
    
    @Test
    public void testExcluirEdicao() {
        edicao.setError(false);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        Assert.assertEquals(true, eCon.excluirEdicao(edicao, "ID"));
        eCon.excluirEdicao(edicao, "ALL");
    }
    
    @Test
    public void testAlterarEdicao() {
        edicao.setError(false);
        //cadastrando
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        edicao.setTitulo("Novo Título");
        Assert.assertEquals(true, eCon.alterarEdicao(edicao));
        

    }
    
}
