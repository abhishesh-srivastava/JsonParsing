package com.abhishesh.projects.medicine.model;

import android.util.Log;

/**
 * Created by Abhishesh on 15/10/15.
 */
public class Item {
    /* <id>199</id>
    <label>Acnil 60 ml Facewash(Merck Ltd)</label>
    <brand>Acnil</brand>
    <type>OTC</type>
    <drugspacksize>60 ml Facewash</drugspacksize>
    <manufacturer>Merck Ltd</manufacturer>
    <mrp>120.00</mrp>
    <packform>Tube</packform>
    <pform>Facewash</pform> */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDrugsPackSize() {
        return drugsPackSize;
    }

    public void setDrugsPackSize(String drugsPackSize) {
        this.drugsPackSize = drugsPackSize;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getMrp() {
        return mrp;
    }

    public void setMrp(float mrp) {
        this.mrp = mrp;
    }

    public String getPackForm() {
        return packForm;
    }

    public void setPackForm(String packForm) {
        this.packForm = packForm;
    }

    public String getpForm() {
        return pForm;
    }

    public void setpForm(String pForm) {
        this.pForm = pForm;
    }

    private String id;
    private String label;
    private String brand;
    private String type;
    private String drugsPackSize;
    private String manufacturer;
    private float mrp;
    private String packForm;
    private String pForm;

    public Item(String id, String label, String brand, String type, String drugsPackSize,
                         String manufacturer, float mrp, String packForm, String pForm) {
        this.id = id;
        this.label = label;
        this.brand = brand;
        this.type = type;
        this.drugsPackSize = drugsPackSize;
        this.manufacturer = manufacturer;
        this.mrp = mrp;
        this.packForm = packForm;
        this.pForm = pForm;
        Log.d("ABHISHESH","this: " + this);
    }

    @Override
    public String toString() {
        return "[" + id + " " + label + " " + brand + " " + type + drugsPackSize + " "
                   + manufacturer + " " + mrp + " " + packForm + " " + pForm + "]";
    }
}
