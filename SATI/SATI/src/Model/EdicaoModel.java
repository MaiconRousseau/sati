/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import valueObject.Edicao;

/**
 *
 * @author jao
 */
public class ModelEdicao{
   
   
    public static void cadastrarEdicao(Edicao edicao){
        try {
                
        MySQLConnector mCon = new MySQLConnector();
        Connection con = mCon.connect();
        
        String query = "insert into Edicao( dataInicio,dataFim, dataVencimentoInscricao,"
                + "agendaDefinida, titulo, tema ,idDadosBancarios) values (?,?,?,?,?,?,?)";
            PreparedStatement stm;
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int cont = 1;
            
            stm.setDate(cont++, (java.sql.Date) edicao.getDataInicio());
            stm.setDate(cont++, (java.sql.Date) edicao.getDataFim());
            stm.setDate(cont++, (java.sql.Date) edicao.getDataVencimentoInscricao());
            stm.setBoolean(cont++, edicao.isAgendaDefinida());
            stm.setString(cont++, edicao.getTitulo());
            stm.setString(cont++, edicao.getTema());
            stm.setInt(cont++, edicao.getDadosBancarios().getIdDadosBancarios());
           
            int status = stm.executeUpdate();
            
            if(status == 1){
                edicao.setError(false);
                edicao.setMessage("Cadastrado com Sucesso!");
                
                ResultSet rs = stm.getGeneratedKeys();
                rs.next();
                
                int key = rs.getInt(1);
                System.out.println(" Key"+key);
                edicao.setIdEdicao(key);
            }
            else{
                edicao.setMessage("Falha ao cadastrar nova Edicao!");
            }
           mCon.disconnect();
        } catch (Exception e) {
           edicao.setError(true);
           edicao.setMessage("Falha ao Cadastrar Edicao\n\t"+ e.getMessage());
        }
        
    }
    public static void alterarEdicao(Edicao edicao){
        try { 
            
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            String query = "update edicao set dataInicio = ?,"
                    + "dataFim = ?,"
                    + "dataVencimentoInscricao = ?,"
                    + "agendaDefinida = ?,"
                    + "titulo = ?,"
                    + "tema = ?,"
                    + "idDadosBancarios = ?"
                    + "where idEdicao = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            int cont = 1;
            
            //Alterando
            stm.setDate(cont++, (java.sql.Date) edicao.getDataInicio());
            stm.setDate(cont++, (java.sql.Date) edicao.getDataFim());
            stm.setDate(cont++, (java.sql.Date) edicao.getDataVencimentoInscricao());
            stm.setBoolean(cont++, edicao.isAgendaDefinida());
            stm.setString(cont++, edicao.getTitulo());
            stm.setString(cont++, edicao.getTema());
            stm.setInt(cont++, edicao.getDadosBancarios().getIdDadosBancarios());
            stm.setInt(cont++, edicao.getIdEdicao());
            
            int status =  stm.executeUpdate();
            
            if(status == 1){
                
                edicao.setError(false);
                edicao.setMessage("Alterado com sucesso");
            
            } else {
                
               edicao.setMessage("Falha ao tentar alterar!");
            }
            mCon.disconnect();
        } catch (Exception e) {
             edicao.setError(true);
           edicao.setMessage("Falha ao Alterar a Edicao\n\t"+ e.getMessage());
        }
    
    }/*
    public static ArrayList<Edicao>  buscarEdicao(Edicao edicao,String tipo){
        
        try {
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            ArrayList<Edicao> listEdicao = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            
            
        } catch (Exception e) {
        }
        
        
    }
    */
}
   /*
    public void load() throws SQLException, Exception{
    
    edicoes.clear();
        
    conn = MySQLConnector.connect();
    Statement selectStat = conn.createStatement();
    
    String sqlQuery = " select idEdicao, dataInicio,datafim,dataVencimentoInscricao,"
            + "agendaDefinida, titulo, tema,idDadosBancarios from Edicao";
    
    ResultSet results = selectStat.executeQuery(sqlQuery);
    
    while (results.next()){
			
			int id = results.getInt("idEdicao");
			Date dataI = results.getDate("dataInicio");
			Date dataF = results.getDate("datafim");
			Date dataV = results.getDate("dataVencimentoInscricao");
			boolean agenda = results.getBoolean("agendaDefinida");
			String titulo = results.getString("titulo");
			String tema = results.getString("tema");
			String idBanco = results.getString("idDadosBancarios");
			
			Edicao edicao;
                        edicao = new Edicao(dataI,dataF,dataV,agenda,titulo,tema,idBanco);
			edicoes.add(edicao);
                        
                        Edicao (Date dataInicio, Date dataFim, Date dataVencimentoInscricao, 
                    boolean agendaDefinida, String titulo, String tema,
                    DadosBancarios dadosBancarios, int idEdicao) {
                    this.dataInicio = dataInicio;
                    this.dataFim = dataFim;
                    this.dataVencimentoInscricao = dataVencimentoInscricao;
                    this.agendaDefinida= agendaDefinida;
                    this.titulo = titulo;
                    this.tema = tema;
                    this.dadosBancarios = dadosBancarios;
                    this.idEdicao = idEdicao;
			
			System.out.println(person);
		}
		results.close();
		selectStatement.close();
    }
}
*/