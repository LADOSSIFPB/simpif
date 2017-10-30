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
        presenter.login(new User(identificadorEditText.getText().toString().trim(), senhaEditText.getText().toString()));
    }

    @Override
    public void setInvalidEmail() {
        identificadorEditText.setError(getString(R.string.invalidEmail));
        identificadorEditText.setFocusable(true);
        identificadorEditText.requestFocus();
    }

    @Override
    public void setInvalidPassword() {
        senhaEditText.setError(getString(R.string.invalidPass));
        senhaEditText.setFocusable(true);
        senhaEditText.requestFocus();
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
