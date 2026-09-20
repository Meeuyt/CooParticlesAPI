package cn.coostack.cooparticlesapi.network.particle.emitters.type

import net.minecraft.network.PacketByteBuf

import net.minecraft.world.phys.Vec3


class LineEmittersShootType(val dir: Vec3, val step: Double) : EmittersShootType {
    companion object {
        @JvmStatic
        val CODEC: cn.coostack.cooparticlesapi.network.packet.api.CommonCodec<EmittersShootType> =
            cn.coostack.cooparticlesapi.network.packet.api.CommonCodec.of(
                { buf, type ->
                    type as LineEmittersShootType
                    buf.writeVec3(type.dir)
                    buf.writeDouble(type.step)

                }, {
                    val dir = it.readVec3()
                    val step = it.readDouble()
                    LineEmittersShootType(dir, step)
                }
            )
        const val ID = "line"
    }

    override fun getID(): String {
        return ID
    }

    override fun getCodec(): cn.coostack.cooparticlesapi.network.packet.api.CommonCodec<EmittersShootType> {
        return CODEC
    }

    override fun getPositions(
        origin: Vec3,
        tick: Int,
        count: Int
    ): List<Vec3> {
        return List(count) {
            origin.add(dir.normalize().scale(it * step))
        }
    }

    override fun getDefaultDirection(
        enter: Vec3,
        tick: Int,
        pos: Vec3,
        origin: Vec3
    ): Vec3 {
        return enter
    }
}