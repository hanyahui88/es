package com.alvin.common.component;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Objects;

/**
 * es component.
 *
 * @author 韩亚辉
 */
@Component
public class EsComponent {
  /**
   * 日志对象
   */
  private static Logger LOGGER = LoggerFactory.getLogger(EsComponent.class);

  private static final String COLON = ":";
  /**
   * 集群名称.
   */
  @Value("${spring.data.elasticsearch.cluster-name}")
  private String clusterName;

  /**
   * 节点，形式 127.0.0.1:9300.
   */
  @Value("${spring.data.elasticsearch.cluster-nodes}")
  private String clusterNode;

  private static TransportClient transportClient;

  private EsComponent() {
  }

  public TransportClient newInstance() {
    if (Objects.nonNull(transportClient)) {
      LOGGER.info("TransportClient 已经赋值了");
      return transportClient;
    }
    Settings settings = Settings.builder()
        .put("cluster.name", clusterName).build();
    TransportClient client = new PreBuiltTransportClient(settings);
    try {
      IpAddress ipAddress = decomposeClusterNode();
      client.addTransportAddress(new TransportAddress(ipAddress.getInetAddress(), ipAddress.getPort()));
      transportClient = client;
      LOGGER.info("TransportClient 开始赋值");
    } catch (Exception ex) {
      LOGGER.error("初始化TransportClient异常：{}",ex.getLocalizedMessage());
      throw new RuntimeException(ex.getMessage());
    }
    return transportClient;
  }

  /**
   * 分解 clusterNode.
   *
   * @return IpAddress
   */
  private IpAddress decomposeClusterNode() throws Exception {
    IpAddress ipAddress = new IpAddress();
    String host = StringUtils.substringBeforeLast(clusterNode, COLON);
    String port = StringUtils.substringAfterLast(clusterNode, COLON);
    ipAddress.setPort(Integer.parseInt(port));
    InetAddress inetAddress = InetAddress.getByName(host);
    ipAddress.setInetAddress(inetAddress);
    return ipAddress;
  }

  @Data
  private class IpAddress {
    private InetAddress inetAddress;
    private int port;
  }

}
