/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DadosBancariosModel;
import java.util.ArrayList;
import valueObject.DadosBancarios;

/**
 *
 * @author Lucas
 */
public class DadosBancariosController {
    
    private final DadosBancariosModel dbModel = new DadosBancariosModel();

    public boolean verificarAtributos(DadosBancarios dadosBancarios) {
        
        
        String mensagem = "";
        // Verifica campos vazios
        if(dadosBancarios.getAgencia().equals(""))
            mensagem = mensagem.concat("Agência não pode estar vazio\n");
        if(dadosBancarios.getConta().equals(""))
            mensagem = mensagem.concat("Conta não pode estar vazio\n");
        if(dadosBancarios.getTipo().equals(""))
            mensagem = mensagem.concat("Tipo de Conta não pode estar vazio\n");
        
        if(dadosBancarios.getCarteira() < 0)
            mensagem = mensagem.concat("Entrada Inválida para Carteira\n");
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( dadosBancarios.getMessage() == null )? "": dadosBancarios.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        dadosBancarios.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }

    public boolean cadastrarDadosBancarios(DadosBancarios dadosBancarios) {
        
        // Verifica os campos dos dados bancários atuais
        boolean verifica = this.verificarAtributos(dadosBancarios);
        
        // Atualização o status de erro do objeto
        if(!verifica) {
            dadosBancarios.setError(true);
            // Algum dado informado é inválido
            //System.out.println(edicao.getMessage());
            return false;
        }
        
        System.out.println("Cadastrar Dados Bancários");
        
        boolean res = dbModel.cadastrarDadosBancarios(dadosBancarios);
        return res;
    }


    public boolean buscarDadosBancariosExistente(DadosBancarios dadosBancarios, String tipo) {
        ArrayList<DadosBancarios> newList = this.buscarDadosBancarios(dadosBancarios, tipo);
        return !(newList == null || newList.isEmpty());
    }
    
    public ArrayList<DadosBancarios> buscarDadosBancarios(DadosBancarios dadosBancarios, String tipo) {
        
        ArrayList<DadosBancarios> newList = this.dbModel.buscarDadosBancarios(dadosBancarios, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(newList == null || dadosBancarios.isError())
        {
            dadosBancarios.setError(true);
            dadosBancarios.setMessage("Erro!");
            //System.out.println("ERRO");
            return null;
        }
        
        if( newList.isEmpty() ) {
           //System.out.println("Nenhum item foi encontrado!");
           dadosBancarios.setMessage("Nenhum item foi encontrado!"); 
        }
        
        return newList;
        
    }
    
}