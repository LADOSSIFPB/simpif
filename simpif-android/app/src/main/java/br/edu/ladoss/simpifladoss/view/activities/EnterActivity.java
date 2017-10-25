package br.edu.ladoss.simpifladoss.view.activities;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.edu.ladoss.simpifladoss.R;
import br.edu.ladoss.simpifladoss.models.User;
import br.edu.ladoss.simpifladoss.mvp.EnterMVP;
import br.edu.ladoss.simpifladoss.mvp.presenter.EnterPresenterImp;
import br.edu.ladoss.simpifladoss.util.PreferencesUtils;
import br.edu.ladoss.simpifladoss.validation.Validate;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterActivity extends AppCompatActivity implements EnterMVP.View{

    private EnterMVP.Presenter presenter;

    @BindView(R.id.identificadorEdit)
    EditText identificadorEditText;
    @BindView(R.id.passwordEdit)
    EditText senhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        presenter = new EnterPresenterImp(this);

        ButterKnife.bind(this);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.getString("email") != null) {
            identificadorEditText.setText(bundle.getString("email"));
            senhaEditText.requestFocus();

            showMessage("Login inválido.");
        }
    }

    @OnClick(R.id.loginButton)
    public void login() {
        if(isValid()) presenter.login(new User(identificadorEditText.getText().toString().trim(), senhaEditText.getText().toString()));
    }

    private boolean isValid() {
        /*TODO Refact deve ser feito pois a validação não deve ser feita na camada de view.
         Tente levar para o presenter pois podemos fazer os testes unitários da validação separados*/
        String identificador = identificadorEditText.getText().toString();
        Boolean validated = Validate.validaIdentificador(identificador);

        if (!validated) {
            identificadorEditText.setError("E-mail inválido.");
            identificadorEditText.setFocusable(true);
            identificadorEditText.requestFocus();
            return false;
        }

        String senha = senhaEditText.getText().toString();

        if (senha == null || senha.isEmpty()) {
            senhaEditText.setError("Senha inválida.");
            senhaEditText.setFocusable(true);
            senhaEditText.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(this.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public AppCompatActivity get() {
        return this;
    }
}
