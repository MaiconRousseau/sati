/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Date;
import java.sql.ResultSet;
import valueObject.Edicao;
import valueObject.Pessoa;
import valueObject.Programacao;

/**
 *
 * @author jao
 */
public class Util {
        public static Programacao criarProgramacao(ResultSet rs) {
            Programacao prog = null;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            int idProgramacao = rs.getInt("idProgramacao");
            Date DataInicio = rs.getDate("dataHoraInicio"); 
            Date DataFim = rs.getDate("dataHoraFim");
            double custo = rs.getDouble("custo");
            double custoResponsavel = rs.getDouble("custoResponsavel");
            String local = rs.getString("local");
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");
            int capacidade = rs.getInt("capacidade");
            int cargaHoraria = rs.getInt("cargaHoraria");
            double valorInscricao = rs.getDouble("valorInscricao");
            int idEdicao = rs.getInt("idEdicao");
            int idPessoa = rs.getInt("idPessoa");
            
            Pessoa pessoa = getPessoaID(idPessoa);//Retorna a pessoa responsavel
            Edicao edicao = getEdicaoID(idEdicao);//Retorna a pessoa respondavel
            
            prog = new Programacao(idProgramacao, DataInicio, DataFim, custo, custoResponsavel, local, titulo, descricao, capacidade, cargaHoraria, valorInscricao, edicao, pessoa);
           
            return prog;
        }
        catch(Exception e) {
           
            prog.setError(true);
            prog.setMessage(e.getMessage());
            return null;
        }
    }


    private static Pessoa getPessoaID(int idPessoa) { //retorna um objeto Pessoa com idPessoa
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Edicao getEdicaoID(int idEdicao) { //retorna um objeto Pessoa com idPessoa
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
