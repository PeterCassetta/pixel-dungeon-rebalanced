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
package com.petercassetta.pdrebalanced.actors.blobs;

import com.petercassetta.pdrebalanced.Dungeon;
import com.petercassetta.pdrebalanced.actors.Actor;
import com.petercassetta.pdrebalanced.actors.Char;
import com.petercassetta.pdrebalanced.actors.buffs.Buff;
import com.petercassetta.pdrebalanced.actors.buffs.Roots;
import com.petercassetta.pdrebalanced.effects.BlobEmitter;
import com.petercassetta.pdrebalanced.effects.particles.LeafParticle;
import com.petercassetta.pdrebalanced.levels.Level;
import com.petercassetta.pdrebalanced.levels.Terrain;
import com.petercassetta.pdrebalanced.scenes.GameScene;

public class Regrowth extends Blob {
	
	@Override
	protected void evolve() {
		super.evolve();
		
		if (volume > 0) {
			
			boolean mapUpdated = false;
			
			for (int i=0; i < LENGTH; i++) {
				if (off[i] > 0) {
					int c = Dungeon.level.map[i];
					if (c == Terrain.EMPTY || c == Terrain.EMBERS || c == Terrain.EMPTY_DECO) {
						
						Level.set( i, cur[i] > 9 ? Terrain.HIGH_GRASS : Terrain.GRASS );
						mapUpdated = true;
						
					} else if (c == Terrain.GRASS && cur[i] > 9) {
						
						Level.set( i, Terrain.HIGH_GRASS );
						mapUpdated = true;
						
					}
					
					Char ch = Actor.findChar( i );
					if (ch != null) {
						Buff.prolong( ch, Roots.class, TICK );
					}
				}
			}
			
			if (mapUpdated) {
				GameScene.updateMap();
				Dungeon.observe();
			}
		}
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		
		emitter.start( LeafParticle.LEVEL_SPECIFIC, 0.2f, 0 );
	}
}
