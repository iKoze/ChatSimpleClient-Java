package de.floriware.gui.chatsimple;

/**
 * ClientGuiExecuteable
 * Main executeable Class. Starts Worker.
 * @version 1.0
 * @author florian
 */
public class ClientGuiExecuteable
{
	protected static Worker w;
	
	/**
	 * Main 
	 * @param args
	 */
	public static void main(String[] args)
	{
		w = new Worker();
	}
}
