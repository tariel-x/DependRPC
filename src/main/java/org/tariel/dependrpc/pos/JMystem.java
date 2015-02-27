/*
 * Copyright (C) 2015 Nikita Gerasimov <n@tariel.ru>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.tariel.dependrpc.pos;

import java.io.*;

import org.tariel.dependrpc.containers.ISentence;
import org.tariel.dependrpc.containers.IWord;
import org.tariel.jsonconfig.JsonConfig;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class JMystem implements IPos
{

    /**
     * Copy of Yandex Mystem executable
     */
    private String executable;

    public JMystem()
    {
	this.executable = JsonConfig.get("lemmatiser").get("path").asStr();
    }

    /**
     * Makes word's description
     * @param word natural language word
     * @return null
     */
    public IWord parseWord(String word)
    {
	return null;
    }

    public ISentence parseSentence(String sentence)
    {
	try
	{
	    Process process = new ProcessBuilder(this.executable, "-i", "--format=json", "goodfox.in").start();
	    InputStream is = process.getInputStream();
	    OutputStream os = process.getOutputStream();
	    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
	    BufferedWriter bw = new BufferedWriter(osw);
	    BufferedReader br = new BufferedReader(isr);
	    bw.write(sentence);
	    String line;

	    while ((line = br.readLine()) != null)
	    {
		System.out.println(line);
	    }

	} catch (IOException ex)
	{
	    ex.printStackTrace();
	}
	return null;
    }
}
