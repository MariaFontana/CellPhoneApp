package com.example.acer.mynewponeapp.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PromoModel {


    public Date startPromo;
    public Date finishPromo;
    public String image;
    public int idPromo;
    public String name;
    private Date date;


    public PromoModel(int idPromo, String name, String startPromo, String finishPromo, String image) {
        this.idPromo = idPromo;
        this.name = name;
        this.startPromo = GetDate(startPromo);
        this.finishPromo = GetDate(finishPromo) ;
        this.image = image;
    }

    public void setStartPromo(Date startPromo) {
        this.startPromo = startPromo;
    }

    public void setFinishPromo(Date finishPromo) {
        this.finishPromo = finishPromo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public String getName() {
        return name;
    }

    public Date getStartPromo() {
        return startPromo;
    }

    public Date getFinishPromo() {
        return finishPromo;
    }

    public String getImage() {
        return image;
    }


    private Date GetDate(String dateUpdate )
    {
        try {
            SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");
            this.date  =(Date)dateParser.parse(dateUpdate);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return this.date;
    }



}
