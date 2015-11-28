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

    // Função verifica os dados do objeto. Se sim, retorna true, se não retorna false
    // Altera a mensagem do objeto para especificar os erros
    private boolean verificarAtributos(Edicao edicao, Verifica verifica) {
        
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
        
        if(edicao.getDataInicio() != null 
                && edicao.getDataVencimentoInscricao() != null
                && edicao.getDataVencimentoInscricao().after( edicao.getDataInicio() ))
            mensagem = mensagem.concat("Data de Vencimento da Inscrição não "
                    + "pode ser maior que Data de Início\n");
        
        // Existe uma edição no mesmo ano
        if(verifica == Verifica.CADASTRAR) {
            if( this.buscarEdicaoExistente(edicao, "ANO") )
                mensagem = mensagem.concat("Não pode haver dois eventos no mesmo ano.\n");
            if (edicao.isAgendaDefinida())
                mensagem = mensagem.concat("Agenda não pode estar definida no momento de cadastro.\n");
        }
  
        
        // A nova mensagem dentro do objeto será a mensagem atual 
        // com as novas informações
        // Verifica de erro para quando mensagem estiver vazia
        String edicaoMensagemAtual = ( edicao.getMessage() == null )? "": edicao.getMessage();
        String edicaoMensagemNova = edicaoMensagemAtual.concat(mensagem);
        edicao.setMessage(edicaoMensagemNova);
        
        
        // Caso alguma regra não tenha sido cumprida, há erro e a mensagem não é vazia
        return mensagem.equals("");
    }
    
    // Retorna verdadeiro se for possível cadastrar
    public boolean cadastrarEdicao(Edicao edicao) {
        
        boolean verifica = this.verificarAtributos(edicao, Verifica.CADASTRAR);
        
        if(!verifica) {
            edicao.setError(true);
            // Algum dado informado é inválido
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
            
            // Algum dado informado é inválido
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
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
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