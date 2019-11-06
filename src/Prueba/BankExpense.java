/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

import Model.Observable;
import Model.Observer;

/**
 *
 * @author pc
 */
public class BankExpense implements Observer{

    private String type;
    private Double totalCalculated = new Double(0D);
    private final Double rateCoefficient;

    public BankExpense(String type, Double rateCoefficient) {
        this.type = type;
        this.rateCoefficient = rateCoefficient;
    }

    @Override
    public void update(Observable o, Object value) {
        this.totalCalculated = ((Double) value) * rateCoefficient;
    }

    public Double getTotalCalculated() {
        return totalCalculated;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "BankExpense{" +
                "type='" + type + '\'' +
                ", rateCoefficient=" + rateCoefficient +
                ", totalCalculated=" + totalCalculated +
                '}';
    }
    
}
