package com.example.myportafolio;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        backgroundAnimation();

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            startButtonAnimationLogin(() -> {
                Intent intent = new Intent(HomeActivity.this, LoginActivty.class);
                startActivity(intent);
            });
        });

        btnRegister.setOnClickListener(v -> {
            startButtonAnimationRegister(() -> {
                Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intent);
            });
        });
    }

    public void backgroundAnimation() {
        ConstraintLayout layout = findViewById(R.id.MenuLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) layout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    private void startButtonAnimationLogin(Runnable onAnimationEnd) {
        btnLogin.animate().scaleX(1.1f).scaleY(1.1f).setDuration(190).withEndAction(() -> {
            btnLogin.animate().scaleX(1.0f).scaleY(1.0f).setDuration(190).withEndAction(onAnimationEnd);
        });
    }

    private void startButtonAnimationRegister(Runnable onAnimationEnd) {
        btnRegister.animate().scaleX(1.1f).scaleY(1.1f).setDuration(190).withEndAction(() -> {
            btnRegister.animate().scaleX(1.0f).scaleY(1.0f).setDuration(190).withEndAction(onAnimationEnd);
        });
    }
}
