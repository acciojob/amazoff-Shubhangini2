package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    //unique entity
   private  Map<String, Order> orderMap = new HashMap<>();

   private Map<String, DeliveryPartner> partnerMap= new HashMap<>();

   private Map<String, String> orderPartnerMap= new HashMap<>();

   private  Map<String,/*ArrayList*/ List<String>> partnerOrderMap= new HashMap<>();

    public void addOrder(Order order){

        orderMap.put(order.getId(),order);
    }

//    public void addPartner(DeliveryPartner partner){
//
//        partnerMap.put(partner.getId(),partner);
//    }
    public void addPartner(String partnerId){

        partnerMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

        if(orderMap.containsKey(orderId) && partnerMap.containsKey(partnerId)){
            orderPartnerMap.put(orderId,partnerId);
        }

        List<String> currentOrders= new ArrayList<>();

        if(partnerOrderMap.containsKey(partnerId)){
            currentOrders= partnerOrderMap.get(partnerId);
        }

        currentOrders.add(orderId);
        partnerOrderMap.put(partnerId,currentOrders);

        //INCREASE THE NUMBER OF PARTNER
        DeliveryPartner deliveryPartner= partnerMap.get(partnerId);
        deliveryPartner.setNumberOfOrders(currentOrders.size() );
    }

    public Order getOrderById(String orderId) {
        return orderMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return partnerMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        return partnerOrderMap.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        return partnerOrderMap.get(partnerId);
    }

    public List<String> getAllOrders() {

        List<String> orders = new ArrayList<>();
        for(String order: orderMap.keySet()){
            orders.add(order);
        }
        return orders;
    }

    public Integer getCountOfUnassignedOrders() {
        return orderMap.size()-orderPartnerMap.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId) {

        int count=0;
        List<String> orders = partnerOrderMap.get(partnerId);

        for(String orderId: orders){
            int deliveryTime = orderMap.get(orderId).getDeliveryTime();
            if(deliveryTime > time)
                count++;
        }
        return count;
    }

    public int getLastDeliveryTimeByPartnerId(String partnerId) {

        int maxTime =0;
        List<String> orders = partnerOrderMap.get(partnerId);
        for(String orderId : orders){
            int currentTime= orderMap.get(orderId).getDeliveryTime();
            maxTime = Math.max(maxTime, currentTime);
        }
        return maxTime;
    }

    public void deletePartnerById(String partnerId){
        partnerMap.remove(partnerId);

        List<String> listOfOrders = partnerOrderMap.get(partnerId);
        partnerOrderMap.remove(partnerId);

        for(String order: listOfOrders){
            orderPartnerMap.remove(order);
        }
    }

    public void deleteOrderById(String orderId){
        orderMap.remove(orderId);

        String partnerId = orderPartnerMap.get(orderId);
        orderPartnerMap.remove(orderId);

        partnerOrderMap.get(partnerId).remove(orderId);

        partnerMap.get(partnerId).setNumberOfOrders(partnerOrderMap.get(partnerId).size());
    }

//    public Optional<Order> getOrderById(String orderId) {
//
//        if(orderMap.containsKey(orderId)){
//            return Optional.of(orderMap.get(orderId));
//        }
//        return Optional.empty();
//    }
//
//    public Optional<DeliveryPartner> getPartnerById(String partnerId){
//        if(partnerMap.containsKey(partnerId)){
//            return Optional.of(partnerMap.get(partnerId));
//        }
//        return Optional.empty();
//    }
//
//    public void addOrderPartnerPair(String orderId, String partnerId) {
//
//      ArrayList<String> orders= partnerOrderMap.getOrDefault(partnerId, new ArrayList<>());
//       orders.add(orderId);
//        partnerOrderMap.put(partnerId,orders);
//    }
}
