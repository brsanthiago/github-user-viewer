package br.com.brsantiago.gituser.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import br.com.brsantiago.gituser.R;
import br.com.brsantiago.gituser.adapters.RepositoryAdapter;
import br.com.brsantiago.gituser.adapters.callbacks.RecyclerCallback;
import br.com.brsantiago.gituser.adapters.decorator.SimpleDividerItemDecoration;
import br.com.brsantiago.gituser.databinding.ActivityGitUserBinding;
import br.com.brsantiago.gituser.databinding.RepositoryItemBinding;
import br.com.brsantiago.gituser.di.GitHubFactory;
import br.com.brsantiago.gituser.domain.Owner;
import br.com.brsantiago.gituser.domain.Repository;
import br.com.brsantiago.gituser.viewmodel.GitUserViewModel;

public class GitUserActivity extends BaseActivity  implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    private GitUserViewModel gitUserViewModel;
    private ActivityGitUserBinding binding;
    private RepositoryAdapter<Repository, RepositoryItemBinding> repositoryAdapter;

    private String userName;
    private boolean isShow = false;
    private int scrollRange = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_git_user);
        gitUserViewModel = ViewModelProviders.of(this, new GitHubFactory(app())).get(GitUserViewModel.class);
        userName = getIntent().getStringExtra("userName");
        gitUserViewModel.findOwner(userName);
        gitUserViewModel.findUserRepositories(userName);
        validContent();
        loadRepositories();
        bindOwner();
        binding.toolbar.setTitle(null);
        binding.toolbar.setNavigationOnClickListener(this);

        binding.appBar.addOnOffsetChangedListener(this);
    }

    protected void validContent() {
        gitUserViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                binding.setIsLoading(aBoolean);
            }
        });
    }
    protected void loadRepositories() {
        gitUserViewModel.getRepositories().observe(this, new Observer<ArrayList<Repository>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Repository> repositories) {
                bindAdapter(repositories);
            }
        });
    }

    protected void bindAdapter(final ArrayList<Repository> repositories) {
        repositoryAdapter = new RepositoryAdapter<>(this, repositories, R.layout.repository_item, new RecyclerCallback<RepositoryItemBinding, Repository>() {
            @Override
            public void bindData(RepositoryItemBinding binder, Repository model) {
                binder.setRepository(model);
            }
        });
        binding.contentIncluded.recycleView.setNestedScrollingEnabled(false);
        binding.contentIncluded.recycleView.setAdapter(repositoryAdapter);
        binding.contentIncluded.recycleView.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    protected void bindOwner() {
        gitUserViewModel.getOnwer().observe(this, new Observer<Owner>() {
            @Override
            public void onChanged(@Nullable Owner owner) {
                if (owner != null) {
                    binding.setOwner(owner);
                }
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (scrollRange == -1)
            scrollRange = appBarLayout.getTotalScrollRange();

        if (scrollRange + verticalOffset == 0) {
            isShow = true;
        } else if (isShow) {
            isShow = false;
        }
        binding.setIsExpanded(isShow);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
