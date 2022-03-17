package com.SendSMS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class SendSMS {
	///send sms on mobile
	public void sendSMS(String mob_num,String sms) throws IOException
	{
	try
	{
		System.out.println("Sending SMS...");
	sms=sms.replace(" ", "%20");
	System.out.println("in send sms"+mob_num);
	URL url2=new URL("http://173.45.76.227/send.aspx?"
	+"username=perclean"
	+"&pass=perclean123"
	+"&route=trans1"
	+"&senderid=Percln"
	+"&numbers="+mob_num
	+"&message="+sms+"Percln");
	BufferedReader reader2 = new BufferedReader(new InputStreamReader(url2.openStream()));
	StringBuffer buffer1 = new StringBuffer();
	System.out.println(url2);   
	    int read1;
	    char[] chars1 = new char[1024];
	    while ((read1 = reader2.read(chars1)) != -1)
	        buffer1.append(chars1, 0, read1);
	    String responce=new String(buffer1);
	System.out.println("mobile sms status      "+responce);
	}
	catch(Exception e)
	{
	//System.out.println("sms meth exe : "+e);
		e.printStackTrace();
	}
	}
}
