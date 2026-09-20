package cn.coostack.cooparticlesapi.platform

import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.ChunkPos

interface ServerNetworking {
    fun send(packet: Any, to: ServerPlayer)
    fun sendAllPlayers(packet: Any)
    fun sendToPlayersTrackingChunk(world: ServerLevel, chunk: ChunkPos, packet: Any)

    fun sendToWorld(world: ServerLevel, packet: Any) {
        world.players().forEach { send(packet, it) }
    }
}
