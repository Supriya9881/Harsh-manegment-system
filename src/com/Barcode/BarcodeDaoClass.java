package com.Barcode;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BarcodeDaoInterface")
public class BarcodeDaoClass implements BarcodeDaoInterface {

	@Autowired
	SessionFactory sessionFactory;
	Session session;
	
	
}
