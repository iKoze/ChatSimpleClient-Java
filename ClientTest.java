import de.floriware.chatsimple.client.simplified.SimplifiedChatsimpleClient;

public class ClientTest
{
	public static SimplifiedChatsimpleClient client;
	public static void main(String [] args)
	{
		client = new SimplifiedChatsimpleClient("vs01.floriware.net", "test", "", new InHandler());
		
		client.connect();
		Boolean lsuccess = client.login();
		//System.out.println(lsuccess);
		int i = 0;
		while(lsuccess)
		{
			i++;
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				
			}
			client.sendChatMessage("" + i);
			if(i == 1)
			{
				client.requestUserList();
			}
			if(i == 5)
			{
				try{Thread.sleep(100);}catch(Exception e){}
				client.changeUsername("florian");
			}
			if(i == 10)
			{
				client.logout();
				lsuccess = false;
			}
		}
		client.disconnect();
	}
}