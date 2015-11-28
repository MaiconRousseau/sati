/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProgramacaoModel;
import java.sql.Timestamp;
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
    public void ProgramacaoValida(){
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
        
        db = new DadosBancarios ("Agencia", "Conta", "Tipo", 1,
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
    public ProgramacaoControllerTest() {
    
    }

    @Test
    public void testSomeMethod() {
    }
    
}
