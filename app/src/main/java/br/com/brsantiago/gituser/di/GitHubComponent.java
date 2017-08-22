package br.com.brsantiago.gituser.di;

import javax.inject.Singleton;

import br.com.brsantiago.gituser.viewmodel.GitUserViewModel;
import dagger.Component;

/**
 * Created by bruno on 09/08/17.
 */

@Singleton
@Component(modules = {GitHubModule.class})
public interface GitHubComponent {

    void inject(GitUserViewModel viewModel);

    interface Injectable {
        void inject(GitHubComponent component);
    }
}
