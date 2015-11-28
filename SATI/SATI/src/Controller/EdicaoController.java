/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.EdicaoModel;
import java.util.ArrayList;
import valueObject.Edicao;

/**
 *
 * @author Lucas
 */
public class EdicaoController {
    
    private final EdicaoModel edicaoModel = new EdicaoModel();
    
    public enum Verifica {
        CADASTRAR, ALTERAR
    }

    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    private boolean verificarAtributos(Edicao edicao, Verifica verifica) {
        
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
        
        if(edicao.getDataInicio() != null 
                && edicao.getDataVencimentoInscricao() != null
                && edicao.getDataVencimentoInscricao().after( edicao.getDataInicio() ))
            mensagem = mensagem.concat("Data de Vencimento da Inscri��o n�o "
                    + "pode ser maior que Data de In�cio\n");
        
        // Existe uma edi��o no mesmo ano
        if(verifica == Verifica.CADASTRAR) {
            if( this.buscarEdicaoExistente(edicao, "ANO") )
                mensagem = mensagem.concat("N�o pode haver dois eventos no mesmo ano.\n");
            if (edicao.isAgendaDefinida())
                mensagem = mensagem.concat("Agenda n�o pode estar definida no momento de cadastro.\n");
        }
  
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        edicao.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }
    
    // Retorna verdadeiro se for poss�vel cadastrar
    public boolean cadastrarEdicao(Edicao edicao) {
        
        boolean verifica = this.verificarAtributos(edicao, Verifica.CADASTRAR);
        
        if(!verifica) {
            edicao.setError(true);
            // Algum dado informado � inv�lido
            //System.out.println(edicao.getMessage());
            return false;
        }
        
        boolean res = edicaoModel.cadastrarEdicao(edicao);
        //System.out.println(edicao.getMessage());
        return res;
        
    }
    
    public boolean alterarEdicao(Edicao edicao) {
        
        boolean verifica = this.verificarAtributos(edicao, Verifica.ALTERAR);
        
        if(!verifica) {
            edicao.setError(true);
            
            // Algum dado informado � inv�lido
            return false;
        }
        
        boolean res = edicaoModel.alterarEdicao(edicao);
        System.out.println(edicao.getMessage());
        return res;
    }
    
    public boolean buscarEdicaoExistente(Edicao edicao, String tipo) {
        
        ArrayList<Edicao> newList = this.buscarEdicao(edicao, tipo);
        return !(newList == null || newList.isEmpty());
        
    }
    
    public ArrayList<Edicao> buscarEdicao(Edicao edicao, String tipo) {
        
        ArrayList<Edicao> newList = this.edicaoModel.buscarEdicao(edicao, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
        if(newList == null || edicao.isError())
        {
            edicao.setError(true);
            edicao.setMessage("Erro!");
            //System.out.println("ERRO");
            return null;
        }
        
        if( newList.isEmpty() ) {
           //System.out.println("Nenhum item foi encontrado!");
           edicao.setMessage("Nenhum item foi encontrado!"); 
        }
        
        return newList;
        
    }

    public boolean excluirEdicao(Edicao edicao, String tipo) {
        
        return edicaoModel.excluirEdicao(edicao, tipo);
    }
    
}