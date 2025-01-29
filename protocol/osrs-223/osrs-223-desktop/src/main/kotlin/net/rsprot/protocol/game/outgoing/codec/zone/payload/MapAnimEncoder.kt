package net.rsprot.protocol.game.outgoing.codec.zone.payload

import net.rsprot.buffer.JagByteBuf
import net.rsprot.protocol.ServerProt
import net.rsprot.protocol.internal.game.outgoing.codec.zone.payload.ZoneProtEncoder
import net.rsprot.protocol.game.outgoing.prot.GameServerProt
import net.rsprot.protocol.game.outgoing.zone.payload.MapAnim

public class MapAnimEncoder : ZoneProtEncoder<MapAnim> {
    override val prot: ServerProt = GameServerProt.MAP_ANIM

    override fun encode(
        buffer: JagByteBuf,
        message: MapAnim,
    ) {
        // While MAP_ANIM does not have a common function like the rest,
        // the constructor for the SpotAnimation object itself has the following order:
        // SpotAnimation(id, level, fineX, fineZ, getGroundHeight(fineX, fineZ, level) - height, delay, cycle)
        buffer.p1Alt1(message.coordInZonePacked)
        buffer.p1Alt2(message.height)
        buffer.p2Alt2(message.delay)
        buffer.p2Alt1(message.id)
    }
}
