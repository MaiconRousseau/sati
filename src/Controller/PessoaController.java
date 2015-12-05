/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Addons.ValidaCPF;
import Model.PessoaModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.MaskFormatter;
import valueObject.Pessoa;

/**
 *
 * @author Lucas
 */
public class PessoaController {
    
    PessoaModel pessoaM = new PessoaModel();
    
    // Função verifica os dados do objeto. Se sim, retorna true, se não retorna false
    // Altera a mensagem do objeto para especificar os erros
    private boolean verificarAtributos(Pessoa pessoa) {
        
        
        if(     pessoa.getNome() == null ||  pessoa.getTipo() == null ||
                pessoa.getRg() == null || pessoa.getCpf() == null ||
                pessoa.getEmail() == null || 
                ( pessoa.getRa() == null && pessoa.getInstituicao() == null ) ||
                
                pessoa.getNome().equals("") || pessoa.getTipo().equals("") ||
                pessoa.getRg().equals("") || pessoa.getCpf().equals("") ||
                pessoa.getEmail().equals("") ||
                ( pessoa.getInstituicao().equals("") &&  pessoa.getRa().equals("") )
                ) 
        {
            return false;
        }
        else {
            
            if(     !validarEmail( pessoa.getEmail())
                )
                return false;
            
            if( !pessoa.getRa().equals("") && !pessoa.getRa().matches("[0-9]{7}")
                )
                return false;
            
            pessoa.setInstituicao("UTFPR");
            
            String tipo = pessoa.getTipo();
            if (    !tipo.equalsIgnoreCase("aluno") &&
                    !tipo.equalsIgnoreCase("professor") &&
                    !tipo.equalsIgnoreCase("servidor") &&
                    !tipo.equalsIgnoreCase("outros")
                    )
                return false;
            
            //String cpf = pessoa.getCpf().replaceAll("(\\.|-)", "");
            boolean validaCPF = ValidaCPF.isCPF(pessoa.getCpf());
            
            return validaCPF;
        }
        
        
    }

    public boolean cadastrarPessoa(Pessoa pessoa) {
        
        if(!this.verificarAtributos(pessoa)) 
            return false;
        
        return pessoaM.cadastrarPessoa(pessoa);
    }
    
    public static boolean validarEmail(String email){
        boolean isEmailIdValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isEmailIdValid = true;
        }
        return isEmailIdValid;
    }
    
    
    private String format(Object value) { 
        String pattern = "###.###.###-##";
        
        MaskFormatter mask; 
        try { 
            mask = new MaskFormatter(pattern); 
            mask.setValueContainsLiteralCharacters(false); 
            return mask.valueToString(value); 
        } catch (ParseException e) { 
            return "ERROR";
        } 
    }
    
    // Objeto1 (da Classe) tem os critérios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {
        
        
        ArrayList<Pessoa> newList = pessoaM.buscarPessoa(pessoa, tipo);
        
        // Se não houve nenhum erro na pesquisa, e ainda assim, a lista está vazia
        if(!pessoa.isError() && newList == null)
        {
            pessoa.setError(true);
            pessoa.setMessage("Erro!");
            return null;
        }
        
        if( newList.isEmpty() ) {
           //System.out.println("Nenhum item foi encontrado!");
           pessoa.setMessage("Nenhum item foi encontrado!"); 
        }
        
        return newList;
    }
    
    

    public boolean alterarPessoa(Pessoa pessoa) {
        boolean verifica = this.verificarAtributos(pessoa);
        
        if(!verifica) {
            pessoa.setError(true);
            System.out.println(pessoa.getMessage());
            // Algum dado informado é inválido
            return false;
        }
        boolean res = pessoaM.alterarPessoa(pessoa);
        
        System.out.println(pessoa.getMessage());
        return res;
        
    }
/*
    public static void excluirPessoa(Pessoa pessoa) {
        PessoaModel.excluirPessoa(pessoa);
    }
    
    // Tes

    public static int buscaIDPessoa(ArrayList<Pessoa> pessoaList, Pessoa pessoa) {
        
        int id = -1;
        for (int i = 0; i < pessoaList.size(); i ++)
        {
            Pessoa pessoaVO = (Pessoa) pessoaList.get(i);
            if( pessoaVO.getIdPessoa() == pessoa.getIdPessoa() ){
                id = i;
                break;
            }
            
        }
        //System.out.println("id " + id);
        return id;
    }
    */
}
