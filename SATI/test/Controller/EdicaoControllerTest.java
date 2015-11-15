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

    private Edicao edicao1;
    
    public EdicaoControllerTest() {
        calendar.set(year, month+1, date);
        this.inicio = calendar.getTime();
        calendar.set(year, month+2, date);
        this.fim = calendar.getTime();
        calendar.set(year, month, date);
        this.vencimento = calendar.getTime();
        
        this.edicao1 = new Edicao (inicio, fim, vencimento, 
                false, "Titulo","Tema",
                null, -1);
    }

    @Test
    public void testCadastrarEdicaoDadosCorretos1() {
        edicao1.setError(false);
        Assert.assertEquals(true, eCon.cadastrarEdicao(edicao1));
        eCon.excluirEdicao(edicao1, "ALL");
    }
    
    @Test
    public void testCadastrarEdicaoMesmoAno() {
        // cadastra um evento no mesmo ano
        eCon.cadastrarEdicao(edicao1);
        edicao1.setError(false);
        Assert.assertEquals(false, eCon.cadastrarEdicao(edicao1));
        eCon.excluirEdicao(edicao1, "ALL");
    }
    
    // Criar mais testes
    
    
}
