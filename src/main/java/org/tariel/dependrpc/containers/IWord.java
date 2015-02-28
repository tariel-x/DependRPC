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

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public interface IWord
{

    public final String[] Pos =
    {
	"A", "ADV", "ADVPRO", "ANUM",
	"APRO", "COM", "CONJ", "INTJ", "NUM", "PART", "PR", "S",
	"SPRO", "V"
    };
    
    public final String[] Tense = 
    {
	"наст", "непрош", "прош"
    };
    
    public final String[] Case = 
    {
	"род", "дат", "вин", "твор", "пр", "парт", "местн", "зват"
    };
    
    public final String[] Number = 
    {
	"ед", "мн"
    };
    
    public final String[] VerbRepr = 
    {
	"деепр", "инф", "прич", "изъяв", "пов"
    };
    
    public final String[] AdjForm = 
    {
	"кр", "полн", "притяж"
    };
    
    public final String[] AdjDegree = 
    {
	"прев", "срав"
    };
    
    public final String[] VerbFace = 
    {
	"1-л", "2-л", "3-л"
    };
    
    public final String[] Gender = 
    {
	"муж", "жен", "сред"
    };
    
    public final String[] Aspect = 
    {
	"несов", "сов"
    };
    
    public final String[] Voice = 
    {
	"действ", "страд"
    };
    
    public final String[] Animacy = 
    {
	"од", "неод"
    };
    
    public final String[] Transitivity = 
    {
	"пе", "нп"
    };
    
    public final String[] AdditionalInfo = 
    {
	"гео", "затр", "имя", "искаж", "мж", "обсц", "отч", "прдк", "разг",
	"редк", "сокр", "устар", "фам"
    };
    

    /**
     * Creates formatted category string, ie malttab
     * @return formatted word characteristicks
     */
    public String getFormattedCategory();

    /**
     * Get origin word
     * @return word
     */
    public String getWord();

    /**
     * Get word lexem
     * @return lexem
     */
    public String getLex();

    public String getPos();

    public String getTense();

    public String getCase();

    public String getNumber();

    public String getVerbRepr();

    public String getVerbMood();

    public String getAdjForm();

    public String getAdjDegree();

    public String getVerbFace();

    public String getGender();

    public String getAspect();

    public String getVoice();

    public String getAnimacy();

    public String getTransitivity();

    public String getAdditionalInfo();

    public void setLex(String value);

    public void setPos(String value);

    public void setTense(String value);

    public void setCase(String value);

    public void setNumber(String value);

    public void setVerbRepr(String value);

    public void setVerbMood(String value);

    public void setAdjForm(String value);

    public void setAdjDegree(String value);

    public void setVerbFace(String value);

    public void setGender(String value);

    public void setAspect(String value);

    public void setVoice(String value);

    public void setAnimacy(String value);

    public void setTransitivity(String value);

    public void setAdditionalInfo(String value);
}
