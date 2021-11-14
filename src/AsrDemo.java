import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import restapi.asrdemo.AsrMain2;

public class AsrDemo {

	public double useTime = 0.0;
	public double bduseTime = 0.0;

	public String recognize(String wavFile) {
		String uttsent = "";
		try {

			double rtStart1 = (double) (System.currentTimeMillis()) / (double) 1000;
			Socket s = new Socket("119.45.103.196", 9080);
			// 构建IO
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			// Hundreds must have seen it, and taken it for a falling star.
			DataInputStream dis = new DataInputStream(new FileInputStream(wavFile));
			DataOutputStream dos = new DataOutputStream(os);

			byte[] buf = new byte[1024];// 1K大小的缓冲区
			int size = -1;
			while ((size = dis.read(buf)) != -1) {
				dos.write(buf, 0, size);
				dos.flush();
			}
			dis.close();

			s.shutdownOutput();

			// 读取服务器返回的消息
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String mess;
			while ((mess = br.readLine()) != null) {
				uttsent = mess;
				// System.out.println("服务器：" + mess);
				break;
			}

			double rtEnd1 = (double) (System.currentTimeMillis()) / (double) 1000;
			// }
			// System.err.println("lq totalTime:" + " time: " + (rtEnd1 - rtStart1) + " s");
			useTime = (rtEnd1 - rtStart1);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return uttsent;

	}

	public String bd_recognize(String wavFile, AsrMain2 demo) {

		String reg_sent = "";
		try {
			// File testdir=new File("test9");
			// File[] testfiles=testdir.listFiles();
			double rtStart = (double) (System.currentTimeMillis()) / (double) 1000;
			String wavPath = wavFile;

			demo.FILENAME = wavPath;
			// 填写下面信息
			String result = demo.run();

			double rtEnd = (double) (System.currentTimeMillis()) / (double) 1000;
			reg_sent = result;
			bduseTime = (rtEnd - rtStart);
			// System.out.println("result:"+result+" usetime:"+(rtEnd - rtStart) + " s");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reg_sent;
	}

	public static void main(String[] args) throws Exception {

		AsrDemo ewc = new AsrDemo();
		String uttsent = ewc.recognize("wavs/moz_common_voice_en_667068.wav");
		System.err.println("uttsent:" + uttsent);

		AsrMain2 demo = new AsrMain2();

		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
		String l;
		String[] fields;
		while ((l = br.readLine()) != null) {

			l = l.trim();
			String wavFile = l + ".wav";
			uttsent = ewc.recognize(wavFile);
			System.out.println(wavFile+" 识别结束：结果是");
			System.out.println("uttsent:" + uttsent + " useTime:" + ewc.useTime);
			String bdrsent = ewc.bd_recognize(wavFile, demo);
			System.out.println("bdrsent:" + bdrsent + " useTime:" + ewc.bduseTime);
			System.out.println();
		}

		br.close();

	}

}
