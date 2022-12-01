package pama1234.gdx.game.app.server.game.net.io;

import static pama1234.gdx.game.app.server.game.net.NetUtil.catchException;
import static pama1234.gdx.game.app.server.game.net.NetUtil.debug;
import static pama1234.gdx.game.app.server.game.net.NetUtil.writeClientHeader;

import java.io.IOException;
import java.net.SocketException;

import pama1234.data.ByteUtil;
import pama1234.gdx.game.app.server.game.net.ClientCore;
import pama1234.gdx.game.app.server.game.net.SocketData;

public class ClientWrite extends Thread{
  public ClientCore p;
  public SocketData s;
  // public boolean stop;
  public ClientWrite(ClientCore p,SocketData dataSocket) {
    super("ClientWrite "+dataSocket.s.getRemoteAddress());
    this.p=p;
    this.s=dataSocket;
  }
  @Override
  public void run() {
    byte[] data=new byte[12];
    while(!s.stop) {
      try {
        doF(data);
      }catch(SocketException e1) {
        catchException(e1,s);
      }catch(IOException e2) {
        catchException(e2,s);
      }
    }
  }
  public void doF(byte[] outData) throws IOException {
    if(debug) System.out.println("ClientWrite state="+s.clientState);
    switch(s.clientState) {
      case ClientAuthentication: {
        // e.name
        // e.o.write(ByteUtil.intToByte(e.state,outData,0),0,4);
        byte[] nameBytes=s.name.getBytes();
        writeClientHeader(s,outData,nameBytes.length);
        s.o.write(nameBytes);
        s.o.flush();
        // s.state=DataTransfer;
        // p.println(Arrays.toString(nameBytes));
        p.sleep(1000);
      }
        break;
      case ClientDataTransfer: {
        // e.o.write(ByteUtil.intToByte(e.state,outData,0),0,4);
        // e.o.write(ByteUtil.intToByte(12,outData,0),0,4);
        writeClientHeader(s,outData,12);
        ByteUtil.floatToByte(p.yourself.x(),outData,0);
        ByteUtil.floatToByte(p.yourself.y(),outData,4);
        ByteUtil.floatToByte(p.yourself.z(),outData,8);
        // ByteUtil.floatToByte(p.yourself.x(),outData);
        s.o.write(outData,0,12);
        s.o.flush();
        p.sleep(40);
      }
        break;
      default:
        throw new RuntimeException("state err="+s.serverState);
    }
  }
  public void dispose() {
    s.stop=true;
  }
}