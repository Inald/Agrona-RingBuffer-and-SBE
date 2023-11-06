import com.baeldung.sbe.stub.MessageHeaderDecoder;
import com.baeldung.sbe.stub.MessageHeaderEncoder;
import com.baeldung.sbe.stub.TradeDataDecoder;
import com.baeldung.sbe.stub.TradeDataEncoder;
import org.agrona.MutableDirectBuffer;

public class TradeDataCodec {

    private final TradeDataEncoder tradeDataEncoder = new TradeDataEncoder();
    private final TradeDataDecoder tradeDataDecoder = new TradeDataDecoder();
    private final MessageHeaderEncoder messageHeaderEncoder = new MessageHeaderEncoder();
    private final MessageHeaderDecoder messageHeaderDecoder = new MessageHeaderDecoder();

    public TradeDataCodec() {

    }

    public int encode(final MutableDirectBuffer buffer, final TradeData trade){
        this.tradeDataEncoder.wrapAndApplyHeader(buffer, 0, messageHeaderEncoder);
        this.tradeDataEncoder.ticker(trade.getTicker());
        return tradeDataEncoder.encodedLength() + MessageHeaderEncoder.ENCODED_LENGTH;
    }

    public void decode(final int msgType, final MutableDirectBuffer mutableDirectBuffer, final int index, final int length, final TradeData trade){
        trade.reset();
        int bufferOffset = 0;
        messageHeaderDecoder.wrap(mutableDirectBuffer, 0);
        // Lookup the applicable flyweight to decode this type of message based on templateId and version.
        final int templateId = messageHeaderDecoder.templateId();
        final int actingBlockLength = messageHeaderDecoder.blockLength();
        final int actingVersion = messageHeaderDecoder.version();

        bufferOffset += messageHeaderDecoder.encodedLength();
        tradeDataDecoder.wrap(mutableDirectBuffer, bufferOffset + 8, actingBlockLength, actingVersion);
        trade.setTicker(tradeDataDecoder.ticker());
    }

}
