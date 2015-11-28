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
    
    // Fun��o verifica os dados do objeto. Se sim, retorna true, se n�o retorna false
    // Altera a mensagem do objeto para especificar os erros
    private boolean verificarAtributos(Pessoa pessoa) {
        
        String mensagem = "";
        if(pessoa.getNome().equals(""))
            mensagem = mensagem.concat("Nome n�o pode estar vazio\n");
        //if(pessoa.getRg().equals("") || pessoa.getRg().equals("12.345.678-X"))
        if(pessoa.getTipo().equals(""))
            mensagem = mensagem.concat("Tipo n�o pode estar vazio\n");
        
        if(pessoa.getRg().equals(""))
            mensagem = mensagem.concat("RG n�o pode estar vazio\n");
        if(pessoa.getCpf().equals(""))
            mensagem = mensagem.concat("CPF n�o pode estar vazio\n");
        
        if(pessoa.getEmail().equals(""))
            mensagem = mensagem.concat("Email n�o pode estar vazio\n");
        else {
            if(!validar( pessoa.getEmail() ))
                mensagem = mensagem.concat("Email informado � inv�lido\n");
        }
        
        if( pessoa.getInstituicao().equals("") &&  pessoa.getRa().equals(""))
            mensagem = mensagem.concat("Institui��o ou RA devem ser informados\n");
        
        if( !pessoa.getInstituicao().equals("") &&  !pessoa.getRa().equals(""))
            mensagem = mensagem.concat("Institui��o e RA n�o podem existir ao mesmo tempo\n");

        String cpf = pessoa.getCpf().replaceAll("(\\.|-)", "");
       
        boolean validaCPF = ValidaCPF.isCPF(cpf);
        if(!validaCPF)
            mensagem = mensagem.concat("CPF informado � inv�lido\n");
        
        // A nova mensagem dentro do objeto ser� a mensagem atual 
        // com as novas informa��es
        // Verifica de erro para quando mensagem estiver vazia
        String mensagemAtual = ( pessoa.getMessage() == null )? "": pessoa.getMessage();
        String mensagemNova = mensagemAtual.concat(mensagem);
        pessoa.setMessage(mensagemNova);
        
        
        // Caso alguma regra n�o tenha sido cumprida, h� erro e a mensagem n�o � vazia
        return mensagem.equals("");
    }

    public boolean cadastrarPessoa(Pessoa pessoa) {
        
        boolean verifica = this.verificarAtributos(pessoa);
        
        if(!verifica) {
            pessoa.setError(true);
            System.out.println(pessoa.getMessage());
            // Algum dado informado � inv�lido
            return false;
        }
        boolean res = pessoaM.cadastrarPessoa(pessoa);
        
        System.out.println(pessoa.getMessage());
        return res;
    }
    
    public static boolean validar(String email){
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
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
    
    // Objeto1 (da Classe) tem os crit�rios da busca, caso algum existe
    // Objeto2 (String) especifica o tipo da pesquisa
    public ArrayList<Pessoa>  buscarPessoa(Pessoa pessoa, String tipo) {
        
        
        ArrayList<Pessoa> newList = pessoaM.buscarPessoa(pessoa, tipo);
        
        // Se n�o houve nenhum erro na pesquisa, e ainda assim, a lista est� vazia
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
            // Algum dado informado � inv�lido
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