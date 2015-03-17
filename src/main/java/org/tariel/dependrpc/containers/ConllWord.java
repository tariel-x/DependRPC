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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Nikita Gerasimov <n@tariel.ru>
 */
public class ConllWord implements IWord
{
    private String word;
    private String lex;
    private String pos;
    private String tense;
    private String lcase;
    private String number;
    private String verberp;
    private String verbmod;
    private String adjform;
    private String adjdegree;
    private String verbface;
    private String gender;
    private String aspect;
    private String voise;
    private String animacity;
    private String transitivity;
    private String addditionalinfo;
    private String other;
    
    private Integer id;

    private final Map<String, String> Translations = new HashMap<String, String>()
    {
	{
	    put("A", "A");
	    put("ADV", "ADV");
	    put("ADVPRO", "ADV");
	    put("ANUM", "A");
	    put("APRO", "A");
	    put("COM", "COM");
	    put("CONJ", "CONJ");
	    put("INTJ", "INTJ");
	    put("NUM", "NUM");
	    put("PART", "PART");
	    put("PR", "PR");
	    put("S", "S");
	    put("SPRO", "S");
	    put("прич", "VADJ");
	    put("деепр", "VADV");
	    put("V", "V");
	    put("ед", "sg");
	    put("мн", "pl");
	    put("жен", "f");
	    put("муж", "m");
	    put("сред", "n");
	    put("им", "nom");
	    put("род", "gen");
	    put("дат", "dat");
	    put("вин", "acc");
	    put("твор", "ins");
	    put("пр", "prep");
	    put("парт", "gen2");
	    put("местн", "loc");
	    put("од", "anim");
//	    put("неод", "inan");
	    put("инф", "VINF");
//	    put("прош", "pst");
//	    put("непрош", "npst");
	    put("наст", "prs");
	    put("1-л", "1p");
	    put("2-л", "2p");
	    put("3-л", "3p");
	    put("изъяв", "real");
	    put("пов", "imp");
	    put("кр", "shrt");
//	    put("несов", "imperf");
//	    put("сов", "perf");
	    put("страд", "pass");
	    put("срав", "comp");
	    put("прев", "supl");
	}
    };
    
    
    @Override
    public String getFormattedCategory()
    {
	//List of fields with order
	List<String> fieldList = new ArrayList<String>()
	{
	    {
		add("tense");
		add("lcase");
		add("number");
		add("verberp");
		add("other");
		add("adjdegree");
		add("verbface");
		add("gender");
		add("aspect");
		add("animacity");
	    }
	};
	List<String> translations = new ArrayList<String>();
	
	for (String fieldName: fieldList)
	{
	    try
	    {
		translations.add((String)ConllWord.class.getField(fieldName).get(this));
	    }
	    catch (NoSuchFieldException | IllegalAccessException ex)
	    {
		ex.printStackTrace();
	    }
	}
	
	return this.word + "\t" + StringUtils.join(translations, ".");
	
    }

    public String getWord()
    {
	return this.word;
    }

    public String getLex()
    {
	return this.lex;
    }

    public String getPos()
    {
	return this.pos;
    }

    public String getTense()
    {
	return this.tense;
    }

    public String getCase()
    {
	return this.lcase;
    }

    public String getNumber()
    {
	return this.number;
    }

    public String getVerbRepr()
    {
	return this.verberp;
    }

    public String getVerbMood()
    {
	return this.verbmod;
    }

    public String getAdjForm()
    {
	return this.adjform;
    }

    public String getAdjDegree()
    {
	return this.adjdegree;
    }

    public String getVerbFace()
    {
	return this.verbface;
    }

    public String getGender()
    {
	return this.gender;
    }

    public String getAspect()
    {
	return this.aspect;
    }

    public String getVoice()
    {
	return this.voise;
    }

    public String getAnimacy()
    {
	return this.animacity;
    }

    public String getTransitivity()
    {
	return this.transitivity;
    }

    public String getAdditionalInfo()
    {
	return this.addditionalinfo;
    }

    @Override
    public void setLex(String value)
    {
	this.lex = value;
    }

    @Override
    public void setPos(String value)
    {
	this.pos = value;
    }

    @Override
    public void setTense(String value)
    {
	this.tense = value;
    }

    @Override
    public void setCase(String value)
    {
	this.lcase = value;
    }

    @Override
    public void setNumber(String value)
    {
	this.number = value;
    }

    @Override
    public void setVerbRepr(String value)
    {
	this.verberp = value;
    }

    @Override
    public void setVerbMood(String value)
    {
	this.verbmod = value;
    }

    @Override
    public void setAdjForm(String value)
    {
	this.adjform = value;
    }

    @Override
    public void setAdjDegree(String value)
    {
	this.adjdegree = value;
    }

    @Override
    public void setVerbFace(String value)
    {
	this.verbface = value;
    }

    @Override
    public void setGender(String value)
    {
	this.gender = value;
    }

    @Override
    public void setAspect(String value)
    {
	this.aspect = value;
    }

    @Override
    public void setVoice(String value)
    {
	this.voise = value;
    }

    @Override
    public void setAnimacy(String value)
    {
	this.animacity = value;
    }

    @Override
    public void setTransitivity(String value)
    {
	this.transitivity = value;
    }

    @Override
    public void setAdditionalInfo(String value)
    {
	this.addditionalinfo = value;
    }
    
}
