/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.util.Date;
import valueObject.DadosBancarios;
import valueObject.Edicao;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class Util {

    public static Edicao criarEdicao(ResultSet rs) {
        Edicao edicao;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            
            int idEdicao = rs.getInt(1);
            Date dataInicio = rs.getDate(2);
            Date dataFim = rs.getDate(3);
            Date dataVencimentoInscricao = rs.getDate(4);
            boolean agendaDefinida = rs.getBoolean(5);
            String titulo = rs.getString(6);
            String tema = rs.getString(7);
            int idDadosBancarios = rs.getInt(8);
            
            DadosBancarios dadosBancarios = Util.getDadosBancarios(idDadosBancarios); // Tem que criar o buscas Dados Bancários
    
            edicao = new Edicao (dataInicio, dataFim, dataVencimentoInscricao, 
                    agendaDefinida, titulo, tema,
                    dadosBancarios, idEdicao);
            
            return edicao;
        }
        catch(Exception e) {
            
            edicao = new Edicao();
            edicao.setError(true);
            edicao.setMessage(e.getMessage());
            
            System.out.println(edicao.getMessage());
            
            return null;
        }
    }

    private static DadosBancarios getDadosBancarios(int idDadosBancarios) {
        return null;
    }
    
}
