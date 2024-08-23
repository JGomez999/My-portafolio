package com.example.myportafolio.Presenters;

import android.animation.Animator;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.myportafolio.Models.LoginModel;
import com.example.myportafolio.R;
import com.example.myportafolio.Views.LoginContract;
import com.google.firebase.auth.FirebaseUser;

// LoginPresenter.java
public class LoginPresenter {
    private LoginContract.View view;
    private LoginModel model;
    private LayoutInflater layoutInflater;
    private ConstraintLayout mainLayout;

    public LoginPresenter(LoginContract.View view, LoginModel model, LayoutInflater layoutInflater, ConstraintLayout mainLayout) {
        this.view = view;
        this.model = model;
        this.layoutInflater = layoutInflater;
        this.mainLayout = mainLayout;
    }

    public void loginUser() {
        String email = view.getEmail();
        String password = view.getPassword();

        // mainLayout is already initialized in the constructor

        if (email.isEmpty() || password.isEmpty()) {
            view.showToast("Por favor, completa todos los campos");
            return;
        }

        model.loginUser(email, password, new LoginModel.LoginCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                showAnimation(R.layout.loading_success, R.id.loadingAnimationSuccess, 1500, () -> {
                    view.showToast("Inicio de sesión exitoso");
                    view.navigateToHome();
                });
            }

            @Override
            public void onFailure(Exception e) {
                showAnimation(R.layout.loading_failed, R.id.loadingAnimationFailed, 1500, () -> {
                    view.showToast("Se ingresaron credenciales incorrectas");
                    showRegisterLayout();
                });
            }
        });
    }

    private void showRegisterLayout() {
        mainLayout.removeAllViews();
        View loginView = layoutInflater.inflate(R.layout.activity_login, mainLayout, false);
        mainLayout.addView(loginView);
    }

    private void showAnimation(int layoutId, int animationViewId, long duration, Runnable onAnimationEnd) {
        mainLayout.removeAllViews();
        View animationView = layoutInflater.inflate(layoutId, mainLayout, false);
        mainLayout.addView(animationView);

        LottieAnimationView animation = animationView.findViewById(animationViewId);
        animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(onAnimationEnd, duration);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        });
    }
}