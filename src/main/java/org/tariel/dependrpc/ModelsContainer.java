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

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.maltparser.concurrent.ConcurrentMaltParserService;
import org.maltparser.concurrent.ConcurrentUtils;
import org.maltparser.concurrent.graph.ConcurrentDependencyGraph;
import org.tariel.jsonconfig.JsonConfig;

/**
 * Models container
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class ModelsContainer
{
    private static Logger log = LoggerFactory.getLogger(ModelsContainer.class);
    
    private Map<String, ConcurrentMaltParserModel> Models = null;
    
    public ModelsContainer()
    {
	Models = new HashMap<String, ConcurrentMaltParserModel>();
	try 
	{
	    Map<String, String> models = JsonConfig.get("models").asStringList();
	    for (Map.Entry<String, String> entry : models.entrySet())
	    {
		this.loadModel(entry.getKey());//load all mentioned models
	    }
		
	} catch (Exception e) 
	{
	    e.printStackTrace();
	}
    }
    
    /**
     * Load model if doesn't exist
     * @param language model language
     */
    public void loadModel(String language)
    {
	if (!this.Models.containsKey(language))
	{
	    try 
	    {
		URL swemaltMiniModelURL = new File(JsonConfig.get("models").get(language).asStr()).toURI().toURL();
		Models.put(language, ConcurrentMaltParserService.initializeParserModel(swemaltMiniModelURL));
	    } catch (Exception e) 
	    {
		e.printStackTrace();
	    }
	} 
	log.info(language + " model loaded");
    }
    
    /**
     * Load model or reload if exists
     * @param language model language
     * @param reload reload model
     */
    public void loadModel(String language, Boolean reload)
    {
	if (!this.Models.containsKey(language) || reload == true)
	{
	    this.loadModel(language);
	}    
    }
    
    /**
     * Returns language model
     * @param language model language
     * @return MaltModel
     */
    public ConcurrentMaltParserModel getModel(String language)
    {
	if (!this.Models.containsKey(language))
	    this.loadModel(language);
	return this.Models.get(language);
    }
}
