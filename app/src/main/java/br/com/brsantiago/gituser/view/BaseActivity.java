package br.com.brsantiago.gituser.view;

import android.arch.lifecycle.LifecycleActivity;

import br.com.brsantiago.gituser.GitUserApp;

/**
 * Created by bruno on 09/08/17.
 */

public class BaseActivity extends LifecycleActivity {

    public GitUserApp app () {
        return (GitUserApp) getApplication();
    }
}
