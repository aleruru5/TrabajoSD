package party;

import static uk.co.caprica.vlcjplayer.Application.application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ManageRequest implements Runnable {

	DataInputStream reader = null;
	DataOutputStream writer = null;

	public ManageRequest(DataInputStream r, DataOutputStream w) {
		this.reader = r;
		this.writer = w;
	}

	@Override
	public void run() { //Lee una linea y ve cual de las 4 ordene es. Actua en consecuencia en cada una de ellas.
		try {
			String lineaLeida = this.reader.readLine();
			System.out.println(lineaLeida); //Para poder ver lo que pasa
			if (lineaLeida.equals("play")) {
				application().mediaPlayer().controls().play();
			} else if (lineaLeida.equals("pause")) {
				application().mediaPlayer().controls().pause();
			} else if (lineaLeida.equals("time")) {
				lineaLeida = this.reader.readLine();
				System.out.println(lineaLeida);
				application().mediaPlayer().controls().setTime(Long.parseLong(lineaLeida));
			} else if (lineaLeida.equals("send")) {
				lineaLeida = this.reader.readLine();
				try(FileOutputStream sout = new FileOutputStream(lineaLeida);) {
					byte[] bytes = new byte [20000];
					int bytesLeidos = reader.read(bytes);
					while(bytesLeidos != -1) {
						sout.write(bytes,0,bytesLeidos);
						bytesLeidos = reader.read(bytes);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.reader.close();
				this.writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
