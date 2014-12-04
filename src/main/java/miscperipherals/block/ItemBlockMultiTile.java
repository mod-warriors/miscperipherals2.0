package miscperipherals.block;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBlockMultiTile extends ItemBlock {
	private final BlockMultiTile block;
	
	public ItemBlockMultiTile(Block blockinit) {
		super(blockinit);
		setMaxDamage(0);
		setHasSubtypes(true);
		
		if (blockinit instanceof BlockMultiTile) {
			block = (BlockMultiTile)blockinit;
		} else throw new IllegalArgumentException("Not assigned to a multi-tile block, assigned to "+blockinit+" ["+blockinit+"]");
	}

	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if (block.data[meta] == null) return "[Invalid Block]";
		else return StatCollector.translateToLocal("miscperipherals."+block.data[stack.getItemDamage()].name+".name");
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean debug) {
		int meta = stack.getItemDamage();
		if (block.data[meta] != null) info.addAll(Arrays.asList(block.data[meta].infoText));
	}
}
