package com.oleginno.webapp.web;

import com.oleginno.webapp.model.MultiTextSection;
import com.oleginno.webapp.model.Section;
import com.oleginno.webapp.model.SectionType;
import com.oleginno.webapp.model.TextSection;

import java.util.Collections;

import static com.oleginno.webapp.web.HtmlUtil.input;
import static com.oleginno.webapp.web.HtmlUtil.textArea;

/**
 * GKislin
 * 26.12.2014.
 */
public enum SectionHtmlType {

    TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return input(type.name(), section == null ? "" : ((TextSection) section).getValue());
        }

        @Override
        public TextSection createSection(String value) {
            return new TextSection(value);
        }
    },

    MULTI_TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return textArea(type.name(), section == null ?
                    Collections.singletonList("") : ((MultiTextSection) section).getValues());
        }

        @Override
        public MultiTextSection createSection(String values) {
            return new MultiTextSection(values.split("\\n"));
        }
    },

    ORGANIZATION {
        @Override
        public String toHtml(Section section, SectionType type) {
            if (section != null) {
                return section.toString();
            } else return "";
        }

        @Override
        public Section createSection(String value) {
            throw new UnsupportedOperationException();
        }
    };

    public abstract String toHtml(Section section, SectionType type);

    public abstract Section createSection(String value);
}
