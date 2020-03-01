package com.ssvictor.samples.autoconfigure.formatter;

import java.util.Objects;

public class DefaultFormatter implements Formatter {
    @Override
    public String format(Object object) {
        return String.valueOf(object);
    }
}
