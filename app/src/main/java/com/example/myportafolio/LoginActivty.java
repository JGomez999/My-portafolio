package com.example.myportafolio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myportafolio.Models.LoginModel;
import com.example.myportafolio.Presenters.LoginPresenter;
import com.example.myportafolio.Views.LoginContract;

public class LoginActivty extends AppCompatActivity implements LoginContract.View {

    private Button btnRegister, btnLogin;
    private EditText etEmail, etPassword;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backgroundAnimation();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        ConstraintLayout mainLayout = findViewById(R.id.LoginLayout);

        presenter = new LoginPresenter(this, new LoginModel(), getLayoutInflater(), mainLayout);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginUser();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToRegistration();
            }
        });

        // En LoginActivity.java
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }



    @Override
    public void navigateToHome() {
        // Navegar a la actividad principal después de iniciar sesión
        Intent intent = new Intent(this, ChatActivity.class); // Assuming MainActivity is the main activity
        startActivity(intent);
        finish();
    }


    @Override
    public String getEmail() {
        // Obtener el correo electrónico ingresado por el usuario
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        // Obtener la contraseña ingresada por el usuario
        return etPassword.getText().toString().trim();
    }

    private void navigateToRegistration() {
        Intent intent = new Intent(LoginActivty.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void backgroundAnimation() {
        ConstraintLayout layout = findViewById(R.id.LoginLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}