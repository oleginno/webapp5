package com.oleginno.webapp.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Oleh Savych
 * 15.04.17
 */

public class OrganizationSection extends Section {

    private List<Organization> values = new ArrayList<>();

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... values) {
        this.values = new ArrayList<>(Arrays.asList(values));
    }

    public OrganizationSection(List<Organization> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return !(values != null ? !values.equals(that.values) : that.values != null);

    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public List<Organization> getValues() {
        return values;
    }

}