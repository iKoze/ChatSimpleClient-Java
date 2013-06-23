package de.floriware.gui.chatsimple;

import de.floriware.chatsimple.ServerInfo;

public interface IConnectWindowHandler
{
	public void e_connect(ServerInfo sinfo);
	public void e_abort();
}
