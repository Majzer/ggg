package com.csanysoft.donto.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.csanysoft.donto.GlobalClasses.Assets;
import com.csanysoft.donto.MyBaseClasses.Game.InitableInterface;

/**
 * Created by tanulo on 2017. 10. 27..
 */

public class MySlider extends Slider implements InitableInterface{
    public MySlider(float min, float max, float stepSize, boolean vertical, Skin skin) {
        super(min, max, stepSize, vertical, skin);
        init();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    String units = " units";

    public MySlider(float min, float max, float stepSize, boolean vertical, Skin skin, String styleName) {
        super(min, max, stepSize, vertical, skin, styleName);
        init();
    }

    public MySlider(float min, float max, float stepSize, boolean vertical, SliderStyle style) {
        super(min, max, stepSize, vertical, style);
        init();
    }
    public Label.LabelStyle getLabelStyle() {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
        style.fontColor = Color.RED;
        return style;
    }
    @Override
    public void init() {
        final Label label = new Label("",getLabelStyle()){

            public float elapsedTime= 0;

            @Override
            public void setVisible(boolean visible) {
                super.setVisible(visible);
                if (visible){
                    elapsedTime = 0;
                }
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                elapsedTime += delta;
                if (elapsedTime>2f){
                    setVisible(false);
                }
            }
        };

        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (getStage()!=null && !getStage().getActors().contains(label, true)) {
                    getStage().addActor(label);
                }
                label.setVisible(true);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //label.setVisible(false);
                super.touchUp(event, x, y, pointer, button);
            }
        });
        //getStage().addActor(label);
        addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                label.setTouchable(Touchable.disabled);
                label.setText(((float)((int)(getValue()*10)))/10f + " " + units);
                label.setPosition(getX()+getPercent()*getWidth()-40, getY()-getHeight()-20);
                label.setVisible(true);
            }
        });
    }

}
