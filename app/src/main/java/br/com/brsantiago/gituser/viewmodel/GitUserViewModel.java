package br.com.brsantiago.gituser.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.brsantiago.gituser.di.GitHubComponent;
import br.com.brsantiago.gituser.domain.Owner;
import br.com.brsantiago.gituser.domain.Repository;
import br.com.brsantiago.gituser.network.GitHubService;
import br.com.brsantiago.gituser.view.GitUserActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by bruno on 09/08/17.
 */

public class GitUserViewModel extends ViewModel implements GitHubComponent.Injectable {
    @Inject
    GitHubService gitHubService;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Repository>> repositories = new MutableLiveData<>();
    private MutableLiveData<Owner> ownerMutableLiveData = new MutableLiveData<>();

    @Override
    public void inject(GitHubComponent component) {
        component.inject(this);
    }

    public void findUserRepositories(final String userName) {
        gitHubService.findRepositories(userName).enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Call<ArrayList<Repository>> call, Response<ArrayList<Repository>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Repository> list = response.body();
                    repositories.setValue(list);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Repository>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void findOwner(final String userName) {
        loading.setValue(true);
        gitHubService.findOwner(userName).enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
                if (response.isSuccessful()) {
                        ownerMutableLiveData.setValue(response.body());
                }
                updateStatus();
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {
                updateStatus();
                t.printStackTrace();
            }
        });
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
    public LiveData<ArrayList<Repository>> getRepositories() {
        return repositories;
    }

    protected void updateStatus() {
        loading.setValue(false);
        getLoading();
    }

    public LiveData<Owner> getOnwer() {
        return ownerMutableLiveData;
    }
}
