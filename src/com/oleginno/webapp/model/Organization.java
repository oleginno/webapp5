package com.oleginno.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Oleh Savych
 * 13.04.17
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization  implements Serializable {

    static final long serialVersionUID = 1L;

    private Link link;

    private List<Period> periods;

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    public Organization() {
    }

    public static class Period implements Serializable {

        static final long serialVersionUID = 1L;

        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

        private LocalDate startDate;

        private LocalDate endDate;

        private String position;

        private String content;

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.position = position;
            this.content = content;
        }

        public Period(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Period() {
        }
    }
}
