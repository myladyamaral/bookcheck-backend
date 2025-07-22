package br.com.bookcheck.util;

import java.util.regex.Pattern;

public class AppUtils {

    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{11}$");
    private static final Pattern CNPJ_PATTERN = Pattern.compile("^\\d{14}$");
    private static final Pattern CEP_PATTERN = Pattern.compile("^\\d{8}$");


    public static boolean isValidCpf(String cpf) {
        String cleanedCpf = removeMaskCpf(cpf);

        // Verifica se tem 11 dígitos
        if (!CPF_PATTERN.matcher(cleanedCpf).matches()) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cleanedCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        return isValidCpfDigits(cleanedCpf);
    }

    public static String removeMaskCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private static boolean isValidCpfDigits(String cpf) {
        int[] numbers = cpf.chars().map(Character::getNumericValue).toArray();

        // Calcula o primeiro dígito verificador
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += numbers[i] * (10 - i);
        }
        int digit1 = (sum1 * 10) % 11;
        if (digit1 == 10) digit1 = 0;

        // Calcula o segundo dígito verificador
        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += numbers[i] * (11 - i);
        }
        int digit2 = (sum2 * 10) % 11;
        if (digit2 == 10) digit2 = 0;

        // Verifica se os dígitos calculados são iguais aos fornecidos
        return numbers[9] == digit1 && numbers[10] == digit2;
    }
    public static boolean isValidCnpj(String cnpj) {
        String cleanedCnpj = removeMaskCnpj(cnpj);

        // Verifica se tem 14 dígitos
        if (!CNPJ_PATTERN.matcher(cleanedCnpj).matches()) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cleanedCnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        return isValidCnpjDigits(cleanedCnpj);
    }

    public static String removeMaskCnpj(String cnpj) {
        return cnpj.replaceAll("\\D", "");
    }

    private static boolean isValidCnpjDigits(String cnpj) {
        int[] numbers = cnpj.chars().map(Character::getNumericValue).toArray();

        // Calcula o primeiro dígito verificador
        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum1 = 0;
        for (int i = 0; i < 12; i++) {
            sum1 += numbers[i] * weights1[i];
        }
        int digit1 = sum1 % 11;
        digit1 = digit1 < 2 ? 0 : 11 - digit1;

        // Calcula o segundo dígito verificador
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum2 = 0;
        for (int i = 0; i < 13; i++) {
            sum2 += numbers[i] * weights2[i];
        }
        int digit2 = sum2 % 11;
        digit2 = digit2 < 2 ? 0 : 11 - digit2;

        // Verifica se os dígitos calculados são iguais aos fornecidos
        return numbers[12] == digit1 && numbers[13] == digit2;
    }
    /**
     * Valida um CEP brasileiro
     * @param cep O CEP a ser validado (com ou sem máscara)
     * @return true se o CEP for válido
     */
    public static boolean isValidCep(String cep) {
        String cleanedCep = removeMaskCep(cep);

        // Verifica se tem 8 dígitos
        if (!CEP_PATTERN.matcher(cleanedCep).matches()) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (CEP não pode ser 00000000, 11111111, etc.)
        if (cleanedCep.matches("(\\d)\\1{7}")) {
            return false;
        }

        return true;
    }

    /**
     * Remove a máscara do CEP (qualquer caractere não numérico)
     * @param cep O CEP com máscara
     * @return CEP apenas com dígitos
     */
    public static String removeMaskCep(String cep) {
        return cep.replaceAll("\\D", "");
    }

    /**
     * Formata um CEP no padrão 00000-000
     * @param cep O CEP sem máscara (deve ter 8 dígitos)
     * @return CEP formatado
     * @throws IllegalArgumentException se o CEP não tiver 8 dígitos
     */
    public static String formatCep(String cep) {
        String cleanedCep = removeMaskCep(cep);
        if (cleanedCep.length() != 8) {
            throw new IllegalArgumentException("CEP deve conter 8 dígitos");
        }
        return cleanedCep.substring(0, 5) + "-" + cleanedCep.substring(5);
    }

    public static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}
