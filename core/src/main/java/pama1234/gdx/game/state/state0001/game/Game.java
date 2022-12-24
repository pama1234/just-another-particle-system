package pama1234.gdx.game.state.state0001.game;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

import pama1234.gdx.game.app.Screen0011;
import pama1234.gdx.game.state.state0001.State0001;
import pama1234.gdx.game.state.state0001.StateGenerator0001.StateEntity0001;
import pama1234.gdx.game.state.state0001.game.player.MainPlayer2D;
import pama1234.gdx.game.state.state0001.game.world.World;
import pama1234.gdx.game.state.state0001.game.world.World0001;
import pama1234.gdx.game.state.state0001.game.world.WorldCenter;
import pama1234.gdx.game.ui.ButtonGenerator;
import pama1234.gdx.game.ui.util.Button;
import pama1234.gdx.game.ui.util.TextButton;
import pama1234.gdx.game.util.RectF;
import pama1234.gdx.util.listener.EntityListener;

public class Game extends StateEntity0001{
  public Button<?>[] menuButtons;
  public TextButton<?>[] ctrlButtons;
  public float time;
  //---
  public World0001 world;
  public WorldCenter<Screen0011,Game,World<Screen0011,Game>> worldCenter;
  public boolean debug;
  // public boolean debug;
  public boolean androidRightMouseButton;
  public EntityListener displayCamTop;
  public boolean firstInit=true;//TODO
  public Game(Screen0011 p) {
    super(p);
    menuButtons=ButtonGenerator.genButtons_0005(p);
    if(p.isAndroid) ctrlButtons=ButtonGenerator.genButtons_0007(p,this);
    worldCenter=new WorldCenter<Screen0011,Game,World<Screen0011,Game>>(p);
    worldCenter.list.add(world=new World0001(p,this));
    worldCenter.pointer=0;
    if(debug) createDebugDisplay();
  }
  public void createDebugDisplay() {
    if(displayCamTop==null) displayCamTop=new EntityListener() {
      @Override
      public void display() {
        MainPlayer2D tp=world.yourself;
        p.beginBlend();
        int bx1=tp.ctrl.bx1,
          by1=tp.ctrl.by1,
          bx2=tp.ctrl.bx2,
          by2=tp.ctrl.by2;
        int bw=world.blockWidth,bh=world.blockHeight;
        p.fill(255,127,191,191);
        p.rect(tp.ctrl.leftWall,tp.ctrl.ceiling,tp.ctrl.rightWall-tp.ctrl.leftWall,tp.ctrl.floor-tp.ctrl.ceiling);
        p.fill(127,255,191,191);
        p.rect(tp.x()+tp.dx,tp.y()+tp.dy,tp.w,tp.h);
        p.fill(94,203,234,191);
        p.rect((bx1)*world.blockWidth,by1*world.blockHeight,bw,bh);
        p.rect((bx1)*world.blockWidth,by2*world.blockHeight,bw,bh);
        p.rect((bx2)*world.blockWidth,by2*world.blockHeight,bw,bh);
        p.rect((bx2)*world.blockWidth,by1*world.blockHeight,bw,bh);
        p.endBlend();
      }
    };
  }
  @Override
  public void from(State0001 in) {
    p.backgroundColor(191);
    p.cam.noGrab();
    // tvgRefresh();
    for(Button<?> e:menuButtons) p.centerScreen.add.add(e);
    if(ctrlButtons!=null) for(Button<?> e:ctrlButtons) p.centerScreen.add.add(e);
    if(firstInit) {
      firstInit=false;
      world.init();
    }
    worldCenter.resume();
    if(debug) p.centerCam.add.add(displayCamTop);
    p.centerCam.add.add(worldCenter);
  }
  @Override
  public void to(State0001 in) {
    for(Button<?> e:menuButtons) p.centerScreen.remove.add(e);
    if(ctrlButtons!=null) for(Button<?> e:ctrlButtons) p.centerScreen.remove.add(e);
    p.centerCam.remove.add(worldCenter);
    worldCenter.pause();
    if(debug) p.centerCam.remove.add(displayCamTop);
  }
  @Override
  public void displayCam() {
    // p.beginBlend();
    // p.image(ImageAsset.background,-288,-162);
    // p.imageBatch.flush();
    // p.endBlend();
    worldCenter.displayCam();
    // MainPlayer2D tp=world.yourself;
  }
  @Override
  public void display() {
    // Block block=world.regions.getBlock(UtilMath.floor(p.mouse.x/world.blockHeight),UtilMath.floor(p.mouse.y/world.blockWidth));
    // p.text(block==null?"null":block.type.name,p.width/2,p.height/2);
    //--------------------------------
    // MainPlayer2D tp=world.yourself;
    // p.text(tp.x()+" "+tp.y()+" "+tp.groundLevel,p.width/2,p.height/2);
    // Block block=tp.getBlock(tp.blockX(),tp.blockY());
    // p.text(block==null?"null":block.type.name,p.width/2,p.height/2+p.bu);
    // p.text("vel.y "+tp.point.vel.y,p.width/2,p.height/2);
    if(debug) {
      p.beginBlend();
      p.fill(94,203,234,127);
      for(RectF e:world.yourself.ctrl.cullRects) p.rect(e.x(),e.y(),e.w(),e.h());
      p.endBlend();
    }
  }
  @Override
  public void update() {
    time+=p.frameRate;
  }
  @Override
  public void frameResized(int w,int h) {}
  @Override
  public void keyReleased(char key,int keyCode) {
    if(keyCode==ESCAPE) p.state(State0001.StartMenu);
  }
  @Override
  public void exit() {
    world.exit();
  }
}