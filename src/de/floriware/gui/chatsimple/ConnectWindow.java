package de.floriware.gui.chatsimple;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.floriware.chatsimple.ServerInfo;

public class ConnectWindow
{
	public JFrame frame;
	public GridBagConstraints l;
	
	public Button b_connect;
	public Button b_abort;
	
	public Label l_address;
	public Label l_password;
	public Label l_username;
	public Label l_separator;
	
	public TextField tf_address;
	public TextField tf_password;
	public TextField tf_username;
	public TextField tf_separator;
	
	public IConnectWindowHandler handler;
	
	public ConnectWindow(IConnectWindowHandler handler)
	{
		this.handler = handler;
		init();
	}
	
	public void init()
	{
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());
		frame.setTitle(Language.COW_TITLE);
		l = new GridBagConstraints();
		
		b_connect = new Button(Language.COW_B_CONNECT);
		b_abort = new Button(Language.COW_B_ABORT);
		
		l_address = new Label(Language.COW_L_ADDRESS);
		l_password = new Label(Language.COW_L_PASSWORD);
		l_username = new Label(Language.COW_L_USERNAME);
		l_separator = new Label(Language.COW_L_SEPARATOR);
		
		tf_address = new TextField();
		tf_password = new TextField();
		tf_username = new TextField(System.getProperty("user.name"));
		tf_separator = new TextField();
		
		l.gridx = 0;
		l.gridy = 0;
		l.gridheight = 1;
		l.gridwidth = 1;
		l.insets = new Insets(5,5,5,5);
		l.fill = GridBagConstraints.NONE;
		l.anchor = GridBagConstraints.WEST;
		l.weightx = 0;
		l.weighty = 0;
		frame.add(l_address, l);
		
		l.gridy++;
		frame.add(l_username, l);
		
		l.gridy++;
		frame.add(l_password, l);
		
		l.gridy++;
		frame.add(l_separator, l);
		
		l.weightx = 100.0;
		l.fill = GridBagConstraints.HORIZONTAL;
		l.gridy = 0;
		l.gridx = 1;
		frame.add(tf_address, l);
		
		l.gridy++;
		frame.add(tf_username, l);
		
		l.gridy++;
		frame.add(tf_password, l);
		
		l.gridy++;
		frame.add(tf_separator, l);
		
		
		l.anchor = GridBagConstraints.SOUTHEAST;
		l.fill = GridBagConstraints.NONE;
		l.gridwidth = 1;
		l.weightx = 100.0;
		l.gridy++;
		frame.add(b_connect, l);
		
		l.anchor = GridBagConstraints.SOUTHWEST;
		l.weightx = 0;
		l.gridx = 0;
		frame.add(b_abort, l);
		
		addListener();
	}
	
	protected void addListener()
	{
		b_connect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_connect(getServerInfo());
			}
		});
		
		b_abort.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_abort();
			}
		});
		
		tf_address.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					handler.e_connect(getServerInfo());
				}
			}
		});
		
		tf_username.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					handler.e_connect(getServerInfo());
				}
			}
		});
		
		tf_password.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					handler.e_connect(getServerInfo());
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				handler.e_abort();
			}
		});
	}
	
	public ServerInfo getServerInfo()
	{
		ServerInfo sinfo = new ServerInfo();
		sinfo.address = tf_address.getText();
		sinfo.password = tf_password.getText();
		sinfo.username = tf_username.getText();
		if(!tf_separator.getText().equalsIgnoreCase(""))
		{
			sinfo.delimiter = tf_separator.getText();
		}
		return sinfo;
	}
	
}
