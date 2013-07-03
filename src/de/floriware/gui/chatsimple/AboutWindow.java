package de.floriware.gui.chatsimple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * The About Window
 * @version 1.0
 * @author florian
 */
public class AboutWindow
{
	public JFrame frame;
	public GridBagConstraints l;
	
	public String api_version = "";
	public String client_version = "";
	
	public Label l_progname;
	public TextArea ta_info;
	public Label l_clientversion;
	public Label l_apiversion;
	public Label l_client;
	public Label l_api;
	public Label l_floriware;
	
	public AboutWindow(String clientversion, String apiversion)
	{
		frame = new JFrame();
		frame.setTitle(Language.AW_TITLE);
		frame.setLayout(new GridBagLayout());
		
		l_progname = new Label(Language.PROG_NAME);
		l_progname.setAlignment(Label.CENTER);
		l_progname.setFont(new Font("", Font.BOLD, 24));
		ta_info = new TextArea(Language.AW_L_INFO);
		ta_info.setEditable(false);
		ta_info.setBackground(Color.WHITE);
		l_clientversion = new Label(clientversion);
		l_apiversion = new Label(apiversion);
		l_client = new Label(Language.AW_L_CLIENT);
		l_client.setAlignment(Label.RIGHT);
		l_api = new Label(Language.AW_L_API);
		l_api.setAlignment(Label.RIGHT);
		l_floriware = new Label("Floriware");
		l_floriware.setFont(new Font("", Font.ITALIC + Font.BOLD, 16));
		l_floriware.setAlignment(Label.RIGHT);
		
		l = new GridBagConstraints();
		l.weightx = 100.0;
		l.weighty = 0;
		l.gridx = 0;
		l.gridy = 0;
		l.gridwidth = 2;
		l.gridheight = 1;
		l.fill = GridBagConstraints.HORIZONTAL;
		l.insets = new Insets(15,15,15,15);
		frame.add(l_progname, l);
		
		l.gridy++;
		l.gridwidth = 1;
		l.anchor = GridBagConstraints.EAST;
		l.insets = new Insets(5,5,5,5);
		frame.add(l_client, l);
		
		l.gridx = 1;
		l.anchor = GridBagConstraints.WEST;
		frame.add(l_clientversion, l);
		
		l.gridy++;
		l.gridx = 0;
		l.anchor = GridBagConstraints.EAST;
		frame.add(l_api, l);
		
		l.gridx = 1;
		l.anchor = GridBagConstraints.WEST;
		frame.add(l_apiversion, l);
		
		l.gridy++;
		l.gridx = 0;
		l.gridwidth = 2;
		l.anchor = GridBagConstraints.CENTER;
		l.fill = GridBagConstraints.HORIZONTAL;
		frame.add(ta_info, l);
		
		l.gridy++;
		l.anchor = GridBagConstraints.EAST;
		l.insets = new Insets(15,15,15,15);
		frame.add(l_floriware, l);
		
		frame.pack();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}
}
