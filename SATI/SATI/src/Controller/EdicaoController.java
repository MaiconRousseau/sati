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

    // Função verifica os dados do objeto. Se sim, retorna true, se não retorna false
    // Altera a mensagem do objeto para especificar os erros
    public static boolean verificaEdicao(Edicao edicao) {
        
        String mensagem = "";
        // Verifica campos vazios
        if(edicao.getTema().equals(""))
            mensagem = mensagem.concat("Tema não pode estar vazio\n");
        if(edicao.getTitulo().equals(""))
            mensagem = mensagem.concat("Titulo não pode estar vazio\n");
        if(edicao.getDataInicio() == null)
            mensagem = mensagem.concat("Data de Início não pode estar vazio\n");
        if(edicao.getDataFim() == null)
            mensagem = mensagem.concat("Data de Fim não pode estar vazio\n");
        if(edicao.getDataVencimentoInscricao() == null)
            mensagem = mensagem.concat("Data de Vencimento das Inscrições não pode estar vazio\n");
        // Não fazer comparações nulas
        if(edicao.getDataInicio() != null 
                && edicao.getDataFim() != null
                && edicao.getDataInicio().after( edicao.getDataFim() ))
            mensagem = mensagem.concat("Data de Fim não pode ser menor que Data de Início\n");
  
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        edicao.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
    public static void cadastrarEdicao(Edicao edicao) {
        
        // Verifica os campos da edição atual
        boolean verificaEdicao = EdicaoController.verificaEdicao(edicao);
        
        // Atualização o status de erro do objeto
        edicao.setError(!verificaEdicao);
        
       // Os dados bancários precisam ser inseridos no banco de dados antes da edição
       DadosBancariosController.cadastrarDadosBancarios(edicao.getDadosBancarios());
       // Caso o cadastro de Dados Bancários não seja possível, retornar.
       if( edicao.getDadosBancarios().isError() ) {
            // A nova mensagem dentro do objeto será a mensagem atual 
            // com as novas informações
            // Verifica de erro para quando mensagem estiver vazia
            String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
            // Unir mensagem de Dados Bancários com Edição
            String edicaoMensagemNova = edicaoMensagemAtual.concat( edicao.getDadosBancarios().getMessage() );
            edicao.setMessage(edicaoMensagemNova);
            // Acionar o erro
            edicao.setError(true);
       }
        
        // Verifica se há um erro, se sim, retorna para com o erro
        if(edicao.isError())
            return;
        
        System.out.println("Cadastrar Edição");

        // Caso algum erro ocorre dentro do cadastro, retornar a informação
    }
    
}
