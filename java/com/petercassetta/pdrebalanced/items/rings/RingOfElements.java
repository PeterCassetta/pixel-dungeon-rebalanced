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
package com.petercassetta.pdrebalanced.items.rings;

import java.util.HashSet;

import com.petercassetta.pdrebalanced.actors.blobs.ToxicGas;
import com.petercassetta.pdrebalanced.actors.buffs.Burning;
import com.petercassetta.pdrebalanced.actors.buffs.Poison;
import com.petercassetta.pdrebalanced.actors.mobs.Eye;
import com.petercassetta.pdrebalanced.actors.mobs.Warlock;
import com.petercassetta.pdrebalanced.actors.mobs.Yog;
import com.petercassetta.pdrebalanced.levels.traps.LightningTrap;
import com.petercassetta.utils.Random;

public class RingOfElements extends Ring {

	{
		name = "Ring of Elements";
	}
	
	@Override
	protected RingBuff buff( ) {
		return new Resistance();
	}
	
	@Override
	public String desc() {
		return isKnown() ?
			"This ring provides resistance to different elements, such as fire, " +
			"electricity, gases etc. Also it decreases duration of negative effects." :
			super.desc();
	}

	private static final HashSet<Class<?>> EMPTY = new HashSet<Class<?>>();
	private static final HashSet<Class<?>> FULL;
	static {
		FULL = new HashSet<Class<?>>();
		FULL.add( Burning.class );
		FULL.add( ToxicGas.class );
		FULL.add( Poison.class );
		FULL.add( LightningTrap.Electricity.class );
		FULL.add( Warlock.class );
		FULL.add( Eye.class );
		FULL.add( Yog.BurningFist.class );
	}
	
	public class Resistance extends RingBuff {
		
		public HashSet<Class<?>> resistances() {
			if (Random.Int( level + 3 ) >= 3) {
				return FULL;
			} else {
				return EMPTY;
			}
		}
		
		public float durationFactor() {
			return level < 0 ? 1 : (2 + 0.5f * level) / (2 + level);
		}
	}
}
