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
package com.petercassetta.pdrebalanced.items.weapon.missiles;

import com.petercassetta.pdrebalanced.Dungeon;
import com.petercassetta.pdrebalanced.actors.Actor;
import com.petercassetta.pdrebalanced.actors.Char;
import com.petercassetta.pdrebalanced.actors.blobs.Blob;
import com.petercassetta.pdrebalanced.actors.blobs.Fire;
import com.petercassetta.pdrebalanced.actors.buffs.Buff;
import com.petercassetta.pdrebalanced.actors.buffs.Burning;
import com.petercassetta.pdrebalanced.items.Item;
import com.petercassetta.pdrebalanced.levels.Level;
import com.petercassetta.pdrebalanced.scenes.GameScene;
import com.petercassetta.pdrebalanced.sprites.ItemSpriteSheet;
import com.petercassetta.utils.Random;

public class IncendiaryDart extends MissileWeapon {

	{
		name = "incendiary dart";
		image = ItemSpriteSheet.INCENDIARY_DART;
		
		STR = 12;
		
		MIN = 1;
		MAX = 2;
	}
	
	public IncendiaryDart() {
		this( 1 );
	}
	
	public IncendiaryDart( int number ) {
		super();
		quantity = number;
	}
	
	@Override
	protected void onThrow( int cell ) {
		Char enemy = Actor.findChar( cell );
		if (enemy == null || enemy == curUser) {
			if (Level.flamable[cell]) {
				GameScene.add( Blob.seed( cell, 4, Fire.class ) );
			} else {
				super.onThrow( cell );
			}
		} else {
			if (!curUser.shoot( enemy, this )) {
				Dungeon.level.drop( this, cell ).sprite.drop();
			}
		}
	}
	
	@Override
	public void proc( Char attacker, Char defender, int damage ) {
		Buff.affect( defender, Burning.class ).reignite( defender );
		super.proc( attacker, defender, damage );
	}
	
	@Override
	public String desc() {
		return 
			"The spike on each of these darts is designed to pin it to its target " +
			"while the unstable compounds strapped to its length burst into brilliant flames.";
	}
	
	@Override
	public Item random() {
		quantity = Random.Int( 3, 6 );
		return this;
	}
	
	@Override
	public int price() {
		return 10 * quantity;
	}
}
