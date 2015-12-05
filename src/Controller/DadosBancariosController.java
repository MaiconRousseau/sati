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
        
        if (    dadosBancarios.getAgencia() == null ||
                dadosBancarios.getConta() == null ||
                dadosBancarios.getTipo() == null ||
                dadosBancarios.getAgencia().equals("") ||
                dadosBancarios.getConta().equals("") ||
                dadosBancarios.getTipo().equals("")
                )
            return false;
        
        return dadosBancarios.getCarteira() >= 0;
    }

    public boolean cadastrarDadosBancarios(DadosBancarios dadosBancarios) {

        
        // Atualização o status de erro do objeto
        if(!this.verificarAtributos(dadosBancarios)) {
            return false;
        }
        
        return dbModel.cadastrarDadosBancarios(dadosBancarios);
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
