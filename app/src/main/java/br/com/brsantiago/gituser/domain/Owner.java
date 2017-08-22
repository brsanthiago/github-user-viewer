package br.com.brsantiago.gituser.domain;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.gson.annotations.SerializedName;
import br.com.brsantiago.gituser.BR;

/**
 * Created by bruno on 09/08/17.
 */

public class Owner extends BaseObservable {
    @SerializedName("name")
    private String userName;
    @SerializedName("avatar_url")
    private String avatar;

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }
}
