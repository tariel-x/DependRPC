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
    /**
     * Creates malttab category string
     * @return comma-separated malltab word characteristicks
     */
    public String getMalttabCategory();

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
}
