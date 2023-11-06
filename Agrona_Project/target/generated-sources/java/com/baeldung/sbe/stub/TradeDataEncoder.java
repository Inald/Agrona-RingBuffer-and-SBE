/* Generated SBE (Simple Binary Encoding) message codec. */
package com.baeldung.sbe.stub;

import org.agrona.MutableDirectBuffer;


/**
 * Represents a quote and amount of trade
 */
@SuppressWarnings("all")
public final class TradeDataEncoder
{
    public static final int BLOCK_LENGTH = 32;
    public static final int TEMPLATE_ID = 1;
    public static final int SCHEMA_ID = 1;
    public static final int SCHEMA_VERSION = 0;
    public static final String SEMANTIC_VERSION = "5.2";
    public static final java.nio.ByteOrder BYTE_ORDER = java.nio.ByteOrder.LITTLE_ENDIAN;

    private final TradeDataEncoder parentMessage = this;
    private MutableDirectBuffer buffer;
    private int initialOffset;
    private int offset;
    private int limit;

    public int sbeBlockLength()
    {
        return BLOCK_LENGTH;
    }

    public int sbeTemplateId()
    {
        return TEMPLATE_ID;
    }

    public int sbeSchemaId()
    {
        return SCHEMA_ID;
    }

    public int sbeSchemaVersion()
    {
        return SCHEMA_VERSION;
    }

    public String sbeSemanticType()
    {
        return "";
    }

    public MutableDirectBuffer buffer()
    {
        return buffer;
    }

    public int initialOffset()
    {
        return initialOffset;
    }

    public int offset()
    {
        return offset;
    }

    public TradeDataEncoder wrap(final MutableDirectBuffer buffer, final int offset)
    {
        if (buffer != this.buffer)
        {
            this.buffer = buffer;
        }
        this.initialOffset = offset;
        this.offset = offset;
        limit(offset + BLOCK_LENGTH);

        return this;
    }

    public TradeDataEncoder wrapAndApplyHeader(
        final MutableDirectBuffer buffer, final int offset, final MessageHeaderEncoder headerEncoder)
    {
        headerEncoder
            .wrap(buffer, offset)
            .blockLength(BLOCK_LENGTH)
            .templateId(TEMPLATE_ID)
            .schemaId(SCHEMA_ID)
            .version(SCHEMA_VERSION);

        return wrap(buffer, offset + MessageHeaderEncoder.ENCODED_LENGTH);
    }

    public int encodedLength()
    {
        return limit - offset;
    }

    public int limit()
    {
        return limit;
    }

    public void limit(final int limit)
    {
        this.limit = limit;
    }

    public static int tickerId()
    {
        return 1;
    }

    public static int tickerSinceVersion()
    {
        return 0;
    }

    public static int tickerEncodingOffset()
    {
        return 0;
    }

    public static int tickerEncodingLength()
    {
        return 32;
    }

    public static String tickerMetaAttribute(final MetaAttribute metaAttribute)
    {
        if (MetaAttribute.PRESENCE == metaAttribute)
        {
            return "required";
        }

        return "";
    }

    public static byte tickerNullValue()
    {
        return (byte)0;
    }

    public static byte tickerMinValue()
    {
        return (byte)32;
    }

    public static byte tickerMaxValue()
    {
        return (byte)126;
    }

    public static int tickerLength()
    {
        return 32;
    }


    public TradeDataEncoder ticker(final int index, final byte value)
    {
        if (index < 0 || index >= 32)
        {
            throw new IndexOutOfBoundsException("index out of range: index=" + index);
        }

        final int pos = offset + 0 + (index * 1);
        buffer.putByte(pos, value);

        return this;
    }

    public static String tickerCharacterEncoding()
    {
        return java.nio.charset.StandardCharsets.US_ASCII.name();
    }

    public TradeDataEncoder putTicker(final byte[] src, final int srcOffset)
    {
        final int length = 32;
        if (srcOffset < 0 || srcOffset > (src.length - length))
        {
            throw new IndexOutOfBoundsException("Copy will go out of range: offset=" + srcOffset);
        }

        buffer.putBytes(offset + 0, src, srcOffset, length);

        return this;
    }

    public TradeDataEncoder ticker(final String src)
    {
        final int length = 32;
        final int srcLength = null == src ? 0 : src.length();
        if (srcLength > length)
        {
            throw new IndexOutOfBoundsException("String too large for copy: byte length=" + srcLength);
        }

        buffer.putStringWithoutLengthAscii(offset + 0, src);

        for (int start = srcLength; start < length; ++start)
        {
            buffer.putByte(offset + 0 + start, (byte)0);
        }

        return this;
    }

    public TradeDataEncoder ticker(final CharSequence src)
    {
        final int length = 32;
        final int srcLength = null == src ? 0 : src.length();
        if (srcLength > length)
        {
            throw new IndexOutOfBoundsException("CharSequence too large for copy: byte length=" + srcLength);
        }

        buffer.putStringWithoutLengthAscii(offset + 0, src);

        for (int start = srcLength; start < length; ++start)
        {
            buffer.putByte(offset + 0 + start, (byte)0);
        }

        return this;
    }

    public String toString()
    {
        if (null == buffer)
        {
            return "";
        }

        return appendTo(new StringBuilder()).toString();
    }

    public StringBuilder appendTo(final StringBuilder builder)
    {
        if (null == buffer)
        {
            return builder;
        }

        final TradeDataDecoder decoder = new TradeDataDecoder();
        decoder.wrap(buffer, initialOffset, BLOCK_LENGTH, SCHEMA_VERSION);

        return decoder.appendTo(builder);
    }
}
