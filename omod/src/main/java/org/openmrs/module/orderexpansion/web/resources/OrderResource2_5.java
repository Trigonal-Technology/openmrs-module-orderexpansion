package org.openmrs.module.orderexpansion.web.resources;

import org.openmrs.DrugOrder;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.ReferralOrder;
import org.openmrs.TestOrder;
import org.openmrs.api.OrderContext;
import org.openmrs.api.context.Context;
import org.openmrs.module.orderexpansion.api.enums.RadiologyOrderStatus;
import org.openmrs.module.orderexpansion.api.model.MedicalSupplyOrder;
import org.openmrs.module.orderexpansion.api.model.ProcedureOrder;
import org.openmrs.module.orderexpansion.api.model.RadiologyOrder;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.v1_0.resource.openmrs2_2.OrderResource2_2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Resource(name = RestConstants.VERSION_1 + "/order", supportedClass = Order.class, supportedOpenmrsVersions = {
        "2.6.* - 9.*" }, order = 3)
public class OrderResource2_5 extends OrderResource2_2 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderResource2_5.class);
	
	private static final String PROCEDURE_ORDER_TYPE_UUID = "4237a01f-29c5-4167-9d8e-96d6e590aa33";
	
	private static final String MEDICAL_SUPPLY_ORDER_TYPE_UUID = "dab3ab30-2feb-48ec-b4af-8332a0831b49";
	
	private static final String RADIOLOGY_ORDER_TYPE_UUID = "c19c8e82-8b8d-4b4e-b1ff-3f09890b2db3";
	
	@Override
	public Order save(Order delegate) {
		LOGGER.info("[orderexpansion] Inside OrderResource2_5.save");
		return Context.getOrderService().saveOrder(delegate, setOrderContext(delegate));
	}
	
	private OrderContext setOrderContext(Order order) {
		LOGGER.info("[orderexpansion] Inside OrderResource2_5.setOrderContext");
		OrderContext orderContext = new OrderContext();
		
		OrderType orderType = Context.getOrderService().getOrderTypeByConcept(order.getConcept());
		
		if (orderType == null && order instanceof DrugOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.DRUG_ORDER_TYPE_UUID);
			
		} else if (orderType == null && order instanceof TestOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.TEST_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof ReferralOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(OrderType.REFERRAL_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof ProcedureOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(PROCEDURE_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof MedicalSupplyOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(MEDICAL_SUPPLY_ORDER_TYPE_UUID);
		} else if (orderType == null && order instanceof RadiologyOrder) {
			orderType = Context.getOrderService().getOrderTypeByUuid(RADIOLOGY_ORDER_TYPE_UUID);
		}
		
		// Mirror radiology's behavior: set default status via context attribute as well
		if (order instanceof RadiologyOrder) {
			orderContext.setAttribute("radiologyStatus", RadiologyOrderStatus.PENDING);
		}
		
		orderContext.setCareSetting(null);
		orderContext.setOrderType(orderType);
		return orderContext;
	}
}
