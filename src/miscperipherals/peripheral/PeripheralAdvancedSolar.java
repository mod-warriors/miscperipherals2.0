package miscperipherals.peripheral;

import miscperipherals.upgrade.UpgradeAdvancedSolar;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.TurtleSide;

public class PeripheralAdvancedSolar extends PeripheralSolar {
	private final int type;
	
	public PeripheralAdvancedSolar(ITurtleAccess turtle, TurtleSide side, int type) {
		super(turtle, side);
		
		this.type = type;
	}
	
	@Override
	boolean canUpdate() {
		return skyVisible;
	}
	
	@Override
	int getProduction() {
		return sunVisible ? UpgradeAdvancedSolar.OUTPUT_DAY[type] : UpgradeAdvancedSolar.OUTPUT_NIGHT[type];
	}
}
