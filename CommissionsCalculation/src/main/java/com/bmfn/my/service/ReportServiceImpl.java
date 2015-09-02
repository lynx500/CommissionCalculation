package com.bmfn.my.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    @Qualifier(value = "sessionFactory2")
    SessionFactory sessionFactory;

    @Override
    public String getCommissionsForIB(int ibId) {

        Session session = null;

        String returnValue = null;

        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("select distinct a.commission from GroupAccount a, Parent p WHERE a.parent=p.id and a.balance>=20 and p.role=4 and p.code = :ibId");
            query.setParameter("ibId",ibId);
            returnValue = String.valueOf(query.list());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
                session = null;
            }
        }
        return returnValue;
    }

    @Override
    public List<Integer> getCodes() {

        Session session = null;

        List<Integer> returnValue = null;

        try{
            session = sessionFactory.openSession();
            Query query = session.createQuery("select distinct p.code from GroupAccount a, Parent p WHERE a.parent=p.id and a.balance>=20 and p.role=4");
            returnValue = query.list();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
                session = null;
            }
        }
        return returnValue;
    }
}
