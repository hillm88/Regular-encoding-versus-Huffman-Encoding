/*

	This code opens a link
	and prints the webpage to the terminal.
*/

import java.net.*;
import java.io.*;

public class ReadPage
{

    
	private static int total=0;
    


	public static void main(String[] args)
	{
        //My array that holds all the interger letter representations
        int[] array = new int[26];
		try
		{
			URL server = new URL("https://en.wikipedia.org/wiki/AMD_580_chipset_series");
			
			HttpURLConnection webpageConnection= (HttpURLConnection) server.openConnection();
			webpageConnection.setRequestMethod("GET");
			webpageConnection.connect();
			InputStream in = webpageConnection.getInputStream();

			byte[] buffer = new byte[4096];
			int bytesRead = in.read(buffer);

			while(bytesRead > 0)
			{
				for(int i=0; i<bytesRead; i++){
				
                    //If the buffer position is a letter
                    if( buffer[i] >= 'A' && buffer[i] <= 'Z' || buffer[i] >= 'a' && buffer[i] <= 'z'){
                        //If the buffer position is a lowercase letter
                        if(buffer[i] >= 'a'){
                            //Turning the letter into an array position for our letter count array
                            array[buffer[i]-'a']++ ;
                        }
                        //If the buffer position is a uppercase letter
                        else{
                            //Turning the letter into an array position for our letter count array
                            array[buffer[i]-'A']++ ;
                        }
                    }
                
                }
					
				bytesRead = in.read(buffer);
			}

            //Printing the count of each letter
            for (int j = 0; j<26;j++){
                System.out.println("Letter " + (char)(j+97) + " "+ array[j]);
				total += array[j];
            }

			//Printing out the total storage and multipling it by 8 to include extended ascii.
			System.out.println(total * 8);
			in.close();
		}
		catch(Exception e) { System.out.println("Error opening page\n"+e); }
	
    


    
    
    
    }

	public static int totalStorageRegular(){
        return total;
    }




}