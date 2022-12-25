package pama1234.gdx.game.state.state0001.game.entity.util;

import pama1234.gdx.game.state.state0001.game.entity.LivingEntity;
import pama1234.gdx.game.state.state0001.game.region.block.Block;

public class MovementLimitBox extends OuterBox{
  public boolean inAir;
  public float floor,leftWall,rightWall,ceiling;
  public MovementLimitBox(LivingEntity p) {
    super(p);
  }
  public void constrain() {
    if(p.point.pos.y>floor) {
      p.point.vel.y=0;
      p.point.pos.y=floor;
    }
    if(p.point.pos.y<ceiling) {
      if(p.point.vel.y<0) p.point.vel.y=0;
      p.point.pos.y=ceiling;
    }
    if(p.point.pos.x<leftWall) p.point.pos.x=leftWall;
    if(p.point.pos.x>rightWall) p.point.pos.x=rightWall;
  }
  public void updateInAir() {
    inAir=p.point.pos.y<floor;
  }
  public void updateLimit() {
    if(inAir&&p.point.vel.y>0) h+=1;
    Block block;
    flagCache=false;
    //------------------------------------------ floor
    for(int i=0;i<=w;i++) {
      block=p.getBlock(x1+i,y2+1);
      if(!Block.isEmpty(block)) {
        flagCache=true;
        break;
      }
    }
    if(flagCache) {
      floor=(y2+1)*p.pw.blockHeight;
      flagCache=false;
    }else floor=(y2+4)*p.pw.blockHeight;
    //------------------------------------------ left
    // for(int i=inAir?-1:0;i<=bh;i++) {
    for(int i=0;i<=h;i++) {
      block=p.getBlock(x1-1,y1+i);
      if(!Block.isEmpty(block)) {
        flagCache=true;
        break;
      }
    }
    if(flagCache) {
      leftWall=(x1+0.5f)*p.pw.blockWidth+1;
      flagCache=false;
    }else leftWall=(x1-4)*p.pw.blockWidth;
    //------------------------------------------ right
    for(int i=0;i<=h;i++) {
      block=p.getBlock(x2+1,y1+i);
      if(!Block.isEmpty(block)) {
        flagCache=true;
        break;
      }
    }
    if(flagCache) {
      rightWall=(x2+0.5f)*p.pw.blockWidth-1;
      flagCache=false;
    }else rightWall=(x2+4)*p.pw.blockWidth;
    //------------------------------------------ ceiling
    for(int i=0;i<=w;i++) {
      block=p.getBlock(x1+i,y1-1);
      if(!Block.isEmpty(block)) {
        flagCache=true;
        break;
      }
    }
    if(flagCache) {
      ceiling=y1*p.pw.blockHeight+p.type.h;
      flagCache=false;
    }else ceiling=(y1-4)*p.pw.blockHeight;
  }
}