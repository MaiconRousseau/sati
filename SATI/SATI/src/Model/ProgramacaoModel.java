/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import valueObject.Edicao;
import valueObject.Programacao;


/**
 *
 * @author jao
 */
public class ProgramacaoModel {
    public static void cadastrarProgramacao(Programacao prog){
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
        
            String query = "insert into Edicao( dataHorarioInicio,dataHorarioFim, custo,"
                + "custoResponsavel, local, titulo, descricao, capacidade, cargaHoraria"
                + "valorInscricao, idEdicao, idPessoa) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int cont = 1;
            
            stm.setDate(cont++, (java.sql.Date) prog.getDataHoraInicio());
            stm.setDate(cont++, (java.sql.Date) prog.getDataHoraFim());
            stm.setDouble(cont++,  prog.getCusto());
            stm.setDouble(cont++,  prog.getCustoResponsavel());
            stm.setString(cont++, prog.getLocal());
            stm.setString(cont++, prog.getTitulo());
            stm.setString(cont++, prog.getDescricao());
            stm.setInt(cont++, prog.getCapacidade());
            stm.setInt(cont++, prog.getCargaHoraria());
            stm.setDouble(cont++, prog.getValorInscricao());
            stm.setInt(cont++, prog.getEdicao().getIdEdicao());
            stm.setInt(cont++, prog.getPessoa().getIdPessoa());
            
            int status = stm.executeUpdate();
            
            if(status == 1){
                
                prog.setError(false);
                prog.setMessage("Programacao inserida com Sucesso");
                
                ResultSet rs = stm.getGeneratedKeys();
                rs.next();
                
                int key = rs.getInt(1);
                System.out.println(" Key"+ key);
                prog.setIdProgramacao(key);
            
            } else {
                prog.setMessage("Falha ao cadastrar programacao");
            }

        } catch (Exception e) {
           prog.setError(true);
           prog.setMessage("Falha ao Cadastrar Edicao\n\t"+ e.getMessage());
        }
    }
    
}
