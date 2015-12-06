/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ProgramacaoModel;
import java.util.ArrayList;
import valueObject.Programacao;

/**
 *
 * @author jao
 */
public class ProgramacaoController {
    
    private ProgramacaoModel progM = new ProgramacaoModel();
    
    public enum VerificaP {
        CADASTRAR, ALTERAR
    }
    private boolean verificarCampos(Programacao prog, VerificaP verifica){
       String msg = "";
              
       if(prog.getCusto() < 0){
           msg.concat("Custo n?o pode ser negativo!\n");
           
       }
       if(prog.getCustoResponsavel() < 0){
           msg.concat("Custo por responsavel nao pode ser negativo!\n");
           
       }
       if(prog.getEdicao() == null){
           msg.concat("N?o existe edi?ao cadastrada para adicionar esta programacao!\n");
        
       }
       if(prog.getPessoa() == null){
           msg.concat("Uma pessoa deve ser responsavel pelo evento!\n");
       
       }
       if(prog.getLocal() == null){
           msg.concat("Um local deve ser especificado!\n");
         
       }
       if(prog.getValorInscricao() < 0){
           msg.concat("valor de inscricao n?o pode ser negativo!\n");
           
       }
       if(prog.getCapacidade() <= 0){
           msg.concat("A capacidade deve ser maior que 0!\n");
           
       }
       if(prog.getTitulo() == null || prog.getTitulo().equals("")){
           msg.concat("O titulo da programacao deve ser informado!\n");
          
       }
       if(prog.getDescricao() == null || prog.getDescricao().equals("")){
           msg.concat("Descricao cont?m o tipo  de programacao e deve ser informado!\n");
           
       }
       if(prog.getDataInicio().after(prog.getDataFim())){
           msg.concat("Data de Inicio n?o pode ser Maior que Data final da programacao \n");
           
       }
       if((prog.getDataInicio() == null) || (prog.getDataFim() == null)){
           msg.concat("Data nao podem ser vazias \n");
           
       } 
       if(prog.getDataInicio() != null && prog.getDataFim() != null && prog.getEdicao().getDataFim() != null
                && prog.getDataInicio().after( prog.getEdicao().getDataFim() )){
           msg.concat("Data de Inicio da programacao não pode ser apos o termino da ediçao\n");
         
       }
       
     if(verifica == VerificaP.CADASTRAR) {
            if( this.verificaRestricoes(prog, "DATA_BET") )
                msg = msg.concat("Conflito entre horarios, sala ou responsavel.\n");
            if (prog.getEdicao().isAgendaDefinida())
                msg = msg.concat("Agenda não pode estar definida no momento de cadastro de programacao.\n");
      }
     
     String edicaoMensagemAtual = ( prog.getMessage() == null )? "": prog.getMessage();
     String edicaoMensagemNova = edicaoMensagemAtual.concat(msg);
     prog.setMessage(edicaoMensagemNova);
        return msg.equals("");
    }
    public boolean cadastrarProgramacao(Programacao prog){
        boolean verifica = this.verificarCampos(prog, VerificaP.CADASTRAR);
        
        if(!verifica){
            prog.setError(true);
            return false;
        }
        boolean res = progM.cadastrarProgramacao(prog); 
        System.out.println(prog.getMessage());
        return  res;
       
    }
    public boolean verificaRestricoes(Programacao prog, String tipo){
        ArrayList<Programacao> newList =  progM.buscarProgramacao(prog, tipo);
        return !(newList == null || newList.isEmpty());
    }
    
    public ArrayList<Programacao> buscarProgramacao(Programacao prog, String tipo) {
        
        ArrayList<Programacao> newList = progM.buscarProgramacao(prog, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(newList == null || prog.isError())
        {
            prog.setError(true);
            prog.setMessage("Erro!");
            //System.out.println("ERRO");
            return null;
        }
        
        if( newList.isEmpty() ) {
           //System.out.println("Nenhum item foi encontrado!");
           prog.setMessage("Nenhum item foi encontrado!"); 
        }
        
        return newList;
        
    }
    public boolean excluirProgramacao(Programacao prog){
        
        return progM.excluirProgramacao(prog);
    }
}
