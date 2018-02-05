package com.alvin.es.web;

import com.alvin.common.component.EsComponent;
import com.alvin.common.result.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 创建index.
 *
 * @author 亚辉a
 */
@RestController
@RequestMapping("/index")
public class CreateIndex {

  private final EsComponent esComponent;

  @Autowired
  public CreateIndex(EsComponent esComponent) {
    this.esComponent = esComponent;
  }

  @RequestMapping(value = "/createIndex")
  public Result createDoc() throws JsonProcessingException {
    Result successInstants = Result.getSuccessInstants();
    Map<String, String> map = Maps.newHashMap();
    map.put("address", "beijing");
    map.put("age", "29");
    map.put("name", "alvin111111");
    TransportClient transportClient = esComponent.newInstance();
    IndexResponse indexResponse = transportClient.prepareIndex("my_index", "user", UUIDs.randomBase64UUID()).setSource(map).get();
    ObjectMapper objectMapper = new ObjectMapper();
    System.out.println(indexResponse.getId());
    System.out.println(objectMapper.writeValueAsString(indexResponse.getResult()));
    System.out.println(objectMapper.writeValueAsString(indexResponse.getShardInfo()));
    return successInstants;

  }
}
