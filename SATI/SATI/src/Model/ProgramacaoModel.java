/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import valueObject.Edicao;
import valueObject.Programacao;


/**
 *
 * @author jao
 */
public class ProgramacaoModel {
    public boolean  cadastrarProgramacao(Programacao prog){
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
        
            String query = "insert into Programacao( dataInicio, custo,"
                + "custoResponsavel, local, titulo, descricao, capacidade, dataFim,"
                + "valorInscricao, idEdicao, idPessoa) values(?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int cont = 1;
            
            stm.setTimestamp(cont++, prog.getDataInicio());
            stm.setDouble(cont++,  prog.getCusto());
            stm.setDouble(cont++,  prog.getCustoResponsavel());
            stm.setString(cont++, prog.getLocal());
            stm.setString(cont++, prog.getTitulo());
            stm.setString(cont++, prog.getDescricao());
            stm.setInt(cont++, prog.getCapacidade());
            stm.setTimestamp(cont++, prog.getDataFim());
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
                prog.setIdProgramacao(key);
                  
            } else {
                prog.setError(true);
                prog.setMessage("Falha ao cadastrar programacao");
            }
            
            mCon.disconnect();
            return !prog.isError();
        } catch (Exception e) {
           prog.setError(true);
           prog.setMessage("Falha ao Cadastrar Programacao\n\t"+ e.getMessage());
           return !prog.isError();
        }
   
    }
    public boolean alterarProgramacao(Programacao prog){
        
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
        
            String query = "update Programacao set dataInicio=?, custo=?,"
                + "custoResponsavel=?, local=?, titulo=?, descricao=?, capacidade=?, datafim=?,"
                + "valorInscricao=?, idEdicao=?, idPessoa=? where idProgramacao=?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            int cont = 1;
            
            stm.setTimestamp(cont++,prog.getDataInicio());
            stm.setDouble(cont++,  prog.getCusto());
            stm.setDouble(cont++,  prog.getCustoResponsavel());
            stm.setString(cont++, prog.getLocal());
            stm.setString(cont++, prog.getTitulo());
            stm.setString(cont++, prog.getDescricao());
            stm.setInt(cont++, prog.getCapacidade());
            stm.setTimestamp(cont++, prog.getDataFim());
            stm.setDouble(cont++, prog.getValorInscricao());
            stm.setInt(cont++, prog.getEdicao().getIdEdicao());
            stm.setInt(cont++, prog.getPessoa().getIdPessoa());
            stm.setInt(cont++, prog.getIdProgramacao());
            
            int status = stm.executeUpdate();
            
            if(status == 1){
                prog.setError(false);
                prog.setMessage("Alterado com sucesso");
            }else{
                prog.setMessage("Falha ao tentar alterar!");
            }
            mCon.disconnect();
            return !prog.isError();
        } catch (Exception e) {
            prog.setError(true);
           prog.setMessage("Falha ao tentar alterar Edicao\n\t"+ e.getMessage());
           return !prog.isError();
        }

    }
    public  ArrayList<Programacao> buscarProgramacao(Programacao prog, String opcao){
        
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            ArrayList<Programacao> progList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            
            switch(opcao){
                    case "DATA_BET":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE dataInicio between ? and ? or dataFim between ? and ? or ? and ? or ?");
                        stm.setTimestamp(1,prog.getDataInicio());
                        stm.setTimestamp(2,prog.getDataFim());
                        stm.setTimestamp(3,prog.getDataInicio());
                        stm.setTimestamp(4,prog.getDataFim());
                        stm.setInt(5, prog.getPessoa().getIdPessoa());
                        stm.setString(6, prog.getLocal());
                        break;
             
                    case "IDPROGRAMACAO":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE idProgramacao = ?");
                        stm.setInt(1, prog.getIdProgramacao());
                        break;
                    case "TITULO":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE titulo = ?");
                        stm.setString(1, prog.getTitulo());
                        break;
                       
                    case "LOCAL":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE local = ?");
                        stm.setString(1, prog.getLocal());
                        break;
                    case "DESCRICAO":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE descricao = ?");
                        stm.setString(1, prog.getDescricao());
                        break;
                    case "IDEDICAO":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE idEdicao = ?");
                        stm.setInt(1, prog.getEdicao().getIdEdicao());
                        break;
                    case "IDPESSOA":
                        stm  = con.prepareStatement("SELECT * FROM Programacao WHERE idPessoa = ?");
                        stm.setInt(1, prog.getPessoa().getIdPessoa());
                        break;
                    default: 
                    stm  = con.prepareStatement("SELECT * FROM Programacao");
                    break;
                       
            }
            
            rs = stm.executeQuery();
                    
            while (rs.next()) {
                Programacao programacao = Util.criarProgramacao(rs);
                
                progList.add(programacao);
            }
            
            mCon.disconnect();
            return progList;
            
        } catch (Exception e) {
            prog.setError(true);
            prog.setMessage("\tFalha Tecnica\n\t" + e.getMessage());
            return null;
        
        }
        
    }
    public boolean excluirProgramacao(Programacao programacao) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            int cont = 0;
            
            String query ="UPDATE Edicao SET "
                            + "idEdicao = 0 "
                    + "WHERE idEdicao = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setInt(1, programacao.getEdicao().getIdEdicao());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                cont++;
            }
            else {
                programacao.setMessage("Falha ao Excluir!");
                mCon.disconnect();
                return !programacao.isError();
            }
            
            String query1 ="UPDATE Pessoa SET "
                            + "idPessoa = 0 "
                            + "WHERE idPessoa = ?";
            
            stm.clearBatch();
            stm = con.prepareStatement(query1);
            
            stm.setInt(1, programacao.getPessoa().getIdPessoa());
            
            status = stm.executeUpdate();
          
            if(status == 1) {
                cont++;
                
            }
            else {
                programacao.setMessage("Falha ao Excluir!");
                mCon.disconnect();
                return !programacao.isError();
            }
            
            String query2 = "DELETE FROM Programacao where idProgramacao = ?";
            stm.clearBatch();
            stm = con.prepareStatement(query2);
            stm.setInt(1,programacao.getIdProgramacao());
            
            status = stm.executeUpdate();
            
            if((status == 1) && (cont == 2)) {
                programacao.setError(false);
                programacao.setMessage("Excluido com Sucesso!");
                return !programacao.isError();
            }
            else {
                programacao.setMessage("Falha ao Excluir!");
            }
            
            mCon.disconnect();
            return !programacao.isError();
        }
        catch(Exception e) {
            programacao.setError(true);
            programacao.setMessage("\tFalha Tecnica\n\t" + e.getMessage());
            return false;
        }
    }
    
}
