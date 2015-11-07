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
import java.util.ArrayList;
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
        
            String query = "insert into Programacao( dataHoraInicio,dataHoraFim, custo,"
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
            
            mCon.disconnect();

        } catch (Exception e) {
           prog.setError(true);
           prog.setMessage("Falha ao Cadastrar Edicao\n\t"+ e.getMessage());
        }
    }
    public static void alterarProgramacao(Programacao prog){
        
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
        
            String query = "update Programacao set dataHorarioInicio=?,dataHorarioFim=?, custo=?,"
                + "custoResponsavel=?, local=?, titulo=?, descricao=?, capacidade=?, cargaHoraria=?"
                + "valorInscricao=?, idEdicao=?, idPessoa=? where idProgramacao=?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
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
            stm.setInt(cont++, prog.getIdProgramacao());
            
            int status = stm.executeUpdate();
            
            if(status == 1){
                prog.setError(false);
                prog.setMessage("Alterado com sucesso");
            }else{
                prog.setMessage("Falha ao tentar alterar!");
            }
            mCon.disconnect();
        } catch (Exception e) {
            prog.setError(true);
           prog.setMessage("Falha ao tentar alterar Edicao\n\t"+ e.getMessage());
        }
    }
    public static ArrayList<Programacao> buscarProgramacao(Programacao prog, String opcao){
        
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            ArrayList<Programacao> progList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm = null;
            
            switch(opcao){
                    
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
                Programacao programacaVO = Util.criarProgramacao(rs);
                
                progList.add(programacaVO);
            }
            
            mCon.disconnect();
            return progList;
            
        } catch (Exception e) {
            prog.setError(true);
            prog.setMessage("\tFalha Tecnica\n\t" + e.getMessage());
            return null;
        
        }
        
    }
    
}
