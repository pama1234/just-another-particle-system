package pama1234.gdx.game.ui;

import com.badlogic.gdx.Input;

import pama1234.gdx.game.app.Screen0003;

public class TextButtonGenerator{
  /**
   * @param p <br/>
   *          _T _Z _H __ <br/>
   *          _X _C _N _M <br/>
   *          _R _F sU sD <br/>
   *          ----------- <br/>
   *          _↑ _W __ __ <br/>
   *          _↓ _A _S _D <br/>
   */
  public static Button[] genButtons(Screen0003 p) {
    return new Button[] {
      new TextButton(p,true,()->true,()-> {},()-> {},()-> {
        p.fullSettings=!p.fullSettings;
      },"T",p::getButtonUnitLength,()->p.bu*0.5f,()->p.bu*0.5f),
      new TextButton(p,true,()->true,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.Z);
        p.inputProcessor.keyUp(Input.Keys.Z);
      },"Z",p::getButtonUnitLength,()->p.bu*1.5f,()->p.bu*0.5f),
      //--------------------------------------------------------------------
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.H);
        p.inputProcessor.keyUp(Input.Keys.H);
      },"H",p::getButtonUnitLength,()->p.bu*2.5f,()->p.bu*0.5f),
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.I);
        p.inputProcessor.keyUp(Input.Keys.I);
      },"I",p::getButtonUnitLength,()->p.bu*3.5f,()->p.bu*0.5f),
      //--------------------------------------------------------------------
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.X);
        p.inputProcessor.keyUp(Input.Keys.X);
      },"X",p::getButtonUnitLength,()->p.bu*0.5f,()->p.bu*1.5f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.C);
        p.inputProcessor.keyUp(Input.Keys.C);
      },"C",p::getButtonUnitLength,()->p.bu*1.5f,()->p.bu*1.5f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.N);
        p.inputProcessor.keyUp(Input.Keys.N);
      },"N",p::getButtonUnitLength,()->p.bu*2.5f,()->p.bu*1.5f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.M);
        p.inputProcessor.keyUp(Input.Keys.M);
      },"M",p::getButtonUnitLength,()->p.bu*3.5f,()->p.bu*1.5f,()->p.bu,()->p.bu*0.75f),
      //--------------------------------------------------------------------
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.R);
        p.inputProcessor.keyUp(Input.Keys.R);
      },"R",p::getButtonUnitLength,()->p.bu*0.5f,()->p.bu*2.25f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,true,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.F);
        p.inputProcessor.keyUp(Input.Keys.F);
      },"F",p::getButtonUnitLength,()->p.bu*1.5f,()->p.bu*2.25f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,false,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.scrolled(0,-1);
      },"sU",p::getButtonUnitLength,()->p.bu*2.5f,()->p.bu*2.25f,()->p.bu,()->p.bu*0.75f),
      new TextButton(p,false,()->p.fullSettings,()-> {},()-> {},()-> {
        p.inputProcessor.scrolled(0,1);
      },"sD",p::getButtonUnitLength,()->p.bu*3.5f,()->p.bu*2.25f,()->p.bu,()->p.bu*0.75f),
      //--------------------------------------------------------------------
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.W);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.W);
      },"W",p::getButtonUnitLength,()->p.bu*2.5f,()->p.height-p.bu*2.5f),
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.S);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.S);
      },"S",p::getButtonUnitLength,()->p.bu*2.5f,()->p.height-p.bu*1.5f),
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.A);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.A);
      },"A",p::getButtonUnitLength,()->p.bu*1.5f,()->p.height-p.bu*1.5f),
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.D);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.D);
      },"D",p::getButtonUnitLength,()->p.bu*3.5f,()->p.height-p.bu*1.5f),
      //--------------------------------------------------------------------
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.SPACE);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.SPACE);
      },"↑",p::getButtonUnitLength,()->p.bu*0.5f,()->p.height-p.bu*2.5f),
      new TextButton(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.SHIFT_LEFT);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.SHIFT_LEFT);
      },"↓",p::getButtonUnitLength,()->p.bu*0.5f,()->p.height-p.bu*1.5f)
    };
  }
}