package com.github.catageek.ByteCart.AddressLayer;

import org.bukkit.block.Block;

import com.github.catageek.ByteCart.HAL.RegistryBoth;
import com.github.catageek.ByteCart.IO.AbstractComponent;
import com.github.catageek.ByteCart.IO.ComponentSign;


final class AddressSign extends AbstractComponent implements Address {
	
	private final AddressString Address;

	AddressSign(Block block, int ligne) {
		
		super(block.getLocation());
		
		this.Address = new AddressString((new ComponentSign(block)).getLine(ligne));
		
/*
			if(ByteCart.debug)
				ByteCart.log.info("ByteCart: creating AddressSign line #" + ligne + " at " + block.getLocation().toString());
	*/	
	}
	
	@Override
	public final RegistryBoth getRegion() {
		return Address.getRegion();
	}

	@Override
	public final RegistryBoth getTrack() {
		return Address.getTrack();
	}

	@Override
	public final RegistryBoth getStation() {
		return Address.getStation();
	}


	@Override
	public final boolean setAddress(String s) {
		this.Address.setAddress(s);
		return true;
	}

	@Override
	public boolean setAddress(String s, String name) {
		(new ComponentSign(this.getLocation().getBlock())).setLine(2, name);		
		return setAddress(s);
	}

	@Override
	public boolean isTrain() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setAddress(com.github.catageek.ByteCart.AddressLayer.Address a, String name) {
		return this.setAddress(a.toString(), name);
	}

	@Override
	public boolean setTrain(boolean istrain) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public final void finalizeAddress() {
		(new ComponentSign(this.getLocation().getBlock())).setLine(3, this.Address.toString());		
	}

	@Override
	public boolean isValid() {
		return this.Address.isValid;
	}

	@Override
	public void remove() {
		this.Address.remove();
		(new ComponentSign(this.getLocation().getBlock())).setLine(3, "");		
	}
	
	@Override
	final public String toString() {
		return Address.toString();
	}

	@Override
	public boolean isReturnable() {
		return false;
	}
}