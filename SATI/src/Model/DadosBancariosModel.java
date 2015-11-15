
package Model;

import Connector.MySQLConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import valueObject.DadosBancarios;

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
    
}
