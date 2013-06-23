package de.floriware.gui.chatsimple;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

public class ChatWindow
{
	public JFrame frame;
	public GridBagConstraints l;
	public MenuBar menubar;
	
	public Menu m_server;
	public Menu m_user;
	public Menu m_help;
	
	public MenuItem mi_connect;
	public MenuItem mi_disconnect;
	public MenuItem mi_username;
	public MenuItem mi_getuserlist;
	public MenuItem mi_about;
	
	public TextArea ta_inbox;
	public TextField tf_sendbox;
	public List li_userlist;
	public Label l_statusline;
	public Label l_connected;
	
	protected boolean state_connected = false;
	
	protected IChatWindowHandler handler;
	
	public ChatWindow(IChatWindowHandler handler)
	{
		this.handler = handler;
		init();
	}
	
	public void init()
	{
		frame = new JFrame();
		frame.setTitle(Language.CHW_TITLE);
		frame.setLayout(new GridBagLayout());
		l = new GridBagConstraints();
		menubar = new MenuBar();
		
		m_server = new Menu(Language.CHW_M_SERVER);
		m_user = new Menu(Language.CHW_M_USER);
		m_help = new Menu(Language.CHW_M_HELP);
		
		mi_connect = new MenuItem(Language.CHW_MI_CONNECT);
		mi_disconnect = new MenuItem(Language.CHW_MI_DISCONNECT);
		mi_username = new MenuItem(Language.CHW_MI_USERNAME);
		mi_getuserlist = new MenuItem(Language.CHW_MI_GETUSERLIST);
		mi_about = new MenuItem(Language.CHW_MI_ABOUT);
		m_server.add(mi_connect);
		m_server.add(mi_disconnect);
		
		m_user.add(mi_username);
		m_user.add(mi_getuserlist);
		
		m_help.add(mi_about);
		
		menubar.add(m_server);
		menubar.add(m_user);
		menubar.add(m_help);
		
		frame.setMenuBar(menubar);
		
		ta_inbox = new TextArea();
		ta_inbox.setEditable(false);
		ta_inbox.setBackground(Color.WHITE);
		tf_sendbox = new TextField();
		li_userlist = new List();
		l_statusline = new Label();
		l_connected = new Label();
		l_connected.setAlignment(2);
		
		l.gridx = 0;
		l.gridy = 0;
		l.gridheight = 1;
		l.gridwidth = 1;
		l.fill = GridBagConstraints.BOTH;
		l.weightx = 100.0;
		l.weighty = 100.0;
		frame.add(ta_inbox, l);
		
		l.gridx = 1;
		frame.add(li_userlist, l);
		
		l.anchor = GridBagConstraints.SOUTH;
		l.fill = GridBagConstraints.HORIZONTAL;
		l.weighty = 0;
		l.gridx = 0;
		l.gridy = 1;
		l.gridwidth = 2;
		frame.add(tf_sendbox, l);
		
		l.gridy = 2;
		l.gridwidth = 1;
		frame.add(l_statusline, l);
		
		l.gridx = 1;
		l.weightx = 0;
		l.anchor = GridBagConstraints.EAST;
		l.fill = GridBagConstraints.HORIZONTAL;
		frame.add(l_connected, l);
		
		addListener();
		setConnected(false);
	}
	
	protected void addListener()
	{
		mi_connect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_connect();
			}
		});
		
		mi_disconnect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_disconnect();
			}
		});
		
		mi_getuserlist.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_userlist();
			}
		});
		
		mi_username.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_changeUsername();
			}
		});
		
		mi_about.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.e_about();
			}
		});
		
		tf_sendbox.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					handler.e_enterPressed();
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				handler.e_onClose();
			}
		});
	}
	
	public void setConnected(boolean connected)
	{
		state_connected = connected;
		if(connected == false)
		{
			mi_connect.setEnabled(true);
			mi_disconnect.setEnabled(false);
			m_user.setEnabled(false);
			mi_getuserlist.setEnabled(false);
			mi_username.setEnabled(false);
			tf_sendbox.setEnabled(false);
			l_connected.setText(Language.CHW_L_CONNECTED_FALSE);
		}
		else
		{
			mi_connect.setEnabled(false);
			mi_disconnect.setEnabled(true);
			m_user.setEnabled(true);
			mi_getuserlist.setEnabled(true);
			mi_username.setEnabled(true);
			tf_sendbox.setEnabled(true);
			l_connected.setText(Language.CHW_L_CONNECTED_TRUE);
		}
	}
	
	public boolean getConnected()
	{
		return state_connected;
	}
}
