package com.alvin.es;


import com.alvin.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.util.Objects;

public class JunitTest {
    @Test
    public void createDoc() {
        ObjectMapper objectMapper = new ObjectMapper();
        TransportClient client = null;
        User user = new User();
        user.setAddress("beijingshi");
        user.setAge(28);
        user.setName("alvin");

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "my-application").build();
            client = new PreBuiltTransportClient(settings);
            IndexResponse indexResponse = client.prepareIndex("my_index", "user").setSource(user).get();
            System.out.println(indexResponse.getId());
            System.out.println(objectMapper.writeValueAsString(indexResponse.getResult()));
            System.out.println(objectMapper.writeValueAsString(indexResponse.getShardInfo()));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (Objects.nonNull(client)) {
                client.close();
            }
        }
    }
}
