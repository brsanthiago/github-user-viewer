package br.com.brsantiago.gituser.network;

import java.util.ArrayList;

import br.com.brsantiago.gituser.domain.Owner;
import br.com.brsantiago.gituser.domain.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bruno on 09/08/17.
 */

public interface GitHubService {
    @GET("users/{username}/repos")
    Call<ArrayList<Repository>> findRepositories(@Path("username") final String username);
    @GET("users/{username}")
    Call<Owner> findOwner(@Path("username") final String username);
}
