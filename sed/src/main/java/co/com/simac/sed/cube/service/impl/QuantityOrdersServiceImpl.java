package co.com.simac.sed.cube.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.simac.sed.cube.dao.OrderRepository;
import co.com.simac.sed.cube.service.QuantityOrdersService;
import co.com.simac.sed.kpiindicatormanagement.dao.KPIIndicatorRepository;

@Service
public class QuantityOrdersServiceImpl implements QuantityOrdersService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private KPIIndicatorRepository kpiIndicatorRepository;

	@Override
	public Integer getCountOrdersSuccessful() {
		Integer count = orderRepository.getQuatityOrderSuccessful();
		if (count != null) {
			return count;
		}
		return 0;
	}
	
	@Override
	public Integer getCountOrdersError() {
		Integer count = orderRepository.getQuatityOrderError();
		if (count != null) {
			return count;
		}
		return 0;
	}
	
	@Override
	public Integer getTotalActiveKPI() {
		Integer count = kpiIndicatorRepository.getTotalActiveKPI();
		if (count != null) {
			return count;
		}
		return 0;
	}
}
