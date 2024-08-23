package com.example.myportafolio;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myportafolio.Models.RegistroModel;
import com.example.myportafolio.Presenters.RegistroPresenter;
import com.example.myportafolio.Views.RegistroContract;


public class RegisterActivity extends AppCompatActivity implements RegistroContract.View {


    private EditText etName, etEmail, etPassword;
    private Button btnRegister, btnBack;
    private RegistroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backgroundAnimation();

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);

        presenter = new RegistroPresenter(this, new RegistroModel());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.registerUser();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                finish(); // Cierra esta actividad para que el usuario no pueda volver atrás
            }
        });
    }

    @Override
    public void showToast(String message) {
        // Mostrar un mensaje de tostada
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearInputFields() {
        // Limpiar los campos de entrada
        etName.setText("");
        etEmail.setText("");
        etPassword.setText("");
    }

    @Override
    public void navigateToLogin() {
        // Navegar a la actividad de inicio de sesión
        Intent intent = new Intent(this, LoginActivty.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String getName() {
        // Obtener el nombre ingresado por el usuario
        return etName.getText().toString().trim();
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

    public void backgroundAnimation() {
        ConstraintLayout layout = findViewById(R.id.RegisterLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }
}