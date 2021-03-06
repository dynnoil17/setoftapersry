package com.dynnoil.ui.mixins;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BindParameter;
import org.apache.tapestry5.annotations.MixinAfter;
import org.apache.tapestry5.dom.Element;

/**
 * Created by krukov on 15.10.2015.
 */

@MixinAfter
public class Zebra {

    @BindParameter
    private String element;

    @BindParameter
    private int index;

    void beginRender(MarkupWriter writer) {
        if (element != null) {
            final Element currentElement = writer.getElement();
            String css = (index % 2 == 0) ? "odd" : "even";
            currentElement.forceAttributes("class", css);
        }
    }
}
