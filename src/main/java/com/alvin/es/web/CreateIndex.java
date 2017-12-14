package com.alvin.es.web;

import com.alvin.common.result.Result;
import com.alvin.es.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("index")
public class CreateIndex {
    @RequestMapping(value = "createIndex", method = RequestMethod.POST)
    public Result createDoc() {
        Result successInstants = Result.getSuccessInstants();
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
        return successInstants;

    }
}
