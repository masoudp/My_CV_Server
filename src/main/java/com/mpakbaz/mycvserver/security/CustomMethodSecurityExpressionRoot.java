package com.mpakbaz.mycvserver.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityManager;
import java.util.Objects;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;


    EntityManager em;

    public CustomMethodSecurityExpressionRoot(Authentication authentication, EntityManager em) {
        super(authentication);
        this.em = em;

    }


    public boolean isAdmin(Long id, String targetType) {
//        Business business = null;
//        JwtUser currantUser = (JwtUser) authentication.getPrincipal();
//
//        switch (targetType.toLowerCase()) {
//            case "offer":
//                Offer obj = em.find(Offer.class, id);
//                business = Objects.isNull(obj) ? null : obj.getBusiness();
//                break;
//            case "service":
//                Service service = em.find(Service.class, id);
//                business = Objects.isNull(service) ? null : service.getBusiness();
//                break;
//            case "BusinessComment":
//                BusinessComment comment = em.find(BusinessComment.class, id);
//                business = Objects.isNull(comment) ? null : comment.getBusiness();
//                break;
//            case "business":
//                business = em.find(Business.class, id);
//                break;
//        }
//
//        if (Objects.isNull(business))
//            return false;
//
//        if (Objects.nonNull(business.getCpUser()) && business.getCpUser().getId().equals(currantUser.getId()))
//            return true;
//
//        return business.getAdmins().stream().anyMatch(u -> currantUser.getId().equals(u.getId()));
        return false;


    }

//    public <T> T find(String className, Long id) {
//        try {
//            return em.find((Class<T>) Class.forName(className).getClass(), id);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}
