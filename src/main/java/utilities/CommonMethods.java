package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethods {
	
	public static BufferedWriter wr;

		public static String createURLFolder() {
			try {
				String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
				String path = System.getProperty("user.dir")+"\\URLs\\"+dateName;
				File folder = new File(path);

				if (!folder.exists()) {
					folder.mkdir();
				}
				return path;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return "";
	}

		}
		
		public static String createURLFile() {
			try {
				String path=createURLFolder();
				String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				FileWriter file=new FileWriter(path+"\\"+"URL"+dateName+".txt",true);
				wr=new BufferedWriter(file);
				wr.newLine();
				wr.flush();
				return path+"\\"+"URL"+dateName+".txt";
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				return "";
			}
			
		}
		public static void AppendURLs(String URL) {
			try {
				

				wr.write(URL);
				wr.newLine();
				wr.flush();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
			public static void closeURLFile() {
				try {
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
	}
		
	

