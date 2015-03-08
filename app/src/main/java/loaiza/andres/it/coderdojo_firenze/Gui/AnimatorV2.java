package loaiza.andres.it.coderdojo_firenze.Gui;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Tommaso Garuglieri on 22/12/2014.
 */

public class AnimatorV2 {

    private Context context;
    private AnimationListenerV2 listener;

    public AnimatorV2(Context context, AnimationListenerV2 listener) {
        this.context = context;
        this.listener = listener;
    }

    public AnimatorV2(Context context) {
        this.context = context;
    }

    public void startAnimation(View view, int resAnimation) {
        Animation animation = AnimationUtils.loadAnimation(this.context, resAnimation);
        this.setupListener(animation);
        view.clearAnimation();
        view.startAnimation(animation);
    }

    public void startAnimation(View view, int resAnimation, int duration) {
        Animation animation = AnimationUtils.loadAnimation(this.context, resAnimation);
        this.setupListener(animation);
        animation.setDuration(duration);
        view.clearAnimation();
        view.startAnimation(animation);
    }

    private void setupListener(Animation animation) {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (listener != null)
                    listener.onAnimationFinished();
            }
        });
    }

    public interface AnimationListenerV2 {
        public abstract void onAnimationFinished();
    }

}
