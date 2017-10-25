package br.edu.ladoss.simpifladoss.validation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*; // Esse import static é para facilitar no uso dos métodos "assert"

/**
 * Created by juan on 25/10/17.
 */

public class EmailValidatorTest {
    private String[] CORRECT_EMAILS = {
            "juan.barros@ccc.ufcg.edu.br",
            "ju@gmail.com",
            "j.a@gmail.cr"
    };
    private String[] INCORRECT_EMAILS = {
            "%abdias05@gmail.com",
            "",
            " @gmail.com",
            "@gmail.com",
            "Juan!Ba@gmail.com",
            "juan@gmail.",
            "juan@gmail.br."
    };

    EmailValidator validator;

    /*
     * Esse método será chamado toda vez que for executar um teste. Ele é usado
     * para criar o objeto do zero para que não exista efeito colateral no objeto
     * Ex: Pessoa recebe o nome Juan e no meio de um teste ele passa a ser José.
     * Com o "Before" ele sempre vai criar o objeto antes.
     */
    @Before
    public void prepara(){
        validator = new EmailValidator();
    }

    /* Cada método que tiver essa anotação "Test" vai executar separadamente para
        que tenhamos a certeza de que cada método funcionará corretamente. Cada teste
        é utilizado para UM método da classe. Você pode chamar outros métodos para certificar
        que o resultado é o esperado, contudo o foco desse teste está em testar UM método apenas.
     */
    @Test
    public void testValidate() {
        for (String email: CORRECT_EMAILS) {
            assertTrue(validator.validate(email)); //Aqui estamos dizendo que esperamos que esse método retorne TRUE
        }
        for (String email: INCORRECT_EMAILS) {
            assertFalse(validator.validate(email)); //Aqui estamos dizendo que esperamos que esse método retorne FALSE
        }
        // Se em algum caso ele não retornar o que esperamos, é lançada uma exception e veremos o caso que está incorreto nos testes
    }
}
