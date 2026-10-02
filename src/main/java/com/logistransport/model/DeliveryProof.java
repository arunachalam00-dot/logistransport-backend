package com.logistransport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_proofs")
public class DeliveryProof {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shipmentId;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String deliveryDate;

    @Column(nullable = false)
    private String deliveryStatus;

    @Column(nullable = false)
    private String proofFileName;

    public DeliveryProof() {
    }

    public DeliveryProof(
            Long id,
            String shipmentId,
            String receiverName,
            String deliveryDate,
            String deliveryStatus,
            String proofFileName) {

        this.id = id;
        this.shipmentId = shipmentId;
        this.receiverName = receiverName;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.proofFileName = proofFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getProofFileName() {
        return proofFileName;
    }

    public void setProofFileName(String proofFileName) {
        this.proofFileName = proofFileName;
    }
}
