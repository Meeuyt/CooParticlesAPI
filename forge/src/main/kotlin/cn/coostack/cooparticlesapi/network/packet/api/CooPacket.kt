package cn.coostack.cooparticlesapi.network.packet.api

import cn.coostack.cooparticlesapi.annotations.packet.CooPacketRegistryHelper
import cn.coostack.cooparticlesapi.network.packet.api.CommonCodec
import net.minecraft.resources.ResourceLocation

abstract class CooPacket {
    abstract fun id(): ResourceLocation
    open fun codec(): CommonCodec<out CooPacket> =
        CooPacketRegistryHelper.generateClassParticleCodec(this::class.java)
    open fun onClientReceive(context: ClientContext) {}
    open fun onServerReceive(context: ServerContext) {}
}
