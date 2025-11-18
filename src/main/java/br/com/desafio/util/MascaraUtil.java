package br.com.desafio.util;

// Classe utilitária para aplicar máscaras em strings como CPF, CEP e telefone

public class MascaraUtil {

    public static String cpf(String cpf) {
    	// Se for nulo ou tamanho diferente de 11, evita erros de substring
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0,3) + "." +
               cpf.substring(3,6) + "." +
               cpf.substring(6,9) + "-" +
               cpf.substring(9,11);
    }

    public static String cep(String cep) {
    	// Formata o CEP no padrão 00000-000
        if (cep == null || cep.length() != 8) return cep;
        return cep.substring(0,5) + "-" + cep.substring(5);
    }

    public static String telefone(String tipo, String numero) {
    	// Verifica se o número é nulo, devolve nulo
        if (numero == null) return null;

        // Celular tem 11 dígitos
        if (tipo.equalsIgnoreCase("CELULAR") && numero.length() == 11) {
            return "(" + numero.substring(0, 2) + ") " +
                    numero.substring(2, 7) + "-" +
                    numero.substring(7);
        }

        // Fixo/comercial (10 dígitos)
        if (numero.length() == 10) {
            return "(" + numero.substring(0, 2) + ") " +
                    numero.substring(2, 6) + "-" +
                    numero.substring(6);
        }

        return numero;
    }
}
