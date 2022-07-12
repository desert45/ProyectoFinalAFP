package com.bootcamp.java.afp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.bootcamp.java.afp.domain.*;
import com.bootcamp.java.afp.model.*;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	 Customers customersModelToEvent (CustomersModel model);
	 
	 CustomersModel customersToEventModel (Customers customers);
	 
	 List<CustomersModel> customersToEventModels(List<Customers> Customers);
	 
	 @Mapping(target = "id", ignore = true)
	 void update(@MappingTarget Customers entity, CustomersModel updateEntity);
}
