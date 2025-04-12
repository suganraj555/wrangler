package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final long millis;

    public TimeDuration(String value) {
        super(value);
        this.millis = parse(value);
    }

    private long parse(String val) {
        val = val.trim().toLowerCase();
        if (val.endsWith("ms")) {
            return (long) Double.parseDouble(val.replace("ms", ""));
        } else if (val.endsWith("s") || val.endsWith("sec") || val.endsWith("seconds")) {
            return (long) (Double.parseDouble(val.replaceAll("s|sec|seconds", "")) * 1000);
        } else if (val.endsWith("m") || val.endsWith("min") || val.endsWith("minutes")) {
            return (long) (Double.parseDouble(val.replaceAll("m|min|minutes", "")) * 60000);
        } else {
            return Long.parseLong(val);
        }
    }

    public long getMilliseconds() {
        return millis;
    }
}
