package com.example.youkumenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.example.youkumenu.util.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout rl_level1;
    RelativeLayout rl_level2;
    RelativeLayout rl_level3;

    boolean isLevel1Display = true;
    boolean isLevel2Display = true;
    boolean isLevel3Display = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.ib_menu).setOnClickListener(this);
        findViewById(R.id.ib_home).setOnClickListener(this);
        rl_level1 = findViewById(R.id.rl_level1);
        rl_level2 = findViewById(R.id.rl_level2);
        rl_level3 = findViewById(R.id.rl_level3);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (AnimationUtils.RUNNING_ANIMATION_COUNT > 0) {
                //如果有动画正在执行，则取消当前事件
                return true;
            }
            //如果一级菜单已经显示
            if (isLevel1Display) {
                int delay = 0;
                //隐藏三级菜单
                if (isLevel3Display) {
                    AnimationUtils.rotateOutAnim(rl_level3, delay);
                    isLevel3Display= false;
                    delay += 200;
                }
                //隐藏二级菜单
                if (isLevel2Display) {
                    AnimationUtils.rotateOutAnim(rl_level2, delay);
                    isLevel2Display= false;
                    delay+=200;
                }
                //隐藏一级菜单
                AnimationUtils.rotateOutAnim(rl_level1, delay);
            } else {
                AnimationUtils.rotateInAnim(rl_level1, 0);
                AnimationUtils.rotateInAnim(rl_level2, 200);
                AnimationUtils.rotateInAnim(rl_level3, 400);
                isLevel2Display= true;
                isLevel3Display= true;
            }
            isLevel1Display = !isLevel1Display;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        if (AnimationUtils.RUNNING_ANIMATION_COUNT > 0) {
            //如果有动画正在执行，取消当前事件
            return;
        }
        switch (v.getId()) {
            case R.id.ib_home:
                if (isLevel2Display) {
                    long delay = 0;
                    if (isLevel3Display) {
                        //如果三级菜单已经显示，先转出去
                        AnimationUtils.rotateOutAnim(rl_level3, 0);
                        isLevel3Display = false;
                        delay += 200;
                    }
                    //如果二级菜单已经显示时，先转出去
                    AnimationUtils.rotateOutAnim(rl_level2, delay);
                } else {
                    //如果二级菜单没有显示，先转出来
                    AnimationUtils.rotateInAnim(rl_level2, 0);
                }
                //置反
                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:
                if (isLevel3Display) {
                    //如果三级菜单已经显示，先转出去
                    AnimationUtils.rotateOutAnim(rl_level3, 0);
                } else {
                    //如果三级菜单没有显示，先转出来
                    AnimationUtils.rotateInAnim(rl_level3, 0);
                }
                isLevel3Display = !isLevel3Display;
                break;
        }
    }
}
