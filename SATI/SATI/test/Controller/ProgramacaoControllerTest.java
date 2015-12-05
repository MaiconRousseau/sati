/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProgramacaoModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import valueObject.DadosBancarios;
import valueObject.Edicao;
import valueObject.Pessoa;
import valueObject.Programacao;

/**
 *
 * @author jao
 */
public class ProgramacaoControllerTest {
    private ProgramacaoController conP = new ProgramacaoController();
    private final EdicaoController eCon = new EdicaoController();
    private DadosBancariosController dbCon = new DadosBancariosController();
    private Programacao prog;
    private Edicao edicao;
    private Pessoa pessoa;
    private DadosBancarios db;
    
    private  int year = 2000;
    private  int month = 01;
    private  int date = 01;

    private Calendar calendar = Calendar.getInstance();

    private  Date inicio;
    private  Date fim;
    private  Date vencimento;
  
    private String agencia;
    private String conta;
    private String tipo;
    private int carteira;
 
    @Test
    public void testProgramacaoValida(){
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Joazinhu", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia1", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo2","Tema2",db, -1);
                Assert.assertEquals(true,eCon.excluirEdicao(edicao,"ALL"));
                Assert.assertEquals(true,eCon.cadastrarEdicao(edicao));
                
        calendar.set(2000, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2000, 02, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
        System.out.println(dataInicio + " " + dataFim);
        prog = new Programacao(-1,dataInicio,500,100,"SALA 01","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Assert.assertEquals(true,conP.cadastrarProgramacao(prog));
    
    }
    
    @Test
    public void testProgramacaoPessoaInvalida(){
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Marcio", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia2", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo2","Tema2",db, -1);
                eCon.excluirEdicao(edicao,"ALL");
                eCon.cadastrarEdicao(edicao);
                
        calendar.set(2001, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2001, 03, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
        System.out.println(dataInicio + " " + dataFim);
        prog = new Programacao(-1,dataInicio,500,100,"SALA 02","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Assert.assertEquals(true,conP.cadastrarProgramacao(prog));
        
    }
    
    
    @Test // Testa se o Joazinhu eh o responsavel pela programaç?o solicitada
    public void testProgramacaoPessoaValida(){
        
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Osvaldo", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+2, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+3, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia3", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo2","Tema2",db, -1);
                eCon.excluirEdicao(edicao,"ALL");
                eCon.cadastrarEdicao(edicao);
                
        calendar.set(2003, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2004, 02, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
        System.out.println(dataInicio + " " + dataFim);
        prog = new Programacao(-1,dataInicio,500,100,"SALA 03","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setIdPessoa(this.prog.getPessoa().getIdPessoa());
        System.out.println(" proggramacaoidpessoa: "+this.prog.getPessoa().getIdPessoa()+" pessoa :"+ pessoa2.getIdPessoa());
        ArrayList<Pessoa> list = conPessoa.buscarPessoa(pessoa2, "ID");
        System.out.println("Controller.ProgramacaoControllerTest.testProgramacaoPessoaValida()");
        for (Pessoa pessoa1 : list) {
            System.out.println(pessoa1.getIdPessoa());
        }
        Pessoa pessoaTest = list.get(0);
        boolean x = false;
        if (pessoa.getIdPessoa() == pessoaTest.getIdPessoa()) x = true;
       
        Assert.assertEquals(true,x);
       
    }
    public ProgramacaoControllerTest() {
    
    }

    @Test
    public void testSomeMethod() {
    }
    
}
