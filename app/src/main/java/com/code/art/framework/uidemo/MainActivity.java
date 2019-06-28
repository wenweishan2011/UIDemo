package com.code.art.framework.uidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.code.art.framework.uidemo.animation.ObjectAnimatorActivity;
import com.code.art.framework.uidemo.animation.ValueAnimatorActivity;

/**
 * @author wenws
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.valueAnimator).setOnClickListener(this);
        findViewById(R.id.objectAnimator).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.valueAnimator:
                startActivity(new Intent(this, ValueAnimatorActivity.class));
                break;
            case R.id.objectAnimator:
                startActivity(new Intent(this, ObjectAnimatorActivity.class));
                break;
            default:
        }
    }
}
