package com.csanysoft.donto.MyBaseClasses.Scene2D;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.csanysoft.donto.MyBaseClasses.InitableInterface;

/**
 * Created by tanulo on 2018. 01. 15..
 */

public class MyButton extends TextButton implements InitableInterface {

    public MyButton(String text, TextButtonStyle style) {
        super(text, style);
        init();
    }

    public void init() {
    }
}
