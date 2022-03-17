package com.Barcode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("BarcodeServiceInterface")
@Transactional(propagation = Propagation.SUPPORTS)
public class BarcodeServiceClass implements BarcodeServiceInterface {

	@Autowired
	BarcodeDaoInterface barcodeDaoInterface;
	
}
