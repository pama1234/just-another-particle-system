package pama1234.gdx.game.net;

import java.io.IOException;

import pama1234.data.ByteUtil;
import pama1234.gdx.game.app.Screen0007;
import pama1234.gdx.game.util.ClientPlayer3D;

public class ServerReadThread extends Thread{
  public Screen0007 p;
  public ServerReadThread(Screen0007 p) {
    this.p=p;
  }
  @Override
  public void run() {
    byte[] inData=new byte[12];
    while(!p.stop) {
      synchronized(p.socketCenter.list) {
        // synchronized(p.group) {
        for(SocketData e:p.socketCenter.list) {
          try {
            doF(e,inData,
              ByteUtil.byteToInt(readNBytes(e,inData,0,4),0),
              ByteUtil.byteToInt(readNBytes(e,inData,0,4),0));
          }catch(IOException e1) {
            e1.printStackTrace();
          }
        }
        // }
      }
    }
  }
  // public void doF(byte[] inData,int state,int readSize) throws IOException {
  public void doF(SocketData e,byte[] inData,int state,int readSize) throws IOException {
    System.out.println("ServerRead state="+state+" readSize="+readSize);
    if(state!=e.state) {
      System.out.println("state!=e.state "+state+" "+e.state);
      return;
    }
    switch(state) {
      case 1: {
        byte[] nameBytes=new byte[readSize];
        readNBytes(e,nameBytes,0,readSize);
        e.name=new String(nameBytes);
        System.out.println("e.name "+e.name);
        e.state=2;
      }
        break;
      case 2: {
        readNBytes(e,inData,0,12);
        ClientPlayer3D tp=p.playerCenter.hashMap.get(e.name);
        tp.point.des.set(
          ByteUtil.byteToFloat(inData,0),
          ByteUtil.byteToFloat(inData,4),
          ByteUtil.byteToFloat(inData,8));
      }
        break;
      default:
        int ti=e.state;
        e.state=1;
        throw new RuntimeException("state err="+ti);
    }
  }
  public byte[] readNBytes(SocketData e,byte[] out,int offset,int size) throws IOException {
    int ti=0;
    while(ti==0) ti=e.i.readNBytes(out,offset,size);
    if(ti!=size) throw new RuntimeException("ti!=size "+ti+" "+size);
    return out;
  }
}