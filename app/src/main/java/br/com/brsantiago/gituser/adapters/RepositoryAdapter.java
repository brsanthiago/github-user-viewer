package br.com.brsantiago.gituser.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import br.com.brsantiago.gituser.adapters.callbacks.RecyclerCallback;

/**
 * Created by bruno on 10/08/17.
 */

public class RepositoryAdapter<T, VM extends ViewDataBinding> extends RecyclerView.Adapter<RepositoryAdapter.RecyclerViewHolder> {
    private final Context context;
    private ArrayList<T> items;
    private int layoutId;
    private RecyclerCallback<VM, T> bindingInterface;

    public RepositoryAdapter(Context context, ArrayList<T> items, int layoutId, RecyclerCallback<VM, T> bindingInterface) {
        this.items = items;
        this.context = context;
        this.layoutId = layoutId;
        this.bindingInterface = bindingInterface;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        VM binding;

        public RecyclerViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public void bindData(T model) {
            bindingInterface.bindData(binding, model);
        }

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.RecyclerViewHolder holder, int position) {
        T item = items.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }
}
