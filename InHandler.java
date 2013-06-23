import de.floriware.chatsimple.client.simplified.ISimplifiedConnectionHandler;
import de.floriware.utils.StringJoin;


public class InHandler implements ISimplifiedConnectionHandler
{
	public void incomingOK() {
		// TODO Auto-generated method stub
		System.out.println("OK");
	}

	public void incomingChatMessage(String sender, String chatmessage) {
		// TODO Auto-generated method stub
		System.out.println(sender + ": "  + chatmessage);
	}

	public void incomingNofification(String sender, String message) {
		// TODO Auto-generated method stub
		System.out.println("NOTIFY ("+sender+"): "+message);
	}

	public void incomingError(String sender, String message) {
		// TODO Auto-generated method stub
		System.out.println("ERROR ("+sender+"): "+message);
	}

	public void incomingUserList(String[] userlist) {
		// TODO Auto-generated method stub
		System.out.println("Users: " + StringJoin.join(" ", userlist));
	}

	public void gotDisconnected()
	{
		System.out.println("Got disconnected!");
	}
	
	public void handleException(Exception e) {
		// TODO Auto-generated method stub
		System.out.println(e.getMessage());
		e.printStackTrace();
		
	}

}
