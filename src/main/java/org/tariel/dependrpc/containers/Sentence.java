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

import java.util.List;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class Sentence implements ISentence
{
    /**
     * Adds word to the end of sentence
     * @param word
     */
    public void appendWord(String word)
    {
	
    }

    /**
     * Parses sentence into words
     */
    public void parseSentence(String sentence)
    {
	
    }

    /**
     * Get root word of the sentence. Often is verb.
     * @return root word
     */
    public IWord getRootWord()
    {
	return null;
    }
    
    /**
     * Returns list of parsed words
     * @return list of words
     */
    public List<IWord> getParsedSentence()
    {
	return null;
    }
    
    /**
     * Creates Malttab-formatted sentence string.
     * @return malttab string
     */
    public String getMalttabSentence()
    {
	return null;
    }
}
