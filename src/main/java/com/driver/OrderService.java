package com.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.*;
import java.util.List;

@Service
public class OrderService {

    //@Autowired
    OrderRepository orderRepository = new OrderRepository();

    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

//    public void addPartner(String partnerId){
//        DeliveryPartner partner = new DeliveryPartner(partnerId);
//        orderRepository.addPartner(partner);
//    }
    public void addPartner(String partnerId){

        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    public Order getOrderById(String orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Integer getCountOfUnassigned() {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime, String partnerId) {

        String time[]= deliveryTime.split(":");
        int newTime = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(newTime, partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {

        int time = orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
            String HH = String.valueOf(time/60);
            String MM = String.valueOf(time%60);

            if(HH.length()<2)
                HH = '0' + HH;
            if(MM.length() < 2)
                MM= '0'+MM;

            return HH+ ':' +MM;
        }
    public void deletePartnerById(String partnerId){

        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }


//    public void addOrderPartnerPair(String orderId, String partnerId) {
//        Optional<Order> orderOpt = orderRepository.getOrderById(orderId);
//        Optional<DeliveryPartner> partnerOpt = orderRepository.getPartnerById(partnerId);
//
//        if(orderOpt.isEmpty()){
//           // log.warn("Order id not present:" + orderId);
//            throw new RuntimeException("Order id not present:" + orderId);
//        }
//        if(partnerOpt.isEmpty()){
//            //log.warn("partner id not present:" + partnerId);
//            throw new RuntimeException("Partner id not present:" + partnerId);
//        }
//        //update number of orders
//      DeliveryPartner partner = partnerOpt.get();
//        partner.setNumberOfOrders(partner.getNumberOfOrders()+1);
//        orderRepository.addPartner(partner);
//        orderRepository.addOrderPartnerPair(orderId,partnerId);
//    }
//
//    public Order getOrderById(String orderId) throws RuntimeException{
//        Optional<Order> orderOpt = orderRepository.getOrderById(orderId);
//        if(orderOpt.isPresent()){
//            return orderOpt.get();
//        }
//        //log.error("order not found for order id :" +orderId);
//        throw new RuntimeException("Order Not Found");
//    }
//
//    public Integer getOrderCountForPartner(String partnerId){
//        Optional<DeliveryPartner> p = orderRepository.getPartnerById(partnerId);
//        if(p.isPresent()){
//            return p.get().getNumberOfOrders();
//        }
//        return 0;
//    }
}
