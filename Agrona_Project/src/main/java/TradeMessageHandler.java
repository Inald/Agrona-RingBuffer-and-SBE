import com.baeldung.sbe.stub.TradeDataDecoder;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.MessageHandler;

public class TradeMessageHandler implements MessageHandler {

    private final TradeDataCodec tradeDataCodec;
    private final TradeData trade = new TradeData();

    public TradeMessageHandler(final TradeDataCodec tradeDataCodec) {
        this.tradeDataCodec = tradeDataCodec;
    }

    @Override
    public void onMessage(int msgType, MutableDirectBuffer mutableDirectBuffer, int index, int length) {
        switch (msgType){
            case 1:
                System.out.println("message: " + msgType);
                tradeDataCodec.decode(msgType, mutableDirectBuffer, index, length, trade);
                System.out.println(trade);
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }
}
