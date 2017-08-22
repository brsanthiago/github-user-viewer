package br.com.brsantiago.gituser.bindings;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.brsantiago.gituser.R;
import br.com.brsantiago.gituser.utils.CircleTransform;

/**
 * Created by bruno on 12/08/17.
 */

public class GitBinding {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            new Picasso.Builder(imageView.getContext())
                    .build()
                    .load(url)
                    .transform(new CircleTransform())
                    .error(R.drawable.git_logo)
                    .into(imageView);
        }
    }
}
