package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import valueObject.DadosBancarios;
import valueObject.Edicao;

/**
 *
 * @author Lucas
 */
public class DadosBancariosModel {

    public boolean cadastrarDadosBancarios(DadosBancarios dadosBancarios) {
       
       try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "insert into DadosBancarios("
                            + "agencia, conta, tipo, carteira"
                            + ") "
                    + " values(?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, dadosBancarios.getAgencia() );
            stm.setString(2, dadosBancarios.getConta() );
            stm.setString(3, dadosBancarios.getTipo() );
            stm.setInt(4, dadosBancarios.getCarteira() );
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                dadosBancarios.setError(false);
                dadosBancarios.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                //System.out.println(key);
                // Adicionando pro Objeto o devido ID
                dadosBancarios.setIdDadosBancarios(key);
            }
            else {
                dadosBancarios.setError(true);
                dadosBancarios.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
            return !dadosBancarios.isError();
        }
        catch(Exception e) {
            dadosBancarios.setError(true);
            dadosBancarios.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return false;
        }
    }

    public ArrayList<DadosBancarios> buscarDadosBancarios(DadosBancarios dadosBancarios, String tipo) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            ArrayList<DadosBancarios> newList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            
            int cont = 1;
            switch(tipo) {
                case "ID":
                    stm  = con.prepareStatement("SELECT * FROM DadosBancarios "
                            + "WHERE idDadosBancarios = ?");
                    stm.setInt(cont++, dadosBancarios.getIdDadosBancarios());
                    break;
                default: 
                    stm  = con.prepareStatement("SELECT * FROM DadosBancarios");
                    break;
            }
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                DadosBancarios dadosBancariosVO = Util.criarDadosBancarios(rs);
                
                if(dadosBancariosVO == null) {
                    
                    SQLException e = new SQLException();
                    throw e;
                }
                newList.add(dadosBancariosVO);
                
            }
            mCon.disconnect();
            return newList;
        }
        catch(Exception e) {
            dadosBancarios.setError(true);
            dadosBancarios.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            System.out.println(e.getMessage());
            return null;
        }
    }
}