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

    private Date inicio;
    private Date fim;
    private Date vencimento;

    private Edicao edicao;
    
    public EdicaoControllerTest() {
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
         eCon.excluirEdicao(new Edicao(), "ALL");
    }
    
    
    @Test
    public void testEdicaoDeveConterTituloETema() {
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "","",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo","",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        eCon.excluirEdicao(new Edicao(), "ALL");
    }
    
    @Test
    public void testDataInicioAntesDataFim(){
        
        Date dataInicio, dataFim;
        
        calendar.set(2015, 11, 15);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 14);
        dataFim = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim, vencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        calendar.set(2015, 11, 14);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 15);
        dataFim = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim, vencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        eCon.excluirEdicao(new Edicao(), "ALL");
    } 
    
    @Test
    public void testCadastrarEdicaoMesmoAno() {
        // cadastra um evento no mesmo ano
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        eCon.excluirEdicao(edicao, "ALL");
    }
    
    @Test
    public void testDataVencimentoDeIncricoesAntesDataInicio(){
        
        Date dataInicio, dataVencimento, dataFim;
        
        calendar.set(2015, 11, 14);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 15);
        dataVencimento = calendar.getTime();
        
        calendar.set(2016, month, date);
        dataFim = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim, dataVencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao));
        
        calendar.set(2015, 11, 15);
        dataInicio = calendar.getTime();
        calendar.set(2015, 11, 14);
        dataVencimento = calendar.getTime();
        
        this.edicao = new Edicao (dataInicio, dataFim, dataVencimento, 
                false, "Titulo","Tema",
                new DadosBancarios(), -1);
        
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao));
        
        eCon.excluirEdicao(new Edicao(), "ALL");
    } 
    
    
    @Test
    public void testCadastrarEdicaoDadosCorretos1() {
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
