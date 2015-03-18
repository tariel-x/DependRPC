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

package org.tariel.dependrpc.containers;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tariel.dependrpc.pos.IPos;
import org.tariel.dependrpc.pos.JMystem;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class ConllSentence implements ISentence
{
    private List<IWord> words;
    private IPos pos;
    
    public ConllSentence() 
    {
//	this.pos = PosFabrique.getPos();
	this.pos = new JMystem();
	this.words = new ArrayList<IWord>();
    }

    @Override
    public void appendWord(String word)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void appendWord(IWord word)
    {
	this.words.add(word);
    }

    @Override
    public void parseSentence(String sentence)
    {
	ISentence tmp = this.pos.parseSentence(sentence);
	this.words = tmp.getParsedSentence();
    }

    @Override
    public IWord getRootWord()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IWord> getParsedSentence()
    {
	return this.words;
    }

    @Override
    public String getFormattedSentence()
    {
	List<String> words = new ArrayList<String>();
	int i = 1;
	for (IWord word : this.words)
	{
	    String formatted = word.getFormattedCategory();
	    if (!formatted.trim().equals(""))
		words.add(String.valueOf(i) + "\t" + word.getFormattedCategory());
	    i++;
	}
	String result = StringUtils.join(words, "\n");
	return result;
    }
    
}
