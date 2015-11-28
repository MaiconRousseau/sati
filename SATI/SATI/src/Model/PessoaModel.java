/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Connector.MySQLConnector;
import Controller.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaModel {
    
    public boolean cadastrarPessoa(Pessoa pessoa){
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "INSERT INTO Pessoa("
                    + "nome, tipo, ra, "
                    + "email, instituicao, cpf, rg"
                    + ")"
                    + "values(?, ?, ?, ?, ?, ?, ?)");
            
            PreparedStatement stm;
            // Statement.RETURN_GENERATED_KEYS retorna os ID gerados pelo Statement no BD
            stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int cont = 1;
            
            stm.setString(cont++, pessoa.getNome());
            stm.setString(cont++, pessoa.getTipo());
            stm.setString(cont++, pessoa.getRa());
            stm.setString(cont++, pessoa.getEmail());
            stm.setString(cont++, pessoa.getInstituicao());
            stm.setString(cont++, pessoa.getCpf());
            stm.setString(cont++, pessoa.getRg());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Cadastrado com Sucesso!");
                // Reebendo o ID recentemente adicionado no BD
                ResultSet keys = stm.getGeneratedKeys();    
                keys.next();  
                
                int key = keys.getInt(1);
                //System.out.println(key);
                // Adicionando pro Objeto o devido ID
                pessoa.setIdPessoa(key);
            }
            else {
                pessoa.setError(true);
                pessoa.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
            return !pessoa.isError();
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return false;
        }
    
    }
    
    public ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {

        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // List of Buses
            ArrayList<Pessoa> newList = new ArrayList<>();
            
            ResultSet rs;
            PreparedStatement stm;
            
            switch(tipo) {
                case "ID":
                    stm = con.prepareStatement("SELECT * FROM Pessoa where idPessoa = ?");
                    System.out.println("ID PESSOA:"+pessoa.getIdPessoa());
                    stm.setInt(1, pessoa.getIdPessoa());
                    break;
                default: 
                    stm  = con.prepareStatement("SELECT * FROM Pessoa");
                    break;
            }

            rs = stm.executeQuery();
                    
            while (rs.next()) {
                Pessoa pessoaVO = Util.criarPessoa(rs);
                
                if(pessoaVO == null) {
                    
                    SQLException e = new SQLException();
                    throw e;
                }
                
                newList.add(pessoaVO);
            }
            
            mCon.disconnect();
            return newList;
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            //System.out.println(e.getMessage());
            return null;
        }
    }
/*
    public static void alterarPessoa(Pessoa pessoa) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            // UPDATE automovel SET modelo = focuss WHERE idAutomovel = ?;
            
            if(!pessoa.isStatus())
            {
                
            int multas_pendentes = Util.multasPendentesCount(pessoa);
            
            if(multas_pendentes > 0) {
                pessoa.setError(true);
                pessoa.setMessage("Há multas pendentes neste veículo\n");
                return;
            }
            }
            
            String query = 
                    "UPDATE pessoa SET "
                            + "nome = ?, "
                            + "cpf = ?, "
                            + "rg = ?, "
                            + "orgaoEmissor = ?, "
                            + "rgEstado = ?, "
                            + "dataNascimento = ?, "
                            + "logradouro = ?, "
                            + "numeroLogradouro = ?, "
                            + "complementoLogradouro = ?, "
                            + "bairro = ? ,"
                            + "cidade = ? ,"
                            + "estado = ? ,"
                            + "cep = ? ,"
                            + "nomeMae = ?, "
                            + "nomePai = ?, "
                            + "status = ? "
                    + "WHERE idPessoa = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setString(1, pessoa.getNome());
            stm.setString(2, pessoa.getCpf());
            stm.setString(3, pessoa.getRg());
            stm.setString(4, pessoa.getOrgaoEmissor());
            stm.setString(5, pessoa.getRgEstado());
            
            Timestamp dataNascimento = new Timestamp(pessoa.getDataNascimento().getTime());
            stm.setTimestamp(6, dataNascimento);
            
            stm.setString(7, pessoa.getLogradouro());
            stm.setString(8, pessoa.getNumeroLogradouro());
            stm.setString(9, pessoa.getComplementoLogradouro());
            stm.setString(10, pessoa.getBairro());
            stm.setString(11, pessoa.getCidade());
            stm.setString(12, pessoa.getEstado());
            stm.setString(13, pessoa.getCep());
            
            stm.setString(14, pessoa.getNomeMae());
            stm.setString(15, pessoa.getNomePai());
            
            stm.setBoolean(16, pessoa.isStatus());
            
            stm.setInt(17, pessoa.getIdPessoa());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Alterado com Sucesso!");
            }
            else {
                pessoa.setError(true);
                pessoa.setMessage("Falha ao Alterar!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    public static void excluirPessoa(Pessoa pessoa) {
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            int multas_pendentes = Util.multasPendentesCount(pessoa);
            if(multas_pendentes > 0) {
                pessoa.setError(true);
                pessoa.setMessage("Há multas pendentes nesta pessoa: " + pessoa + "\n");
                return;
            }
                 
            // SQL que vai ser executada
            // UPDATE automovel SET status = false WHERE idAutomovel = ?
            String query = 
                    "UPDATE pessoa SET "
                            + "status = false "
                    + "WHERE idPessoa = ?";
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            
            stm.setInt(1, pessoa.getIdPessoa());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Excluído com Sucesso!");
            }
            else {
                pessoa.setMessage("Falha ao Excluir!");
            }
            
            mCon.disconnect();
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
        }
    }
    */

    public boolean alterarPessoa(Pessoa pessoa) {
        
        try {
            // Connect with database
            MySQLConnector mCon = new MySQLConnector();
            Connection con = mCon.connect();
            
            // SQL que vai ser executada
            String query = (
                    "UPDATE Pessoa SET "
                    + "nome = ?, "
                    + "tipo = ?, "
                    + "ra = ?, "
                    + "email = ?, "
                    + "instituicao = ?, "
                    + "cpf = ?, "
                    + "rg = ?"
                    + "WHERE idPessoa = ?");
            
            
            PreparedStatement stm;
            stm = con.prepareStatement(query);
            int cont = 1;
            
            stm.setString(cont++, pessoa.getNome());
            stm.setString(cont++, pessoa.getTipo());
            stm.setString(cont++, pessoa.getRa());
            stm.setString(cont++, pessoa.getEmail());
            stm.setString(cont++, pessoa.getInstituicao());
            stm.setString(cont++, pessoa.getCpf());
            stm.setString(cont++, pessoa.getRg());
            
            stm.setInt(cont++, pessoa.getIdPessoa());
            
            // Confere se alguma linha do BD foi modificada
            int status = stm.executeUpdate();
            
            if(status == 1) {
                pessoa.setError(false);
                pessoa.setMessage("Cadastrado com Sucesso!");
            }
            else {
                pessoa.setError(true);
                pessoa.setMessage("Falha ao Cadastrar!");
            }
            
            mCon.disconnect();
            return !pessoa.isError();
        }
        catch(Exception e) {
            pessoa.setError(true);
            pessoa.setMessage("\tFalha Técnica\n\t" + e.getMessage());
            return false;
        }
    }
}
