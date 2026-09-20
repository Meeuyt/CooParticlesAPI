package cn.coostack.cooparticlesapi.network.packet.server

import cn.coostack.cooparticlesapi.CooParticlesConstants
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.PacketByteBuf

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.phys.Vec3

/**
 *
 */
class PacketParticleS2C(
    val type: ParticleOptions,
    val pos: Vec3,
    val velocity: Vec3,
)  {
    companion object {
        private val identifierID = ResourceLocation.fromNamespaceAndPath(CooParticlesConstants.MOD_ID, "particle")
        val payloadID = ResourceLocation(identifierID)
        val CODEC: cn.coostack.cooparticlesapi.network.packet.api.CommonCodec<PacketParticleS2C> =
            cn.coostack.cooparticlesapi.network.packet.api.CommonCodec.of({ buf, packet ->
                buf.writeVec3(packet.pos)
                buf.writeVec3(packet.velocity)
                ParticleTypes.STREAM_CODEC.encode(buf, packet.type)
            }, { buf ->
                val pos = buf.readVec3()
                val velocity = buf.readVec3()
                val type = ParticleTypes.STREAM_CODEC.decode(buf)
                PacketParticleS2C(type, pos, velocity)
            })

    }
}