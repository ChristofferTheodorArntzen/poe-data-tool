package com.carnnjoh.poedatatool.db.dao;


import com.carnnjoh.poedatatool.db.model.Subscription;

public interface SubscriptionDAO extends DAO<Subscription> {

	Subscription fetchFirstActive(boolean isActive);

}
