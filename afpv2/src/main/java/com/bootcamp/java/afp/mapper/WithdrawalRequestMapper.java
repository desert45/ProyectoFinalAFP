package com.bootcamp.java.afp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.bootcamp.java.afp.domain.*;
import com.bootcamp.java.afp.model.*;

@Mapper(componentModel = "spring")
public interface WithdrawalRequestMapper {

	WithdrawalRequest requestModelToEvent (WithdrawalRequestModel model);
	 
	WithdrawalRequestModel requestToEventModel (WithdrawalRequest customers);
	 
	 List<WithdrawalRequestModel> requestToEventModels(List<WithdrawalRequest> WithdrawalRequest);
	 
}
