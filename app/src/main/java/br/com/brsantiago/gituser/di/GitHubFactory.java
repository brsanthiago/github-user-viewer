package br.com.brsantiago.gituser.di;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import br.com.brsantiago.gituser.GitUserApp;

/**
 * Created by bruno on 09/08/17.
 */

public class GitHubFactory extends ViewModelProvider.NewInstanceFactory {
    private GitUserApp app;

    public GitHubFactory(GitUserApp app) {
        this.app = app;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T generic = super.create(modelClass);
        if (generic instanceof GitHubComponent.Injectable) {
            ((GitHubComponent.Injectable)generic).inject(app.getComponent());
        }
        return generic;
    }
}
