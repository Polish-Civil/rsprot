package net.rsprot.protocol.game.outgoing.codec.npcinfo

import net.rsprot.buffer.bitbuffer.BitBuf
import net.rsprot.protocol.common.client.OldSchoolClientType
import net.rsprot.protocol.internal.game.outgoing.info.CoordGrid
import net.rsprot.protocol.internal.game.outgoing.info.npcinfo.NpcAvatarDetails
import net.rsprot.protocol.internal.game.outgoing.info.npcinfo.encoder.NpcResolutionChangeEncoder
import kotlin.math.min

public class DesktopLowResolutionChangeEncoder :
	net.rsprot.protocol.internal.game.outgoing.info.npcinfo.encoder.NpcResolutionChangeEncoder {
    override val clientType: OldSchoolClientType = OldSchoolClientType.DESKTOP

    override fun encode(
	    bitBuffer: BitBuf,
	    details: net.rsprot.protocol.internal.game.outgoing.info.npcinfo.NpcAvatarDetails,
	    extendedInfo: Boolean,
	    localPlayerCoordGrid: net.rsprot.protocol.internal.game.outgoing.info.CoordGrid,
	    largeDistance: Boolean,
	    cycleCount: Int,
    ) {
        val numOfBitsUsed = if (largeDistance) 8 else 5
        val maximumDistanceTransmittableByBits = if (largeDistance) 0xFF else 0x1F
        val deltaX = details.currentCoord.x - localPlayerCoordGrid.x
        val deltaZ = details.currentCoord.z - localPlayerCoordGrid.z

        bitBuffer.pBits(16, details.index)
        bitBuffer.pBits(1, if (extendedInfo) 1 else 0)
        // New NPCs should always be marked as "jumping" unless they explicitly only teleported without a jump
        val noJump = details.isTeleWithoutJump() && details.allocateCycle != cycleCount
        bitBuffer.pBits(1, if (noJump) 0 else 1)
        bitBuffer.pBits(numOfBitsUsed, deltaZ and maximumDistanceTransmittableByBits)
        if (details.spawnCycle != 0) {
            bitBuffer.pBits(1, 1)
            bitBuffer.pBits(32, details.spawnCycle)
        } else {
            bitBuffer.pBits(1, 0)
        }
        bitBuffer.pBits(14, min(16383, details.id))
        bitBuffer.pBits(3, details.direction)
        bitBuffer.pBits(numOfBitsUsed, deltaX and maximumDistanceTransmittableByBits)
    }
}
