package net.movierama.test.api;

import net.movierama.test.api.model.BaseMovie;
import net.movierama.test.api.rest.JsonUtils;
import net.movierama.test.api.rest.ResponseDto;
import net.movierama.test.api.rest.RestApiHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseApi {

    private static final int OK = 200;

    @Autowired
    private RestApiHelper restApiHelper;

    protected <T> List<T> getListFromResponse(final ResponseDto responseDto, final String tag, Class<? extends BaseMovie> clazz) {
        final List<T> retrievedList = new ArrayList<>();
        if(responseDto.getCode() == OK) {
            final String results = JsonUtils.getFieldFromJson(responseDto.getEntity(), tag);
            JsonUtils.deserializeAsList(results, clazz).stream().forEach(x -> retrievedList.add((T) x.mapEntityToDomain()));
        }
        return retrievedList;
    }

    protected RestApiHelper connect() {
        return restApiHelper;
    }
}
