package br.com.brsantiago.gituser.domain;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import br.com.brsantiago.gituser.BR;

/**
 * Created by bruno on 09/08/17.
 */

public class Repository extends BaseObservable {
    private String name;
    private String description;
    private String language ;
    private Owner owner;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
    }

    @Bindable
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        notifyPropertyChanged(BR.owner);
    }
}
