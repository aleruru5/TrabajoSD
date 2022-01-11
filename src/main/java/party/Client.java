package party;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	public String partner = "";
	
	public int port;

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Client(String partner, int port) {
		super();
		this.partner = partner;
		this.port = port;
	}

	public void play() { //Manda la orden play al servidor al que estás conectado
		if (partner != "") {
			try (Socket s = new Socket(partner, port);
					OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());) {
				String accionRequerida = "play";
				writer.write(accionRequerida);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pause() { //Manda la orden pause al servidor al que estás conectado
		if (partner != "") {
			try (Socket s = new Socket(partner, port);
					OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());) {
				String accionRequerida = "pause";
				writer.write(accionRequerida);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void time(long time) { //Manda la orden time + un long que se corresponde con el tiempo actual del vídeo en reproduccion al servidor al que estás conectado 
		if (partner != "") {
			try (Socket s = new Socket(partner, port);
					OutputStreamWriter writer = new OutputStreamWriter(s.getOutputStream());) {
				String accionRequerida = "time";
				writer.write(accionRequerida + "\n");
				accionRequerida = String.valueOf(time);
				writer.write(accionRequerida);
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void send(String path, String name) {
		if (partner != "") { //Manda la orden send + un vídeo elegido por el usuario al servidor al que estás conectado
			try (Socket s = new Socket(partner, port);
					DataOutputStream writer = new DataOutputStream(s.getOutputStream());) {
				String accionRequerida = "send";
				writer.writeBytes(accionRequerida + "\n");
				accionRequerida = name + "\n";
				writer.writeBytes(accionRequerida);
				writer.flush();
				System.out.println(name);
				System.out.println(path);
				try(FileInputStream sin = new FileInputStream(path);) {
					byte[] bytes = new byte [20000];
					int bytesLeidos = sin.read(bytes);
					while(bytesLeidos != -1) {
						writer.write(bytes,0,bytesLeidos);
						bytesLeidos = sin.read(bytes);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
