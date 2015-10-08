package com.dynnoil.ui.pages;

import com.dynnoil.store.order.Goods;
import com.dynnoil.store.order.Order;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ValueEncoderSource;
import org.apache.tapestry5.util.EnumSelectModel;

import java.util.*;

/**
 * Created by krukov on 06.10.2015.
 */
public class OrderPage {

    /**
     * Order's date
     */
    @Property
    private Date ordersDate;

    /**
     * Order's recieved date needs to set
     */
    @Property
    @Persist
    private Date recievedDate;

    /**
     * For checkbox
     */
    @Property
    private boolean addPack;

    /**
     * SSO of Order class
     */
    @SessionState
    private Order usersOrder;

    @Inject
    private Messages messages;

    /**
     * Service for creating value encoder
     * which makes translations between server-side
     * and client-side
     */
    @Inject
    private ValueEncoderSource valueEncoderSource;

    /**
     * List of selected goods
     */
    @Property
    @Persist
    private ArrayList<Goods> goods;

    public SelectModel getModel() {
        return new EnumSelectModel(Goods.class, messages);
    }

    public ValueEncoder getEncoder() {
        return valueEncoderSource.getValueEncoder(Goods.class);
    }

    /**
     * Method runs on form's preparing to
     * submission and rendering
     */
    void onPrepare() {
        this.ordersDate = usersOrder.getCurrentDate();
    }

    /**
     * Method runs on validation form's class
     */
    void onValidate() {
        usersOrder.removeGoods();
    }

    /**
     * Method runs on success form'class's submission
     *
     * @return
     */
    Object onSuccess() {
        usersOrder.addGoods(this.goods);
        usersOrder.setRecievedDate(recievedDate);
        return CheckOut.class;
    }

}