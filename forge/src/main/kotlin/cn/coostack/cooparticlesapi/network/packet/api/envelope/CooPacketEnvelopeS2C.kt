package cn.coostack.cooparticlesapi.network.packet.api.envelope

import cn.coostack.cooparticlesapi.CooParticlesConstants
import cn.coostack.cooparticlesapi.network.packet.api.CooPacket
import net.minecraft.network.PacketByteBuf
import net.minecraft.resources.ResourceLocation

class CooPacketEnvelopeS2C(
    val kindId: Int,
    val packetId: ResourceLocation,
    val correlationId: Long,
    val timeoutTicks: Int,
    val data: ByteArray,
) : CooPacket() {

    companion object {
        private val ID = ResourceLocation.fromNamespaceAndPath(
            CooParticlesConstants.MOD_ID, "coo_packet_envelope_s2c"
        )

        @JvmStatic
        fun write(buf: PacketByteBuf, packet: CooPacketEnvelopeS2C) {
            buf.writeVarInt(packet.kindId)
            buf.writeResourceLocation(packet.packetId)
            buf.writeLong(packet.correlationId)
            buf.writeVarInt(packet.timeoutTicks)
            buf.writeByteArray(packet.data)
        }

        @JvmStatic
        fun read(buf: PacketByteBuf): CooPacketEnvelopeS2C {
            return CooPacketEnvelopeS2C(
                kindId = buf.readVarInt(),
                packetId = buf.readResourceLocation(),
                correlationId = buf.readLong(),
                timeoutTicks = buf.readVarInt(),
                data = buf.readByteArray()
            )
        }
    }

    override fun id(): ResourceLocation = ID
}
