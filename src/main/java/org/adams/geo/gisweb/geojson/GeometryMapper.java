package org.adams.geo.gisweb.geojson;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class GeometryMapper extends ObjectMapper implements InitializingBean {

    private static final long serialVersionUID = 1L;

    public GeometryMapper() {
        super();
    }

    public GeometryMapper(final JsonFactory jf, final DefaultSerializerProvider sp,
                          final DefaultDeserializationContext dc) {
        super(jf, sp, dc);
    }

    public GeometryMapper(final JsonFactory jf) {
        super(jf);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        registerModule(new JtsModule());
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

}
