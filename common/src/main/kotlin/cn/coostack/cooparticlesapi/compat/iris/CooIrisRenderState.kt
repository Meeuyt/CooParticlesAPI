package cn.coostack.cooparticlesapi.compat.iris

object CooIrisRenderState {
    fun snapshot(): IrisSnapshot = EmptySnapshot
    fun clear() {}
    fun clearFinalColor() {}

    object EmptySnapshot : IrisSnapshot {
        override fun terrainDepthTextureId(): Int = 0
        override fun terrainDepthWidth(): Int = 0
        override fun terrainDepthHeight(): Int = 0
        override fun sceneDepthTextureId(): Int = 0
        override fun sceneDepthWidth(): Int = 0
        override fun sceneDepthHeight(): Int = 0
        override fun noHandDepthTextureId(): Int = 0
        override fun noHandDepthWidth(): Int = 0
        override fun noHandDepthHeight(): Int = 0
        override fun finalColorTextureId(): Int = 0
        override fun finalColorWidth(): Int = 0
        override fun finalColorHeight(): Int = 0
        override fun finalColorFramebufferId(): Int = 0
    }
}

interface IrisSnapshot {
    fun terrainDepthTextureId(): Int
    fun terrainDepthWidth(): Int
    fun terrainDepthHeight(): Int
    fun sceneDepthTextureId(): Int
    fun sceneDepthWidth(): Int
    fun sceneDepthHeight(): Int
    fun noHandDepthTextureId(): Int
    fun noHandDepthWidth(): Int
    fun noHandDepthHeight(): Int
    fun finalColorTextureId(): Int
    fun finalColorWidth(): Int
    fun finalColorHeight(): Int
    fun finalColorFramebufferId(): Int
}
