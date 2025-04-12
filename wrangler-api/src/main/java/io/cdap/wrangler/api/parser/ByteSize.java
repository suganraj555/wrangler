package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        this.bytes = parse(value);
    }

    private long parse(String val) {
        val = val.trim().toLowerCase();
        if (val.endsWith("kb")) {
            return (long) (Double.parseDouble(val.replace("kb", "")) * 1024);
        } else if (val.endsWith("mb")) {
            return (long) (Double.parseDouble(val.replace("mb", "")) * 1024 * 1024);
        } else if (val.endsWith("gb")) {
            return (long) (Double.parseDouble(val.replace("gb", "")) * 1024 * 1024 * 1024);
        } else if (val.endsWith("tb")) {
            return (long) (Double.parseDouble(val.replace("tb", "")) * 1024L * 1024 * 1024 * 1024);
        } else {
            return Long.parseLong(val); // default
        }
    }

    public long getBytes() {
        return bytes;
    }
}
