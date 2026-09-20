package cn.coostack.cooparticlesapi.network.particle.emitters.environment.wind

import cn.coostack.cooparticlesapi.network.particle.emitters.ParticleEmitters
import com.ezylang.evalex.Expression
import net.minecraft.network.PacketByteBuf

import net.minecraft.world.phys.Vec3
import kotlin.math.exp

class GlobalWindDirection(
    override var direction: Vec3
) : WindDirection {
    override var relative: Boolean = false
    override var windSpeedExpress: String = "1"
    override fun loadEmitters(emitters: ParticleEmitters): WindDirection {
        this.emitters = emitters
        return this
    }

    private var emitters: ParticleEmitters? = null

    companion object {
        @JvmStatic
        val CODEC = cn.coostack.cooparticlesapi.network.packet.api.CommonCodec.of(
            { buf, data ->
                buf.writeVec3(data.direction)
                buf.writeBoolean(data.relative)
                buf.writeUtf(data.windSpeedExpress)
            }, {
                val direction = it.readVec3()
                val relative = it.readBoolean()
                val express = it.readUtf()
                GlobalWindDirection(direction).apply {
                    this.relative = relative
                    this.windSpeedExpress = express
                }
            }
        )
        const val ID = "global"
    }

    override fun getID(): String {
        return ID
    }
    override fun hasLoadedEmitters(): Boolean {
        return emitters != null
    }
    override fun getWind(particlePos: Vec3): Vec3 {
        if (relative) {
            val pos = emitters?.pos ?: return direction
            val dir = particlePos.subtract(pos)
            val express = Expression(windSpeedExpress)
                .with("l", dir.length())
                .evaluate().numberValue.toDouble()
            dir.normalize().scale(express)
            return dir
        }
        return direction
    }

    override fun inRange(pos: Vec3): Boolean {
        return true
    }

    override fun getCodec(): cn.coostack.cooparticlesapi.network.packet.api.CommonCodec<WindDirection> {
        return CODEC
    }
}