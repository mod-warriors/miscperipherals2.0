package miscperipherals.network;

import io.netty.buffer.ByteBuf;
import miscperipherals.core.MiscPeripherals;
import miscperipherals.core.Module;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class NetworkHelper {
	public static S34PacketMaps getTileInfoPacket(TileEntity tile) {
		if (!(tile instanceof IEntityAdditionalSpawnData)) throw new IllegalArgumentException("Tile information packets require a TileEntity implementing IEntityAdditionalSpawnData");
		IEntityAdditionalSpawnData data = (IEntityAdditionalSpawnData)tile;
		
		ByteBuf os = (ByteBuf) ByteStreams.newDataOutput();
		os.writeInt(tile.xCoord);
		os.writeInt(tile.yCoord);
		os.writeInt(tile.zCoord);
		data.writeSpawnData(os);
		
		return PacketDispatcher.getTinyPacket(MiscPeripherals.instance, (short)3, ((ByteArrayDataOutput) os).toByteArray());
	}
	
	public static S34PacketMaps getModulePacket(Module module, byte[] data) {
		ByteArrayDataOutput os = ByteStreams.newDataOutput();
		os.writeUTF(module.getClass().getSimpleName().substring(6));
		os.write(data);
		
		return PacketDispatcher.getTinyPacket(MiscPeripherals.instance, (short)6, os.toByteArray());
	}
}
