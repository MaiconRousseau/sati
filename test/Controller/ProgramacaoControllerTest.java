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
    
    @Test // vou criar uma programaç?o valida e apos busca-la 
    public void testBuscarProgramacao(){
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
        
        prog = new Programacao(-1,dataInicio,500,100,"SALA 01","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
       boolean cadastroSucesso = conP.cadastrarProgramacao(prog);
      
       ArrayList<Programacao> list = conP.buscarProgramacao(prog, "IDPROGRAMACAO");
       Programacao progTest = list.get(0);
       boolean x = false;
       if (progTest.getIdProgramacao() == prog.getIdProgramacao()) x = true;
        Assert.assertEquals(true, x);
    }
    
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
        
        prog = new Programacao(-1,dataInicio,500,100,"SALA 01","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Assert.assertEquals(true,conP.cadastrarProgramacao(prog));
    
    }
  
    
    @Test
    public void testExlusaoProgramacao(){
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Vagner", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia10", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo10","Tema2",db, -1);
                eCon.excluirEdicao(edicao,"ALL");
                eCon.cadastrarEdicao(edicao);
                
        calendar.set(2001, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2001, 03, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
       
        prog = new Programacao(-1,dataInicio,500,100,"SALA 10","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        boolean sucess = conP.cadastrarProgramacao(prog);
        boolean x = false;
       
        x = conP.excluirProgramacao(prog);
       
        if (x){
           x = false;
           ArrayList<Programacao> list = conP.buscarProgramacao(prog, "IDPROGRAMACAO");
           if (list == null || list.isEmpty()) x = true;
        }
        Assert.assertEquals(true, x);
    }
    
    
    @Test // Testa se de fato a programaç?o foi atribuida ao  responsavel adequado e retorna verdaddeiro se sim
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
        
        prog = new Programacao(-1,dataInicio,500,100,"SALA 03","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setIdPessoa(this.prog.getPessoa().getIdPessoa());
     
        ArrayList<Pessoa> list = conPessoa.buscarPessoa(pessoa2, "ID");
        if (list == null) return;
        // Uma objeto pessoa diferente recebe um resultado que foi solicitado na pesquisa
        //Como a pesquisa retorna pelo idPessoa trata-se da mesma pessoa.
        Pessoa pessoaTest = list.get(0);
        boolean x = false;
        if (pessoa.getIdPessoa() == pessoaTest.getIdPessoa()) x = true;
       
        Assert.assertEquals(true,x);
       
    }
             // Temo o mesmo objetivo do teste anterior que ? certificar que a ediç?o que
    @Test   //cadastrada eh a mesma retornada em uma busca generica pelo IDedicao salvo em programacao 
    public void testProgramacaoEdicaoValido(){
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Soares", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+2, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+3, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia4", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo4","Tema2",db, -1);
                eCon.excluirEdicao(edicao,"ALL");
                eCon.cadastrarEdicao(edicao);
                
        calendar.set(2003, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2004, 02, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
        
        prog = new Programacao(-1,dataInicio,500,100,"SALA 04","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Edicao edicao2 = new Edicao();
        edicao2.setIdEdicao(this.prog.getEdicao().getIdEdicao());
     
        ArrayList<Edicao> list = eCon.buscarEdicao(edicao2, "ID");
        if (list == null) return;
        // Uma objeto pessoa diferente recebe um resultado que foi solicitado na pesquisa
        //Como a pesquisa retorna pelo idPessoa trata-se da mesma pessoa.
        Edicao edicaoTest = list.get(0);
        boolean x = false;
        if (edicao.getIdEdicao() == edicaoTest.getIdEdicao()) x = true;
       
        Assert.assertEquals(true,x);
    }
     @Test //pretende-se mostrar que um idEdicao diferente n?o retorna qualquer valor
    public void testProgramacaoEdicaoInvalido(){ 
        PessoaController conPessoa = new PessoaController();
        this.pessoa = new Pessoa(-1, "Messi", 
                "Tipo", "RA", "email@valido.com", "",//"Instituição", 
                        "377.894.178-00", "RG");
        conPessoa.cadastrarPessoa(pessoa);
        
        calendar.set(year, month+2, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+3, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        db = new DadosBancarios ("Agencia4", "Conta", "Tipo", 1,
                            0);
        dbCon.cadastrarDadosBancarios(db);
        
        this.edicao = new Edicao (inicio, fim, vencimento, 
                false, "Titulo4","Tema2",db, -1);
                eCon.excluirEdicao(edicao,"ALL");
                eCon.cadastrarEdicao(edicao);
                
        calendar.set(2003, 02, 01, 12, 00, 01);
        Timestamp dataInicio = new Timestamp(calendar.getTimeInMillis());
        calendar.set(2004, 02, 01, 14, 00, 00);
        Timestamp dataFim =    new Timestamp(calendar.getTimeInMillis());
     
        prog = new Programacao(-1,dataInicio,500,100,"SALA 04","Palestra", "TESTANDO 2 descricao", 50, dataFim, 15, edicao, pessoa);
        
        Edicao edicao2 = new Edicao();
        edicao2.setIdEdicao(this.prog.getEdicao().getIdEdicao() + 1);// aqui altero tentando trazer para a mesma programacao uma edicao diferente da registrada
     
        ArrayList<Edicao> list = eCon.buscarEdicao(edicao2, "ID");
        boolean x = false;
        if (list == null){
            Assert.assertEquals(false,x);
        }//O resultado deve ser NULL
    }
    
    public ProgramacaoControllerTest() {
    
    }

    @Test
    public void testSomeMethod() {
    }
    
}
