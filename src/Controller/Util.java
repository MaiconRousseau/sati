/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import valueObject.DadosBancarios;
import valueObject.Edicao;
import valueObject.Pessoa;
import valueObject.Programacao;

/**
 *
 * @author Lucas
 */
public class Util {
        public static Programacao criarProgramacao(ResultSet rs) {
            Programacao prog = null;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            int idProgramacao = rs.getInt("idProgramacao");
            Timestamp dataInicio = rs.getTimestamp("dataInicio");
            double custo = rs.getDouble("custo");
            double custoResponsavel = rs.getDouble("custoResponsavel");
            String local = rs.getString("local");
            String titulo = rs.getString("titulo");
            String descricao = rs.getString("descricao");
            int capacidade = rs.getInt("capacidade");
            Timestamp dataFim = rs.getTimestamp("dataFim");
            double valorInscricao = rs.getDouble("valorInscricao");
            int idEdicao = rs.getInt("idEdicao");
            int idPessoa = rs.getInt("idPessoa");
            
            Pessoa pessoa = getPessoaID(idPessoa);//Retorna a pessoa responsavel
            Edicao edicao = getEdicaoID(idEdicao);//Retorna a ediç?o
            
            prog = new Programacao(idProgramacao, 
                    dataInicio, custo, custoResponsavel, local, titulo, 
                    descricao, capacidade, dataFim, valorInscricao, 
                    edicao, pessoa);
           
            return prog;
        }
        catch(Exception e) {
           
            prog.setError(true);
            prog.setMessage(e.getMessage());
            return null;
        }
    }
        
    
    private static Pessoa getPessoaID(int idPessoa) { 
        PessoaController pessoaC = new PessoaController();
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(idPessoa);
        
        ArrayList<Pessoa> newList = pessoaC.buscarPessoa(pessoa, "ID");
        
        pessoa = (newList == null || newList.isEmpty())? null: newList.get(0);
        
        return pessoa;
    }

    private static Edicao getEdicaoID(int idEdicao) { 
        EdicaoController edicaoC = new EdicaoController();
        Edicao edicao = new Edicao();
        edicao.setIdEdicao(idEdicao);
        
        ArrayList<Edicao> newList = edicaoC.buscarEdicao(edicao, "ID");
        
        edicao = (newList == null || newList.isEmpty())? null: newList.get(0);
        
        return edicao;
    }
        
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
    
            edicao = new Edicao (dataInicio, dataFim, 
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
        
        DadosBancariosController dbM = new DadosBancariosController();
        DadosBancarios db = new DadosBancarios();
        db.setIdDadosBancarios(idDadosBancarios);
        
        ArrayList<DadosBancarios> newList = dbM.buscarDadosBancarios(db, "ID");
        
        db = (newList == null || newList.isEmpty())? null: newList.get(0);
        
        return db;
    }

    public static DadosBancarios criarDadosBancarios(ResultSet rs) {
        DadosBancarios dadosBancarios;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            
            int idDadosBancarios = rs.getInt(1);
            String agencia = rs.getString(2);
            String conta = rs.getString(3);
            String tipo = rs.getString(4);
            int carteira = rs.getInt(5);
            
            dadosBancarios = new DadosBancarios (agencia, conta, tipo, carteira,
                            idDadosBancarios);
            
            return dadosBancarios;
        }
        catch(Exception e) {
            
            dadosBancarios = new DadosBancarios();
            dadosBancarios.setError(true);
            dadosBancarios.setMessage(e.getMessage());
            
            //System.out.println(dadosBancarios.getMessage());
            
            return null;
        }
    }

    public static Pessoa criarPessoa(ResultSet rs) {
        Pessoa pessoa;
        try {
            // Criando objeto para receber os dados preenchidos na tela
            
            int cont = 0;
            int idPessoa = rs.getInt(cont++);
            String nome = rs.getString(cont++);
            String tipo = rs.getString(cont++);
            String ra = rs.getString(cont++);
            String email = rs.getString(cont++);
            String instituicao = rs.getString(cont++);
            String cpf = rs.getString(cont++);
            String rg = rs.getString(cont++);
            
            
            
            pessoa = new Pessoa(idPessoa, nome, tipo, ra, email, instituicao, 
                    cpf, rg);
        
            return pessoa;
        }
        catch(Exception e) {
            pessoa = new Pessoa();
            pessoa.setError(true);
            pessoa.setMessage(e.getMessage());
            return null;
        }
    }
    
}
