package br.com.brsantiago.gituser.di;

import android.content.Context;

import javax.inject.Singleton;

import br.com.brsantiago.gituser.GitUserApp;
import br.com.brsantiago.gituser.network.GitHubApi;
import br.com.brsantiago.gituser.network.GitHubService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by bruno on 09/08/17.
 */
@Module
public class GitHubModule {
    private GitUserApp app;

    public GitHubModule(GitUserApp app) {
        this.app = app;
    }

    @Provides
    public Context getApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    public GitHubApi gitHubApi() {
        return new GitHubApi();
    }

    @Provides
    @Singleton
    public GitHubService gitHubService() {
        return gitHubApi().provideApi().create(GitHubService.class);
    }
}
