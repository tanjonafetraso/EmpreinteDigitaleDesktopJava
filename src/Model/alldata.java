/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Eqima
 */

public class alldata {

    private int id;
    private Date date;
    private Date heureDebut;
    private Date heureFin;
    private String heureTotal;
    private Integer idInscription;
    private byte[] photo;
    private String nom;

    public alldata() {
    }

    public alldata(int id, Date date, Date heureDebut, Date heureFin, String heureTotal, Integer idInscription, byte[] photo, String nom) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.heureTotal = heureTotal;
        this.idInscription = idInscription;
        this.photo = photo;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getHeureTotal() {
        return heureTotal;
    }

    public void setHeureTotal(String heureTotal) {
        this.heureTotal = heureTotal;
    }

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
