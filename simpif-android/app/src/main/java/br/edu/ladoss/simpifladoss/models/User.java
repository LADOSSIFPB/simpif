package br.edu.ladoss.simpifladoss.models;

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
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
