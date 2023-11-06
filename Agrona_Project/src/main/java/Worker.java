import org.agrona.concurrent.ringbuffer.ManyToOneRingBuffer;

public class Worker implements Runnable {

    private final ManyToOneRingBuffer ringBuffer;
    private final TradeMessageHandler tradeMessageHandler;
    private final TradeDataCodec tradeDataCodec;

    public Worker(final ManyToOneRingBuffer ringBuffer) {
        this.ringBuffer = ringBuffer;
        this.tradeDataCodec = new TradeDataCodec();
        this.tradeMessageHandler = new TradeMessageHandler(tradeDataCodec);
    }

    @Override
    public void run() {
        System.out.println("started worker");
        while(true){
            ringBuffer.read(tradeMessageHandler);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
