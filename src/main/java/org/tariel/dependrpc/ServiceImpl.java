/*
 * Copyright (C) 2014 Nikita Gerasimov <tariel-x@ya.ru>
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

package org.tariel.dependrpc;

import java.io.File;
import java.net.URL;

import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.maltparser.concurrent.ConcurrentMaltParserService;
import org.maltparser.concurrent.ConcurrentUtils;
import org.maltparser.concurrent.graph.ConcurrentDependencyGraph;

import java.util.*;
import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.tariel.dependrpc.server.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tariel.dependrpc.containers.ISentence;
import org.tariel.dependrpc.containers.SentenceFabrique;
import org.tariel.jsonconfig.JsonConfig;


/**
 * RPC Implementation
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class ServiceImpl implements ParseServer.Iface
{
    private static Logger log = LoggerFactory.getLogger(ServiceImpl.class);
    
    /**
     * Models container
     */
    private ModelsContainer Models = null;
        
    public ServiceImpl()
    {
	Models = new ModelsContainer();
    }
    
    /**
     * Returns server information
     * @return list with server info
     */
    @Override
    public List<String> getServerInfo()
    {
	return new ArrayList<String>();
    }
    
    /**
     * Parse text into sentence structure
     * @param language sentence language
     * @param text one sentence
     * @return list of CoNLL strings
     */
    @Override 
    public List<String> ParseText(String language, String text)
    {
	ISentence sentence = SentenceFabrique.getSentence();
	sentence.parseSentence(text);
	String[] tokens = sentence.getFormattedSentence().split("\n");
	//ConcurrentDependencyGraph outputGraph = null;
	String[] outputTokens = null;
	try {
		outputTokens = Models.getModel(language).parseTokens(tokens);
		
		//outputGraph = model.parse(tokens);
		//System.out.println(outputGraph);
	} catch (Exception e) {
		e.printStackTrace();
	}
	log.info("Parser sentence, language: " + language);
	return Arrays.asList(outputTokens);
	//ConcurrentUtils.printTokens(outputTokens);
    }
}
