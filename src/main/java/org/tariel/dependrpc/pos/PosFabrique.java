/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.tariel.dependrpc.pos;

/**
 *
 * @author Nikita Gerasimov <tariel-x@ya.ru>
 */
public class PosFabrique
{
    public static IPos getPos()
    {
	return new JMystem();
    }
}
