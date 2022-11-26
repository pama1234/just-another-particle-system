package pama1234.gdx.game.net.io;

import static pama1234.gdx.game.net.NetUtil.catchException;
import static pama1234.gdx.game.net.NetUtil.readNBytes;
import static pama1234.gdx.game.net.ClientState.ClientAuthentication;
import static pama1234.gdx.game.net.ClientState.ClientDataTransfer;
import static pama1234.gdx.game.net.ClientState.intToState;

import java.io.IOException;
import java.net.SocketException;

import pama1234.data.ByteUtil;
import pama1234.gdx.game.app.Screen0003;
import pama1234.gdx.game.net.ClientState;
import pama1234.gdx.game.net.SocketData;

public class ClientRead extends Thread{
  public Screen0003 p;
  public SocketData s;
  // public boolean stop;
  public ClientRead(Screen0003 p,SocketData dataSocket) {
    this.p=p;
    this.s=dataSocket;
  }
  @Override
  public void run() {
    // byte[] td=new byte[4];
    byte[] data=new byte[20];
    // int state;
    // int readSize;
    while(!s.stop) {
      try {
        // int ti=client.i.readNBytes(td,0,4);
        // println(ti+" "+Arrays.toString(td)+" "+ByteUtil.byteToInt(td));
        // synchronized(cellData) {
        // state=ByteUtil.byteToInt(readNBytes(inData,0,4),0);
        // readSize=ByteUtil.byteToInt(readNBytes(inData,0,4),0);
        doF(data,
          intToState(ByteUtil.byteToInt(readNBytes(s,data,0,4),0)),
          ByteUtil.byteToInt(readNBytes(s,data,0,4),0));
        // doF(inData,state,readSize);
        // }
      }catch(SocketException e1) {
        catchException(e1,s);
      }catch(IOException e2) {
        catchException(e2,s);
      }
    }
  }
  public void doF(byte[] inData,ClientState state,int readSize) throws IOException {
    System.out.println("ClientRead state="+state+" readSize="+readSize);
    // p.println(state,readSize);
    switch(state) {
      case ClientAuthentication: {
        if(readSize!=4) throw new RuntimeException("state 0 readSize!=4 "+readSize);//TODO
        readNBytes(s,inData,0,readSize);
        // System.out.println(ByteUtil.byteToInt(inData,0));
        s.clientState=ClientAuthentication;
        // p.println("e.state=0");//TODO
        // p.println(inData);
      }
        break;
      case ClientDataTransfer: {
        s.clientState=ClientDataTransfer;
        if(readSize!=p.cellData.length) throw new RuntimeException("state DataTransfer readSize!=p.cellData.length "+readSize+" "+p.cellData.length);//TODO
        for(int i=0;i<readSize;i++) {
          readNBytes(s,inData,0,inData.length);
          p.cellData[i].id=ByteUtil.byteToInt(inData,0);
          p.cellData[i].type=ByteUtil.byteToInt(inData,4);
          p.cellData[i].x=ByteUtil.byteToFloat(inData,8);
          p.cellData[i].y=ByteUtil.byteToFloat(inData,12);
          p.cellData[i].z=ByteUtil.byteToFloat(inData,16);
        }
      }
        break;
      default:
        throw new RuntimeException("state err="+state);
    }
  }
  public void dispose() {
    s.stop=true;
  }
}