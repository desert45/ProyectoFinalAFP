package com.bootcamp.java.afp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.bootcamp.java.afp.domain.*;
import com.bootcamp.java.afp.model.*;

@Mapper(componentModel = "spring")
public interface PensionFundMapper {

	 PensionFund pensionModelToEvent (PensionFundModel model);
	 
	 PensionFundModel pensionToEventModel (PensionFund pensionFund);
	 
	 List<PensionFundModel> pensionToEventModels(List<PensionFund> pensionFund);

}
