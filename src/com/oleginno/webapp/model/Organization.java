package com.oleginno.webapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Oleh Savych
 * 13.04.17
 */

public class Organization  implements Serializable {

    static final long serialVersionUID = 1L;

    private Link link;

    private List<Period> periods;


    public class Period {

        private Date startDate;

        private Date endDate;

        private String position;

        private String content;

        public Period(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Period() {
        }
    }
}
