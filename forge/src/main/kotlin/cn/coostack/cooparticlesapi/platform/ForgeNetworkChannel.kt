package cn.coostack.cooparticlesapi.platform

import cn.coostack.cooparticlesapi.network.packet.api.CooPacket
import cn.coostack.cooparticlesapi.network.packet.api.envelope.CooPacketEnvelopeC2S
import cn.coostack.cooparticlesapi.network.packet.api.envelope.CooPacketEnvelopeS2C
import net.minecraft.network.PacketByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.network.NetworkEvent
import net.minecraftforge.network.simple.SimpleChannel

class ForgeNetworkChannel(private val modId: String) {
    val channel: SimpleChannel = net.minecraftforge.network.NetworkRegistry.newSimpleChannel(
        ResourceLocation(modId, "main"),
        { true },
        { true },
        { true }
    )
    private var nextId = 0

    fun registerEnvelopeS2C(handler: (CooPacketEnvelopeS2C) -> Unit = {}) {
        channel.registerMessage(nextId++, CooPacketEnvelopeS2C::class.java,
            { packet, buf -> CooPacketEnvelopeS2C.write(buf, packet) },
            { buf -> CooPacketEnvelopeS2C.read(buf) },
            { packet, ctx ->
                handler(packet)
                ctx.packetHandled = true
            }
        )
    }

    fun registerEnvelopeC2S(handler: (CooPacketEnvelopeC2S, ServerPlayer) -> Unit = { _, _ -> }) {
        channel.registerMessage(nextId++, CooPacketEnvelopeC2S::class.java,
            { packet, buf -> CooPacketEnvelopeC2S.write(buf, packet) },
            { buf -> CooPacketEnvelopeC2S.read(buf) },
            { packet, ctx ->
                if (ctx.sender != null) {
                    handler(packet, ctx.sender)
                }
                ctx.packetHandled = true
            }
        )
    }

    fun sendTo(player: net.minecraft.server.level.ServerPlayer, packet: CooPacket) {
        channel.sendTo(player, packet)
    }

    fun sendToAll(packet: CooPacket) {
        channel.sendToAll(packet)
    }

    fun sendToServer(packet: CooPacket) {
        channel.sendToServer(packet)
    }

    fun sendEnvelopeC2S(packet: CooPacketEnvelopeC2S) {
        channel.sendToServer(packet)
    }
}
