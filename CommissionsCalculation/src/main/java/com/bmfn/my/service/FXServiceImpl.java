package com.bmfn.my.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FXServiceImpl implements FXService {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sessionFactory;

    @Override
    public String getTotalCommissionsPerIB(int ibId, String firstCloseTime, String lastCloseTime) {

        Session session = null;

        String returnValue = null;

        try{
            session = sessionFactory.openSession();

            Query query = session.createQuery("SELECT SUM(commission) FROM TradersFX WHERE " +
                    "login in (SELECT login FROM UsersFX WHERE group NOT LIKE '%MAM%' AND code like:ibId) " +
                    "AND closeTime BETWEEN :firstCloseTime AND :lastCloseTime");
            query.setParameter("ibId",ibId+",%");
            query.setParameter("firstCloseTime",firstCloseTime);
            query.setParameter("lastCloseTime",lastCloseTime);
            returnValue = String.valueOf(query.list());
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if (session != null && session.isOpen()){
                session.close();
                session = null;
            }
        }
        return returnValue;
    }
}
