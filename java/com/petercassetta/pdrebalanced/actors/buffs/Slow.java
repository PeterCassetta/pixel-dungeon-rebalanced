/*
 * Pixel Dungeon: Rebalanced
 * Copyright (C) 2012-2015 Oleg Dolya
 * Copyright (C) 2015 Peter Cassetta
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.petercassetta.pdrebalanced.actors.buffs;

import com.petercassetta.pdrebalanced.actors.Char;
import com.petercassetta.pdrebalanced.items.rings.RingOfElements.Resistance;
import com.petercassetta.pdrebalanced.ui.BuffIndicator;

public class Slow extends FlavourBuff {

	private static final float DURATION = 10f;

	@Override
	public int icon() {
		return BuffIndicator.SLOW;
	}
	
	@Override
	public String toString() {
		return "Slowed";
	}

	public static float duration( Char ch ) {
		Resistance r = ch.buff( Resistance.class );
		return r != null ? r.durationFactor() * DURATION : DURATION;
	}
}
