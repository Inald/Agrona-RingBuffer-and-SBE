import com.baeldung.sbe.stub.TradeDataEncoder;
import org.agrona.MutableDirectBuffer;
import org.agrona.concurrent.AtomicBuffer;
import org.agrona.concurrent.UnsafeBuffer;
import org.agrona.concurrent.ringbuffer.ManyToOneRingBuffer;
import org.agrona.concurrent.ringbuffer.RingBuffer;
import org.agrona.concurrent.ringbuffer.RingBufferDescriptor;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class main {

    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        final UnsafeBuffer internalBuffer = new UnsafeBuffer(ByteBuffer.allocateDirect(1024*16 + RingBufferDescriptor.TRAILER_LENGTH));
        final ManyToOneRingBuffer ringBuffer = new ManyToOneRingBuffer(internalBuffer);

        Map<String, String> config = getConfig();

        ConnectionAcceptor connectionAcceptor = new ConnectionAcceptor(1234, ringBuffer);
        Worker worker = new Worker(ringBuffer);
        worker.run();
//        executor.execute(connectionAcceptor);
//        executor.execute(worker);


    }

    public static Map<String, String> getConfig(){
        return new HashMap<>();
    }

}
