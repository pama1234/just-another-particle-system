package pama1234.gdx.game.app;

import com.badlogic.gdx.Gdx;

import pama1234.gdx.game.asset.MusicAsset;
import pama1234.gdx.game.state.state0001.State0001;
import pama1234.gdx.game.state.state0001.State0001.StateChanger;
import pama1234.gdx.game.state.state0001.StateGenerator0001;
import pama1234.gdx.util.app.ScreenCore2D;
import pama1234.gdx.util.info.MouseInfo;
import pama1234.math.Tools;

public class Screen0011 extends ScreenCore2D implements StateChanger{
  public State0001 state;
  public boolean firstRun;
  public boolean debugInfo;
  public long renderTime,updateTime;
  public boolean mute;
  public float volume=1;
  //---
  public float debugTextX,debugTextY,debugTextH,debugTextCountY;
  @Override
  public void setup() {
    noStroke();
    MusicAsset.load_init();
    StateGenerator0001.loadState0001(this);
    firstRun=!Gdx.files.local("data/firstRun.txt").exists();
    // firstRun=true;
    if(firstRun) {
      state(State0001.FirstRun);
      Gdx.files.local("data/firstRun.txt").writeString("1234",false);
    }else {
      state(State0001.Loading);
    }
  }
  @Override
  public State0001 state(State0001 in) {
    State0001 out=state;
    state=in;
    if(out!=null) {
      centerScreen.remove.add(out);
      centerCam.remove.add(out.displayCam);
      out.to(in);
      out.pause();
    }
    if(in!=null) {
      in.resume();
      in.from(state);
      centerScreen.add.add(in);
      centerCam.add.add(in.displayCam);
    }
    return out;
  }
  public State0001 stateNull() {
    State0001 out=state;
    state=null;
    if(out!=null) {
      centerScreen.list.remove(out);
      centerCam.list.remove(out.displayCam);
      out.to(null);
      out.pause();
    }
    return out;
  }
  @Override
  public void update() {}
  @Override
  public void mousePressed(MouseInfo info) {}
  @Override
  public void displayWithCam() {}
  public void drawCursor() {
    beginBlend();
    final int a=0,b=255;
    fill(mouse.left?a:b,mouse.center?a:b,mouse.right?a:b,127);
    rect(mouse.x-4,mouse.y-0.5f,8,1);
    rect(mouse.x-0.5f,mouse.y-4,1,8);
    endBlend();
  }
  @Override
  public void doDraw() {
    if(debugInfo) {
      Tools.time();
      super.doDraw();
      renderTime=Tools.period();
    }else super.doDraw();
  }
  @Override
  public void doUpdate() {
    if(debugInfo) {
      Tools.time();
      super.doUpdate();
      updateTime=Tools.period();
    }else super.doUpdate();
  }
  @Override
  public void display() {
    if(debugInfo) {
      textScale(pus/2f);
      initDebugText();
      debugText("Memory   ="+getMemory()+"Mb");
      float tf=1/frameRate;
      debugText("FrameRate="+(tf<999?getFloatString(tf,6):"???.??")+"fps "+getMillisString((int)(frameRate*1000))+"ms");
      debugText("Render   ="+getMillisString(renderTime)+"ms");
      debugText("Update   ="+getMillisString(updateTime)+"ms");
      debugText("CamScale ="+getFloatString(cam2d.scale.pos));
      textScale(pus);
    }
    if(cam.grabCursor) {
      withCam();
      drawCursor();
    }
  }
  public void initDebugText() {
    debugTextH=pu/2f;
    debugTextX=debugTextH;
    debugTextY=bu*1.5f;
    debugTextCountY=0;
  }
  public void debugText(String in) {
    text(in,debugTextX,debugTextY+debugTextH*debugTextCountY);
    debugTextCountY+=1;
  }
  public String getMillisString(long in) {
    return String.format("%03d",in);
  }
  public String getMillisString(long in,int l) {
    return String.format("%0"+l+"d",in);
  }
  public String getFloatString(float in) {
    return String.format("%05.2f",in);
  }
  public String getFloatString(float in,int l) {
    return String.format("%0"+l+".2f",in);
  }
  public String getFloatString(float in,int l,int l2) {
    return String.format("%0"+l+"."+l2+"f",in);
  }
  public String getMemory() {
    return Tools.cutToLastDigitString((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/(1024*1024));
  }
  @Override
  public void frameResized() {}
  @Override
  public void dispose() {
    stateNull();
    super.dispose();
    State0001.disposeAll();
    // State0001.exit();
  }
}
