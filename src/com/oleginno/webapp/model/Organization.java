package com.oleginno.webapp.model;

import com.oleginno.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Oleh Savych
 * 13.04.17
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization  implements Serializable {

    static final long serialVersionUID = 1L;

    private Link link;

    private List<Period> periods;

    public List<Period> getPeriods() {
        return periods;
    }

    public Organization(Link link, List<Period> periods) {
        this.link = link;
        this.periods = periods;
    }

    public Organization(String name, String url, Period... periods) {
        this(new Link(name, url), new ArrayList<>(Arrays.asList(periods)));
    }

    public Organization() {
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {

        static final long serialVersionUID = 1L;

        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;

        private String position;

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPosition() {
            return position;
        }

        public String getContent() {
            return content;
        }

        private String content;

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content) {
            this(LocalDate.of(startYear, startMonth, 1),
                    LocalDate.of(endYear, endMonth, 1),
                    position,
                    content);
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            Objects.requireNonNull(startDate, "startDate is null");
            Objects.requireNonNull(startDate, "position is null");
            this.startDate = startDate;
            this.endDate = endDate == null ? NOW : endDate;
            this.position = position;
            this.content = content == null ? "<NULL>" : content;
        }

        public Period() {
        }
    }
}
