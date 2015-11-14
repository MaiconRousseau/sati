/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import valueObject.Edicao;

/**
 *
 * @author Lucas
 */
public class EdicaoController {

    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    public static boolean verificaEdicao(Edicao edicao) {
        
        String mensagem = "";
        // Verifica campos vazios
        if(edicao.getTema().equals(""))
            mensagem = mensagem.concat("Tema n�o pode estar vazio\n");
        if(edicao.getTitulo().equals(""))
            mensagem = mensagem.concat("Titulo n�o pode estar vazio\n");
        if(edicao.getDataInicio() == null)
            mensagem = mensagem.concat("Data de In�cio n�o pode estar vazio\n");
        if(edicao.getDataFim() == null)
            mensagem = mensagem.concat("Data de Fim n�o pode estar vazio\n");
        if(edicao.getDataVencimentoInscricao() == null)
            mensagem = mensagem.concat("Data de Vencimento das Inscri��es n�o pode estar vazio\n");
        // N�o fazer compara��es nulas
        if(edicao.getDataInicio() != null 
                && edicao.getDataFim() != null
                && edicao.getDataInicio().after( edicao.getDataFim() ))
            mensagem = mensagem.concat("Data de Fim n�o pode ser menor que Data de In�cio\n");
  
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        edicao.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }
    
    public static void cadastrarEdicao(Edicao edicao) {
        
        // Verifica os campos da edi��o atual
        boolean verificaEdicao = EdicaoController.verificaEdicao(edicao);
        
        // Atualiza��o o status de erro do objeto
        edicao.setError(!verificaEdicao);
        
       // Os dados banc�rios precisam ser inseridos no banco de dados antes da edi��o
       DadosBancariosController.cadastrarDadosBancarios(edicao.getDadosBancarios());
       // Caso o cadastro de Dados Banc�rios n�o seja poss�vel, retornar.
       if( edicao.getDadosBancarios().isError() ) {
            // A nova mensagem dentro do objeto ser� a mensagem atual 
            // com as novas informa��es
            // Verifica de erro para quando mensagem estiver vazia
            String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
            // Unir mensagem de Dados Banc�rios com Edi��o
            String edicaoMensagemNova = edicaoMensagemAtual.concat( edicao.getDadosBancarios().getMessage() );
            edicao.setMessage(edicaoMensagemNova);
            // Acionar o erro
            edicao.setError(true);
       }
        
        // Verifica se h� um erro, se sim, retorna para com o erro
        if(edicao.isError())
            return;
        
        System.out.println("Cadastrar Edi��o");

        // Caso algum erro ocorre dentro do cadastro, retornar a informa��o
    }
    
}
