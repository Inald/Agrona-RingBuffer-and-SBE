public class AsciiString implements CharSequence{

    byte[] bytes;

    public AsciiString(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }
}
