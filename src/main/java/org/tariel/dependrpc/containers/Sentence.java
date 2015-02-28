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
import org.tariel.dependrpc.pos.IPos;
import org.tariel.dependrpc.pos.JMystem;
import org.tariel.dependrpc.pos.PosFabrique;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class Sentence implements ISentence
{
    private List<IWord> words;
    private IPos pos;
    
    public Sentence() 
    {
//	this.pos = PosFabrique.getPos();
	this.pos = new JMystem();
	this.words = new ArrayList<IWord>();
    }
    
    /**
     * Adds parsed word to the end of sentence
     * @param word
     */
    public void appendWord(IWord word)
    {
	this.words.add(word);
    }

    /**
     * Parses sentence into words
     */
    public void parseSentence(String sentence)
    {
	ISentence tmp = this.pos.parseSentence(sentence);
	this.words = tmp.getParsedSentence();
    }

    /**
     * Get root word of the sentence. Often is verb.
     * @return root word
     */
    public IWord getRootWord()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Returns list of parsed words
     * @return list of words
     */
    public List<IWord> getParsedSentence()
    {
	return this.words;
    }
    
    /**
     * Creates Malttab-formatted sentence string.
     * @return malttab string
     */
    public String getMalttabSentence()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void appendWord(String word)
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
