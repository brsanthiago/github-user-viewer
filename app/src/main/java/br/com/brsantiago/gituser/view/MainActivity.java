package br.com.brsantiago.gituser.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import br.com.brsantiago.gituser.R;
import br.com.brsantiago.gituser.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotToDetails();
            }
        });
    }

    private void gotToDetails() {
        Intent intent = new Intent(this, GitUserActivity.class);
        intent.putExtra("userName", binding.getUserName());
        startActivity(intent);
    }
}
