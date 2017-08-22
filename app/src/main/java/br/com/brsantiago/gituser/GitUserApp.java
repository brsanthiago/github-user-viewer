package br.com.brsantiago.gituser;

import android.app.Application;

import br.com.brsantiago.gituser.di.DaggerGitHubComponent;
import br.com.brsantiago.gituser.di.GitHubComponent;
import br.com.brsantiago.gituser.di.GitHubModule;

/**
 * Created by bruno on 09/08/17.
 */

public class GitUserApp extends Application {
    private GitHubComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerGitHubComponent.builder().gitHubModule(new GitHubModule(this)).build();
    }

    public GitHubComponent getComponent() {
        return component;
    }
}
