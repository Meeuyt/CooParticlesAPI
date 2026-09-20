package cn.coostack.cooparticlesapi.cparticle.force

class CParticleForceResourceTable {
    companion object {
        const val MAX_TEXTURE_RESOURCES: Int = 16
    }
    fun resolve(resource: CParticleForceResource): Any? = null
    fun slotFor(resource: CParticleTextureResource): Int = -1
    fun slotFor(resource: CParticleFluidResource): Int = -1
    fun textureBinding(slot: Int): Any? = null
    fun fluidBinding(slot: Int): Any? = null
    fun textureBindings(): List<CParticleTextureResource> = emptyList()
    fun fluidBindings(): List<CParticleFluidResource> = emptyList()
    fun clear() {}
}
