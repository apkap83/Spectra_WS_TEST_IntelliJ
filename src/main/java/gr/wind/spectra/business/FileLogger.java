package gr.wind.spectra.business;

import java.io.File;
import java.io.FileOutputStream;

public class FileLogger
{

	String filePath;

	public FileLogger(String filePath)
	{
		this.filePath = filePath;
	}

	public void Log(String lineOfText)
	{
		File file = null;
		FileOutputStream fileOutputStream = null;
		String data = lineOfText + "\n";

		System.out.print(data);

		try
		{
			file = new File(filePath);
			fileOutputStream = new FileOutputStream(file, true);
			// create file if not exists
			if (!file.exists())
			{
				file.createNewFile();
			}
			// fetch bytes from data
			byte[] bs = data.getBytes();
			fileOutputStream.write(bs);
			fileOutputStream.flush();
			fileOutputStream.close();
			// System.out.println("Log File was written successfully.");
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (fileOutputStream != null)
				{
					fileOutputStream.close();
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
}
