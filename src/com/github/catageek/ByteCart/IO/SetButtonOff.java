package com.github.catageek.ByteCart.IO;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.material.Button;

import com.github.catageek.ByteCart.Util.MathUtil;

// this call represents a thread that powers off a button

public class SetButtonOff implements Runnable {

	final private Component component;
	final private Map<Location, Integer> ActivatedButtonMap;

	public SetButtonOff(Component component, Map<Location, Integer> ActivatedButtonMap){
		this.component = component;
		this.ActivatedButtonMap = ActivatedButtonMap;
	}

	@Override
	public void run() {

		BlockState block = component.getBlock().getState();

		if (block.getData() instanceof Button) {
			Button button = (Button) block.getData();

			button.setPowered(false);
			block.setData(button);

			block.update(false, true);
			MathUtil.forceUpdate(component.getBlock().getRelative(button.getAttachedFace()));
		}

		ActivatedButtonMap.remove(block.getLocation());
	}
}
