package Model;
// Generated 30 janv. 2024 10:33:01 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Inscriptions generated by hbm2java
 */
@Entity
@Table(name="inscriptions"
    ,catalog="empreinte"
)
public class Inscriptions extends BaseModel implements java.io.Serializable {



     private byte[] photo;
     private String nom;
     private String contact;
     private String adresse;
     private String poste;
     private byte[] empreinte;
     private String status;
     private Date dateEmbauche;
     private Date finContrat;

    public Inscriptions() {
    }

    public Inscriptions(byte[] photo, String nom, String contact, String adresse, String poste, byte[] empreinte, String status, Date dateEmbauche, Date finContrat) {
       this.photo = photo;
       this.nom = nom;
       this.contact = contact;
       this.adresse = adresse;
       this.poste = poste;
       this.empreinte = empreinte;
       this.status = status;
       this.dateEmbauche = dateEmbauche;
       this.finContrat = finContrat;
    }
   
    
    @Column(name="photo")
    public byte[] getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    
    @Column(name="nom", length=50)
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    @Column(name="contact", length=50)
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }

    
    @Column(name="adresse", length=50)
    public String getAdresse() {
        return this.adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    @Column(name="poste", length=50)
    public String getPoste() {
        return this.poste;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }

    
    @Column(name="empreinte")
    public byte[] getEmpreinte() {
        return this.empreinte;
    }
    
    public void setEmpreinte(byte[] empreinte) {
        this.empreinte = empreinte;
    }

    
    @Column(name="status", length=50)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateEmbauche", length=10)
    public Date getDateEmbauche() {
        return this.dateEmbauche;
    }
    
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="finContrat", length=10)
    public Date getFinContrat() {
        return this.finContrat;
    }
    
    public void setFinContrat(Date finContrat) {
        this.finContrat = finContrat;
    }




}

