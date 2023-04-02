package com.letterboxedsolver;

// Java program to perform file
// operation output = input-delete

import java.io.*;

public class cleanWords
{
	public static void main(String[] args) throws IOException
	{
		// PrintWriter object for output.txt
		PrintWriter pw = new PrintWriter("solver\\data\\cleanerWords.txt");
		
		// BufferedReader object for input.txt
		BufferedReader br1 = new BufferedReader(new FileReader("solver\\data\\cleanWords.txt"));
		
		String line1 = br1.readLine();
		
		// loop for each line of input.txt
		while(line1 != null)
		{
			boolean flag = false;
			
			// BufferedReader object for delete.txt
			BufferedReader br2 = new BufferedReader(new FileReader("solver\\data\\morebadwords.txt"));
			
			String line2 = br2.readLine();
			
			// loop for each line of delete.txt
			while(line2 != null)
			{
				if(line1.equals(line2))
				{
					flag = true;
					break;
				}
				
				line2 = br2.readLine();
			}
			
			// if flag = false
			// write line of input.txt to output.txt
			if(!flag)
				pw.println(line1);
			
			line1 = br1.readLine();
			
		}
		
		pw.flush();
		
		// closing resources
		br1.close();
		pw.close();
		
		System.out.println("File operation performed successfully");
	}
    
}

