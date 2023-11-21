package com.example.stepcounter.service.stepcounter;

import com.example.stepcounter.dto.stepcounter.StepCounterOutDto;
import com.example.stepcounter.model.StepCounter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-21T19:49:28+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class StepCounterMapperImpl implements StepCounterMapper {

    @Override
    public StepCounterOutDto getOutputFromEntity(StepCounter stepCounter) {
        if ( stepCounter == null ) {
            return null;
        }

        StepCounterOutDto stepCounterOutDto = new StepCounterOutDto();

        stepCounterOutDto.setId( stepCounter.getId() );
        stepCounterOutDto.setStep( stepCounter.getStep() );
        stepCounterOutDto.setTime( stepCounter.getTime() );
        stepCounterOutDto.setDistance( stepCounter.getDistance() );
        stepCounterOutDto.setCalo( stepCounter.getCalo() );
        stepCounterOutDto.setRealTime( stepCounter.getRealTime() );

        return stepCounterOutDto;
    }
}
