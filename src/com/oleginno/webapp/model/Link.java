package com.oleginno.webapp.model;

/**
 * Oleh Savych
 * 11.04.17
 */

public class Link {

    private static Link empty = new Link();

    private final String name;

    private final String url;


    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link(Link link) {
        this(link.name, link.url);
    }

    private Link() {
        this.name = "";
        this.url = null;
    }

    public Link empty() {
        return empty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        return name.equals(link.name)
                && (url != null ? url.equals(link.url) : link.url == null);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 29 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Link {" +
                "name = '" + name + '\'' +
                ", url ='" + url + '\'' +
                '}';
    }
}
