package net.rsprot.protocol.game.outgoing.codec.zone.payload

import net.rsprot.buffer.JagByteBuf
import net.rsprot.protocol.ServerProt
import net.rsprot.protocol.game.outgoing.prot.GameServerProt
import net.rsprot.protocol.game.outgoing.zone.payload.MapProjAnim
import net.rsprot.protocol.common.game.outgoing.codec.zone.payload.ZoneProtEncoder

public class MapProjAnimEncoder : ZoneProtEncoder<MapProjAnim> {
    override val prot: ServerProt = GameServerProt.MAP_PROJANIM

    override fun encode(
        buffer: JagByteBuf,
        message: MapProjAnim,
    ) {
        // The function at the bottom of the MAP_PROJANIM has a consistent order,
        // making it easy to identify all the properties of this packet:
        // map_projanim(level, startX, startZ, endX, endZ, targetIndex, id,
        // startHeight, endHeight, startTime, endTime, angle, progress, sourceIndex)
        buffer.p1Alt2(message.endHeight)
        buffer.p1Alt2(message.deltaZ)
        buffer.p2Alt1(message.progress)
        buffer.p2Alt1(message.endTime)
        buffer.p1Alt1(message.startHeight)
        buffer.p3Alt1(message.targetIndex)
        buffer.p3Alt2(message.sourceIndex)
        buffer.p1Alt3(message.coordInZonePacked)
        buffer.p1Alt1(message.deltaX)
        buffer.p1(message.angle)
        buffer.p2Alt2(message.id)
        buffer.p2Alt1(message.startTime)
    }
}
