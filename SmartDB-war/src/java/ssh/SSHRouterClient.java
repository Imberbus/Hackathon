package ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


/*
 * This code is basically from :
 * http://stackoverflow.com/questions/16468475/sending-commands-to-remote-server-through-ssh-by-java-with-jsch
 */

public class SSHRouterClient {

	public static void sendCommand( String ipRtr, int port, String user, String password, String command ) throws JSchException, IOException {
        JSch jsch = new JSch();
        
        Session session = jsch.getSession(user, ipRtr, port);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // Skip prompting for the password info and go direct...
        session.setPassword(password);
        session.connect();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);

        ((ChannelExec) channel).setErrStream(System.err);
		BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
		System.out.println("Connect to session...");
		System.out.println("");
		System.out.println("**********");
		channel.connect();

		String msg=null;
		while((msg=in.readLine())!=null){
		  System.out.println(msg);
		}

		channel.disconnect();
		session.disconnect();
		System.out.println("");
		System.out.println("**********");
		System.out.println("Exit from the session...");
	}
        
        public static String sendCommandResult( String ipRtr, int port, String user, String password, String command ) throws JSchException, IOException {
        JSch jsch = new JSch();
        
        Session session = jsch.getSession(user, ipRtr, port);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        // Skip prompting for the password info and go direct...
        session.setPassword(password);
        session.connect();
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);

        ((ChannelExec) channel).setErrStream(System.err);
		BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
		System.out.println("Connect to session...");
		System.out.println("");
		System.out.println("**********");
		channel.connect();

		String msg=null;
		int i = 0;
		String result="";
		while((msg=in.readLine())!=null){
		  i++;	
		  System.out.println(msg);
		  if (i == 2) {
			  result = msg;
		  }
		}

		channel.disconnect();
		session.disconnect();
		System.out.println("");
		System.out.println("**********");
		System.out.println("Exit from the session...");
		return result;
	}

}
