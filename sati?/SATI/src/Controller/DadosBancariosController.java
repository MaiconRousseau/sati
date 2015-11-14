/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import valueObject.DadosBancarios;

/**
 *
 * @author Lucas
 */
public class DadosBancariosController {

    public static boolean verificaDadosBancarios(DadosBancarios dadosBancarios) {
        
        
        String mensagem = "";
        // Verifica campos vazios
        if(dadosBancarios.getAgencia().equals(""))
            mensagem = mensagem.concat("Agência não pode estar vazio\n");
        if(dadosBancarios.getConta().equals(""))
            mensagem = mensagem.concat("Conta não pode estar vazio\n");
        if(dadosBancarios.getTipo().equals(""))
            mensagem = mensagem.concat("Tipo de Conta não pode estar vazio\n");
        /*
        // Criar alguma regra da carteira se necessário
        if(dadosBancarios.getCarteira() == 0)
            mensagem = mensagem.concat("Carteira não pode estar vazio\n");
        */
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( dadosBancarios.getMessage() == null )? "": dadosBancarios.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        dadosBancarios.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }

    public static void cadastrarDadosBancarios(DadosBancarios dadosBancarios) {
        
        // Verifica os campos dos dados bancários atuais
        boolean verificaDadosBancarios = DadosBancariosController.verificaDadosBancarios(dadosBancarios);
        
        // Atualização o status de erro do objeto
        dadosBancarios.setError(!verificaDadosBancarios);
       
        // Verifica se há um erro, se sim, retorna para com o erro
        if(dadosBancarios.isError())
            return;
        
        System.out.println("Cadastrar Dados Bancários");
    }
    
}
