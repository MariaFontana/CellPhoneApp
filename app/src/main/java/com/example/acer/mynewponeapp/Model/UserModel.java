package com.example.acer.mynewponeapp.Model;

import java.util.Date;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class UserModel {

    private int idUser;

    private String name;

    private String adress;

    private String telephone;

    private String pet;

    private String mail;

    private Date dateStart;

    private String password;

    private ProductModel product;



    private BreedModel breedItem;



    private BrandModel brandItem;

    private int diasCount;

    private List<UpdateNotificationModel> listNotificationModel;


    public UserModel(int idUser, String name, String mail, int diasCount,ProductModel product, String password,  String pet, String telephone, String address,BrandModel brandItem,BreedModel breedItem,List<UpdateNotificationModel> listNotificationModel)
    {
        this.idUser=idUser;
        this.adress=address;
        this.product=product;
        this.diasCount=diasCount;
        this.pet=pet;
        this.telephone=telephone;
        this.name=name ;
        this.mail=mail;
        this.password=password;
        this.brandItem=brandItem;
        this.breedItem=breedItem;
        this.listNotificationModel=listNotificationModel;

    }

    public UserModel( int idUser,String name, String mail,String password, ProductModel product)
    {
        this.mail=mail;
        this.name=name ;
        this.password=password;
        this.product=product;
        this.idUser=idUser;
    }

    public UserModel()
    {

    }

    public String getName() {
        return name;
    }

    public Date GetDateStart() {
        return dateStart;
    }

    public int getDiasCount() {
        return this.diasCount;
    }

    public Date SetDateStart(Date date) {
        return this.dateStart = date;
    }
    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getPet() {
        return this.pet;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdress() {
        return adress;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public void setProduct(ProductModel product)
    {
            this.product = product;
    }

    public void setDiasCount(int diasCount)
    {
        this.diasCount = diasCount;
    }
    public BreedModel getBreedItem() {
        return breedItem;
    }

    public BrandModel getBrandItem() {
        return brandItem;
    }

    public void setBreedItem(BreedModel breedItem) {
        this.breedItem = breedItem;
    }

    public void setBrandItem(BrandModel brandItem) {
        this.brandItem = brandItem;
    }
    public void setListNotificationModel(List<UpdateNotificationModel> listNotificationModel) {
        this.listNotificationModel = listNotificationModel;
    }
    public List<UpdateNotificationModel> getNotificationModelList() {
        return listNotificationModel;
    }

}
