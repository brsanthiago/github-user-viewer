package br.com.brsantiago.gituser.adapters.callbacks;

import android.databinding.ViewDataBinding;

/**
 * Created by bruno on 10/08/17.
 */

public interface RecyclerCallback<VM extends ViewDataBinding, T> {
    void bindData(VM binder, T model);
}
