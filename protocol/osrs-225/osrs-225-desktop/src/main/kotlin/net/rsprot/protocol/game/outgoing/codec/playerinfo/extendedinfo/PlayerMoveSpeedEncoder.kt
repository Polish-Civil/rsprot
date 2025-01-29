package net.rsprot.protocol.game.outgoing.codec.playerinfo.extendedinfo

import io.netty.buffer.ByteBufAllocator
import net.rsprot.buffer.JagByteBuf
import net.rsprot.buffer.extensions.toJagByteBuf
import net.rsprot.compression.provider.HuffmanCodecProvider
import net.rsprot.protocol.internal.game.outgoing.info.encoder.PrecomputedExtendedInfoEncoder
import net.rsprot.protocol.internal.game.outgoing.info.playerinfo.extendedinfo.MoveSpeed

public class PlayerMoveSpeedEncoder : PrecomputedExtendedInfoEncoder<net.rsprot.protocol.internal.game.outgoing.info.playerinfo.extendedinfo.MoveSpeed> {
    override fun precompute(
	    alloc: ByteBufAllocator,
	    huffmanCodecProvider: HuffmanCodecProvider,
	    extendedInfo: net.rsprot.protocol.internal.game.outgoing.info.playerinfo.extendedinfo.MoveSpeed,
    ): JagByteBuf {
        val buffer =
            alloc
                .buffer(1, 1)
                .toJagByteBuf()
        buffer.p1Alt2(extendedInfo.value)
        return buffer
    }
}
