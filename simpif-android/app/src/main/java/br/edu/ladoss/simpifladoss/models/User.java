package br.edu.ladoss.simpifladoss.models;

import br.edu.ladoss.simpifladoss.exception.ValidationException;
import br.edu.ladoss.simpifladoss.util.StringUtil;
import br.edu.ladoss.simpifladoss.validation.EmailValidator;

/**
 * Created by Rennan on 18/10/17.
 */

public class User {

    private String email;
    protected String senha;

    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        EmailValidator emailValidator = new EmailValidator();
        if (emailValidator.validate(email))
            this.email = email;
        else {
            throw new ValidationException("Email incorreto!");
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
