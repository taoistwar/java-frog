/*
 * Copyright 2018 the original author or authors. 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.frog.brain.organ;

import com.github.drinkjava2.frog.Frog;
import com.github.drinkjava2.frog.brain.Cell;
import com.github.drinkjava2.frog.brain.Input;
import com.github.drinkjava2.frog.brain.Organ;
import com.github.drinkjava2.frog.util.RandomUtils;

/**
 * Hungry will active cell's inputs, if frog's energy not enough
 */
public class Hungry extends Organ {
	private static final long serialVersionUID = 1L;

	@Override
	public void initFrog(Frog f) { // 仅在Frog生成时这个方法会调用一次，缺省啥也不干，通常用于Group子类的初始化
		if (!initilized) {
			initilized = true;
			organOutputEnergy = 2;
		}
	}

	@Override
	public Organ[] vary() {
		if (RandomUtils.percent(20)) // 有20机率权重变化
			organOutputEnergy = RandomUtils.vary(organOutputEnergy);
		return new Organ[] { this };
	}

	@Override
	public void active(Frog f) {
		if (f.frogEngery < 100000000)// 所有的硬编码都是bug，包括这个100000000
			for (Cell cell : f.cells) {
				if (cell.energy > 0)
					cell.energy--;
				if (cell.energy < Cell.MAX_ENERGY_LIMIT)
					for (Input input : cell.inputs)
						if (input.nearby(this)) // input zone near by hungry zone
							cell.energy += organOutputEnergy;
			}
	}

}
