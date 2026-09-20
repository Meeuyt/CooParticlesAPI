package cn.coostack.cooparticlesapi.cparticle.force

import cn.coostack.cooparticlesapi.cparticle.force.CParticleForceResourceTable.CParticleFluidBinding

class CParticleFluidResource(val id: String) : CParticleFluidBinding {
    override fun sampleFluid(x: Double, y: Double, z: Double, out: FloatArray) {}
    fun bindCompute(index: Int) {}
    fun resetCompute() {}
}
