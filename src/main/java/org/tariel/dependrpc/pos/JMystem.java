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
import java.util.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.google.gson.*;

import org.apache.commons.lang.ArrayUtils;

import org.tariel.dependrpc.containers.ISentence;
import org.tariel.dependrpc.containers.IWord;
import org.tariel.dependrpc.containers.SentenceFabrique;
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
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public ISentence parseSentence(String sentence)
    {
	String readyJson = new String();
	try
	{
	    Process process = new ProcessBuilder(this.executable, "-i", "--format=json").start();
	    InputStream is = process.getInputStream();
	    OutputStream os = process.getOutputStream();
	    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
	    OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
	    BufferedWriter bw = new BufferedWriter(osw);
	    BufferedReader br = new BufferedReader(isr);
	    bw.write(sentence);
	    bw.close();
	    String line = new String();

	    while ((line = br.readLine()) != null)
	    {
		if (!line.isEmpty())
		{
		    readyJson = line;
		}
	    }

	} catch (IOException ex)
	{
	    ex.printStackTrace();
	}
	List<JsonWord> sent = this.processJsonOutput(readyJson);
	return this.createSentence(sent);
    }

    /**
     * Converts temp words list into ISentence
     * @param sent List<JsonWord> list of parsed words from mystem
     * @return ISentence ready sentence
     */
    private ISentence createSentence(List<JsonWord> sent)
    {
	ISentence sentence = SentenceFabrique.getSentence();
	for (JsonWord entry : sent)
	{
	    Class cls = IWord.class;
	    Class[] paramString = new Class[1];	
	    paramString[0] = String.class;
	    
	    IWord tmpWord = SentenceFabrique.getWord(entry.text);
	    tmpWord.setLex(entry.analysis.lex);
	    String[] gr = entry.analysis.gr.split(",");
	    //Clear array from "=" symbols
	    int index = 0;
	    for (String grstr : gr)
	    {
		if (grstr.contains("="))
		{
		    String[] eqSymParts = grstr.split("=");
		    grstr = eqSymParts[0];
		    gr = (String[])ArrayUtils.remove(gr, index);
		    gr = (String[])ArrayUtils.addAll(gr, eqSymParts);
		}
		index++;
	    }
	    //Parse array into IWord instance
	    for (String grstr : gr)
	    {
		try
		{
		    //Recogise current mystem propertie and set such one in IWord
		    for (Field field : IWord.class.getDeclaredFields())
		    {
			List<String> values = Arrays.asList((String[])field.get(tmpWord));
			if ( values.contains(grstr) )
			{
			    Method method = cls.getDeclaredMethod("set" + field.getName(), paramString);
			    method.invoke(tmpWord, grstr);
			}
		    }
		} catch (Exception ex)
		{
		    ex.printStackTrace();
		}
		
	    }
	    sentence.appendWord(tmpWord);
	}
	return sentence;
    }
    
    /**
     * Parses mystem output into temp word instances
     * @param in Mystem JSON Sentence
     * @return List<JsonWord> words
     */
    private List<JsonWord> processJsonOutput(String in)
    {
	JsonParser Unserializer_ = new JsonParser();
	JsonElement element = Unserializer_.parse(in);
	JsonArray arr = element.getAsJsonArray();

	List<JsonWord> words = new ArrayList<JsonWord>();

	for (JsonElement analysis : arr)
	{
	    JsonWord tmpWord = new JsonWord();
	    for (Map.Entry<String, JsonElement> entry : analysis.getAsJsonObject().entrySet())
	    {
		if (entry.getKey().equals("analysis"))
		{
		    if (entry.getValue().getAsJsonArray().size() > 0)
		    {
			tmpWord.analysis = new JsonAnalysis();
			Set<Map.Entry<String, JsonElement>> analysis_parts = entry.getValue()
				.getAsJsonArray()
				.get(0)
				.getAsJsonObject()
				.entrySet();
			for (Map.Entry<String, JsonElement> part : analysis_parts)
			{
			    if (part.getKey().equals("lex"))
			    {
				tmpWord.analysis.lex = part.getValue().getAsString();
			    } else
			    {
				tmpWord.analysis.gr = part.getValue().getAsString();
			    }
			}
		    }
		} else
		{
		    tmpWord.text = entry.getValue().getAsString();
		}
	    }
	    words.add(tmpWord);
	}

	return words;
    }
}

/**
 * Analysis output structure of mystem
 * @author Nikita Gerasimov <n@tariel.ru>
 */
class JsonAnalysis
{

    public String lex;
    public String gr;
}

class JsonWord
{

    public JsonAnalysis analysis;
    public String text;
}
