package com.alvin.es;


import com.alvin.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.util.Map;
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
        Map map= Maps.newHashMap();
        map.put("address","beijing");
        map.put("age","29");
        map.put("name","alvin111111");

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "my-application").build();
            client = new PreBuiltTransportClient(settings);
            InetAddress inetAddress=InetAddress.getByAddress(new byte[]{127,0,0,1});
            client.addTransportAddress(new TransportAddress(inetAddress, 9300));
            IndexResponse indexResponse = client.prepareIndex("my_index", "user","1").setSource(map).get();
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
