/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.BaseModel;
import Utils.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Eqima
 */
public class HibernateDAO {

    public int save(BaseModel o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tr = session.beginTransaction();
            session.save(o);
            tr.commit();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return o.getId();
    }
    public int delete(BaseModel o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tr = session.beginTransaction();
            session.delete(o);
            tr.commit();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return o.getId();
    }

    public int update(BaseModel o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction tr = session.beginTransaction();
            session.update(o);
            tr.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return o.getId();
    }

    public List findAll(BaseModel o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(o.getClass());
            return cr.list();
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }

    }

    public BaseModel findAllByDateAjourdhui(BaseModel o, int idInscription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(o.getClass());
            cr.add(Restrictions.eq("date", new Date()));
            cr.add(Restrictions.eq("idInscription", idInscription));
            return (BaseModel) cr.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        } finally {
            session.close();
        }
    }

    public BaseModel findAllByName(BaseModel o, String nom) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(o.getClass());
            cr.add(Restrictions.eq("nom", nom));
            return (BaseModel) cr.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        } finally {
            session.close();
        }
    }
    public BaseModel findAllByID(BaseModel o, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(o.getClass());
            cr.add(Restrictions.eq("id", id));
            return (BaseModel) cr.uniqueResult();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        } finally {
            session.close();
        }
    }

    public List findAllByDateAjourdhui(BaseModel o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria cr = session.createCriteria(o.getClass());
            cr.add(Restrictions.eq("date", new Date()));
            return cr.list();
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        } finally {
            session.close();
        }
    }
}
