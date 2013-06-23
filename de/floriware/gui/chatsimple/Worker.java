package de.floriware.gui.chatsimple;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import de.floriware.chatsimple.ServerInfo;
import de.floriware.chatsimple.client.simplified.ISimplifiedConnectionHandler;
import de.floriware.chatsimple.client.simplified.SimplifiedChatsimpleClient;
import de.floriware.utils.StatusLine;

public class Worker implements IChatWindowHandler, ISimplifiedConnectionHandler, IConnectWindowHandler
{
	protected ChatWindow window;
	protected SimplifiedChatsimpleClient client;
	protected ConnectWindow connectwindow;
	protected StatusLine statusline;
	
	public static final String VERSION = "alpha 0.99";
	
	public Worker()
	{
		window = new ChatWindow(this);
		window.frame.setSize(600,400);
		statusline = new StatusLine(window.l_statusline, Language.CHW_L_STATUSLINE_READY, 5000);
		JFrame wf = window.frame;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		wf.setLocation(dim.width/2-wf.getSize().width/2, dim.height/2-wf.getSize().height/2);
		window.frame.setVisible(true);
	}
	
	@Override
	public void e_connect() {
		window.mi_connect.setEnabled(false);
		connectwindow = new ConnectWindow(this);
		connectwindow.frame.setSize(400,180);
		JFrame cframe = connectwindow.frame;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		cframe.setLocation(dim.width/2-cframe.getSize().width/2, dim.height/2-cframe.getSize().height/2);
		connectwindow.frame.setVisible(true);
		
	}
	@Override
	public void e_disconnect() {
		if(client.isConnected())
		{
			client.logout();
			client.disconnect();
		}
		window.setConnected(false);
	}
	@Override
	public void e_userlist() {
		client.requestUserList();
		
	}
	@Override
	public void e_changeUsername() {
		String s = "";
		s = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Neuer Benutzername:",
		                    s, JOptionPane.QUESTION_MESSAGE,
		                    null,
		                    null,
		                    client.getServerInfo().username);

		if ((s != null) && (s.length() > 0)) {
		    if(client.changeUsername(s))
		    {
		    	statusline.notify(Language.CHW_L_STATUSLINE_NICK_CHANGED);
		    }
		    else
		    {
		    	statusline.notify(Language.CHW_L_STATUSLINE_NICK_UNCHANGED);
		    }
		    return;
		}
	}
	@Override
	public void e_enterPressed() {
		client.sendChatMessage(window.tf_sendbox.getText());
		window.tf_sendbox.setText("");
		
	}
	@Override
	public void e_onClose() {
		if(client != null && client.isConnected())
		{
			client.logout();
			client.disconnect();
		}
		System.exit(0);
	}
	@Override
	public void incomingOK() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void incomingChatMessage(String sender, String chatmessage) {
		window.ta_inbox.append(timestamp()+" "+sender+": "+chatmessage+"\n");
		
	}
	@Override
	public void incomingNofification(String sender, String message) {
		window.ta_inbox.append(timestamp()+" NOTIFY "+sender+": "+message+"\n");
		client.requestUserList();
		
	}
	@Override
	public void incomingError(String sender, String message) {
		window.ta_inbox.append(timestamp()+" ERROR "+sender+": "+message+"\n");
		statusline.notify(sender + " Error: " + message);
		
	}
	@Override
	public void incomingUserList(String[] userlist) {
		window.li_userlist.removeAll();
		for(String user : userlist)
		{
			window.li_userlist.add(user);
		}
		
	}
	@Override
	public void gotDisconnected() {
		window.setConnected(false);
		window.frame.setTitle("ChatSimple Client");
		window.li_userlist.removeAll();
		statusline.notify(Language.CHW_L_STATUSLINE_CONN_DISCONNECTED);
	}
	
	@Override
	public void handleException(Exception e) {
		statusline.notify(e.getMessage(), 20000);
		e.printStackTrace();
		if(client != null && client.isConnected())
		{
			client.disconnect();
		}
		window.setConnected(false);
		window.mi_connect.setEnabled(true);
	}

	@Override
	public void e_connect(ServerInfo sinfo) {
		connectwindow.frame.setVisible(false);
		window.ta_inbox.setText("");
		client = new SimplifiedChatsimpleClient(sinfo, this);
		if(client.connect() && client.login())
		{
			window.setConnected(true);
			window.frame.setTitle(sinfo.address);
			statusline.notify(Language.CHW_L_STATUSLINE_CONN_ESTABLISHED);
		}
		else
		{
			client.disconnect();
			client = null;
			statusline.notify(Language.CHW_L_STATUSLINE_UNABLE_TO_CONNECT);
			window.setConnected(false);
		}
	}

	@Override
	public void e_abort() {
		window.mi_connect.setEnabled(true);
		connectwindow.frame.setVisible(false);
		
	}
	
	public String timestamp()
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return "[" + sdf.format(cal.getTime()) + "]";
		
	}

	@Override
	public void e_about() {
		new AboutWindow(VERSION, SimplifiedChatsimpleClient.VERSION).frame.setVisible(true);
		
	}
}
