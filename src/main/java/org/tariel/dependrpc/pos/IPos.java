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

import org.tariel.dependrpc.containers.ISentence;
import org.tariel.dependrpc.containers.IWord;

/**
 * 
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public interface IPos {
    /**
     * Makes word's description
     * @param word natural language word
     * @return 
     */
    public IWord parseWord(String word);
    
    /**
     * Devides sentence into sequence of parsed words
     * @param sentence
     * @return 
     */
    public ISentence parseSentence(String sentence);
}
