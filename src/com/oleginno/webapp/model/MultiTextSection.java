package com.oleginno.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Oleh Savych
 * 15.04.17
 */

public class MultiTextSection extends Section {

    private List<String> values = new ArrayList<>();

    public MultiTextSection(String... values) {
        this(new ArrayList<>(Arrays.asList(values)));
    }

    public MultiTextSection(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public MultiTextSection() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTextSection that = (MultiTextSection) o;

        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return values.toString();
    }

}
