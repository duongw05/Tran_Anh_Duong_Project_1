/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Product.GUI.chart;
import java.awt.Color;
/**
 *
 * @author admin
 */
public class ModelLegend {
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public ModelLegend(String name, Color color1, Color color2) {
        this.name = name;
        this.color1 = color1;
        this.color2 = color2;
    }

    public ModelLegend() {
    }

    private String name;
    private Color color1;
    private Color color2;
}
