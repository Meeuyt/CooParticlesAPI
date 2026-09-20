package cn.coostack.cooparticlesapi.network.particle.composition

import cn.coostack.cooparticlesapi.annotations.composition.handler.ParticleCompositionRegistryHelper
import net.minecraft.network.PacketByteBuf

import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

abstract class AutoSequencedParticleComposition(position: Vec3, world: Level? = null) :
    SequencedParticleComposition(position, world) {

    override fun getCodec(): cn.coostack.cooparticlesapi.network.packet.api.CommonCodec<ParticleComposition> {
        return ParticleCompositionRegistryHelper.generateCodec(this)
    }

}