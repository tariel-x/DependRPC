/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.tariel.jsonconfig.JsonConfig;


/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class ServiceImpl implements ParseServer.Iface
{
    private static Logger log = LoggerFactory.getLogger(App.class);
    
    ConcurrentMaltParserModel model = null;
    
    public ServiceImpl()
    {
	try 
	{
		URL swemaltMiniModelURL = new File("model/engmalt.linear-1.7.mco").toURI().toURL();
		model = ConcurrentMaltParserService.initializeParserModel(swemaltMiniModelURL);
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
	System.out.println( "Loaded model" );
    }
    
    @Override
    public List<String> getServerInfo()
    {
	return new ArrayList<String>();
    }
    
    @Override 
    public List<String> ParseText(String language, List<String> text)
    {
	String[] tokens = text.toArray(new String[text.size()]);
	//ConcurrentDependencyGraph outputGraph = null;
	String[] outputTokens = null;
	try {
		outputTokens = model.parseTokens(tokens);
		
		//outputGraph = model.parse(tokens);
		//System.out.println(outputGraph);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Arrays.asList(outputTokens);
	//ConcurrentUtils.printTokens(outputTokens);
    }
}
