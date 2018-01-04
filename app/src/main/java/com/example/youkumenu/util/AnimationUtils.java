package com.example.youkumenu.util;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by 12191 on 2017/12/27.
 */

public class AnimationUtils {

    public static int RUNNING_ANIMATION_COUNT = 0;  //正在运行的动画个数

    public static void rotateOutAnim(RelativeLayout layout, long delay) {
        RotateAnimation ra = new RotateAnimation(
                0f, -180f,   //开始、结束的角度，逆时针
                Animation.RELATIVE_TO_SELF, 0.5f,  //指定旋转中心x值
                Animation.RELATIVE_TO_SELF, 1f   //指定旋转中心y值
        );
        ra.setDuration(500);
        ra.setFillAfter(true);  //设置动画停留在结束位置
        ra.setStartOffset(delay);   //设置动画开始延时
        ra.setAnimationListener(new MyAnimationListener());
        layout.startAnimation(ra);
    }

    public static void rotateInAnim(RelativeLayout layout, long delay) {
        RotateAnimation ra = new RotateAnimation(
                -180f, 0f,   //开始、结束的角度，顺时针
                Animation.RELATIVE_TO_SELF, 0.5f,  //指定旋转中心x值
                Animation.RELATIVE_TO_SELF, 1f   //指定旋转中心y值
        );
        ra.setDuration(500);
        ra.setFillAfter(true);  //设置动画停留在结束位置
        ra.setStartOffset(delay);   //设置动画开始延时
        ra.setAnimationListener(new MyAnimationListener());
        layout.startAnimation(ra);
    }

    static class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            RUNNING_ANIMATION_COUNT++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            RUNNING_ANIMATION_COUNT--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
