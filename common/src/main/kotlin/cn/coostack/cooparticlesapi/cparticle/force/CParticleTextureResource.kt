package cn.coostack.cooparticlesapi.cparticle.force

import cn.coostack.cooparticlesapi.cparticle.force.CParticleForceResourceTable.CParticleTextureBinding

class CParticleTextureResource(val id: String) : CParticleTextureBinding {
    override fun sampleTexture(x: Double, y: Double, z: Double, out: FloatArray) {}
    fun bindCompute(index: Int) {}
    fun resetCompute() {}
}
